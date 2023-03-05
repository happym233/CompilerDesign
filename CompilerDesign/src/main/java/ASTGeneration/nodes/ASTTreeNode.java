package ASTGeneration.nodes;

public abstract class ASTTreeNode implements ASTSymbol {
    private String name;

    public ASTTreeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDotStr() {
        return null;
    }

    public String toASTString(String prefix) {
        return null;
    }
}
