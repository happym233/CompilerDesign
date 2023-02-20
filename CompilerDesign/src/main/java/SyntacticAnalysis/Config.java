package SyntacticAnalysis;

public class Config {
    private static String LL1GrammarPath = "resource/syntacticAnalysis/LL1Grammar.csv";
    private static String parsingTablePath = "resource/syntacticAnalysis/parsingTable.csv";

    public static String getLL1GrammarPath() { return LL1GrammarPath; }
    public static String getParsingTablePath() { return parsingTablePath; }
}
