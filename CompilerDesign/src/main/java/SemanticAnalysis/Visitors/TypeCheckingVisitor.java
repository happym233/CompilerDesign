package SemanticAnalysis.Visitors;

import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import LexicalAnalyse.Token;
import SemanticAnalysis.ASTVisitor;
import SemanticAnalysis.SemanticTreeNode;
import SemanticAnalysis.SymbolTableEntry.*;

import java.util.*;

public class TypeCheckingVisitor extends ASTVisitor {

    private Stack<String> scope;
    private Stack<String> classScope;

    private SymbolTable globalTable;

    private PriorityQueue<ErrorMessege> errors;

    private VisitorHelper visitorHelper;

    public TypeCheckingVisitor(SymbolTable globalTable, PriorityQueue<ErrorMessege> errors) {
        this.scope = new Stack<>();
        this.classScope = new Stack<>();
        this.globalTable = globalTable;
        this.errors = errors;
        this.visitorHelper = new VisitorHelper(errors);
    }

    public PriorityQueue<ErrorMessege> getErrors() {
        return errors;
    }

    public void setErrors(PriorityQueue<ErrorMessege> errors) {
        this.errors = errors;
    }

    /*
    private SymbolTable findScope(List<String> scopes) {
        SymbolTable curTable = globalTable;
        SymbolTableEntry symbolTableEntry = null;
        for (String scope: scopes) {
            if (curTable.containsKey(scope)) {

            } else {
                errors.add();
            }
        }
    }

    private String getType(List<String> scope, String variableName, int lineNum) {
    }
    */

    @Override
    public void visit(SemanticTreeNode semanticTreeNode) {
        switch (semanticTreeNode.getName()) {
            case "function":
                visitFunction(semanticTreeNode);
                break;
            case "class":
                visitClass(semanticTreeNode);
                break;
            case "numFactor":
                visitNumFactor(semanticTreeNode);
                break;
            case "notFactor":
                visitNotFactor(semanticTreeNode);
                break;
            case "idFactor":
            case "functionCallStatement":
                visitIdFactor(semanticTreeNode);
                break;
            case "signFactor":
                visitSignFactor(semanticTreeNode);
                break;
            case "ifCondition":
            case "whileCondition":
            case "term":
            case "arithExpr":
            case "expr":
                visitTerm(semanticTreeNode);
                break;
            case "assignStatement":
                visitAssignStatement(semanticTreeNode);
                break;
            case "returnStatement":
                visitReturnStatement(semanticTreeNode);
                break;
            case "arraySize":
                visitArraySize(semanticTreeNode);
                break;
            case "indices":
                visitIndices(semanticTreeNode);
            default:
                visitOthers(semanticTreeNode);
        }
    }

    private void visitClass(SemanticTreeNode semanticTreeNode) {
        Token idToken = ((ASTTreeLeaf) semanticTreeNode.getChildren()[0]).getToken();
        SemanticTreeNode classBody = (SemanticTreeNode) semanticTreeNode.getChildren()[2];
        scope.push(idToken.getLexeme());
        classScope.push(idToken.getLexeme());
        for (ASTTreeNode astTreeNode: classBody.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode)astTreeNode).accept(this);
            }
        }
        scope.pop();
        classScope.pop();
    }

    private void visitOthers(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode)astTreeNode).accept(this);
            }
        }
    }

    public void visitArraySize(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode)astTreeNode).accept(this);
            }
        }
        if (semanticTreeNode.getChildren().length > 0) {
            semanticTreeNode.setType(((ASTTreeLeaf)semanticTreeNode.getChildren()[0]).getToken().getType().tokenTypeStr);
            if (!semanticTreeNode.getType().equals("intnum")) {
                errors.add(new ErrorMessege(semanticTreeNode.getLocation(), ErrorLevel.ERROR, "Arraysize should be of type integer"));
            }
        }
    }

    public void visitIndices(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode)astTreeNode).accept(this);
            }
        }

        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                if (!((SemanticTreeNode)astTreeNode).getType().equals("integer")) {
                    errors.add(new ErrorMessege(((SemanticTreeNode)astTreeNode).getLocation(), ErrorLevel.ERROR, "Arraysize should be of type integer"));
                }
            }
        }
    }

    private void visitReturnStatement(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode)astTreeNode).accept(this);
            }
        }
        semanticTreeNode.setType(((SemanticTreeNode) semanticTreeNode.getChildren()[0]).getType());
    }

    private void visitFunction(SemanticTreeNode semanticTreeNode) {
        FunctionPair functionPair = visitorHelper.createFunctionPair(semanticTreeNode);
        SemanticTreeNode functionBody = (SemanticTreeNode) semanticTreeNode.getChildren()[1];
        if (functionPair.scope != null) {
            scope.add(functionPair.scope);
            classScope.add(functionPair.scope);
        }
        scope.add(functionPair.funcNameEncoded);
        for (ASTTreeNode astTreeNode: functionBody.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode)astTreeNode).accept(this);
            }
        }
        SemanticTreeNode functionHead = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
        if (functionHead.getName().equals("scopedFunctionHead") || functionHead.getName().equals("normalFunctionHead")) {
            SemanticTreeNode lastStatement = (functionBody.getChildren().length > 0) ? (SemanticTreeNode) functionBody.getChildren()[functionBody.getChildren().length - 1] : null;
            if (lastStatement == null) {
                if (functionPair.returnType != null && functionPair.returnType != "void") {
                    errors.add(new ErrorMessege(functionBody.getLocation(), ErrorLevel.ERROR, "return type " + functionPair.returnType + " given, expected void"));
                }
            } else if (functionPair.returnType.equals("void") && !lastStatement.getName().equals("returnStatement")) {

            } else if (functionPair.returnType.equals("void") && lastStatement.getName().equals("returnStatement")) {
                errors.add(new ErrorMessege(lastStatement.getLocation(), ErrorLevel.ERROR, "unexpected return type " + functionPair.returnType + " for void"));
            } else if (!lastStatement.getName().equals("returnStatement") && !functionPair.returnType.equals("void")) {
                errors.add(new ErrorMessege(lastStatement.getLocation(), ErrorLevel.ERROR, "return statement expeceted"));
            } else if (!functionPair.returnType.equals(lastStatement.getType())) {
                errors.add(new ErrorMessege(lastStatement.getLocation(), ErrorLevel.ERROR, "return type " + functionPair.returnType + " expected, given " + lastStatement.getType()));
            }
        } else {
            // System.out.println(functionHead.getName());
            SemanticTreeNode lastStatement = (functionBody.getChildren().length > 0) ? (SemanticTreeNode) functionBody.getChildren()[functionBody.getChildren().length - 1] : null;
            if (lastStatement != null && (lastStatement.getName().equals("returnStatement") && lastStatement.getType() != functionPair.scope)) {
                errors.add(new ErrorMessege(lastStatement.getLocation(), ErrorLevel.ERROR, "wrong return type " + lastStatement.getType() + " for constructor"));
            }
        }
        scope.pop();
        if (functionPair.scope != null) {
            scope.pop();
            classScope.pop();
        }
    }


    private void visitNumFactor(SemanticTreeNode semanticTreeNode) {
        String tokenType = ((ASTTreeLeaf)semanticTreeNode.getChildren()[0]).getToken().getType().tokenTypeStr;
        if (tokenType.equals("intnum")) {
            semanticTreeNode.setType("integer");
        }
        else if (tokenType.equals("floatnum")) semanticTreeNode.setType("float");
        semanticTreeNode.setDims(0);
    }

    private void visitNotFactor(SemanticTreeNode semanticTreeNode) {
        ASTTreeLeaf notSign = (ASTTreeLeaf) semanticTreeNode.getChildren()[0];
        SemanticTreeNode childFactor = (SemanticTreeNode) semanticTreeNode.getChildren()[1];
        for (ASTTreeNode astTreeNode: childFactor.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode) astTreeNode).accept(this);
            }
        }
        if (childFactor.getTypeDimsNum() != 0 || childFactor.getType() != "integer") {
            errors.add(new ErrorMessege(notSign.getToken().getLocation(), ErrorLevel.ERROR, "Type error after " + notSign.getToken().getLexeme() + ", integer expected"));
        }
        semanticTreeNode.setType("integer");
        semanticTreeNode.setDims(0);
    }

    private void visitSignFactor(SemanticTreeNode semanticTreeNode) {
        ASTTreeLeaf sign = (ASTTreeLeaf) semanticTreeNode.getChildren()[0];
        SemanticTreeNode childFactor = (SemanticTreeNode) semanticTreeNode.getChildren()[1];
        for (ASTTreeNode astTreeNode: childFactor.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode) astTreeNode).accept(this);
            }
        }
        childFactor.accept(this);
        if (childFactor.getTypeDimsNum() != 0 && !childFactor.getType().equals("integer") && !childFactor.getType().equals("float")) {
            errors.add(new ErrorMessege(sign.getToken().getLocation(), ErrorLevel.ERROR, "Type error after " + sign.getToken().getLexeme() + ", integer or float expected, given " + childFactor.getType()));
        }
        semanticTreeNode.setType(childFactor.getType());
        semanticTreeNode.setDims(0);
    }

    private VariableType checkIdnestVariable(String scope, int location, String variableName, int dims) {
        if (globalTable.containsKey(scope)) {
            ClassEntry classEntry = (ClassEntry) globalTable.get(scope);
            Queue<ClassEntry> queue = new LinkedList<>();
            queue.add(classEntry);
            while (!queue.isEmpty()) {
                ClassEntry tmpClassEntry = queue.remove();
                SymbolTable symbolTable = tmpClassEntry.getClassTable();
                if (symbolTable.containsKey(variableName)) {
                    MemberVarEntry memberVarEntry = ((MemberVarEntry)symbolTable.get(variableName));
                    VariableType variableType = new VariableType();
                    int memberVarDims = (memberVarEntry.getDims() == null)? 0: memberVarEntry.getDims().length;
                    if (dims > memberVarDims) {
                        errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Dimensions out of boundary"));
                        return null;
                    }
                    variableType.dims = memberVarDims - dims;
                    variableType.name = memberVarEntry.getType();
                    return variableType;
                } else {
                    for (String str: symbolTable.getSymTable().keySet()) {
                        if (symbolTable.get(str) instanceof ClassEntry) {
                            queue.add((ClassEntry) symbolTable.get(str));
                        }
                    }
                }
            }
            errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Variable " + variableName + " not defined under class " + scope));
        } else {
            if (scope.equals("integer") || scope.equals("float")) {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Function call on built-in type " + scope));
            } else{
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Undefined class " + scope));
            }
        }
        return null;
    }


    private String checkFuncCall(String scope, int location, String encodedFuncName) {
        if (scope == "") {
            if (globalTable.containsKey(encodedFuncName)) {
                return ((FunctionEntry) globalTable.get(encodedFuncName)).getReturnType();
            } else {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, encodedFuncName + " not defined"));
                return null;
            }
        }
        if (globalTable.containsKey(scope)) {
            ClassEntry classEntry = (ClassEntry) globalTable.get(scope);
            Queue<ClassEntry> queue = new LinkedList<>();
            queue.add(classEntry);
            while (!queue.isEmpty()) {
                ClassEntry tmpClassEntry = queue.remove();
                SymbolTable symbolTable = tmpClassEntry.getClassTable();
                if (symbolTable.containsKey(encodedFuncName)) {
                    SymbolTableEntry symbolTableEntry = symbolTable.get(encodedFuncName);
                    if (symbolTableEntry instanceof FunctionEntry) {
                        return ((FunctionEntry)symbolTable.get(encodedFuncName)).getReturnType();
                    } else if (symbolTableEntry instanceof MemberFuncEntry) {
                        return ((MemberFuncEntry)symbolTable.get(encodedFuncName)).getReturnType();
                    }
                } else {
                    for (String str: symbolTable.getSymTable().keySet()) {
                        if (symbolTable.get(str) instanceof ClassEntry) {
                            queue.add((ClassEntry) symbolTable.get(str));
                        }
                    }
                }
            }

            errors.add(new ErrorMessege(location, ErrorLevel.ERROR, encodedFuncName + " not defined under class " + scope));
        } else {
            if (scope.equals("integer") || scope.equals("float")) {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Function call on built-in type " + scope));
            } else{
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Undefined class " + scope));
            }
        }
        return null;
    }

    private VariableType checkVariable(Stack<String> scopes, int location, String variableName, int dims) {
        String tmpStr = "";
        if (variableName.equals("self")) {
            if (dims != 0) {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Invalid dimensions after self"));
            }
            if (scopes.size() == 0) {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "self keyword outside a class"));
                return null;
            }
            tmpStr = scopes.pop();
            if (scopes.size() == 0) {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "self keyword outside a class"));
                scopes.push(tmpStr);
                return null;
            }
            String returnStr = scopes.peek();
            scopes.push(tmpStr);
            VariableType variableType = new VariableType();
            variableType.name = returnStr;
            variableType.dims = 0;
            return variableType;
        }
        String str = "";
        int i = 0;
        for (String scope: scopes) {
            if (i > 0) str += ".";
            str += scope;
            i++;
        }

        SymbolTable tmpTable = globalTable;
        boolean lastFunction = true;
        for (String scope: scopes) {
            if (!tmpTable.containsKey(scope)) {
                errors.add(new ErrorMessege(location, ErrorLevel.ERROR, str + "not defined."));
                return null;
            } else {
                SymbolTableEntry symbolTableEntry = tmpTable.get(scope);
                if (symbolTableEntry instanceof FunctionEntry) {
                    FunctionEntry functionEntry = (FunctionEntry) symbolTableEntry;
                    tmpTable = functionEntry.getFunctionTable();
                    lastFunction = true;
                } else if (symbolTableEntry instanceof MemberFuncEntry) {
                    MemberFuncEntry functionEntry = (MemberFuncEntry) symbolTableEntry;
                    tmpTable = functionEntry.getFunctionTable();
                    lastFunction = true;
                } else if (symbolTableEntry instanceof MemberConEntry) {
                    MemberConEntry functionEntry = (MemberConEntry) symbolTableEntry;
                    tmpTable = functionEntry.getFunctionTable();
                    lastFunction = true;
                } else if (symbolTableEntry instanceof ClassEntry) {
                    ClassEntry classEntry = (ClassEntry) symbolTableEntry;
                    tmpTable = classEntry.getClassTable();
                    lastFunction = false;
                } else {
                    errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "type error: " + scope));
                    return null;
                }
            }
        }

        if (lastFunction) {
            SymbolTableEntry varEntry = tmpTable.get(variableName);
            if (varEntry instanceof  ParameterEntry) {
                ParameterEntry parameterEntry = (ParameterEntry) varEntry;
                int parameterDims = ((parameterEntry.getDims() == null)? 0: parameterEntry.getDims().length);
                if (dims > parameterDims) {
                    errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Dimensions out of boundary"));
                    return null;
                }
                VariableType variableType = new VariableType();
                variableType.dims = parameterDims - dims;
                variableType.name = parameterEntry.getType();
                return variableType;
            } else if (varEntry instanceof  LocalVarEntry) {
                LocalVarEntry localVarEntry = (LocalVarEntry)  varEntry;
                int localVarDims = ((localVarEntry.getDims() == null)? 0: localVarEntry.getDims().length);
                if (dims > localVarDims) {
                    errors.add(new ErrorMessege(location, ErrorLevel.ERROR, "Dimensions out of boundary"));
                    return null;
                }
                VariableType variableType = new VariableType();
                variableType.dims = localVarDims - dims;
                variableType.name = localVarEntry.getType();
                return variableType;
            } else {
                return null;
            }
        } else {
            return checkIdnestVariable(scopes.peek(), location, variableName, dims);
        }
    }


    private VariableType handleIdIndice(SemanticTreeNode semanticTreeNode, String scope) {
        Token idToken = ((ASTTreeLeaf) semanticTreeNode.getChildren()[0]).getToken();
        VariableType variableType = new VariableType();
        variableType.name = idToken.getLexeme();
        variableType.location = idToken.getLocation();
        if (semanticTreeNode.getChildren().length > 1) {
            SemanticTreeNode indicesNode = (SemanticTreeNode) semanticTreeNode.getChildren()[1];
            variableType.dims = indicesNode.getChildren().length;
        }
        return checkIdnestVariable(scope, variableType.location, variableType.name, variableType.dims);
    }


    private VariableType handleIdIndice(SemanticTreeNode semanticTreeNode, Stack<String> scope) {
        Token idToken = ((ASTTreeLeaf) semanticTreeNode.getChildren()[0]).getToken();
        VariableType variableType = new VariableType();
        variableType.name = idToken.getLexeme();
        variableType.location = idToken.getLocation();
        if (semanticTreeNode.getChildren().length > 1) {
            SemanticTreeNode indicesNode = (SemanticTreeNode) semanticTreeNode.getChildren()[1];
            variableType.dims = indicesNode.getChildren().length;
        }
        VariableType res = checkVariable(scope, variableType.location, variableType.name, variableType.dims);
        if (res == null) {
            res = checkVariable(classScope, variableType.location, variableType.name, variableType.dims);
        }
        return res;
    }


    private VariableType handleFunctionCall(SemanticTreeNode semanticTreeNode, String scope) {
        Token functionIdToken = ((ASTTreeLeaf) semanticTreeNode.getChildren()[0]).getToken();
        String encoded = "";
        for (int i = 1; i < semanticTreeNode.getChildren().length; i++) {
            SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
            encoded += child.getType();
            for (int j = 0; j < child.getDims(); j++) {
                encoded += "[]";
            }
            if (i < semanticTreeNode.getChildren().length - 1) {
                encoded += ", ";
            }
        }
        String returnType = checkFuncCall(scope, functionIdToken.getLocation(), functionIdToken.getLexeme() + "(" + encoded + ")");
        VariableType variableType = new VariableType();
        variableType.name = returnType;
        variableType.dims = 0;
        variableType.location = functionIdToken.getLocation();
        return variableType;
    }

    private void visitIdFactor(SemanticTreeNode semanticTreeNode) {
        String subscope = "";
        SemanticTreeNode firstChildIdnest = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
        VariableType variableType;
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode) astTreeNode).accept(this);
            }
        }
        int l = semanticTreeNode.getChildren().length;
        if (firstChildIdnest.getName().equals("idIndice")) {
            variableType = handleIdIndice(firstChildIdnest, scope);
            if (variableType == null) return;
            firstChildIdnest.setType(variableType.name);
        } else {
            variableType = handleFunctionCall(firstChildIdnest, (classScope.size() == 0)? "": classScope.peek());
            if (variableType == null) return;
            firstChildIdnest.setType(variableType.name);
        }
        if (variableType == null) return;
        if (l == 1) {
            semanticTreeNode.setType(variableType.name);
            semanticTreeNode.setDims(variableType.dims);
        } else {
            if (variableType.dims != 0) {
                errors.add(new ErrorMessege(variableType.location, ErrorLevel.ERROR, "Type error before . (1)"));
                return;
            }
        }
        subscope = variableType.name;
        for (int i = 1; i < l; i++) {
            SemanticTreeNode childIdnest = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
            if (childIdnest.getName().equals("idIndice")) {
                variableType = handleIdIndice(childIdnest, subscope);
                if (variableType == null) return;
                childIdnest.setType(variableType.name);
                subscope = variableType.name;
            } else { //funcCall
                variableType = handleFunctionCall(childIdnest, subscope);
                if (variableType == null) return;
                subscope = variableType.name;
            }

            if (i < l - 1 && variableType.dims != 0) {
                errors.add(new ErrorMessege(((ASTTreeLeaf)childIdnest.getChildren()[0]).getToken().getLocation(), ErrorLevel.ERROR,
                        "Type error before . after " + ((ASTTreeLeaf)childIdnest.getChildren()[0]).getToken().getLexeme()));
                return;
            }
        }
        semanticTreeNode.setType(variableType.name);
        semanticTreeNode.setDims(variableType.dims);
    }

    public void visitTerm(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode) astTreeNode).accept(this);
            }
        }
        String type = null;
        if (semanticTreeNode.getChildren().length == 1) {
            SemanticTreeNode childNode = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
            semanticTreeNode.setType(childNode.getType());
            semanticTreeNode.setDims(childNode.getDims());
            return;
        }
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode factorNode = (SemanticTreeNode) astTreeNode;
                if (factorNode.getType() == null) {
                    semanticTreeNode.setType(null);
                    return;
                }
                if (type == null) {
                    type = factorNode.getType();
                    if (!type.equals("integer") && !type.equals("float")) {
                        errors.add(new ErrorMessege(semanticTreeNode.getLocation(), ErrorLevel.ERROR, "Type error, integer or float expected, given " + factorNode.getType()));
                        semanticTreeNode.setType(null);
                        return;
                    }
                } else if (!factorNode.getType().equals(type)) {
                    errors.add(new ErrorMessege(semanticTreeNode.getLocation(), ErrorLevel.ERROR, "Type error, "
                            + factorNode.getType() + " with " + type));
                    semanticTreeNode.setType(null);
                    return;
                }
            }
        }
        if (semanticTreeNode.getName().equals("expr") && semanticTreeNode.getChildren().length > 1)
            type = "integer";
        semanticTreeNode.setType(type);
    }

    private void visitAssignStatement(SemanticTreeNode semanticTreeNode) {
        String subscope = "";
        SemanticTreeNode firstChildIdnest = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
        VariableType variableType;
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode) astTreeNode).accept(this);
            }
        }
        int l = semanticTreeNode.getChildren().length;
        if (firstChildIdnest.getName().equals("idIndice")) {
            variableType = handleIdIndice(firstChildIdnest, scope);
            if (variableType == null) return;
            firstChildIdnest.setType(variableType.name);
        } else {
            variableType = handleFunctionCall(semanticTreeNode, (classScope.size() == 0)? "": classScope.peek());
            if (variableType == null) return;
            firstChildIdnest.setType(variableType.name);
        }
        if (variableType == null) return;
        if (l == 2) {
            semanticTreeNode.setType(variableType.name);
            semanticTreeNode.setDims(variableType.dims);
        } else {
            if (variableType.dims != 0) {
                errors.add(new ErrorMessege(variableType.location, ErrorLevel.ERROR, "Type error before . (1)"));
                return;
            }
        }
        subscope = variableType.name;
        for (int i = 1; i < l - 1; i++) {
            SemanticTreeNode childIdnest = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
            if (childIdnest.getName().equals("idIndice")) {
                variableType = handleIdIndice(childIdnest, subscope);
                if (variableType == null) return;
                childIdnest.setType(variableType.name);
                subscope = variableType.name;
            } else { //funcCall
                variableType = handleFunctionCall(childIdnest, subscope);
                if (variableType == null) return;
                subscope = variableType.name;
            }

            if (variableType.dims != 0) {
                errors.add(new ErrorMessege(((ASTTreeLeaf)childIdnest.getChildren()[0]).getToken().getLocation(), ErrorLevel.ERROR,
                        "Type error before . after " + ((ASTTreeLeaf)childIdnest.getChildren()[0]).getToken().getLexeme()));
                return;
            }
        }
        if (variableType.name == null || ((SemanticTreeNode)semanticTreeNode.getChildren()[l - 1]).getType() == null) return;
        if (variableType.dims != 0) {
            errors.add(new ErrorMessege(semanticTreeNode.getLocation(), ErrorLevel.ERROR,
                        "assign value to array"));
        }
        else if (!variableType.name.equals(((SemanticTreeNode)semanticTreeNode.getChildren()[l - 1]).getType())) {
            errors.add(new ErrorMessege(semanticTreeNode.getLocation(), ErrorLevel.ERROR,
                        "Type error, assign type " + ((SemanticTreeNode)semanticTreeNode.getChildren()[l - 1]).getType() + " expected " + variableType.name));
        }
    }

}
