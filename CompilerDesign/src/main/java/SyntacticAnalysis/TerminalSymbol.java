package SyntacticAnalysis;

import LexicalAnalyse.Token;
import LexicalAnalyse.TokenType;

public class TerminalSymbol implements Symbol{
    private TokenType type;

    public TerminalSymbol(TokenType type) {
        this.type = type;
    }

    public boolean isTerminal() {
        return true;
    }

    public boolean isSemanticAction() {
        return false;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.tokenTypeStr;
    }
}
