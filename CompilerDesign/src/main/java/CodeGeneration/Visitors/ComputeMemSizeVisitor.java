package CodeGeneration.Visitors;

import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import CodeGeneration.SymbolTableEntry.TmpVarEntry;
import LexicalAnalyse.TokenType;
import SemanticAnalysis.ASTVisitor;
import SemanticAnalysis.SemanticAnalyser;
import SemanticAnalysis.SemanticTreeNode;
import SemanticAnalysis.SymbolTableEntry.*;
import SemanticAnalysis.Visitors.ClassPair;
import SemanticAnalysis.Visitors.VisitorHelper;

import java.util.*;

public class ComputeMemSizeVisitor extends ASTVisitor {
    private int tmpVarNum;
    private SymbolTable globalSymbolTable;

    public ComputeMemSizeVisitor() {
        this.tmpVarNum = 0;
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
            case "ifCondition":
            case "whileCondition":
            case "arithExpr":
            case "term":
            case "expr":
                visitArithExpr(semanticTreeNode);
                break;
            case "idFactor":
            case "arithFactor":
            case "signFactor":
            case "numFactor":
                visitNumFactor(semanticTreeNode);
                break;
            case "funcCall":
                visitRef(semanticTreeNode);
                break;
            default:
                visitOther(semanticTreeNode);
        }
    }

    public void visitProgram(SemanticTreeNode semanticTreeNode) {
        ASTTreeNode[] children = semanticTreeNode.getChildren();
        List<ClassPair> classPairs = new ArrayList<>();
        globalSymbolTable = semanticTreeNode.getSymbolTable();
        VisitorHelper visitorHelper = new VisitorHelper(null);
        for (ASTTreeNode child: children) {
            if (child.getName().equals("class")) {
                SemanticTreeNode classNode = (SemanticTreeNode) child;
                ClassPair classPair = visitorHelper.createClassPair(classNode);
                classPairs.add(classPair);
            }
        }
        classPairTraversal(semanticTreeNode, classPairs);
        for (ASTTreeNode child: children) {
            if (child.getName().equals("function")) {
                ((SemanticTreeNode)child).accept(this);
            }
        }
    }


    private void classPairTraversal(SemanticTreeNode programNode, List<ClassPair> classPairs) {
        HashMap<String, Integer> inDegrees = new HashMap<>();
        // parentClass, childrenClasses
        HashMap<String, List<String>> dependencies = new HashMap<>();
        HashMap<String, ClassPair> classDict = new HashMap<>();
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
                }
            }
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visitedNodes = new HashSet<>();
        for (String className: dependencies.keySet()) {
            if (inDegrees.get(className) == 0) {
                queue.add(className);
                visitedNodes.add(className);
                classDict.get(className).node.accept(this);
            }
        }

        while (!queue.isEmpty()) {
            String className = queue.remove();
            for (String childClassName: dependencies.get(className)) {
                int childInDegree = inDegrees.get(childClassName);
                childInDegree--;
                inDegrees.put(childClassName, childInDegree);
                SemanticTreeNode childTreeNode = classDict.get(childClassName).node;
                SemanticTreeNode parentTreeNode = classDict.get(className).node;
                for (String entry: parentTreeNode.getSymbolTable().getSymTable().keySet()) {
                    if (!childTreeNode.getSymbolTable().getSymTable().containsKey(entry))
                        childTreeNode.getSymbolTable().addEntry(entry, parentTreeNode.getSymbolTable().get(entry));
                }
                if (childInDegree == 0) {
                    queue.add(childClassName);
                    visitedNodes.add(childClassName);
                    classDict.get(childClassName).node.accept(this);
                }
            }
        }
    }
    public void visitFunction(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.setSymbolTable(child.getSymbolTable());
                child.accept(this);
            }
        }
        int offset = -4;
        List<String> toAddEntry = new LinkedList<>();
        List<SymbolTableEntry> toAddSymEntry = new LinkedList<>();
        for (String entry: semanticTreeNode.getSymbolTable().getSymTable().keySet()) {
            SymbolTableEntry symbolTableEntry = semanticTreeNode.getSymbolTable().get(entry);
            if (symbolTableEntry instanceof LocalVarEntry && !(((LocalVarEntry) symbolTableEntry).isBasicType())) {
                LocalVarEntry localVarEntry = (LocalVarEntry) symbolTableEntry;
                if (!(localVarEntry.getType().equals("integer")) && !localVarEntry.getType().equals("float")) {
                    localVarEntry.setSpace(((ClassEntry)globalSymbolTable.get(localVarEntry.getType())).getClassTable().getTotalSize());
                }
                localVarEntry.setName(entry + "Data");
                LocalVarEntry pointerEntry = new LocalVarEntry(entry, semanticTreeNode.getLocation(), "pointer", null);
                toAddEntry.add(entry);
                toAddSymEntry.add(pointerEntry);
                toAddEntry.add(entry + "Data");
                toAddSymEntry.add(localVarEntry);
            }
        }
        for (int i = 0; i < toAddEntry.size(); i++) {
            semanticTreeNode.getSymbolTable().addEntry(toAddEntry.get(i), toAddSymEntry.get(i));
        }
        for (String entry: semanticTreeNode.getSymbolTable().getSymTable().keySet()) {
            System.out.println(entry);
            SymbolTableEntry symbolTableEntry = semanticTreeNode.getSymbolTable().get(entry);
            symbolTableEntry.updateSpace();
            offset = symbolTableEntry.updateOffset(offset);
        }
        semanticTreeNode.getSymbolTable().setTotalSize(-1 * offset);
    }

    public void visitClass(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.setSymbolTable(child.getSymbolTable());
                child.accept(this);
            }
        }

        int offset = 0;
        for (SymbolTableEntry symbolTableEntry: semanticTreeNode.getSymbolTable().getSymTable().values()) {
            if (symbolTableEntry instanceof ClassEntry) {
                symbolTableEntry.setSpace(((ClassEntry)globalSymbolTable.get(symbolTableEntry.getName())).getClassTable().getTotalSize());
            }
            symbolTableEntry.updateSpace();
            offset = symbolTableEntry.updateOffset(offset);
        }
        semanticTreeNode.getSymbolTable().setTotalSize(-1 * offset);
    }

    public void visitArithExpr(SemanticTreeNode semanticTreeNode) {
        semanticTreeNode.getSymbolTable().addEntry("tmpVar" + tmpVarNum, new TmpVarEntry("tmpVar" + tmpVarNum, semanticTreeNode.getLocation(), semanticTreeNode.getType()));
        semanticTreeNode.getTmpSymbol().add("tmpVar" + tmpVarNum);
        tmpVarNum++;
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.setSymbolTable(child.getSymbolTable());
                child.accept(this);
            } else {
                semanticTreeNode.getSymbolTable().addEntry("tmpVar" + tmpVarNum, new TmpVarEntry("tmpVar" + tmpVarNum, semanticTreeNode.getLocation(), semanticTreeNode.getType()));
                semanticTreeNode.getTmpSymbol().add("tmpVar" + tmpVarNum);
                tmpVarNum++;
            }
        }
    }

    public void visitOther(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.setSymbolTable(child.getSymbolTable());
                child.accept(this);
            }
        }
    }

    public void visitNumFactor(SemanticTreeNode semanticTreeNode) {
        semanticTreeNode.getSymbolTable().addEntry("tmpVar" + tmpVarNum, new TmpVarEntry("tmpVar" + tmpVarNum, semanticTreeNode.getLocation(), semanticTreeNode.getType()));
        semanticTreeNode.getTmpSymbol().add("tmpVar" + tmpVarNum);
        tmpVarNum++;
        visitOther(semanticTreeNode);
    }

    public void visitRef(SemanticTreeNode semanticTreeNode) {
        if (semanticTreeNode.getType() == null) {
            visitOther(semanticTreeNode);
            return;
        }
        semanticTreeNode.getSymbolTable().addEntry("tmpVar" + tmpVarNum, new TmpVarEntry("tmpVar" + tmpVarNum, semanticTreeNode.getLocation(), semanticTreeNode.getType()));
        semanticTreeNode.getTmpSymbol().add("tmpVar" + tmpVarNum);
        tmpVarNum++;
        visitOther(semanticTreeNode);
    }

}
