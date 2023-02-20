package SyntacticAnalysis;

public class Rule {
    private String name;
    private NonTerminalSymbol nonTerminalSymbol;
    private Symbol[] symbols;

    public Rule(String name, NonTerminalSymbol nonTerminalSymbol, Symbol[] symbols) {
        this.name = name;
        this.nonTerminalSymbol = nonTerminalSymbol;
        this.symbols = symbols;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NonTerminalSymbol getNonTerminalSymbol() {
        return nonTerminalSymbol;
    }

    public void setNonTerminalSymbol(NonTerminalSymbol nonTerminalSymbol) {
        this.nonTerminalSymbol = nonTerminalSymbol;
    }

    public Symbol[] getSymbols() {
        return symbols;
    }

    public void setSymbols(Symbol[] symbols) {
        this.symbols = symbols;
    }

    @Override
    public String toString() {
        String str =  nonTerminalSymbol.toString() + ": ";
        for (Symbol symbol: symbols) {
            str += symbol.toString() + " ";
        }
        return str;
    }
}
