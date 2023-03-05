package ASTGeneration.nodes;

public class ASTTreeParent extends ASTTreeNode{
    private ASTTreeNode[] children;

    public ASTTreeParent(String name, ASTTreeNode[] children) {
        super(name);
        this.children = children;
    }

    public ASTTreeNode[] getChildren() {
        return children;
    }

    @Override
    public String getDotStr() {
        return getName();
    }

    @Override
    public String toASTString (String prefix) {
        String str = "";
        str += prefix + this.getName();
        if (children.length == 0) {
            str += "\n" + prefix + "|.";
        } else {
            for (ASTTreeNode child : children) {
                str += "\n" + child.toASTString("|" + prefix);
            }
        }
        return str;
    }
}
