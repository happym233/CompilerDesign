package SyntacticAnalysis;

import ASTGeneration.SemanticActionSymbol;
import ASTGeneration.SemanticSymbolType;

import java.util.HashSet;

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

    /*
    public void toReportString(StringBuilder a, StringBuilder b, HashSet<String> s) {
        a.append(nonTerminalSymbol.toString() + " ::= ");
        if (symbols.length == 0) {
            a.append("$\\epsilon$\n");
        } else {
            for (Symbol symbol: symbols) {
                if (symbol instanceof TerminalSymbol || symbol instanceof NonTerminalSymbol) {
                    a.append(symbol.toString() + " ");
                } else {
                    a.append("[" + ((SemanticActionSymbol)symbol).getName() + "] ");
                    SemanticActionSymbol semanticActionSymbol = (SemanticActionSymbol) symbol;
                    if (!s.contains(semanticActionSymbol.getName())) {
                        s.add(semanticActionSymbol.getName());
                        if (semanticActionSymbol.getSemanticSymbolType() == SemanticSymbolType.EPSILON) {
                            b.append("[" + semanticActionSymbol.getName() + "]: " + "push($\\epsilon$)\n");
                        } else if (semanticActionSymbol.getSemanticSymbolType() == SemanticSymbolType.CREATE_LEAF) {
                            b.append("[" + semanticActionSymbol.getName() + "]: " + "push(createLeaf(" + semanticActionSymbol.getName() + "))\n");
                        } else {
                            if (semanticActionSymbol.getNumOfPop() < 0) {
                                b.append("[" + semanticActionSymbol.getName() + "]: " + "push(createSubtree(" + semanticActionSymbol.getName() + ", popuntil$\\epsilon$" + "))\n");
                            } else {
                                b.append("[" + semanticActionSymbol.getName() + "]: " + "push(createSubtree(" + semanticActionSymbol.getName());
                                for (int i = 0; i < semanticActionSymbol.getNumOfPop(); i++) {
                                    b.append(", pop");
                                }
                                b.append("))\n");
                            }
                        }
                    }
                }
            }
            a.append("\n");
        }
    }
     */

    @Override
    public String toString() {
        String str =  nonTerminalSymbol.toString() + ": ";
        for (Symbol symbol: symbols) {
            str += symbol.toString() + " ";
        }
        return str;
    }
}
