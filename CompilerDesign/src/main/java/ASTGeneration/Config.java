package ASTGeneration;

public class Config {
    private static String AttributeGrammar = "resource/ASTGeneration/AttributeGrammar.csv";
    private static String parsingTablePath = "resource/syntacticAnalysis/parsingTable.csv";

    public static String getAttributeGrammar() { return AttributeGrammar; }
    public static String getParsingTablePath() { return parsingTablePath; }
}
