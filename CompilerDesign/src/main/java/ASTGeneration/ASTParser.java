package ASTGeneration;

import ASTGeneration.nodes.*;
import LexicalAnalyse.Token;
import SyntacticAnalysis.ParsingTable;

import java.util.Stack;

public class ASTParser extends ParsingTable {
    private Stack<ASTSymbol> ASTNodeStack;

    public ASTParser(ParsingTable parsingTable) {
        super(parsingTable.getTableRules(), parsingTable.getTableTsMap(), parsingTable.getTableNtsMap(), parsingTable.getLl1Table(), parsingTable.getStartSymbol());
        ASTNodeStack = new Stack<>();
    }

    @Override
    public void doSemanticAction(SemanticActionSymbol s, Token lastToken) {
        if (s.getSemanticSymbolType() == SemanticSymbolType.EPSILON) {
            ASTNodeStack.push(ASTEpsilon.getInstance());
        } else if (s.getSemanticSymbolType() == SemanticSymbolType.CREATE_LEAF) {
            ASTNodeStack.push(new ASTTreeLeaf(s.getName(), lastToken));
        } else {
            Stack<ASTTreeNode> childrenStack = new Stack<>();
            if (s.getNumOfPop() >= 0) {
                for (int i = 0; i < s.getNumOfPop(); i++) {
                    childrenStack.push((ASTTreeNode) ASTNodeStack.pop());
                }
            } else {
                while (ASTNodeStack.peek() != ASTEpsilon.getInstance()) {
                    childrenStack.push((ASTTreeNode) ASTNodeStack.pop());
                }
                ASTNodeStack.pop();
            }
            ASTTreeNode[] children = new ASTTreeNode[childrenStack.size()];
            for (int i = 0; i < children.length; i++) {
                children[i] = childrenStack.pop();
            }
            ASTTreeParent parent = new ASTTreeParent(s.getName(), children);
            ASTNodeStack.push(parent);
        }
    }

    public Stack<ASTSymbol> getASTNodeStack() {
        return ASTNodeStack;
    }
}
