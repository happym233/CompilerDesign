package SemanticAnalysis.SymbolTableEntry;

public enum Visibility {
    PRIVATE("private"),
    PUBLIC("public");

    private String visStr;
    private Visibility(String str) {
        this.visStr = str;
    }

    public String getVisStr() {
        return visStr;
    }

    public static Visibility createVisibility(String str) {
        switch (str) {
            case "private":
                return PRIVATE;
            default:
                return PUBLIC;
        }
    }
}
