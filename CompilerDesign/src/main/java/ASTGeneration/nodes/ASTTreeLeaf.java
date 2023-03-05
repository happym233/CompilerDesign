package ASTGeneration.nodes;

import LexicalAnalyse.Token;
import LexicalAnalyse.TokenType;

public class ASTTreeLeaf extends ASTTreeNode {
    private Token token;

    public ASTTreeLeaf(String name, Token token) {
       super(name);
       this.token = token;
    }

    @Override
    public String getDotStr() {
        if (token.getType() == TokenType.ID || token.getType() == TokenType.FLOAT_NUM || token.getType() == TokenType.INT_NUM)
            return token.getType().tokenTypeStr + "|" + token.getLexeme();
        else return token.getType().tokenTypeStr;
    }

    @Override
    public String toASTString(String prefix) {
        return prefix + "[" + token.getLexeme() + "]";
    }
}
