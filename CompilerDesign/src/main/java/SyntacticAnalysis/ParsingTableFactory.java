package SyntacticAnalysis;

import LexicalAnalyse.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ParsingTableFactory {
    public static ParsingTable generate(String LL1GrammarPath, String parsingTablePath) throws FileNotFoundException {
        File LL1GrammarCSV = new File(LL1GrammarPath);
        File parsingTableCSV = new File(parsingTablePath);
        Scanner scanner = new Scanner(LL1GrammarCSV);
        Map<String, Rule> rules = new HashMap<>();
        Map<String, TerminalSymbol> terminalSymbols = new HashMap<>();
        Map<String, NonTerminalSymbol> nonTerminalSymbols = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] strs = line.split(",");
            String ruleName = strs[0].strip();
            NonTerminalSymbol nonTerminalSymbol = null;
            String nonTerName = strs[1].strip().substring(1);
            if (!nonTerminalSymbols.containsKey(nonTerName)) {
                nonTerminalSymbols.put(nonTerName, new NonTerminalSymbol(nonTerName));
            }
            nonTerminalSymbol = nonTerminalSymbols.get(nonTerName);
            Symbol[] ruleSymbols;
            if (strs.length == 3) {
                String strStrip = strs[2].strip();
                if (strStrip.equals("")) {
                    ruleSymbols = new Symbol[0];
                } else {
                    String[] symStrs = strStrip.split(" ");
                    ruleSymbols = new Symbol[symStrs.length];
                    for (int i = 0; i < symStrs.length; i++) {
                        if (symStrs[i].charAt(0) == '*') {
                            NonTerminalSymbol nts = null;
                            String ntsName = symStrs[i].substring(1);
                            if (!nonTerminalSymbols.containsKey(ntsName)) {
                                nonTerminalSymbols.put(ntsName, new NonTerminalSymbol(ntsName));
                            }
                            nts = nonTerminalSymbols.get(ntsName);
                            ruleSymbols[i] = nts;
                        } else {
                            TerminalSymbol ts = null;
                            String tsStr = symStrs[i].toLowerCase();
                            if (!terminalSymbols.containsKey(tsStr)) {
                                terminalSymbols.put(tsStr, new TerminalSymbol(TokenType.create(tsStr)));
                            }
                            ts = terminalSymbols.get(tsStr);
                            ruleSymbols[i] = ts;
                        }
                    }
                }
            } else {
                ruleSymbols = new Symbol[0];
            }
            Rule rule = new Rule(ruleName, nonTerminalSymbol, ruleSymbols);
            rules.put(ruleName,rule);
            // System.out.println(rule);
        }
        /*
        System.out.println(rules.keySet().size());
        for (String s: terminalSymbols.keySet().stream().sorted().collect(Collectors.toList())) {
            System.out.println(s);
        }
        for (String s: nonTerminalSymbols.keySet().stream().sorted().collect(Collectors.toList())) {
            System.out.println(s);
        }
         */
        ArrayList<Rule> tableRules = new ArrayList<>();
        Map<TokenType, Integer> tableTsMap = new HashMap<>();
        Map<NonTerminalSymbol, Integer> tableNtsMap = new HashMap<>();
        Map<Rule, Integer> tableRulesDict = new HashMap<>();
        List<List<Integer>> ll1TableArr = new ArrayList<>();
        Scanner tableScanner = new Scanner(parsingTableCSV);
        String header = tableScanner.nextLine();
        String[] tableTsStr = header.split(",");
        int start = -1;
        for (String str: tableTsStr) {
            String tsSymbol = str.strip();
            if (tsSymbol.contains("(")) {
                tsSymbol = tsSymbol.substring(0, tsSymbol.indexOf("("));
            }
            if (tsSymbol.equals("$")) tableTsMap.put(TokenType.EOF, start++);
            else if (terminalSymbols.get(tsSymbol) == null) {
                tableTsMap.put(null, start++);
            }
            else tableTsMap.put(terminalSymbols.get(tsSymbol).getType(), start++);
            // System.out.println(tsSymbol + ':' + start);
        }
        /*
        for (TerminalSymbol symbol: tableTsMap.keySet()) {
            System.out.println(symbol + ": " + tableTsMap.get(symbol));
        }
        */
        // 0 - scan, 1 - pop
        int nonTerminalStart = 0, ruleStart = 2;
        while (tableScanner.hasNextLine()) {
            String line = tableScanner.nextLine();
            String[] strs = line.split(",");
            String nonTsName = strs[0].strip();
            /*
            if (!nonTerminalSymbols.containsKey(nonTsName)) {
                System.out.println("error: " + nonTsName + " do not exist");
            }
            */
            tableNtsMap.put(nonTerminalSymbols.get(nonTsName), nonTerminalStart++);
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i].strip();
                // System.out.println(str);
                if (str.equals("scan")) list.add(0);
                else if (str.equals("pop")) list.add(1);
                else {
                    /*
                    if (!rules.containsKey(str)) {
                        System.out.println("rule: " + str + "do not exist");
                    }
                     */
                    Rule rule = rules.get(str);
                    if (!tableRulesDict.containsKey(rule)) {
                        tableRulesDict.put(rule, ruleStart++);
                        tableRules.add(rule);
                    }
                    list.add(tableRulesDict.get(rule));
                }
            }
            ll1TableArr.add(list);
        }
        int[][] ll1Table = ll1TableArr.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        /*
        for (int i = 0; i < ll1Table.length; i++) {
            for (int j = 0; j < ll1Table[0].length; j++) {
                System.out.print(ll1Table[i][j] + " ");
            }
            System.out.println();
        }
        */
        return new ParsingTable(tableRules, tableTsMap, tableNtsMap, ll1Table, nonTerminalSymbols.get("START"));
    }

    public static void main(String[] args) {
        try {
            ParsingTableFactory.generate("resource/syntacticAnalysis/LL1Grammar.csv",
                    "resource/syntacticAnalysis/parsingTable.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
