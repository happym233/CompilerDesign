package SyntacticAnalysis;

import ASTGeneration.SemanticActionSymbol;
import ASTGeneration.nodes.ASTTreeNode;
import LexicalAnalyse.Token;
import LexicalAnalyse.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParsingTable {
    private static int SCAN_NUM = 0;
    private static int POP_NUM = 1;
    private ArrayList<Rule> tableRules;
    private Map<TokenType, Integer> tableTsMap;
    private Map<NonTerminalSymbol, Integer> tableNtsMap;
    private int[][] ll1Table;

    private String lastRule;

    private NonTerminalSymbol startSymbol;

    private boolean readNext;

    private Token lastToken;

    public ParsingTable(ArrayList<Rule> tableRules, Map<TokenType, Integer> tableTsMap, Map<NonTerminalSymbol, Integer> tableNtsMap, int[][] ll1Table, NonTerminalSymbol startSymbol) {
        this.tableRules = tableRules;
        this.tableTsMap = tableTsMap;
        this.tableNtsMap = tableNtsMap;
        this.ll1Table = ll1Table;
        this.lastRule = null;
        this.startSymbol = startSymbol;
        this.readNext = true;
        this.lastToken = null;
    }

    public String getLastRule() {
        return lastRule;
    }

    public NonTerminalSymbol getStartSymbol() { return startSymbol; }

    public boolean readNext() {
        return readNext;
    }

    protected void doSemanticAction(SemanticActionSymbol s, Token lastToken) {}

    public boolean parse(Stack<Symbol> stack, Token token) {
        Symbol s = stack.peek();
        if (s.isTerminal()) {
            TerminalSymbol symbol = (TerminalSymbol) stack.pop();
            readNext = true;
            lastRule = "matching: " + symbol + " " + token;
            lastToken = token;
            return true;
        } else if (s.isSemanticAction()) {
            doSemanticAction((SemanticActionSymbol) s, lastToken);
            stack.pop();
            return parse(stack, token);
        } else {
            NonTerminalSymbol nts = (NonTerminalSymbol) s;
            if (nts.getName() == "$") {
                return true;
            }
            TokenType tokenType = token.getType();
            lastToken = token;
            if (tokenType == TokenType.SELF || tokenType == TokenType.INVALID_ID) {
                tokenType = TokenType.ID;
            }
            else if (tokenType == TokenType.INVALID_NUM) tokenType = TokenType.INT_NUM;
            else if (tokenType == TokenType.INVALID_CHAR || tokenType == TokenType.INVALID_CMT || tokenType == TokenType.BK_CMT || tokenType == TokenType.IN_CMT) {
                readNext = true;
                lastRule = "skipping";
                return true;
            }
            int ruleIdx = ll1Table[tableNtsMap.get(nts)][tableTsMap.get(tokenType)];
            if (ruleIdx == SCAN_NUM) {
                readNext = true;
                lastRule = "scan";
                return false;
            } else if (ruleIdx == POP_NUM) {
                readNext = false;
                stack.pop();
                lastRule = "pop";
                return false;
            } else {
                // System.out.println(stack.peek() + " " + token);
                Rule rule = tableRules.get(ruleIdx - 2);
                lastRule = rule.toString();
                // System.out.println(rule);
                Symbol[] symbols = rule.getSymbols();
                stack.pop();
                for (int i = symbols.length - 1; i >= 0; i--) {
                    stack.push(symbols[i]);
                }
                readNext = false;
                return true;
            }
        }
    }

    public ArrayList<Rule> getTableRules() {
        return tableRules;
    }

    public Map<TokenType, Integer> getTableTsMap() {
        return tableTsMap;
    }

    public Map<NonTerminalSymbol, Integer> getTableNtsMap() {
        return tableNtsMap;
    }

    public int[][] getLl1Table() {
        return ll1Table;
    }


}
