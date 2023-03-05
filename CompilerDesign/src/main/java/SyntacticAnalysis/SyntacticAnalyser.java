package SyntacticAnalysis;

import LexicalAnalyse.LexemeAnalyser;
import LexicalAnalyse.Token;
import LexicalAnalyse.TokenType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class SyntacticAnalyser {
    private LexemeAnalyser lexemeAnalyser;
    private Stack<Symbol> stack;
    private ParsingTable parsingTable;
    private FileWriter outDerivationWriter;
    private FileWriter outSyntaxErrorWriter;
    private StringBuilder parsedString;

    private Token lastToken;

    public SyntacticAnalyser(String fileName, ParsingTable parsingTable, String outDerivationFilePath,  String outSyntaxErrorFilePath) throws IOException {
        lexemeAnalyser = new LexemeAnalyser(fileName);
        this.parsingTable = parsingTable;
        stack = new Stack<>();
        outDerivationWriter = new FileWriter(outDerivationFilePath);
        outSyntaxErrorWriter = new FileWriter(outSyntaxErrorFilePath);
        stack.push(new NonTerminalSymbol("$"));
        stack.push(parsingTable.getStartSymbol());
        // System.out.println(stack.isEmpty());
        parsedString = new StringBuilder();
    }


    public SyntacticAnalyser(String fileName, String outDerivationFilePath, String outSyntaxErrorFilePath) throws IOException {
        lexemeAnalyser = new LexemeAnalyser(fileName);
        parsingTable = ParsingTableFactory.generate(Config.getLL1GrammarPath(), Config.getParsingTablePath());
        stack = new Stack<>();
        outDerivationWriter = new FileWriter(outDerivationFilePath);
        outSyntaxErrorWriter = new FileWriter(outSyntaxErrorFilePath);
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
        outSyntaxErrorWriter.write("Syntax error at line " + token.getLocation() + " near \"" + token.getLexeme() + "\".\n");
    }

    public ParsingTable getParsingTable() {
        return parsingTable;
    }

    public Stack<Symbol> getStack() {
        return stack;
    }

    public FileWriter getOutDerivationWriter() {
        return outDerivationWriter;
    }

    public StringBuilder getParsedString() {
        return parsedString;
    }

    private void writeLexicalError(Token token) throws IOException {
        outSyntaxErrorWriter.write(token.errMessage() + "\n");
    }

    protected void writeStackTrace() throws IOException {
        outDerivationWriter.write("stack: \n");
        outDerivationWriter.write(stackToString(stack) + "\n");
        outDerivationWriter.write("input: \n");
        outDerivationWriter.write(parsedString.toString() + "\n");
        outDerivationWriter.write("rule: \n");
        outDerivationWriter.write(parsingTable.getLastRule() + "\n");
        outDerivationWriter.write("-----------------------------------------------------------------------\n");
    }

    private void writeSkippedToken(StringBuilder skippedToken) throws IOException {
        outDerivationWriter.write("Syntax error detected, skipping token: \n");
        outDerivationWriter.write(skippedToken.toString() + "\n");
    }

    private void close() throws IOException {
        outDerivationWriter.close();
        outSyntaxErrorWriter.close();
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
            close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
