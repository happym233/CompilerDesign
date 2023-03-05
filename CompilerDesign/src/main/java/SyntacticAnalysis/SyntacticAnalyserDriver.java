package SyntacticAnalysis;

import java.io.IOException;

public class SyntacticAnalyserDriver {
    public static void main(String[] args) {
        try {
            SyntacticAnalyser syntacticAnalyser1 = new SyntacticAnalyser("tests/syntax/test1.src");
            SyntacticAnalyser syntacticAnalyser = new SyntacticAnalyser("tests/syntax/example-bubblesort.src");
            SyntacticAnalyser syntacticAnalyser2 = new SyntacticAnalyser("tests/syntax/example-polynomial.src");
            syntacticAnalyser1.parseAll();
            syntacticAnalyser.parseAll();
            syntacticAnalyser2.parseAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
