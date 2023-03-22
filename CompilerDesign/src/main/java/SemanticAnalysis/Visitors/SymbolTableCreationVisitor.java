package SemanticAnalysis.Visitors;

import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import SemanticAnalysis.ASTVisitor;
import SemanticAnalysis.SemanticTreeNode;
import SemanticAnalysis.SymbolTableEntry.*;

import java.util.*;

public class SymbolTableCreationVisitor extends ASTVisitor {

    private PriorityQueue<ErrorMessege> errors;
    private VisitorHelper visitorHelper;

    private SymbolTable globalTable;

    public SymbolTableCreationVisitor(PriorityQueue<ErrorMessege> errors) {
        this.errors = errors;
        this.visitorHelper = new VisitorHelper(errors);
    }

    public PriorityQueue<ErrorMessege> getErrors() {
        return errors;
    }

    public void setErrors(PriorityQueue<ErrorMessege> errors) {
        this.errors = errors;
    }

    @Override
    public void visit(SemanticTreeNode semanticTreeNode) {
        switch (semanticTreeNode.getName()) {
            case "program":
                visitProgram(semanticTreeNode);
                break;
            case "function":
                visitFunction(semanticTreeNode);
                break;
            case "class":
                visitClass(semanticTreeNode);
                break;
        }
    }


    // topological sort
    private void classPairTraversal(SemanticTreeNode programNode, List<ClassPair> classPairs) {
        HashMap<String, Integer> inDegrees = new HashMap<>();
        // parentClass, childrenClasses
        HashMap<String, List<String>> dependencies = new HashMap<>();
        HashMap<String, ClassPair> classDict = new HashMap<>();

        HashSet<String> hashSet = new HashSet<>();
        for (ClassPair classPair: classPairs) {
            if (hashSet.contains(classPair.name)) {
                errors.add(new ErrorMessege(classPair.classPos, ErrorLevel.ERROR, "multiple declared class " + classPair.name));
            } else {
                hashSet.add(classPair.name);
            }
        }

        for (ClassPair classPair: classPairs) {
            inDegrees.put(classPair.name, 0);
            dependencies.put(classPair.name, new ArrayList<>());
            classDict.put(classPair.name, classPair);
        }

        for (ClassPair classPair: classPairs) {
            for (int i = 0; i < classPair.extend_classes.length; i++) {
                if (inDegrees.containsKey(classPair.extend_classes[i])) {
                    inDegrees.put(classPair.name,
                            inDegrees.get(classPair.name) + 1);
                    dependencies.get(classPair.extend_classes[i]).add(classPair.name);
                } else {
                    errors.add(new ErrorMessege(classPair.extend_classes_tokens[i].getLocation(), ErrorLevel.ERROR, "" +
                            "extended class \"" + classPair.extend_classes_tokens[i].getLexeme() + "\" do not exist."));
                }
            }
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visitedNodes = new HashSet<>();
        for (String className: dependencies.keySet()) {
            if (inDegrees.get(className) == 0) {
                queue.add(className);
                visitedNodes.add(className);
                SymbolTable classTable = new SymbolTable();
                programNode.getSymbolTable().addEntry(className, new ClassEntry(className, classDict.get(className).node.getLocation(), classTable));
                classDict.get(className).node.setSymbolTable(classTable);
                classDict.get(className).node.accept(this);
            }
        }

        while (!queue.isEmpty()) {
            String className = queue.remove();
            ClassPair classPair = classDict.get(className);
            for (String childClassName: dependencies.get(className)) {
                int childInDegree = inDegrees.get(childClassName);
                ClassPair childClassPair = classDict.get(childClassName);
                SymbolTable childSymbolTable = null;
                if (childClassPair.node.getSymbolTable() == null) {
                    childSymbolTable = new SymbolTable();
                    childClassPair.node.setSymbolTable(childSymbolTable);
                } else {
                    childSymbolTable = childClassPair.node.getSymbolTable();
                }
                childSymbolTable.addEntry(className, new ClassEntry(className, classDict.get(className).node.getLocation(), classPair.node.getSymbolTable()));
                childInDegree--;
                inDegrees.put(childClassName, childInDegree);
                if (childInDegree == 0) {
                    queue.add(childClassName);
                    visitedNodes.add(childClassName);
                    programNode.getSymbolTable().addEntry(childClassName, new ClassEntry(childClassName, classDict.get(childClassName).node.getLocation(), childSymbolTable));
                    classDict.get(childClassName).node.accept(this);
                }
            }
        }
        if (visitedNodes.size() != classDict.keySet().size()) {
            String message = "Circular class dependency detected between ";
            int i = 0;
            String exampleClass = "";
            for (String className: classDict.keySet()) {
                if (!visitedNodes.contains(className)) {
                    if (i > 0) message += ", ";
                    exampleClass = className;
                    message += className;
                    i++;
                }
            }
            errors.add(new ErrorMessege(classDict.get(exampleClass).classPos, ErrorLevel.ERROR, message));
        }
    }


    private void visitProgram(SemanticTreeNode semanticTreeNode) {
        ASTTreeNode[] children = semanticTreeNode.getChildren();
        semanticTreeNode.setSymbolTable(new SymbolTable());
        globalTable = semanticTreeNode.getSymbolTable();
        List<FunctionPair> functionPairs = new ArrayList<>();
        List<ClassPair> classPairs = new ArrayList<>();
        for (ASTTreeNode child: children) {
            if (child.getName().equals("function")) {
                SemanticTreeNode functionNode = (SemanticTreeNode) child;
                FunctionPair functionPair = visitorHelper.createFunctionPair(functionNode);
                functionPairs.add(functionPair);
            } else { // classNode
                SemanticTreeNode classNode = (SemanticTreeNode) child;
                ClassPair classPair = visitorHelper.createClassPair(classNode);
                classPairs.add(classPair);
            }
        }

        classPairTraversal(semanticTreeNode, classPairs);

        for (FunctionPair functionPair: functionPairs) {
            if (functionPair.scope == null) {
                SymbolTable funcSymbolTable = new SymbolTable();
                if (funcSymbolTable.containsKey(functionPair.funcNameEncoded)) {
                    errors.add(new ErrorMessege(functionPair.funcPos, ErrorLevel.ERROR, "function " + functionPair.funcNameEncoded + " has been declared."));
                    continue;
                }
                SymbolTable symbolTable = semanticTreeNode.getSymbolTable();
                if (symbolTable.containsKey(functionPair.funcNameEncoded)) {
                    errors.add(new ErrorMessege(functionPair.funcPos, ErrorLevel.ERROR, "function " + functionPair.funcNameEncoded + " has been declared."));
                } else if (symbolTable.containsKey(functionPair.name)) {
                    errors.add(new ErrorMessege(functionPair.funcPos, ErrorLevel.WARNING, "Overloading function " + functionPair.funcNameEncoded));
                }
                semanticTreeNode.getSymbolTable().addEntry(functionPair.name, new FunctionEntry(functionPair.name, functionPair.funcPos, functionPair.returnType,funcSymbolTable));
                semanticTreeNode.getSymbolTable().addEntry(functionPair.funcNameEncoded, new FunctionEntry(functionPair.name, functionPair.funcPos, functionPair.returnType,funcSymbolTable));
                functionPair.node.setSymbolTable(funcSymbolTable);
                functionPair.node.accept(this);
            } else {
                // scoped function
                if (!semanticTreeNode.getSymbolTable().containsKey(functionPair.scope)) {
                    errors.add(new ErrorMessege(functionPair.funcPos, ErrorLevel.ERROR, "class name " + functionPair.scope + " is not declared."));
                } else {
                    SymbolTable classTable = ((ClassEntry) semanticTreeNode.getSymbolTable().get(functionPair.scope)).getClassTable();
                    if (!classTable.containsKey(functionPair.funcNameEncoded)) {
                        errors.add(new ErrorMessege(functionPair.funcPos, ErrorLevel.ERROR, "function not declared in class " + functionPair.scope + "."));
                    } else {
                        SymbolTableEntry etr = classTable.get(functionPair.funcNameEncoded);
                        if (etr instanceof MemberFuncEntry) {
                            functionPair.node.setSymbolTable(((MemberFuncEntry) etr).getFunctionTable());
                        } else {
                            functionPair.node.setSymbolTable(((MemberConEntry) etr).getFunctionTable());
                        }
                        functionPair.node.accept(this);
                    }
                }
            }
        }
    }

    private void visitFunction(SemanticTreeNode semanticTreeNode) {
        SemanticTreeNode functionHead = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
        SemanticTreeNode funcBody = (SemanticTreeNode) semanticTreeNode.getChildren()[1];
        SymbolTable funcSymbolTable = semanticTreeNode.getSymbolTable();
        SemanticTreeNode fParamsNodes = null;
        if (functionHead.getName().equals("scopedFunctionHead")) {
            fParamsNodes = (SemanticTreeNode) functionHead.getChildren()[2];
        } else if (functionHead.getName().equals("constructorHead")) {
            fParamsNodes = (SemanticTreeNode) functionHead.getChildren()[1];
        } else if (functionHead.getName().equals("normalFunctionHead")) {
            fParamsNodes = (SemanticTreeNode) functionHead.getChildren()[1];
        } else {
            return;
        }
        for (ASTTreeNode node: fParamsNodes.getChildren()) {
            SemanticTreeNode fParamNode = (SemanticTreeNode) node;
            String paramName = ((ASTTreeLeaf) fParamNode.getChildren()[0]).getToken().getLexeme();
            String paramType = ((ASTTreeLeaf) fParamNode.getChildren()[1]).getToken().getLexeme();
            SemanticTreeNode dimsNodes = (SemanticTreeNode) fParamNode.getChildren()[2];
            Integer[] dims;
            if (dimsNodes.getChildren().length == 0) {
                dims = null;
            } else {
                dims = new Integer[dimsNodes.getChildren().length];
                for (int i = 0; i < dimsNodes.getChildren().length; i++) {
                    SemanticTreeNode dimNode = (SemanticTreeNode) dimsNodes.getChildren()[i];
                    if (dimNode.getChildren().length == 0) {
                        dims[i] = null;
                    } else {
                        try {
                            dims[i] = Integer.valueOf(((ASTTreeLeaf) dimNode.getChildren()[0]).getToken().getLexeme());
                        } catch (Exception e) {
                            errors.add(new ErrorMessege(((ASTTreeLeaf) dimNode.getChildren()[0]).getToken().getLocation(), ErrorLevel.ERROR,
                                    "array size should be type integer"));
                        }
                    }
                }
            }
            ParameterEntry parameterEntry = new ParameterEntry(paramName, fParamNode.getLocation(), paramType, dims);
            funcSymbolTable.addEntry(paramName, parameterEntry);
        }
        for (ASTTreeNode ASTStatement: funcBody.getChildren()) {
            SemanticTreeNode statementNode = (SemanticTreeNode) ASTStatement;
            if (statementNode.getName().equals("localVarDecl")) {
                if (statementNode.getChildren()[2].getName().equals("arraySizes")) {
                    SemanticTreeNode dimsNodes = (SemanticTreeNode) statementNode.getChildren()[2];
                    Integer[] dims = visitorHelper.parseDims(dimsNodes);
                    String varName = ((ASTTreeLeaf) statementNode.getChildren()[0]).getToken().getLexeme();
                    String type = ((ASTTreeLeaf) statementNode.getChildren()[1]).getToken().getLexeme();
                    if (!type.equals("integer") && !type.equals("float") && !globalTable.containsKey(type)) {
                        errors.add(new ErrorMessege(((ASTTreeLeaf) statementNode.getChildren()[0]).getToken().getLocation(), ErrorLevel.ERROR,
                                "invalid type " + type));
                    }
                    if (funcSymbolTable.containsKey(varName)) {
                        errors.add(new ErrorMessege(((ASTTreeLeaf) statementNode.getChildren()[0]).getToken().getLocation(), ErrorLevel.ERROR,
                                "Shallow variable name " + varName));
                    }
                    funcSymbolTable.addEntry(varName, new LocalVarEntry(varName, statementNode.getLocation(),type, dims));
                } else {
                    String varName = ((ASTTreeLeaf) statementNode.getChildren()[0]).getToken().getLexeme();
                    String type = ((ASTTreeLeaf) statementNode.getChildren()[1]).getToken().getLexeme();
                    funcSymbolTable.addEntry(varName, new LocalVarEntry(varName, statementNode.getLocation(),type, null));
                }
                // System.out.println(funcSymbolTable);
            }
        }
    }

    private void visitClass(SemanticTreeNode classNode) {
        // System.out.println(classNode.getName());
        SemanticTreeNode classBody = (SemanticTreeNode) classNode.getChildren()[2];
        for (ASTTreeNode ASTMemberDecl: classBody.getChildren()) {
            SemanticTreeNode memberDecl = (SemanticTreeNode) ASTMemberDecl;
            if (memberDecl.getName().equals("memberVarDecl")) {
                SemanticTreeNode visibilityNode = (SemanticTreeNode) memberDecl.getChildren()[0];
                ASTTreeLeaf idLeaf = (ASTTreeLeaf) memberDecl.getChildren()[1];
                ASTTreeLeaf typeLeaf = (ASTTreeLeaf) memberDecl.getChildren()[2];
                SemanticTreeNode dimsNodes = (SemanticTreeNode) memberDecl.getChildren()[3];
                Visibility visibility;
                if (visibilityNode.getChildren().length == 0) {
                    visibility = Visibility.PUBLIC;
                } else {
                    visibility = Visibility.createVisibility(((ASTTreeLeaf) visibilityNode.getChildren()[0]).getToken().getLexeme());
                }
                String varName = idLeaf.getToken().getLexeme();
                String typeName = typeLeaf.getToken().getLexeme();
                Integer[] dims = visitorHelper.parseDims(dimsNodes);
                MemberVarEntry memberVarEntry = new MemberVarEntry(visibility, memberDecl.getLocation(),varName, typeName, dims);
                if (classNode.getSymbolTable().containsKey(varName)) {
                    errors.add(new ErrorMessege(visibilityNode.getLocation(), ErrorLevel.ERROR, "variable " + varName + " multi declared in class"));
                }
                else classNode.getSymbolTable().addEntry(varName, memberVarEntry);
            } else if (memberDecl.getName().equals("memberConstructorDecl")) {
                String memConName = "constructor";
                SemanticTreeNode visibilityNode = (SemanticTreeNode) memberDecl.getChildren()[0];
                SemanticTreeNode fParamsNodes = (SemanticTreeNode) memberDecl.getChildren()[1];
                Visibility visibility;
                if (visibilityNode.getChildren().length == 0) {
                    visibility = Visibility.PUBLIC;
                } else {
                    visibility = Visibility.createVisibility(((ASTTreeLeaf) visibilityNode.getChildren()[0]).getToken().getLexeme());
                }
                SymbolTable memberConstructorTable = new SymbolTable();
                classNode.getSymbolTable().addEntry(memConName + visitorHelper.encodeParameters(fParamsNodes), new MemberConEntry(memConName, memberDecl.getLocation(), visibility, memberConstructorTable));
            } else if (memberDecl.getName().equals("memberFuncDecl")) {
                SemanticTreeNode visibilityNode = (SemanticTreeNode) memberDecl.getChildren()[0];
                ASTTreeLeaf idNode  = (ASTTreeLeaf) memberDecl.getChildren()[1];
                SemanticTreeNode fParamsNodes = (SemanticTreeNode) memberDecl.getChildren()[2];
                SemanticTreeNode returnTypeNode = (SemanticTreeNode) memberDecl.getChildren()[3];

                Visibility visibility;
                if (visibilityNode.getChildren().length == 0) {
                    visibility = Visibility.PUBLIC;
                } else {
                    visibility = Visibility.createVisibility(((ASTTreeLeaf) visibilityNode.getChildren()[0]).getToken().getLexeme());
                }

                String returnType;
                ASTTreeNode[] returnTypeNodes = returnTypeNode.getChildren();
                if (returnTypeNodes.length == 0) {
                    returnType = "void";
                } else {
                    returnType = ((ASTTreeLeaf) returnTypeNodes[0]).getToken().getLexeme();
                }
                SymbolTable memberFunctionTable = new SymbolTable();
                // System.out.println(idNode.getToken().getLexeme());
                // System.out.println(visitorHelper.encodeParameters(fParamsNodes));
                // System.out.println(fParamsNodes.getChildren().length);

                String funcName = idNode.getToken().getLexeme();
                String funcNameDecoded = idNode.getToken().getLexeme() + visitorHelper.encodeParameters(fParamsNodes);
                if (classNode.getSymbolTable().getSymTable().containsKey(funcName)) {
                    errors.add(new ErrorMessege(idNode.getToken().getLocation(), ErrorLevel.WARNING, "Overloading member function " + funcName));
                }
                else {
                    classNode.getSymbolTable().addEntry(idNode.getToken().getLexeme(), new MemberFuncEntry(idNode.getToken().getLexeme(), idNode.getToken().getLocation(),
                            visibility,
                            returnType,
                            memberFunctionTable));
                }
                if (classNode.getSymbolTable().getSymTable().containsKey(funcNameDecoded)) {
                    errors.add(new ErrorMessege(idNode.getToken().getLocation(), ErrorLevel.ERROR, "Redefining member function " + funcName));
                } else {
                classNode.getSymbolTable().addEntry(idNode.getToken().getLexeme() + visitorHelper.encodeParameters(fParamsNodes), new MemberFuncEntry(idNode.getToken().getLexeme(),
                        idNode.getToken().getLocation(),
                        visibility,
                        returnType,
                        memberFunctionTable));
                }
            }
        }

        /*
        for (String name: classNode.getSymbolTable().getSymTable().keySet()) {
            System.out.println(name);
            System.out.println(classNode.getSymbolTable().getSymTable().get(name));
        }
         */
    }

}
