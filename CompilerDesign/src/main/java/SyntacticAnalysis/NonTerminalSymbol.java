package SyntacticAnalysis;

import LexicalAnalyse.TokenType;

public class NonTerminalSymbol implements Symbol{
    private String name;

    public NonTerminalSymbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTerminal() {
        return false;
    }

    public boolean isSemanticAction() {
        return false;
    }

    @Override
    public String toString() {
        return "<" + name + ">";
    }
}
