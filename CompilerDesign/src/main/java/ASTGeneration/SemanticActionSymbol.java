package ASTGeneration;

import SyntacticAnalysis.Symbol;

public class SemanticActionSymbol implements Symbol {
    private SemanticSymbolType semanticSymbolType;
    private String name;
    // -1: popUntilEpsilon, 0: create leaf, >= 0: create node
    private int numOfPop;

    public SemanticActionSymbol(SemanticSymbolType semanticSymbolType, String name, int numOfPop) {
        this.semanticSymbolType = semanticSymbolType;
        this.name = name;
        this.numOfPop = numOfPop;
    }

    public SemanticSymbolType getSemanticSymbolType() {
        return semanticSymbolType;
    }

    public int getNumOfPop() {
        return numOfPop;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isSemanticAction() {
        return true;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }


    @Override
    public String toString() {
        return "[" + semanticSymbolType.toString() + ": " + name + "]";
    }
}
