package ASTGeneration;

public class SemanticActionSymbolFactory {
    public static SemanticActionSymbol create(String str) {
        str = str.substring(1, str.length() - 1);
        if (str.startsWith("!")) return new SemanticActionSymbol(SemanticSymbolType.EPSILON, "epsilon", 0);
        else if (str.startsWith("#")) return new SemanticActionSymbol(SemanticSymbolType.CREATE_LEAF, str.substring(1), 1);
        else {
            String[] strs = str.split("\\|");
            return new SemanticActionSymbol(SemanticSymbolType.CREATE_NODE, strs[0].strip(), Integer.valueOf(strs[1]));
        }
    }
}
