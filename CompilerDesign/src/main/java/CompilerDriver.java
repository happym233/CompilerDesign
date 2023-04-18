import ASTGeneration.ASTGenerator;
import CodeGeneration.CodeGeneration;
import LexicalAnalyse.LexemeAnalyser;
import LexicalAnalyse.Token;
import SemanticAnalysis.SemanticAnalyser;
import SemanticAnalysis.Visitors.ErrorLevel;
import SemanticAnalysis.Visitors.ErrorMessege;
import SyntacticAnalysis.SyntacticAnalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CompilerDriver {
    public static String getUsage() {
        return "Usage: CompilerDriver fileName [+t]\n";
    }

    public static void writeOutput(String fileName, PriorityQueue<ErrorMessege> synErrors, PriorityQueue<ErrorMessege> semErrors) throws IOException {
        String errFilePath = fileName.replace(".src", ".outerrors");
        FileWriter errFileWriter = new FileWriter(errFilePath);
        ArrayList<String> errors = new ArrayList<>(synErrors.size() + semErrors.size());
        int i = 1,  cnt = 1, cntSem = synErrors.size() + 1;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (!semErrors.isEmpty() && semErrors.peek().getLineNum() == 0) {
            errFileWriter.write("//" + cntSem++ + "\n");
            errors.add("[Semantic]" + semErrors.remove().toString());
        }
        String line = reader.readLine();
        while (line != null) {
            if (line == null) break;
            errFileWriter.write(line);
            // System.out.println(synErrors.peek().getLineNum());
            // System.out.println(i);
            while (!synErrors.isEmpty() && synErrors.peek().getLineNum() == i) {
                if (synErrors.peek().getMessage().startsWith("Syntax")) {
                    errors.add(cnt - 1, "[Syntax]" + synErrors.remove().toString());
                    errFileWriter.write("// " + cnt++);
                } else {
                    errors.add(cnt - 1, "[Lexical]" + synErrors.remove().toString());
                    errFileWriter.write("// " + cnt++);
                }
            }
            if (!semErrors.isEmpty() && semErrors.peek().getLineNum() == i) {
                errors.add(errors.size(), "[Semantic]" + semErrors.remove().toString());
                errFileWriter.write(" // " + cntSem++);
                while (!semErrors.isEmpty() && semErrors.peek().getLineNum() == i) {
                    semErrors.remove();
                }
            }
            errFileWriter.write("\n");
            i++;
            line = reader.readLine();
        }
        for (int j = 0; j < errors.size(); j++) {
            errFileWriter.write("// " + (j + 1) + ": " + errors.get(j) + "\n");
        }
        errFileWriter.close();
    }


    public static void writeCodeGenerationOutput(String fileName, CodeGeneration codeGeneration) throws IOException {
        String outputFilePath = fileName.replace(".src", ".m");
        FileWriter moonFileWriter = new FileWriter(outputFilePath);
        codeGeneration.run();
        moonFileWriter.write(codeGeneration.getAssemblyCode());
        moonFileWriter.close();
    }


    public static void runLexical(StringBuilder out, StringBuilder err, LexemeAnalyser lexemeAnalyser) throws IOException {
        int line = 1;
        boolean k = false;
        Token t;
        while ((t = lexemeAnalyser.nextToken()) != null) {
            if (t.getLocation() != line) {
                out.append("\n");
                line = t.getLocation();
            } else if (k) out.append(" ");
            k = true;
            out.append(t.toString());
            if (t.isError()) err.append(t.errMessage() + "\n");
        }
    }

    public static String errorPQToString(PriorityQueue<ErrorMessege> errorMesseges) {
        String res = "";
        for (ErrorMessege errorMessege: errorMesseges) {
            res += errorMessege + "\n";
        }
        return res;
    }

    public static String errorPQPerLineToString(PriorityQueue<ErrorMessege> errorMesseges) {
        String res = "";
        int i = -1;
        for (ErrorMessege errorMessege: errorMesseges) {
            if (errorMessege.getLineNum() > i) {
                i = errorMessege.getLineNum();
                res += errorMessege + "\n";
            }
        }
        return res;
    }

    public static void runDebugMode(String fileName) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("debugMode");
        System.out.println("======================================================================================");
        System.out.println("Lexical Analysing");
        LexemeAnalyser lexemeAnalyser = new LexemeAnalyser(fileName);
        StringBuilder lexicalOut = new StringBuilder();
        StringBuilder lexicalErr = new StringBuilder();
        runLexical(lexicalOut, lexicalErr, lexemeAnalyser);
        System.out.println("printing to console? [y]/[n]");
        String text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Lexical output: ");
            System.out.println(lexicalOut);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Lexical error: ");
            System.out.println(lexicalErr);
            System.out.println("--------------------------------------------------------------------------------------");
        }
        System.out.println("printing to file? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            String outputFilePath = fileName.replace(".src", ".outlextokens");
            String errFilePath = fileName.replace(".src", ".outlexerrors");
            FileWriter fileWriter = new FileWriter(outputFilePath);
            FileWriter errWriter = new FileWriter(errFilePath);
            fileWriter.write(lexicalOut.toString());
            errWriter.write(lexicalErr.toString());
            fileWriter.close();
            errWriter.close();
        }
        if (lexicalErr.length() != 0) {
            System.out.println("Lexical Error detected.");
            return;
        }
        System.out.println("stop? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            return;
        }
        System.out.println("======================================================================================");
        System.out.println("Syntactic Analysing");
        SyntacticAnalyser syntacticAnalyser = new SyntacticAnalyser(fileName);
        syntacticAnalyser.parseAll();
        StringBuilder outDeriv = syntacticAnalyser.getDerivationStr();
        PriorityQueue<ErrorMessege> errors = syntacticAnalyser.getErrors();
        System.out.println("printing to console? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Syntax derivation output: ");
            System.out.println(outDeriv);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Syntax error: ");
            System.out.println(errorPQToString(errors));
            System.out.println("--------------------------------------------------------------------------------------");
        }
        System.out.println("printing to file? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            String outputFilePath = fileName.replace(".src", ".outderivation");
            String errFilePath = fileName.replace(".src", ".outsyntaxerrors");
            FileWriter fileWriter = new FileWriter(outputFilePath);
            FileWriter errWriter = new FileWriter(errFilePath);
            fileWriter.write(outDeriv.toString());
            errWriter.write(errorPQToString(errors));
            fileWriter.close();
            errWriter.close();
        }
        if (errors.size() != 0) {
            System.out.println("Syntax Error detected.");
            return;
        }
        System.out.println("stop? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            return;
        }
        System.out.println("======================================================================================");
        System.out.println("AST Generation");
        ASTGenerator astGenerator = new ASTGenerator(fileName);
        String astStr = astGenerator.getRoot().toASTString("");
        System.out.println("printing to console? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("generated AST: ");
            System.out.println(astStr);
            System.out.println("--------------------------------------------------------------------------------------");
        }
        System.out.println("printing to file? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            String outputFilePath = fileName.replace(".src", ".outast");
            FileWriter fileWriter = new FileWriter(outputFilePath);
            fileWriter.write(astStr);
            fileWriter.close();
        }
        System.out.println("stop? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            return;
        }

        System.out.println("======================================================================================");
        System.out.println("Semantic Analysis");
        SemanticAnalyser semanticAnalyser = new SemanticAnalyser(fileName);
        semanticAnalyser.run();
        String outSymTable = semanticAnalyser.getSymbolTableStr();
        PriorityQueue<ErrorMessege> errorMesseges = semanticAnalyser.getErrorMesseges();
        System.out.println("printing to console? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("generated symbol table: ");
            System.out.println(outSymTable);
            System.out.println("semantic errors: ");
            System.out.println(errorPQPerLineToString(errorMesseges));
            System.out.println("--------------------------------------------------------------------------------------");
        }
        System.out.println("printing to file? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            String outputFilePath = fileName.replace(".src", ".outsymboltable");
            String outputErrorPath = fileName.replace(".src", ".outsemanticerrors");
            FileWriter fileWriter = new FileWriter(outputFilePath);
            FileWriter errorWrite = new FileWriter(outputErrorPath);
            fileWriter.write(outSymTable);
            errorWrite.write(errorPQPerLineToString(errorMesseges));
            fileWriter.close();
            errorWrite.close();
        }
        if (errorMesseges.size() != 0) {
            System.out.println("Semantic error detected");
            return;
        }
        System.out.println("stop? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            return;
        }

        System.out.println("======================================================================================");
        System.out.println("Code Generation");
        CodeGeneration codeGeneration = new CodeGeneration(fileName);
        codeGeneration.run();
        String codeSymTableStr = codeGeneration.getTableStr();
        String outAssembly = codeGeneration.getAssemblyCode();
        System.out.println("printing to console? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("generated symbol table: ");
            System.out.println(codeSymTableStr);
            System.out.println("Assembly: ");
            System.out.println(outAssembly);
            System.out.println("--------------------------------------------------------------------------------------");
        }
        System.out.println("printing to file? [y]/[n]");
        text = scan.next().strip();
        if (text.toLowerCase().equals("y")) {
            String outputTablePath = fileName.replace(".src", ".outsymboltable");
            String outputAssemblyPath = fileName.replace(".src", ".m");
            FileWriter outTableWriter = new FileWriter(outputTablePath);
            FileWriter outAssemblyWriter = new FileWriter(outputAssemblyPath);
            outTableWriter.write(codeSymTableStr);
            outAssemblyWriter.write(outAssembly);
            outTableWriter.close();
            outAssemblyWriter.close();
        }
        System.out.println("Complete!");
    }

    public static void main(String[] args) {
        boolean debugMod = false;
        if (args.length == 0 || args.length > 2) {
            throw new IllegalArgumentException("Input file required\n" + getUsage());
        } else if (args.length == 2) {
            if (!args[1].equals("+t")) {
                throw new IllegalArgumentException(getUsage());
            } else {
                debugMod = true;
            }
        }
        try {
            String fileName = args[0];
            if (!debugMod) {
                CodeGeneration codeGeneration = new CodeGeneration(fileName);
                codeGeneration.runSemanticAnalyser();
                PriorityQueue<ErrorMessege> synErrors = codeGeneration.getSemanticAnalyser().getAstGenerator().getErrors();
                PriorityQueue<ErrorMessege> semErrors = codeGeneration.getSemanticAnalyser().getErrorMesseges();
                boolean hasErrors = false;
                for (ErrorMessege errorMessege: synErrors) {
                    if (errorMessege.getErrorLevel() == ErrorLevel.ERROR) {
                        hasErrors = true;
                        break;
                    }
                }

                for (ErrorMessege errorMessege: semErrors) {
                    if (errorMessege.getErrorLevel() == ErrorLevel.ERROR) {
                        hasErrors = true;
                        break;
                    }
                }
                if (hasErrors) {
                    writeOutput(fileName, synErrors, semErrors);
                } else {
                    writeCodeGenerationOutput(fileName, codeGeneration);
                }
            } else {
                runDebugMode(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
