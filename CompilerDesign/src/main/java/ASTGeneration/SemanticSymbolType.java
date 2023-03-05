package ASTGeneration;

public enum SemanticSymbolType {
    CREATE_LEAF("createLeaf"),
    CREATE_NODE("createNode"),
    EPSILON("epsilon");

    public final String name;

    private SemanticSymbolType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
