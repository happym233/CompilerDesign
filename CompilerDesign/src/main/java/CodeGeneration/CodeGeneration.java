package CodeGeneration;

import CodeGeneration.SymbolTableEntry.TmpVarEntry;
import CodeGeneration.Visitors.ComputeMemSizeVisitor;
import CodeGeneration.Visitors.StackBasedCodeGenerationVisitor;
import SemanticAnalysis.SemanticAnalyser;
import SemanticAnalysis.SymbolTableEntry.*;
import SemanticAnalysis.Visitors.ErrorMessege;

import java.io.IOException;

public class CodeGeneration {
    private SemanticAnalyser semanticAnalyser;
    private StackBasedCodeGenerationVisitor stackBasedCodeGenerationVisitor;

    private String tableStr;

    public CodeGeneration(String fileName) throws IOException {
        semanticAnalyser = new SemanticAnalyser(fileName);
    }

    public void run() {
        semanticAnalyser.run();
        semanticAnalyser.getRoot().accept(new ComputeMemSizeVisitor());
        StringBuilder stringBuilder = new StringBuilder();
        symbolTableToStr(stringBuilder, semanticAnalyser.getRoot().getSymbolTable(), "global", 0);
        // System.out.println(stringBuilder.toString());
        tableStr = stringBuilder.toString();
        stackBasedCodeGenerationVisitor = new StackBasedCodeGenerationVisitor();
        semanticAnalyser.getRoot().accept(stackBasedCodeGenerationVisitor);
    }

    public String getTableStr() {
        return tableStr;
    }
    public String getAssemblyCode() {
        String str = "";
        str += stackBasedCodeGenerationVisitor.moonExecCode;
        str += "\n";
        str += stackBasedCodeGenerationVisitor.moonDataCode;
        return str;
    }

    public String addBoundary(String str) {
        return String.format("%-79s", str) + "|"  + "\n";
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
                        String.format("%-23s", functionEntry.toOuputStr()) + "|" + String.format("%-5s", functionEntry.getSpace()) + "|" + String.format("%-5s", functionEntry.getOffset())));
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
                        + String.format("%-27s", localVarEntry.getType() + dimsStr  + "|" + String.format("%-5s", symbolTableEntry.getSpace()) + "|" + String.format("%-5s", symbolTableEntry.getOffset()))));
            } else if (symbolTableEntry instanceof MemberConEntry) {
                if (!entry.contains("(")) continue;
                MemberConEntry memberConEntry = (MemberConEntry) symbolTableEntry;
                sb.append(addBoundary(offset + " constructor    | " + String.format("%-43s", memberConEntry.toOuputStr()) + " | " + memberConEntry.getVisibility().getVisStr() + "|" + String.format("%-5s", symbolTableEntry.getSpace()) + "|" + String.format("%-5s", symbolTableEntry.getOffset())));
                symbolTableToStr(sb, memberConEntry.getFunctionTable(), "constructor" + memberConEntry.toOuputStr(), level + 1);
            } else if (symbolTableEntry instanceof MemberFuncEntry) {
                if (!entry.contains("(")) continue;
                MemberFuncEntry memberFuncEntry = (MemberFuncEntry) symbolTableEntry;
                sb.append(addBoundary(offset + " memberFunction | " + String.format("%-20s", memberFuncEntry.getName()) + " | " +
                        String.format("%-20s", memberFuncEntry.toOuputStr()) + " | " + memberFuncEntry.getVisibility().getVisStr() + "|" + String.format("%-5s", symbolTableEntry.getSpace()) + "|" + String.format("%-5s", symbolTableEntry.getOffset())));
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
                        + String.format("%-20s", memberVarEntry.getType() + dimsStr)  + " | " + String.format("%-7s", memberVarEntry.getVisibility().getVisStr()) + "|" + String.format("%-5s", symbolTableEntry.getSpace()) + "|" + String.format("%-5s", symbolTableEntry.getOffset())));
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
                        + String.format("%-23s", parameterEntry.getType() + dimsStr) + "|" + String.format("%-5s", symbolTableEntry.getSpace()) + "|" + String.format("%-5s", symbolTableEntry.getOffset())));
            } else if (symbolTableEntry instanceof TmpVarEntry) {
                TmpVarEntry tmpVarEntry = (TmpVarEntry) symbolTableEntry;
                sb.append(addBoundary(offset + " tmpvar         | " + String.format("%-20s", tmpVarEntry.getName()) + " | "
                        + String.format("%-23s", tmpVarEntry.getType()) + "|" + String.format("%-5s", symbolTableEntry.getSpace()) + "|" + String.format("%-5s", symbolTableEntry.getOffset())));
            }
        }
        sb.append(sepLine);
    }

    public static void main(String[] args) {
        try {
            CodeGeneration codeGeneration1 = new CodeGeneration("tests/CodeGeneration/example-bubblesort.src");
            // CodeGeneration codeGeneration2 = new CodeGeneration("tests/CodeGeneration/example-polynomial.src");
            // CodeGeneration codeGeneration3 = new CodeGeneration("tests/CodeGeneration/example-simplemain.src");
            codeGeneration1.run();
            // codeGeneration2.run();
            // codeGeneration3.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
