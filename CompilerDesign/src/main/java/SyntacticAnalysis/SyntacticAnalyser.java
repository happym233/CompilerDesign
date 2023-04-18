package SyntacticAnalysis;

import LexicalAnalyse.LexemeAnalyser;
import LexicalAnalyse.Token;
import LexicalAnalyse.TokenType;
import SemanticAnalysis.Visitors.ErrorLevel;
import SemanticAnalysis.Visitors.ErrorMessege;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class SyntacticAnalyser {
    private LexemeAnalyser lexemeAnalyser;
    private Stack<Symbol> stack;
    private ParsingTable parsingTable;
    private StringBuilder derivationStr;
    private PriorityQueue<ErrorMessege> errors;
    private StringBuilder parsedString;

    private Token lastToken;

    public SyntacticAnalyser(String fileName, ParsingTable parsingTable, String outDerivationFilePath,  String outSyntaxErrorFilePath) throws IOException {
        lexemeAnalyser = new LexemeAnalyser(fileName);
        this.parsingTable = parsingTable;
        stack = new Stack<>();
        derivationStr = new StringBuilder();
        errors = new PriorityQueue<>(Comparator.comparingInt(ErrorMessege::getLineNum));
        stack.push(new NonTerminalSymbol("$"));
        stack.push(parsingTable.getStartSymbol());
        // System.out.println(stack.isEmpty());
        parsedString = new StringBuilder();
    }


    public SyntacticAnalyser(String fileName, String outDerivationFilePath, String outSyntaxErrorFilePath) throws IOException {
        lexemeAnalyser = new LexemeAnalyser(fileName);
        parsingTable = ParsingTableFactory.generate(Config.getLL1GrammarPath(), Config.getParsingTablePath());
        stack = new Stack<>();
        derivationStr = new StringBuilder();
        errors = new PriorityQueue<>(Comparator.comparingInt(ErrorMessege::getLineNum));
        stack.push(new NonTerminalSymbol("$"));
        stack.push(parsingTable.getStartSymbol());
        // System.out.println(stack.isEmpty());
        parsedString = new StringBuilder();
    }

    public SyntacticAnalyser(String fileName) throws IOException {
        this(fileName, fileName.replace(".src", ".outderivation"), fileName.replace(".src", ".outsyntaxerrors"));
    }

    public SyntacticAnalyser(String fileName, ParsingTable parsingTable) throws IOException {
        this(fileName, parsingTable, fileName.replace(".src", ".outderivation"), fileName.replace(".src", ".outsyntaxerrors"));
    }

    private void writeSyntaxError(Token token) throws IOException {
        errors.add(new ErrorMessege(token.getLocation(), ErrorLevel.ERROR, "Syntax error at line " + token.getLocation() + " near \"" + token.getLexeme() + "\"."));
    }

    public ParsingTable getParsingTable() {
        return parsingTable;
    }

    public Stack<Symbol> getStack() {
        return stack;
    }

    public StringBuilder getParsedString() {
        return parsedString;
    }

    private void writeLexicalError(Token token) throws IOException {
        errors.add(new ErrorMessege(token.getLocation(), ErrorLevel.ERROR, token.errMessage() ));
    }

    protected void writeStackTrace() throws IOException {
        derivationStr.append("stack: \n");
        derivationStr.append(stackToString(stack) + "\n");
        derivationStr.append("input: \n");
        derivationStr.append(parsedString.toString() + "\n");
        derivationStr.append("rule: \n");
        derivationStr.append(parsingTable.getLastRule() + "\n");
        derivationStr.append("-----------------------------------------------------------------------\n");
    }

    public StringBuilder getDerivationStr() {
        return derivationStr;
    }

    public void setDerivationStr(StringBuilder derivationStr) {
        this.derivationStr = derivationStr;
    }

    public PriorityQueue<ErrorMessege> getErrors() {
        return errors;
    }

    public void setErrors(PriorityQueue<ErrorMessege> errors) {
        this.errors = errors;
    }

    private void writeSkippedToken(StringBuilder skippedToken) throws IOException {
        derivationStr.append("Syntax error detected, skipping token: \n");
        derivationStr.append(skippedToken.toString() + "\n");
    }

    protected String stackToString(Stack<Symbol> stack) {
        String str = "";
        for (Symbol s: stack) {
            str += s.toString() + " ";
        }
        return str;
    }

    private void addToken(StringBuilder s, Token token) {
        if (token == null) return;
        if (lastToken != null && token.getLocation() != lastToken.getLocation()) {
            s.append("\n");
        }
        s.append(token.getLexeme() + " ");
    }

    public void parseAll() {
        Token token = lexemeAnalyser.nextToken();
        try {
            if (token.isError()) {
                writeLexicalError(token);
            }
            addToken(parsedString, token);
            StringBuilder skippedToken = new StringBuilder();
            while (token != null) {
                boolean parseSuccess = parsingTable.parse(stack, token);
                if (parseSuccess) {
                    if (skippedToken.length() != 0) {
                        writeSkippedToken(skippedToken);
                        skippedToken = new StringBuilder();
                    }
                } else {
                    if (skippedToken.length() == 0) {
                        writeSyntaxError(token);
                    }
                }
                writeStackTrace();
                if (parsingTable.readNext()) {
                    if (!parseSuccess) {
                        addToken(skippedToken, token);
                    }
                    lastToken = token;
                    token = lexemeAnalyser.nextToken();
                    if (token != null && token.isError()) {
                        writeLexicalError(token);
                    }
                    addToken(parsedString, token);
                }
                if (token == null) {
                    token = new Token(TokenType.EOF, "EOF", lastToken.getLocation());
                    while (!stack.peek().isTerminal() && stack.size() != 1) {
                        parsingTable.parse(stack, token);
                    }
                    writeStackTrace();
                    if (stack.peek().isTerminal() || (!((NonTerminalSymbol) stack.peek()).getName().equals("$")))
                        writeSyntaxError(token);
                    token = null;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
