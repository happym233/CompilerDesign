package CodeGeneration.Visitors;

import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import LexicalAnalyse.Token;
import LexicalAnalyse.TokenType;
import SemanticAnalysis.ASTVisitor;
import SemanticAnalysis.SemanticTreeNode;
import SemanticAnalysis.SymbolTableEntry.*;
import SemanticAnalysis.Visitors.FunctionPair;
import SemanticAnalysis.Visitors.VisitorHelper;
import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StackBasedCodeGenerationVisitor extends ASTVisitor {
    public Stack<String> registerPool;
    public StringBuilder moonExecCode;
    public StringBuilder moonDataCode;
    public String moonCodeIndent;

    public SymbolTable globalTable;

    public int cnt;

    public StackBasedCodeGenerationVisitor() {
        this.registerPool = new Stack<>();
        this.moonExecCode = new StringBuilder();
        this.moonDataCode = new StringBuilder();
        this.moonCodeIndent = "          ";
        for (int i = 12; i >= 1; i--) {
            registerPool.push("r" + i);
        }
        cnt = 1;
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
            case "arithExpr":
                visitArithExpr(semanticTreeNode);
                break;
            case "term":
                visitTerm(semanticTreeNode);
                break;
            case "ifCondition":
            case "whileCondition":
            case "expr":
                visitExpr(semanticTreeNode);
                break;
            case "numFactor":
                visitNumFactor(semanticTreeNode);
                break;
            case "functionCallStatement":
            case "idFactor":
                visitIdFactor(semanticTreeNode);
                break;
            case "ifStatement":
                visitIfStatement(semanticTreeNode);
                break;
            case "whileStatement":
                visitWhileStatement(semanticTreeNode);
                break;
            case "readStatement":
                visitReadStatement(semanticTreeNode);
                break;
            case "returnStatement":
                visitReturnStatement(semanticTreeNode);
            case "writeStatement":
                visitWriteStatement(semanticTreeNode);
                break;
            case "assignStatement":
                visitAssignmentStatement(semanticTreeNode);
                break;
            default:
                visitOther(semanticTreeNode);
        }
    }

    public void visitReturnStatement(SemanticTreeNode semanticTreeNode) {
        SemanticTreeNode expr = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        int offset = semanticTreeNode.getSymbolTable().get(expr.getTmpSymbol().get(0)).getOffset();
        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        if (expr.getType().equals("float")) {
            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + offset +  "(r14)\n");
            moonExecCode.append(moonCodeIndent + "lw " + register2 + ", " + (offset - 4) +  "(r14)\n");
            moonExecCode.append(moonCodeIndent + "sw " + register1 + ", " + "-4(r14)\n");
            moonExecCode.append(moonCodeIndent + "sw " + register2 + ", " + "-8(r14)\n");
        } else {
            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + offset +  "(r14)\n");
            moonExecCode.append(moonCodeIndent + "sw " + register1 + ", " + "-4(r14)\n");
        }
        registerPool.push(register2);
        registerPool.push(register1);
    }

    public void visitIfStatement(SemanticTreeNode semanticTreeNode) {
        ASTTreeNode[] children = semanticTreeNode.getChildren();
        SemanticTreeNode ifCondition = (SemanticTreeNode) children[0];
        SemanticTreeNode thenStatements = (SemanticTreeNode) children[1];
        SemanticTreeNode elseStatements = (SemanticTreeNode) children[2];

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        ifCondition.accept(this);
        String register1 = registerPool.pop();
        int tmpCnt = cnt;
        cnt++;
        moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + semanticTreeNode.getSymbolTable().get(ifCondition.getTmpSymbol().get(0)).getOffset() +  "(r14)\n");
        moonExecCode.append(moonCodeIndent + "bz " + register1 + ", elseStatement" + tmpCnt + "\n");
        thenStatements.accept(this);
        moonExecCode.append(moonCodeIndent + "j endif" + tmpCnt + "\n");
        moonExecCode.append(String.format("%-10s", "elseStatement" + tmpCnt) + "\n");
        elseStatements.accept(this);
        moonExecCode.append(String.format("%-10s", "endif" + tmpCnt) + "\n");
        registerPool.push(register1);
    }

    public void visitWhileStatement(SemanticTreeNode semanticTreeNode) {
        ASTTreeNode[] child = semanticTreeNode.getChildren();
        SemanticTreeNode whileCondition = (SemanticTreeNode) child[0];
        SemanticTreeNode whileBlockStatements = (SemanticTreeNode) child[1];

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        String register1 = registerPool.pop();
        int tmpCnt = cnt;
        cnt++;
        moonExecCode.append(String.format("%-10s", "gowhile" + tmpCnt) + "\n");
        whileCondition.accept(this);
        moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + whileCondition.getOffset() + "(r14)\n");
        moonExecCode.append(moonCodeIndent + "bz " + register1 + ", endwhile" + tmpCnt + "\n");
        whileBlockStatements.accept(this);
        moonExecCode.append(moonCodeIndent + "j " + "gowhile" + tmpCnt + "\n");
        moonExecCode.append(String.format("%-10s", "endwhile" + tmpCnt) + "\n");
        registerPool.push(register1);
    }

    public void visitReadStatement(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        String register3 = registerPool.pop();
        String register4 = registerPool.pop();
        String register5 = registerPool.pop();
        String register6 = registerPool.pop();

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        moonExecCode.append(moonCodeIndent + "% processing read statement\n");
        moonExecCode.append(moonCodeIndent + "addi " + register5 + ", r14, 0\n");
        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, buf\n");
        moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * semanticTreeNode.getSymbolTable().getTotalSize() + "\n");
        moonExecCode.append(moonCodeIndent + "sw -8(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "jl r15,getstr\n");
        moonExecCode.append(moonCodeIndent + "jl r15,strint\n");
        moonExecCode.append(moonCodeIndent + "addi " + register6 + ", r13, 0\n");
        moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * semanticTreeNode.getSymbolTable().getTotalSize() + "\n");
        SymbolTable tmpTable = semanticTreeNode.getSymbolTable();
        for (int i = 0; i < semanticTreeNode.getChildren().length; i++) {
            SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
            if (child.getName().equals("idIndice")) {
                ASTTreeLeaf idLeaf = (ASTTreeLeaf) child.getChildren()[0];
                String variableName = idLeaf.getToken().getLexeme();
                SemanticTreeNode indices = null;
                if (child.getChildren().length > 1) {
                    indices = (SemanticTreeNode) child.getChildren()[1];
                }
                if (tmpTable.containsKey(variableName)) {
                    if (indices == null) {
                        moonExecCode.append(moonCodeIndent + "sw " + tmpTable.get(variableName).getOffset() + "(" + register5 + "), " + register6 + "\n");
                    } else {
                        int idFactor = 4;
                        if (child.getType().equals("float")) {
                            idFactor = 8;
                        }
                        Integer[] intIndices = null;
                        SymbolTableEntry symbolTableEntry = semanticTreeNode.getSymbolTable().get(variableName);
                        if (symbolTableEntry instanceof ParameterEntry) {
                            intIndices = ((ParameterEntry)symbolTableEntry).getDims();
                        } else if (symbolTableEntry instanceof LocalVarEntry) {
                            intIndices = ((LocalVarEntry)symbolTableEntry).getDims();
                        }
                        int j = -1;
                        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, 0 \n");
                        moonExecCode.append(moonCodeIndent + "addi " + register2 + ", r0, " + idFactor + " \n");
                        for (ASTTreeNode astTreeNode: indices.getChildren()) {
                            SemanticTreeNode indiceNode = (SemanticTreeNode) astTreeNode;
                            String tmpVarName = indiceNode.getTmpSymbol().get(0);
                            moonExecCode.append(moonCodeIndent + "% processing: dimension " + (j + 1) + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register3 + ", " + tmpTable.get(tmpVarName).getOffset() + "(" + register5 + ")\n");
                            moonExecCode.append(moonCodeIndent + "add " + register1 + ", " + register1 + ", " + register3 + "\n");
                            moonExecCode.append(moonCodeIndent + "mul " + register1 + ", " + register1 + ", " + register2 + "\n");
                            j++;
                            moonExecCode.append(moonCodeIndent + "muli " + register2 + ", " + register2 + ", " + intIndices[j] + " \n");
                        }
                        int offset = tmpTable.get(variableName).getOffset();
                        moonExecCode.append(moonCodeIndent + "muli " + register1 + ", " + register1 + ", -1\n");
                        moonExecCode.append(moonCodeIndent + "add " + register1 + ", " + register5 + ", " + register1 + "\n");
                        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", " + register1 + ", " + offset + "\n");
                        moonExecCode.append(moonCodeIndent + "sw  0(" + register1 + "), " + register6 + "\n");
                    }
                } else {
                    ParameterEntry parameterEntry = (ParameterEntry) semanticTreeNode.getSymbolTable().get("self");
                    tmpTable = ((ClassEntry)globalTable.get(parameterEntry.getType())).getClassTable();
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + semanticTreeNode.getSymbolTable().get("self").getOffset() + "(" + register5 + ")\n");
                    moonExecCode.append(moonCodeIndent + "addi " + register5 + ", " + register1 + ", 0 \n");
                    i--;
                }
            } else {
                if (i == 0) tmpTable = globalTable;
                String encoded = "";
                for (int k = 1; k < child.getChildren().length; k++) {
                    SemanticTreeNode dchild = (SemanticTreeNode) child.getChildren()[k];
                    encoded += dchild.getType();
                    for (int l = 0; l < dchild.getDims(); l++) {
                        encoded += "[]";
                    }
                    if (k < child.getChildren().length - 1) {
                        encoded += ", ";
                    }
                }
                ASTTreeLeaf funName = (ASTTreeLeaf) child.getChildren()[0];
                SemanticTreeNode aParams = (SemanticTreeNode) child.getChildren()[1];
                SymbolTableEntry funcTableEntry = tmpTable.get(funName.getToken().getLexeme() + "(" + encoded + ")");
                SymbolTable funcTable = null;
                if (funcTableEntry instanceof MemberFuncEntry) {
                    funcTable = ((MemberFuncEntry) funcTableEntry).getFunctionTable();
                } else if (funcTableEntry instanceof MemberConEntry) {
                    funcTable = ((MemberConEntry) funcTableEntry).getFunctionTable();
                } else if (funcTableEntry instanceof FunctionEntry) {
                    funcTable = ((FunctionEntry) funcTableEntry).getFunctionTable();
                }
                int tmpTableOffset = tmpTable.getTotalSize();
                int j = 0;
                List<ParameterEntry> parameterEntries = new LinkedList<>();
                for (String key: funcTable.getSymTable().keySet()) {
                    if (funcTable.getSymTable().get(key) instanceof ParameterEntry) {
                        parameterEntries.add((ParameterEntry) funcTable.getSymTable().get(key));
                    }
                }
                if (i != 0) {
                    moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntries.get(0).getOffset())  + "(r14), " + register5 + "\n");
                    j++;
                }
                for (ASTTreeNode paramNode: aParams.getChildren()) {
                    SemanticTreeNode sParamNode = (SemanticTreeNode) paramNode;
                    ParameterEntry parameterEntry = parameterEntries.get(j);
                    j++;
                    moonExecCode.append(moonCodeIndent + "%processing parameter: " + parameterEntry.getName() + "\n");
                    if (sParamNode.getDims() == 0) {
                        if (sParamNode.getType().equals("integer")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        } else if (sParamNode.getType().equals("float")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        }
                    } else {
                        moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                        moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                    }
                }
                moonExecCode.append(moonCodeIndent + "addi r14, r14, " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize()) + "\n");
                moonExecCode.append(moonCodeIndent + "jl r15, " + funcTableEntry.getEncoding() + "\n");
                if (semanticTreeNode.getType().equals("integer")) {
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + ", -4(r14)\n");
                } else {
                    // return type float
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + ", -4(r14)\n");
                    moonExecCode.append(moonCodeIndent + "lw " + register2 + ", -8(r14)\n");
                }
                moonExecCode.append(moonCodeIndent + "subi r14, r14, " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize()) + "\n");
            }
        }
        registerPool.push(register6);
        registerPool.push(register5);
        registerPool.push(register4);
        registerPool.push(register3);
        registerPool.push(register2);
        registerPool.push(register1);
    }

    public void visitWriteStatement(SemanticTreeNode semanticTreeNode) {

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        moonExecCode.append(moonCodeIndent + "% processing write statement\n");
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
        String register1 = registerPool.pop();
        SemanticTreeNode expr = (SemanticTreeNode) semanticTreeNode.getChildren()[0];
        String tmpVarName = expr.getTmpSymbol().get(0);

        if (expr.getType().equals("integer")) {
            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + expr.getSymbolTable().get(tmpVarName).getOffset() + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, buf\n");
            moonExecCode.append(moonCodeIndent + "sw -12(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "jl r15, intstr\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14),r13\n");
            moonExecCode.append(moonCodeIndent + "jl r15, putstr\n");
            moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");

            moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, charLineSep\n");
            moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "jl r15, putstr\n");
            moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
        } else {
            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + expr.getSymbolTable().get(tmpVarName).getOffset() + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, buf\n");
            moonExecCode.append(moonCodeIndent + "sw -12(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "jl r15, intstr\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14),r13\n");
            moonExecCode.append(moonCodeIndent + "jl r15, putstr\n");
            moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");

            moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, charE\n");
            moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "jl r15, putstr\n");
            moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");

            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + (expr.getSymbolTable().get(tmpVarName).getOffset() - 4) + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "addi r1, r0, buf\n");
            moonExecCode.append(moonCodeIndent + "sw -12(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "jl r15, intstr\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14),r13\n");
            moonExecCode.append(moonCodeIndent + "jl r15, putstr\n");
            moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");


            moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, charLineSep\n");
            moonExecCode.append(moonCodeIndent + "addi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
            moonExecCode.append(moonCodeIndent + "sw -8(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "jl r15, putstr\n");
            moonExecCode.append(moonCodeIndent + "subi r14, r14, " + -1 * expr.getSymbolTable().getTotalSize() + "\n");
        }
        registerPool.push(register1);
    }

    public void visitAssignmentStatement(SemanticTreeNode semanticTreeNode) {

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }

        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        String register3 = registerPool.pop();
        String register4 = registerPool.pop();
        String register5 = registerPool.pop();
        String register6 = registerPool.pop();
        String register7 = registerPool.pop();
        moonExecCode.append(moonCodeIndent + "% processing assignment statement\n");
        moonExecCode.append(moonCodeIndent + "addi " + register5 + ", r14, 0\n");
        SymbolTable tmpTable = semanticTreeNode.getSymbolTable();
        SemanticTreeNode rightExpr = (SemanticTreeNode) semanticTreeNode.getChildren()[semanticTreeNode.getChildren().length - 1];
        if (rightExpr.getType().equals("float")) {
            moonExecCode.append(moonCodeIndent + "lw " + register6 + "," + rightExpr.getOffset() + "(" + register5 + ")\n");
            moonExecCode.append(moonCodeIndent + "lw " + register7 + "," + (rightExpr.getOffset() - 4) + "(" + register5 + ")\n");
        } else {
            moonExecCode.append(moonCodeIndent + "lw " + register6 + "," + rightExpr.getOffset() + "(" + register5 + ")\n");
        }

        for (int i = 0; i < semanticTreeNode.getChildren().length - 1; i++) {
            SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
            if (child.getName().equals("idIndice")) {
                ASTTreeLeaf idLeaf = (ASTTreeLeaf) child.getChildren()[0];
                String variableName = idLeaf.getToken().getLexeme();
                SemanticTreeNode indices = null;
                if (child.getChildren().length > 1) {
                    indices = (SemanticTreeNode) child.getChildren()[1];
                }
                if (tmpTable.containsKey(variableName)) {
                    if (indices == null) {
                        if (child.getType().equals("integer")) {
                            moonExecCode.append(moonCodeIndent + "sw " + tmpTable.get(variableName).getOffset() + "(" + register5 + "), " + register6 + "\n");
                        } else if (child.getType().equals("float")){
                            moonExecCode.append(moonCodeIndent + "sw " + tmpTable.get(variableName).getOffset() + "(" + register5 + "), " + register6 + "\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (tmpTable.get(variableName).getOffset() - 4) + "(" + register5 + "), " + register7 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "sw " + tmpTable.get(variableName).getOffset() + "(" + register5 + ")" + register6 + "\n");
                        }
                    } else {
                        int idFactor = 4;
                        if (child.getType().equals("float")) {
                            idFactor = 8;
                        }
                        Integer[] intIndices = null;
                        SymbolTableEntry symbolTableEntry = semanticTreeNode.getSymbolTable().get(variableName);
                        if (symbolTableEntry instanceof ParameterEntry) {
                            intIndices = ((ParameterEntry)symbolTableEntry).getDims();
                        } else if (symbolTableEntry instanceof LocalVarEntry) {
                            intIndices = ((LocalVarEntry)symbolTableEntry).getDims();
                        }
                        int j = -1;
                        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, 0 \n");
                        moonExecCode.append(moonCodeIndent + "addi " + register2 + ", r0, " + idFactor + " \n");
                        for (ASTTreeNode astTreeNode: indices.getChildren()) {
                            SemanticTreeNode indiceNode = (SemanticTreeNode) astTreeNode;
                            String tmpVarName = indiceNode.getTmpSymbol().get(0);
                            moonExecCode.append(moonCodeIndent + "% processing: dimension " + (j + 1) + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register3 + ", " + tmpTable.get(tmpVarName).getOffset() + "(" + register5 + ")\n");
                            moonExecCode.append(moonCodeIndent + "add " + register1 + ", " + register1 + ", " + register3 + "\n");
                            moonExecCode.append(moonCodeIndent + "mul " + register1 + ", " + register1 + ", " + register2 + "\n");
                            j++;
                            moonExecCode.append(moonCodeIndent + "muli " + register2 + ", " + register2 + ", " + intIndices[j] + " \n");
                        }
                        int offset = tmpTable.get(variableName).getOffset();
                        moonExecCode.append(moonCodeIndent + "muli " + register1 + ", " + register1 + ", -1\n");
                        moonExecCode.append(moonCodeIndent + "add " + register1 + ", " + register5 + ", " + register1 + "\n");
                        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", " + register1 + ", " + offset + "\n");
                        if (child.getType().equals("integer")) {
                            moonExecCode.append(moonCodeIndent + "sw  0(" + register1 + "), " + register6 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "sw  0(" + register1 + ")" + register6 + "\n");
                            moonExecCode.append(moonCodeIndent + "sw -4(" + register1 + register7 + ")\n");
                        }
                    }
                } else {
                    ParameterEntry parameterEntry = (ParameterEntry) semanticTreeNode.getSymbolTable().get("self");
                    tmpTable = ((ClassEntry)globalTable.get(parameterEntry.getType())).getClassTable();
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + semanticTreeNode.getSymbolTable().get("self").getOffset() + "(" + register5 + ")\n");
                    moonExecCode.append(moonCodeIndent + "addi " + register5 + ", " + register1 + ", 0 \n");
                    i--;
                }
            } else {
                if (i == 0) tmpTable = globalTable;
                String encoded = "";
                for (int k = 1; k < child.getChildren().length; k++) {
                    SemanticTreeNode dchild = (SemanticTreeNode) child.getChildren()[k];
                    encoded += dchild.getType();
                    for (int l = 0; l < dchild.getDims(); l++) {
                        encoded += "[]";
                    }
                    if (k < child.getChildren().length - 1) {
                        encoded += ", ";
                    }
                }
                ASTTreeLeaf funName = (ASTTreeLeaf) child.getChildren()[0];
                SemanticTreeNode aParams = (SemanticTreeNode) child.getChildren()[1];
                SymbolTableEntry funcTableEntry = tmpTable.get(funName.getToken().getLexeme() + "(" + encoded + ")");
                SymbolTable funcTable = null;
                if (funcTableEntry instanceof MemberFuncEntry) {
                    funcTable = ((MemberFuncEntry) funcTableEntry).getFunctionTable();
                } else if (funcTableEntry instanceof MemberConEntry) {
                    funcTable = ((MemberConEntry) funcTableEntry).getFunctionTable();
                } else if (funcTableEntry instanceof FunctionEntry) {
                    funcTable = ((FunctionEntry) funcTableEntry).getFunctionTable();
                }
                int j = 0;
                List<ParameterEntry> parameterEntries = new LinkedList<>();
                for (String key: funcTable.getSymTable().keySet()) {
                    if (funcTable.getSymTable().get(key) instanceof ParameterEntry) {
                        parameterEntries.add((ParameterEntry) funcTable.getSymTable().get(key));
                    }
                }
                if (i != 0) {
                    moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntries.get(0).getOffset())  + "(r14), " + register5 + "\n");
                    j++;
                }
                for (ASTTreeNode paramNode: aParams.getChildren()) {
                    SemanticTreeNode sParamNode = (SemanticTreeNode) paramNode;
                    ParameterEntry parameterEntry = parameterEntries.get(j);
                    j++;
                    moonExecCode.append(moonCodeIndent + "%processing parameter: " + parameterEntry.getName() + "\n");
                    if (sParamNode.getDims() == 0) {
                        if (sParamNode.getType().equals("integer")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");

                        } else if (sParamNode.getType().equals("float")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        }
                    } else {
                        moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                        moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                    }
                }
                moonExecCode.append(moonCodeIndent + "addi r14, r14, " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize()) + "\n");
                moonExecCode.append(moonCodeIndent + "jl r15, " + funcTableEntry.getEncoding() + "\n");
                if (semanticTreeNode.getType().equals("integer")) {
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + ", -4(r14)\n");
                } else {
                    // return type float
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + ", -4(r14)\n");
                    moonExecCode.append(moonCodeIndent + "lw " + register2 + ", -8(r14)\n");
                }
                moonExecCode.append(moonCodeIndent + "subi r14, r14, " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize()) + "\n");
                if (semanticTreeNode.getType().equals("integer")) {
                    moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getOffset() + "(r14), " + register1 +"\n");
                } else {
                    // return type float
                    moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getOffset() + "(r14), " + register1 +"\n");
                    moonExecCode.append(moonCodeIndent + "sw " + (semanticTreeNode.getOffset() - 4) + "(r14), " + register2 +"\n");
                }
            }
        }
        registerPool.push(register7);
        registerPool.push(register6);
        registerPool.push(register5);
        registerPool.push(register4);
        registerPool.push(register3);
        registerPool.push(register2);
        registerPool.push(register1);

    }

    public void visitFunctionCallStatement(SemanticTreeNode semanticTreeNode) {

        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
    }

    private void init() {
        moonExecCode.append("\n");
        moonExecCode.append("floatAdd\n");
        moonExecCode.append(moonCodeIndent + "sw -4(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "sw -8(r14), r2\n");
        moonExecCode.append(moonCodeIndent + "sw -12(r14), r3\n");
        moonExecCode.append(moonCodeIndent + "sw -16(r14), r4\n");
        moonExecCode.append("gowhileFAdd\n");
        moonExecCode.append(moonCodeIndent + "cne r5, r2, r4\n");
        moonExecCode.append(moonCodeIndent + "bz r5, endwhileFAdd\n");
        moonExecCode.append(moonCodeIndent + "clt r6, r2, r4\n");
        moonExecCode.append(moonCodeIndent + "bz r6, elseFAdd\n");
        moonExecCode.append(moonCodeIndent + "muli r3, r3, 10\n");
        moonExecCode.append(moonCodeIndent + "subi r4, r4, 1\n");
        moonExecCode.append(moonCodeIndent + "j endIfFAdd\n");
        moonExecCode.append("elseFAdd\n");
        moonExecCode.append(moonCodeIndent + "muli r1, r1, 10\n");
        moonExecCode.append(moonCodeIndent + "subi r2, r2, 1\n");
        moonExecCode.append("endIfFAdd\n");
        moonExecCode.append(moonCodeIndent + "j gowhileFAdd\n");
        moonExecCode.append("endwhileFAdd\n");
        moonExecCode.append(moonCodeIndent + "add  r1, r1, r3\n");
        moonExecCode.append(moonCodeIndent + "sw   -4(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "sw   -8(r14), r2\n");
        moonExecCode.append(moonCodeIndent + "jr   r15\n");

        moonExecCode.append("floatMul\n");
        moonExecCode.append(moonCodeIndent + "sw -4(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "sw -8(r14), r2\n");
        moonExecCode.append(moonCodeIndent + "sw -12(r14), r3\n");
        moonExecCode.append(moonCodeIndent + "sw -16(r14), r4\n");
        moonExecCode.append(moonCodeIndent + "mul r1, r1, r3\n");
        moonExecCode.append(moonCodeIndent + "add r2, r2, r4\n");
        moonExecCode.append(moonCodeIndent + "sw  -4(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "sw  -8(r14), r2\n");
        moonExecCode.append(moonCodeIndent + "jr   r15\n");

        moonExecCode.append("floatLeq\n");
        moonExecCode.append(moonCodeIndent + "sw -4(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "sw -8(r14), r2\n");
        moonExecCode.append(moonCodeIndent + "sw -12(r14), r3\n");
        moonExecCode.append(moonCodeIndent + "sw -16(r14), r4\n");
        moonExecCode.append("gowhileFLeq\n");
        moonExecCode.append(moonCodeIndent + "cne r5, r2, r4\n");
        moonExecCode.append(moonCodeIndent + "bz   r5, endwhileFLeq\n");
        moonExecCode.append(moonCodeIndent + "clt r6, r2, r4\n");
        moonExecCode.append(moonCodeIndent + "bz r6, elseFLeq\n");
        moonExecCode.append(moonCodeIndent + "muli r3, r3, 10\n");
        moonExecCode.append(moonCodeIndent + "subi r4, r4, 1\n");
        moonExecCode.append(moonCodeIndent + "j endIfFLeq\n");
        moonExecCode.append("elseFLeq\n");
        moonExecCode.append(moonCodeIndent + "muli r1, r1, 10\n");
        moonExecCode.append(moonCodeIndent + "subi r2, r2, 1\n");
        moonExecCode.append(moonCodeIndent + "endIfFLeq\n");
        moonExecCode.append(moonCodeIndent + "j gowhileFLeq\n");
        moonExecCode.append("endwhileFLeq \n");
        moonExecCode.append(moonCodeIndent + "cle r1, r1, r3\n");
        moonExecCode.append(moonCodeIndent + "sw -4(r14), r1\n");
        moonExecCode.append(moonCodeIndent + "jr r15\n");
        moonDataCode.append(String.format("%-10s", "buf") + "res 20\n");
        moonDataCode.append(String.format("%-10s", "charE") + "db \"e\", 0\n");
        moonDataCode.append(String.format("%-15s", "charLineSep") + "db \"  \", 0\n");
    }

    private void visitProgram(SemanticTreeNode semanticTreeNode) {
        this.globalTable = semanticTreeNode.getSymbolTable();
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            SemanticTreeNode funcNode = (SemanticTreeNode)astTreeNode;
            if (funcNode.getSymbolTableEntry() != null) {
                if (funcNode.getSymbolTableEntry().getName().equals("main")) {
                    moonExecCode.append("entry\n");
                    moonExecCode.append(moonCodeIndent + "addi r14, r0, topaddr\n");
                    funcNode.accept(this);
                    moonExecCode.append("hlt\n");
                } else {
                    moonExecCode.append(funcNode.getSymbolTableEntry().getEncoding() + "\n");
                    funcNode.accept(this);
                    moonExecCode.append("jr r15\n");
                }
            }
        }
        init();
    }

    private void visitFunction(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
    }

    private void visitClass(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
    }

    private void visitArithExpr(SemanticTreeNode semanticTreeNode) {

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        String register3 = registerPool.pop();
        String register4 = registerPool.pop();
        TokenType operatorType = null;
        for (int i = 0; i < semanticTreeNode.getChildren().length; i++) {
            ASTTreeNode astTreeNode = semanticTreeNode.getChildren()[i];
            if (i == 0) {
                SemanticTreeNode firstNode = (SemanticTreeNode) astTreeNode;
                generateDataTransferCode(register1, register2, semanticTreeNode, firstNode);
            } else if (astTreeNode instanceof ASTTreeLeaf) {
                ASTTreeLeaf astTreeLeaf = (ASTTreeLeaf) semanticTreeNode.getChildren()[i];
                operatorType = astTreeLeaf.getToken().getType();
            } else {
                SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
                generateAddOpCode(operatorType, register1, register2, register3, register4, semanticTreeNode, child);
            }
        }
        registerPool.push(register4);
        registerPool.push(register3);
        registerPool.push(register2);
        registerPool.push(register1);
    }

    public void visitTerm(SemanticTreeNode semanticTreeNode) {

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        String register3 = registerPool.pop();
        String register4 = registerPool.pop();
        TokenType operatorType = null;
        for (int i = 0; i < semanticTreeNode.getChildren().length; i++) {
            ASTTreeNode astTreeNode = semanticTreeNode.getChildren()[i];
            if (i == 0) {
                SemanticTreeNode firstNode = (SemanticTreeNode) astTreeNode;
                generateDataTransferCode(register1, register2, semanticTreeNode, firstNode);
            } else if (astTreeNode instanceof ASTTreeLeaf) {
                ASTTreeLeaf astTreeLeaf = (ASTTreeLeaf) semanticTreeNode.getChildren()[i];
                operatorType = astTreeLeaf.getToken().getType();
            } else {
                SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
                generateMultiOpCode(operatorType, register1, register2, register3, register4, semanticTreeNode, child);
            }
        }
        registerPool.push(register4);
        registerPool.push(register3);
        registerPool.push(register2);
        registerPool.push(register1);
    }

    public void visitExpr(SemanticTreeNode semanticTreeNode) {

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        String register3 = registerPool.pop();
        String register4 = registerPool.pop();
        TokenType operatorType = null;
        for (int i = 0; i < semanticTreeNode.getChildren().length; i++) {
            ASTTreeNode astTreeNode = semanticTreeNode.getChildren()[i];
            if (i == 0) {
                SemanticTreeNode firstNode = (SemanticTreeNode) astTreeNode;
                generateDataTransferCode(register1, register2, semanticTreeNode, firstNode);
            } else if (astTreeNode instanceof ASTTreeLeaf) {
                ASTTreeLeaf astTreeLeaf = (ASTTreeLeaf) semanticTreeNode.getChildren()[i];
                operatorType = astTreeLeaf.getToken().getType();
            } else {
                SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
                generateRelOpCode(operatorType, register1, register2, register3, register4, semanticTreeNode, child);
            }
        }
        registerPool.push(register4);
        registerPool.push(register3);
        registerPool.push(register2);
        registerPool.push(register1);
    }

    public void visitIdFactor(SemanticTreeNode semanticTreeNode) {

        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.setSymbolTable(child.getSymbolTable());
                child.accept(this);
            }
        }
        String register1 = registerPool.pop();
        String register2 = registerPool.pop();
        String register3 = registerPool.pop();
        String register4 = registerPool.pop();
        String register5 = registerPool.pop();
        SymbolTable tmpTable = semanticTreeNode.getSymbolTable();

        for (int i = 0; i < semanticTreeNode.getChildren().length; i++) {
            SemanticTreeNode child = (SemanticTreeNode) semanticTreeNode.getChildren()[i];
            if (child.getName().equals("idIndice")) {
                ASTTreeLeaf idLeaf = (ASTTreeLeaf) child.getChildren()[0];
                String variableName = idLeaf.getToken().getLexeme();
                SemanticTreeNode indices = null;
                if (child.getChildren().length > 1) {
                    indices = (SemanticTreeNode) child.getChildren()[1];
                }
                moonExecCode.append(moonCodeIndent + "addi " + register5 + ", r14, 0\n");
                if (tmpTable.containsKey(variableName)) {
                    if (indices == null) {
                        if (child.getType().equals("integer")) {
                            if (semanticTreeNode.getDims() == 0) {
                                moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + variableName + "\n");
                                moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + tmpTable.get(variableName).getOffset() + "(" + register5 + ")\n");
                                moonExecCode.append(moonCodeIndent + "sw " + tmpTable.get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(" + register5 + "), " + register1 + "\n");
                            } else {
                                moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + variableName + "\n");
                                moonExecCode.append(moonCodeIndent + "addi " + register1 + "," + register5 + ", " + tmpTable.get(variableName).getOffset() + "\n");
                                moonExecCode.append(moonCodeIndent + "sw " + tmpTable.get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(" + register5 + "), " + register1 + "\n");
                            }
                        } else if (child.getType().equals("float")){
                            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + variableName + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + tmpTable.get(variableName).getOffset() + "(" + register5 + ")\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register2 + "," + (tmpTable.get(variableName).getOffset() - 4) + "(" + register5 + ")\n");
                            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(r14), " + register1 + "\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14), " + register2 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + variableName + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + semanticTreeNode.getSymbolTable().get(variableName).getOffset() + "(r14)\n");
                            String variableType = child.getType();
                            tmpTable = ((ClassEntry)globalTable.get(variableType)).getClassTable();
                            moonExecCode.append(moonCodeIndent + "addi " + register5 + ", " + register1 + ", 0 \n");
                        }
                    } else {
                        int idFactor = 4;
                        if (child.getType().equals("float")) {
                            idFactor = 8;
                        }
                        Integer[] intIndices = null;
                        SymbolTableEntry symbolTableEntry = semanticTreeNode.getSymbolTable().get(variableName);
                        if (symbolTableEntry instanceof ParameterEntry) {
                            intIndices = ((ParameterEntry)symbolTableEntry).getDims();
                        } else if (symbolTableEntry instanceof LocalVarEntry) {
                            intIndices = ((LocalVarEntry)symbolTableEntry).getDims();
                        }
                        moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + variableName + "\n");
                        int j = -1;
                        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", r0, 0 \n");
                        moonExecCode.append(moonCodeIndent + "addi " + register2 + ", r0, " + idFactor + " \n");
                        for (ASTTreeNode astTreeNode: indices.getChildren()) {
                            SemanticTreeNode indiceNode = (SemanticTreeNode) astTreeNode;
                            String tmpVarName = indiceNode.getTmpSymbol().get(0);
                            moonExecCode.append(moonCodeIndent + "% processing: dimension " + (j + 1) + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register3 + ", " + tmpTable.get(tmpVarName).getOffset() + "(" + register5 + ")\n");
                            moonExecCode.append(moonCodeIndent + "add " + register1 + ", " + register1 + ", " + register3 + "\n");
                            moonExecCode.append(moonCodeIndent + "mul " + register1 + ", " + register1 + ", " + register2 + " \n");
                            j++;
                            moonExecCode.append(moonCodeIndent + "muli " + register2 + ", " + register2 + ", " + intIndices[j] + " \n");
                        }
                        int offset = tmpTable.get(variableName).getOffset();
                        moonExecCode.append(moonCodeIndent + "muli " + register1 + ", " + register1 + ", -1\n");
                        moonExecCode.append(moonCodeIndent + "add " + register1 + ", " + register5 + ", " + register1 + "\n");
                        moonExecCode.append(moonCodeIndent + "addi " + register1 + ", " + register1 + ", " + offset + "\n");
                        if (child.getType().equals("integer")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register3 + ", 0(" + register1 + ")\n");
                            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(r14), " + register3 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "lw " + register3 + ", 0(" + register1 + ")\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register4 + ", -4(" + register1 + ")\n");
                            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(r14), " + register3 + "\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14), " + register4 + "\n");
                        }
                    }
                } else {
                    ParameterEntry parameterEntry = (ParameterEntry) semanticTreeNode.getSymbolTable().get("self");
                    tmpTable = ((ClassEntry)globalTable.get(parameterEntry.getType())).getClassTable();
                    moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + variableName + "\n");
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + semanticTreeNode.getSymbolTable().get("self").getOffset() + "(" + register5 + ")\n");
                    moonExecCode.append(moonCodeIndent + "addi " + register5 + ", " + register1 + ", 0 \n");
                    i--;
                }
            } else {
                if (i == 0) tmpTable = globalTable;
                String encoded = "";
                for (int k = 1; k < child.getChildren().length; k++) {
                    SemanticTreeNode dchild = (SemanticTreeNode) child.getChildren()[k];
                    encoded += dchild.getType();
                    for (int l = 0; l < dchild.getDims(); l++) {
                        encoded += "[]";
                    }
                    if (k < child.getChildren().length - 1) {
                        encoded += ", ";
                    }
                }
                ASTTreeLeaf funName = (ASTTreeLeaf) child.getChildren()[0];
                SymbolTableEntry funcTableEntry = tmpTable.get(funName.getToken().getLexeme() + "(" + encoded + ")");
                SymbolTable funcTable = null;
                if (funcTableEntry instanceof MemberFuncEntry) {
                    funcTable = ((MemberFuncEntry) funcTableEntry).getFunctionTable();
                } else if (funcTableEntry instanceof MemberConEntry) {
                    funcTable = ((MemberConEntry) funcTableEntry).getFunctionTable();
                } else if (funcTableEntry instanceof FunctionEntry) {
                    funcTable = ((FunctionEntry) funcTableEntry).getFunctionTable();
                }
                int tmpTableOffset = tmpTable.getTotalSize();
                int j = 0;
                List<ParameterEntry> parameterEntries = new LinkedList<>();
                for (String key: funcTable.getSymTable().keySet()) {
                    if (funcTable.getSymTable().get(key) instanceof ParameterEntry) {
                        parameterEntries.add((ParameterEntry) funcTable.getSymTable().get(key));
                    }
                }
                if (i != 0) {
                    moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntries.get(0).getOffset())  + "(r14), " + register5 + "\n");
                    j++;
                }
                for (ASTTreeNode paramNode: child.getChildren()) {
                    if (paramNode instanceof ASTTreeLeaf) continue;
                    SemanticTreeNode sParamNode = (SemanticTreeNode) paramNode;
                    ParameterEntry parameterEntry = parameterEntries.get(j);
                    moonExecCode.append(moonCodeIndent + "%processing parameter" + j + ": " + parameterEntry.getName() + "\n");
                    j++;
                    if (sParamNode.getDims() == 0) {
                        if (sParamNode.getType().equals("integer")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        } else if (sParamNode.getType().equals("float")) {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        } else {
                            moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                            moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                        }
                    } else {
                        moonExecCode.append(moonCodeIndent + "lw " + register1 + ", " + sParamNode.getOffset() + "(r14)\n");
                        moonExecCode.append(moonCodeIndent + "sw " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize() + parameterEntry.getOffset())  + "(r14), " + register1 + "\n");
                    }
                }
                moonExecCode.append(moonCodeIndent + "addi r14, r14, " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize()) + "\n");
                moonExecCode.append(moonCodeIndent + "jl r15, " + funcTableEntry.getEncoding() + "\n");
                if (semanticTreeNode.getType().equals("integer")) {
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + ", -4(r14)\n");
                } else {
                    // return type float
                    moonExecCode.append(moonCodeIndent + "lw " + register1 + ", -4(r14)\n");
                    moonExecCode.append(moonCodeIndent + "lw " + register2 + ", -8(r14)\n");
                }
                moonExecCode.append(moonCodeIndent + "subi r14, r14, " + (-1 * semanticTreeNode.getSymbolTable().getTotalSize()) + "\n");
            }
        }
        registerPool.push(register5);
        registerPool.push(register4);
        registerPool.push(register3);
        registerPool.push(register2);
        registerPool.push(register1);
    }

    private void generateDataTransferCode(String register1, String register2, SemanticTreeNode semanticTreeNode, SemanticTreeNode firstNode) {

        if (semanticTreeNode.getType().equals("float")) {
            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + firstNode.getTmpSymbol().get(0) + "\n");
            moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + firstNode.getOffset() + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "lw " + register2 + "," + (firstNode.getOffset() - 4) + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getOffset() + "(r14), " + register1 + "\n");
            moonExecCode.append(moonCodeIndent + "sw " + (semanticTreeNode.getOffset() - 4) + "(r14), " + register2 + "\n");
        } else {
            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0) + " := " + firstNode.getTmpSymbol().get(0) + "\n");
            moonExecCode.append(moonCodeIndent + "lw " + register1 + "," + firstNode.getOffset() + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getOffset() + "(r14), " + register1 + "\n");
        }
    }

    private void generateAddOpCode(TokenType operatorType, String register1, String register2, String register3, String register4, SemanticTreeNode parentNode, SemanticTreeNode childNode) {
        if (childNode.getType().equals("integer")) {
            if (operatorType.equals(TokenType.PLUS)) {
                moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " + " + childNode.getTmpSymbol().get(0) + "\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "add "  + register1 + "," + register1 + "," + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register1 + "\n");
            } else if (operatorType.equals(TokenType.MINUS)) {
                moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " - " + childNode.getTmpSymbol().get(0) + "\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "sub "  + register1 + "," + register1 + "," + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register1 + "\n");
            } else if (operatorType.equals(TokenType.AND)) {
                moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " and " + childNode.getTmpSymbol().get(0) + "\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "bz "  + register1 + ", zero" + cnt + "\n");
                moonExecCode.append(moonCodeIndent + "bz "  + register2 + ", zero" + cnt + "\n");
                moonExecCode.append(moonCodeIndent + "addi " + register3 + ", r0, 1\n");
                moonExecCode.append(moonCodeIndent + "j endand" + cnt + "\n");
                moonExecCode.append(String.format("-10s", "zero" + cnt) + "addi r3, r0, 0\n");
                moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register3 + "\n");
                cnt++;
            }
        } else {
            // float
            if (operatorType.equals(TokenType.PLUS)) {
                int offset = parentNode.getSymbolTable().getTotalSize();
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + (parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register3 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register4 + "," + (childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 4) + "(r14), " + register1 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 8) + "(r14), " + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 12) + "(r14), " + register3 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 16) + "(r14), " + register4 + "\n");
                moonExecCode.append(moonCodeIndent + "addi r14, r14, " + (-1 * offset) + "\n");
                moonExecCode.append(moonCodeIndent + "jl r15, floatAdd\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + ", -4(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + ", -8(r14)\n");
                moonExecCode.append(moonCodeIndent + "subi r14, r14, " + (-1 * offset) + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14), " + register1 +"\n");
                moonExecCode.append(moonCodeIndent + "sw " + (parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14), " + register2 + "\n");
            }
        }
    }

    private void generateMultiOpCode(TokenType operatorType, String register1, String register2, String register3, String register4, SemanticTreeNode parentNode, SemanticTreeNode childNode) {

        if (childNode.getType().equals("integer")) {
            if (operatorType.equals(TokenType.MULT)) {
                moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " * " + childNode.getTmpSymbol().get(0) + "\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "mul "  + register1 + "," + register1 + "," + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register1 + "\n");
            } else if (operatorType.equals(TokenType.DIV)) {
                moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " / " + childNode.getTmpSymbol().get(0) + "\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "div "  + register1 + "," + register1 + "," + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register1 + "\n");
            } else if (operatorType.equals(TokenType.OR)) {
                moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " and " + childNode.getTmpSymbol().get(0) + "\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "bnz "  + register1 + ", zero" + cnt + "\n");
                moonExecCode.append(moonCodeIndent + "bnz "  + register2 + ", zero" + cnt + "\n");
                moonExecCode.append(moonCodeIndent + "addi " + register3 + ", r0, 1\n");
                moonExecCode.append(moonCodeIndent + "j endor" + cnt + "\n");
                moonExecCode.append(String.format("-10s", "zero" + cnt) + "addi r3, r0, 0\n");
                moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register3 + "\n");
                cnt++;
            }
        } else {
            // float
            if (operatorType.equals(TokenType.MULT)) {
                int offset = parentNode.getSymbolTable().getTotalSize();
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + (parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register3 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register4 + "," + (childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 4) + "(r14), " + register1 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 8) + "(r14), " + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 12) + "(r14), " + register3 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 16) + "(r14), " + register4 + "\n");
                moonExecCode.append(moonCodeIndent + "addi r14, r14, " + (-1 * offset) + "\n");
                moonExecCode.append(moonCodeIndent + "jl r15, floatMul\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + ", -4(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + ", -8(r14)\n");
                moonExecCode.append(moonCodeIndent + "subi r14, r14, " + (-1 * offset) + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14), " + register1 +"\n");
                moonExecCode.append(moonCodeIndent + "sw " + (parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14), " + register2 + "\n");
            }
        }
    }

    private void generateRelOpCode(TokenType operatorType, String register1, String register2, String register3, String register4, SemanticTreeNode parentNode, SemanticTreeNode childNode) {
        if (childNode.getType().equals("integer")) {
            String operatorStr = "";
            if (operatorType == TokenType.EQ) {
                operatorStr = "ceq";
            } else if (operatorType == TokenType.NOT_EQ) {
                operatorStr = "cne";
            } else if (operatorType == TokenType.LT) {
                operatorStr = "clt";
            } else if (operatorType == TokenType.LEQ) {
                operatorStr = "cle";
            } else if (operatorType == TokenType.GT) {
                operatorStr = "cgt";
            } else if (operatorType == TokenType.GEQ) {
                operatorStr = "cge";
            }
            moonExecCode.append(moonCodeIndent + "% processing: " + parentNode.getTmpSymbol().get(0) + " := " + parentNode.getTmpSymbol().get(0) + " " + operatorType.tokenTypeStr + " " + childNode.getTmpSymbol().get(0) + "\n");
            moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
            moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
            moonExecCode.append(moonCodeIndent + operatorStr + " "  + register1 + "," + register1 + "," + register2 + "\n");
            moonExecCode.append(moonCodeIndent + "sw "   + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + register1 + "\n");
        } else {
            // float
            if (operatorType == TokenType.LEQ) {
                int offset = parentNode.getSymbolTable().getTotalSize();
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + "," + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + "," + (parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register3 + "," + childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register4 + "," + (childNode.getSymbolTable().get(childNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 4) + "(r14), " + register1 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 8) + "(r14), " + register2 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 12) + "(r14), " + register3 + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + (-1 * offset - 16) + "(r14), " + register4 + "\n");
                moonExecCode.append(moonCodeIndent + "addi r14, r14, " + (-1 * offset) + "\n");
                moonExecCode.append(moonCodeIndent + "jl r15, floatLeq\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register1 + ", -4(r14)\n");
                moonExecCode.append(moonCodeIndent + "lw "   + register2 + ", -8(r14)\n");
                moonExecCode.append(moonCodeIndent + "subi r14, r14, " + (-1 * offset) + "\n");
                moonExecCode.append(moonCodeIndent + "sw " + parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() + "(r14), " + register1 +"\n");
                moonExecCode.append(moonCodeIndent + "sw " + (parentNode.getSymbolTable().get(parentNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14), " + register2 + "\n");
            }
        }
    }
    private void visitNumFactor(SemanticTreeNode semanticTreeNode) {

        String localregister1 = this.registerPool.pop();
        moonExecCode.append(moonCodeIndent + "% processing line" + semanticTreeNode.getLocation() + "\n");
        ASTTreeLeaf astTreeLeaf = (ASTTreeLeaf) semanticTreeNode.getChildren()[0];
        String numData = astTreeLeaf.getToken().getLexeme();
        if (astTreeLeaf.getToken().getType() == TokenType.INT_NUM) {
            // generate code
            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0)  + " := " + numData + "\n");
            moonExecCode.append(moonCodeIndent + "addi " + localregister1 + ",r0," + numData + "\n");
            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + localregister1 + "\n");
        } else {
            float f = java.lang.Float.valueOf(numData);
            int n = 0, e = 0;
            while (Math.round(f) != f) {
                e++;
                f = f * 10;
            }
            n = Math.round(f);
            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0)  + " := " + numData + "\n");
            moonExecCode.append(moonCodeIndent + "addi " + localregister1 + ",r0," + n + "\n");
            moonExecCode.append(moonCodeIndent + "sw " + semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() + "(r14)," + localregister1 + "\n");

            moonExecCode.append(moonCodeIndent + "% processing: " + semanticTreeNode.getTmpSymbol().get(0)  + " := " + numData + "\n");
            moonExecCode.append(moonCodeIndent + "addi " + localregister1 + ",r0," + e + "\n");
            moonExecCode.append(moonCodeIndent + "sw " + (semanticTreeNode.getSymbolTable().get(semanticTreeNode.getTmpSymbol().get(0)).getOffset() - 4) + "(r14)," + localregister1 + "\n");
        }
        registerPool.push(localregister1);
    }

    private void visitOther(SemanticTreeNode semanticTreeNode) {
        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                SemanticTreeNode child = (SemanticTreeNode) astTreeNode;
                child.accept(this);
            }
        }
    }
}
