package SemanticAnalysis.Visitors;

import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import SemanticAnalysis.ASTVisitor;
import SemanticAnalysis.SemanticTreeNode;

public class LocationAssignVisitor extends ASTVisitor {

    @Override
    public void visit(SemanticTreeNode semanticTreeNode) {
        int minLocation = Integer.MAX_VALUE;

        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof SemanticTreeNode) {
                ((SemanticTreeNode) astTreeNode).accept(this);
            }
        }

        for (ASTTreeNode astTreeNode: semanticTreeNode.getChildren()) {
            if (astTreeNode instanceof ASTTreeLeaf) {
                minLocation = Math.min(minLocation, ((ASTTreeLeaf) astTreeNode).getToken().getLocation());
            }   else {
                minLocation = Math.min(minLocation, ((SemanticTreeNode)astTreeNode).getLocation());
            }
        }
        semanticTreeNode.setLocation(minLocation);
    }
}
