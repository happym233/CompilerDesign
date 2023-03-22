package SemanticAnalysis;

import ASTGeneration.ASTGenerator;
import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import ASTGeneration.nodes.ASTTreeParent;
import SemanticAnalysis.SymbolTableEntry.*;
import SemanticAnalysis.Visitors.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SemanticAnalyser {
    private SemanticTreeNode root;

    private String symbolTableStr;

    private PriorityQueue<ErrorMessege> errorMesseges;

    public SemanticAnalyser(String fileName) throws IOException {
        ASTGenerator astGenerator = new ASTGenerator(fileName);
        ASTTreeParent astRoot = astGenerator.getRoot();
        root = copyASTTreeToSemanticTree(astRoot);
        errorMesseges = new PriorityQueue<>(Comparator.comparingInt(ErrorMessege::getLineNum));
    }

    private SemanticTreeNode copyASTTreeToSemanticTree(ASTTreeParent astRoot) {
        SemanticTreeNode root = new SemanticTreeNode(astRoot);
        ASTTreeNode[] children = root.getChildren();
        Queue<SemanticTreeNode> queue = new LinkedList<>();
        Queue<ASTTreeParent> astNodeQueue = new LinkedList<>();
        for (int i = 0; i < children.length; i++) {
            ASTTreeNode child = astRoot.getChildren()[i];
            if (child instanceof ASTTreeLeaf) {
                children[i] = child;
            } else {
                SemanticTreeNode newChild = new SemanticTreeNode(((ASTTreeParent) child));
                children[i] = newChild;
                queue.add(newChild);
                astNodeQueue.add((ASTTreeParent) child);
            }
        }
        while (!queue.isEmpty()) {
            SemanticTreeNode node = queue.remove();
            ASTTreeParent astNode = astNodeQueue.remove();
            ASTTreeNode[] nodeChildren = node.getChildren();
            for (int i = 0; i < nodeChildren.length; i++) {
                ASTTreeNode child = astNode.getChildren()[i];
                if (child instanceof ASTTreeLeaf) {
                    nodeChildren[i] = child;
                } else {
                    SemanticTreeNode newChild = new SemanticTreeNode(((ASTTreeParent) child));
                    nodeChildren[i] = newChild;
                    queue.add(newChild);
                    astNodeQueue.add((ASTTreeParent) child);
                }
            }
        }
        return root;
    }

    public void setRoot(SemanticTreeNode root) {
        this.root = root;
    }

    public PriorityQueue<ErrorMessege> getErrorMesseges() {
        return errorMesseges;
    }

    public void setErrorMesseges(PriorityQueue<ErrorMessege> errorMesseges) {
        this.errorMesseges = errorMesseges;
    }

    public String getSymbolTableStr() {
        return symbolTableStr;
    }

    public void setSymbolTableStr(String symbolTableStr) {
        this.symbolTableStr = symbolTableStr;
    }

    public SemanticTreeNode getRoot() {
        return root;
    }

    public void run() {

        root.accept(new LocationAssignVisitor());
        SymbolTableCreationVisitor symbolTableCreationVisitor = new SymbolTableCreationVisitor(errorMesseges);
        root.accept(symbolTableCreationVisitor);
        checkEmptyEntry(root.getSymbolTable());
        checkOverriding(root.getSymbolTable(), new HashMap<>());
        checkShallow(root.getSymbolTable(), new HashMap<>());

        StringBuilder sb = new StringBuilder();
        symbolTableToStr(sb, root.getSymbolTable(), "global", 0);
        this.symbolTableStr = sb.toString();


        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(root.getSymbolTable(), errorMesseges);
        root.accept(typeCheckingVisitor);
        this.errorMesseges = typeCheckingVisitor.getErrors();
    }

    public String addBoundary(String str) {
        return String.format("%-79s", str) + "|"  + "\n";
    }

    private void checkOverriding(SymbolTable symbolTable, HashMap<String, MemberFuncEntry> hashMap) {
        for (String str: symbolTable.getSymTable().keySet()) {
            SymbolTableEntry symbolTableEntry = symbolTable.get(str);
            if (symbolTableEntry instanceof MemberFuncEntry) {
                MemberFuncEntry memberFuncEntry = (MemberFuncEntry)  symbolTableEntry;
                if (hashMap.containsKey(str)) {
                    errorMesseges.add(new ErrorMessege(memberFuncEntry.getLocation(), ErrorLevel.WARNING, "Overriding member function " + hashMap.get(str).getName()));
                } else {
                    hashMap.put(str, memberFuncEntry);
                }
            } else if (symbolTableEntry instanceof ClassEntry) {
                checkOverriding(((ClassEntry)symbolTableEntry).getClassTable(), hashMap);
                if (symbolTable == getRoot().getSymbolTable()) {
                    hashMap = new HashMap<>();
                }
            }
        }
    }


    private void checkShallow(SymbolTable symbolTable, HashMap<String, MemberVarEntry> hashMap) {
        for (String str: symbolTable.getSymTable().keySet()) {
            SymbolTableEntry symbolTableEntry = symbolTable.get(str);
            if (symbolTableEntry instanceof MemberVarEntry) {
                MemberVarEntry memberVarEntry = (MemberVarEntry)  symbolTableEntry;
                if (hashMap.containsKey(str)) {
                    errorMesseges.add(new ErrorMessege(hashMap.get(str).getLocation(), ErrorLevel.WARNING, "shallow variable " + hashMap.get(str).getName()));
                } else {
                    hashMap.put(str, memberVarEntry);
                }
            } else if (symbolTableEntry instanceof ClassEntry) {
                checkShallow(((ClassEntry)symbolTableEntry).getClassTable(), hashMap);
                if (symbolTable == getRoot().getSymbolTable()) {
                    hashMap = new HashMap<>();
                }
            }
        }
    }

    private void checkEmptyEntry(SymbolTable symbolTable) {
        for (String str: symbolTable.getSymTable().keySet()) {
            SymbolTableEntry symbolTableEntry = symbolTable.get(str);
            if (symbolTableEntry instanceof MemberFuncEntry) {
                MemberFuncEntry memberFuncEntry = (MemberFuncEntry)  symbolTableEntry;
                if (memberFuncEntry.getFunctionTable().getSymTable().size() == 0) {
                    errorMesseges.add(new ErrorMessege(memberFuncEntry.getLocation(), ErrorLevel.ERROR, "Member function " + memberFuncEntry.getName() + " declared but not implemented"));
                }
            } else if (symbolTableEntry instanceof MemberConEntry) {
                MemberConEntry memberConEntry = (MemberConEntry)  symbolTableEntry;
                if (memberConEntry.getFunctionTable().getSymTable().size() == 0) {
                    errorMesseges.add(new ErrorMessege(memberConEntry.getLocation(), ErrorLevel.ERROR, "Constructor " + memberConEntry.getName() + " declared but not implemented"));
                }
            } else if (symbolTableEntry instanceof ClassEntry) {
              checkEmptyEntry(((ClassEntry)symbolTableEntry).getClassTable());
            }
        }
    }

    public void symbolTableToStr(StringBuilder sb, SymbolTable symbolTable, String tableName, int level) {

        String offset = "|";
        for (int i = 0; i < level; i++) {
            offset += "    |";
        }

        String sepLine = "";
        sepLine += offset;
        sepLine = sepLine.substring(0, sepLine.length() - 1);
        sepLine += "=";

        int sepLength = sepLine.length();
        for (int i = 0; i < 80 - sepLength; i++) {
            sepLine += "=";
        }
        sepLine += "\n";
        sb.append(sepLine);
        sb.append(addBoundary(offset + " table: " + tableName));
        sb.append(sepLine);
        for (String entry: symbolTable.getSymTable().keySet()) {
            SymbolTableEntry symbolTableEntry = symbolTable.get(entry);
            if (symbolTableEntry instanceof ClassEntry) {
                ClassEntry classEntry = (ClassEntry) symbolTableEntry;
                if (level == 0) {
                    sb.append(addBoundary(offset + " class          | " + classEntry.getName()));
                    symbolTableToStr(sb, classEntry.getClassTable(), classEntry.getName(), level + 1);
                } else {
                    sb.append(addBoundary(offset + " inherit        |" + classEntry.getName()));
                }
            } else if (symbolTableEntry instanceof FunctionEntry) {
                if (!entry.contains("(")) continue;
                FunctionEntry functionEntry = (FunctionEntry) symbolTableEntry;
                sb.append(addBoundary(offset + " function       | " + String.format("%-20s", functionEntry.getName()) + " | " +
                        String.format("%-23s", functionEntry.toOuputStr())));
                symbolTableToStr(sb, functionEntry.getFunctionTable(), functionEntry.getName(), level + 1);
            } else if (symbolTableEntry instanceof LocalVarEntry) {
                LocalVarEntry localVarEntry = (LocalVarEntry) symbolTableEntry;
                String dimsStr = "";
                if (localVarEntry.getDims() != null) {
                    for (Integer i : localVarEntry.getDims()) {
                        if (i == null) {
                            dimsStr += "[]";
                        } else {
                            dimsStr += "[" + i + "]";
                        }
                    }
                }
                sb.append(addBoundary(offset + " localVar       | " + String.format("%-20s", localVarEntry.getName()) + " | "
                        + String.format("%-27s", localVarEntry.getType() + dimsStr)));
            } else if (symbolTableEntry instanceof MemberConEntry) {
                if (!entry.contains("(")) continue;
                MemberConEntry memberConEntry = (MemberConEntry) symbolTableEntry;
                sb.append(addBoundary(offset + " constructor    | " + String.format("%-43s", memberConEntry.toOuputStr()) + " | " + memberConEntry.getVisibility().getVisStr()));
                symbolTableToStr(sb, memberConEntry.getFunctionTable(), "constructor" + memberConEntry.toOuputStr(), level + 1);
            } else if (symbolTableEntry instanceof MemberFuncEntry) {
                if (!entry.contains("(")) continue;
                MemberFuncEntry memberFuncEntry = (MemberFuncEntry) symbolTableEntry;
                sb.append(addBoundary(offset + " memberFunction | " + String.format("%-20s", memberFuncEntry.getName()) + " | " +
                        String.format("%-20s", memberFuncEntry.toOuputStr()) + " | " + memberFuncEntry.getVisibility().getVisStr()));
                symbolTableToStr(sb, memberFuncEntry.getFunctionTable(), memberFuncEntry.getName() + memberFuncEntry.toOuputStr(), level + 1);
            } else if (symbolTableEntry instanceof  MemberVarEntry) {
                MemberVarEntry memberVarEntry = (MemberVarEntry) symbolTableEntry;
                String dimsStr = "";
                if (memberVarEntry.getDims() != null) {
                    for (Integer i : memberVarEntry.getDims()) {
                        if (i == null) {
                            dimsStr += "[]";
                        } else {
                            dimsStr += "[" + i + "]";
                        }
                    }
                }
                sb.append(addBoundary(offset + " memberVar      | " + String.format("%-20s", memberVarEntry.getName()) + " | "
                        + String.format("%-20s", memberVarEntry.getType() + dimsStr)  + " | " + String.format("%-7s", memberVarEntry.getVisibility().getVisStr())));
            } else if (symbolTableEntry instanceof ParameterEntry) {
                ParameterEntry parameterEntry = (ParameterEntry) symbolTableEntry;
                String dimsStr = "";
                if (parameterEntry.getDims() != null) {
                    for (Integer i : parameterEntry.getDims()) {
                        if (i == null) {
                            dimsStr += "[]";
                        } else {
                            dimsStr += "[" + i + "]";
                        }
                    }
                }
                sb.append(addBoundary(offset + " parameter      | " + String.format("%-20s", parameterEntry.getName()) + " | "
                        + String.format("%-23s", parameterEntry.getType() + dimsStr)));
            }
        }
        sb.append(sepLine);
    }

}
