package LexicalAnalyse;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class LexemeAnalyser {
    private static int winEOF = 0xffff;
    private RandomAccessFile raf;
    private long filePointer;

    private LinkedList<Character> buffer;

    private int lineNum = 1;

    private char lastCharacter;

    private static Map<String, TokenType> reservedWords = new HashMap<String, TokenType>() {{
        put("and", TokenType.AND);
        put("or", TokenType.OR);
        put("not", TokenType.NOT);
        put("integer", TokenType.INTEGER);
        put("float", TokenType.FLOAT);
        put("void", TokenType.VOID);
        put("class", TokenType.CLASS);
        put("self", TokenType.SELF);
        put("isa", TokenType.ISA);
        put("while", TokenType.WHILE);
        put("if", TokenType.IF);
        put("then", TokenType.THEN);
        put("else", TokenType.ELSE);
        put("read", TokenType.READ);
        put("write", TokenType.WRITE);
        put("return", TokenType.RETURN);
        put("localvar", TokenType.LOCAL_VAR);
        put("constructor", TokenType.CONSTRUCTOR);
        put("attribute", TokenType.ATTRIBUTE);
        put("function", TokenType.FUNCTION);
        put("private", TokenType.PRIVATE);
        put("public", TokenType.PUBLIC);
    }};

    public LexemeAnalyser(String fileName) throws IOException {
        raf = new RandomAccessFile(fileName, "r");
        filePointer = raf.getFilePointer();
    }

    private char nextChar() throws IOException {
        char c = (char) raf.read();
        lastCharacter = c;
        if (!isEOF(c) && !isSpace(c)) buffer.add(c);
        if (c == '\n') lineNum++;
        filePointer++;
        return c;
    }

    private void backtrack() throws IOException {
        if (!isEOF(lastCharacter) && !isSpace(lastCharacter)) {
            filePointer--;
            buffer.removeLast();
            raf.seek(filePointer);
        }
    }

    private Token createToken(TokenType tokenType) {
        StringBuilder sb = new StringBuilder();
        for (char c: buffer) sb.append(c);
        return new Token(tokenType, sb.toString(), lineNum);
    }

    private Token createToken(TokenType tokenType, String s, int lineNum) {
        return new Token(tokenType, s, lineNum);
    }

    private Token createWordToken() {
        StringBuilder sb = new StringBuilder();
        for (char c: buffer) sb.append(c);
        String w = sb.toString();
        if (reservedWords.containsKey(w.toLowerCase())) return new Token(reservedWords.get(w.toLowerCase()), w, lineNum);
        else return new Token(TokenType.ID, w, lineNum);
    }
    private boolean isSpace(char c) {
        return c <= 32;
    }

    private void skipSpace() throws IOException {
        char c = ' ';
        while (isSpace(c)) {
            c = (char) raf.read();
            lastCharacter = c;
            if (c == '\n') lineNum++;
            filePointer++;
        }
        if (!isEOF(lastCharacter) && !isSpace(lastCharacter)) {
            filePointer--;
            raf.seek(filePointer);
        }
    }

    private boolean isEOF(char c) {
        return c == winEOF;
    }
    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || ( c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isNonZeroDigit(char c) {
        return c >= '1' && c <= '9';
    }

    private boolean isAlphaNum(char c) {
        return  isLetter(c) || isDigit(c) || c == '_';
    }

    private boolean toSpecialCharacter() throws IOException {
        if (!isAlphaNum(lastCharacter) || isEOF(lastCharacter)) return false;
        else {
            while (!isEOF(lastCharacter) && isAlphaNum(lastCharacter)) {
                nextChar();
            }
            return true;
        }
    }

    private Token nextId() throws IOException {
        char c = lastCharacter;
        if (isLetter(c)) {
            while (!isEOF(c) && isAlphaNum(c)) {
                c = nextChar();
            }
        }
        if (toSpecialCharacter()) return createToken(TokenType.INVALID_ID);
        else {
            backtrack();
            return createWordToken();
        }
    }

    private void nextInteger() throws IOException {
        if (lastCharacter == '0'){
            nextChar();
            backtrack();
            return;
        }
        char c = nextChar();
        while (!isEOF(c) && isDigit(c)) {
            c = nextChar();
        }
        backtrack();
    }

    private boolean nextFraction() throws IOException {
        nextChar(); //.
        nextChar(); //first character after .
        if (isDigit(lastCharacter)) {
            char prev = lastCharacter, c = nextChar();
            boolean f = true;
            while (!isEOF(c) && isDigit(c)) {
                f = false;
                prev = c;
                c = nextChar();
            }
            backtrack();
            if (prev == '0' && !f) {
                return false;
            } else {
                return true;
            }
        } else {
            // not digit, return false
            backtrack();
            return false;
        }
    }

    private Token nextNum() throws IOException {
        boolean validNum = true;
        nextInteger();
        if (lastCharacter == '.') {
            validNum = validNum & nextFraction();
            if (validNum && lastCharacter == 'e') {
                nextChar();
                nextChar();
                if (isDigit(lastCharacter)) {
                    nextInteger();
                } else if (lastCharacter == '+' || lastCharacter == '-') {
                    nextChar();
                    if (isDigit(lastCharacter)) {
                        nextInteger();
                    } else {
                        validNum = false;
                    }
                } else {
                    validNum = false;
                }
            }
            validNum = validNum & !toSpecialCharacter();
            if (validNum) return createToken(TokenType.FLOAT_NUM);
        } else {
            validNum = validNum & !toSpecialCharacter();
            if (validNum) return createToken(TokenType.INT_NUM);
        }
        return createToken(TokenType.INVALID_NUM);
    }

    private Token nextEq() throws IOException {
        nextChar();
        if (lastCharacter == '=') return createToken(TokenType.EQ);
        else if (lastCharacter == '>') return createToken(TokenType.RE_TYPE);
        else {
            backtrack();
            return createToken(TokenType.ASSIGN);
        }
    }

    private Token nextLt() throws IOException {
        nextChar();
        if (lastCharacter == '=') return createToken(TokenType.LEQ);
        else if (lastCharacter == '>') return createToken(TokenType.NOT_EQ);
        else {
            backtrack();
            return createToken(TokenType.LT);
        }
    }

    private Token nextGt() throws IOException {
        nextChar();
        if (lastCharacter == '=') return createToken(TokenType.GEQ);
        else {
            backtrack();
            return createToken(TokenType.GT);
        }
    }

    private Token nextColon() throws IOException {
        nextChar();
        if (lastCharacter == ':') return createToken(TokenType.SCOPEOP);
        else {
            backtrack();
            return createToken(TokenType.COLON);
        }
    }

    private void putSpecialCharacter(StringBuilder sb, char c) {
        if (isEOF(c)) return;
        if (isSpace(c)) {
            if(c == 10) sb.append("\n");
            else sb.append(" ");
        } else sb.append(c);
    }

    private void stateA(Stack<Character> stack, StringBuilder sb) throws IOException {
        nextChar();
        while (lastCharacter != '*' && lastCharacter != '/') {
            nextChar();
            putSpecialCharacter(sb, lastCharacter);
        }
        if (lastCharacter == '/') {
            stack.push('/');
            stateB(stack, sb);
        } else if (lastCharacter == '*') {
            stack.pop();
            stateC(stack, sb);
        }
    }

    private void stateB(Stack<Character> stack, StringBuilder sb) throws IOException {
        nextChar();
        if (lastCharacter == '*') {
            stack.push('*');
        }
        else stack.pop();
        putSpecialCharacter(sb, lastCharacter);
        stateA(stack, sb);
    }

    private void stateC(Stack<Character> stack, StringBuilder sb) throws IOException {
        nextChar();
        if (lastCharacter == '/') {
            stack.pop();
            sb.append('/');
            stateD(stack, sb);
        } else {
            stack.push('*');
            putSpecialCharacter(sb, lastCharacter);
            stateA(stack, sb);
        }
    }

    private void stateD(Stack<Character> stack, StringBuilder sb) throws IOException {
        if (!stack.isEmpty()) stateA(stack, sb);
    }

    private Token nextComment() throws IOException {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append(lastCharacter);
        stack.push('/');
        nextChar();
        if (lastCharacter == '/') {
            sb.append(lastCharacter);
            while (!isEOF(lastCharacter) && lastCharacter != '\n') {
                nextChar();
                if (!isEOF(lastCharacter) && lastCharacter != '\n') {
                    if (isSpace(lastCharacter)) sb.append(" ");
                    else sb.append(lastCharacter);
                }
                else break;
            }
           return createToken(TokenType.BK_CMT, sb.toString(), lineNum - 1);
        } else if (lastCharacter == '*') {
            stack.push('*');
            sb.append("*");
            stateA(stack, sb);
            if (isEOF(lastCharacter)) {
                return createToken(TokenType.INVALID_CMT, sb.toString(), lineNum);
            } else return createToken(TokenType.IN_CMT, sb.toString(), lineNum);
        } else {
            backtrack();
            return createToken(TokenType.DIV);
        }
    }

    public Token nextToken() {
        buffer = new LinkedList<>();
        try {
            skipSpace();
            char c = nextChar();
            // ID
            if (isLetter(c) || c == '_') {
                return nextId();
            } else if (isDigit(c)) {
                return nextNum();
            } else if (c == '=') {
                return nextEq();
            } else if (c == '<') {
                return nextLt();
            } else if (c == '>') {
                return nextGt();
            } else if (c == ':') {
                return nextColon();
            } else if (c == '+') {
                return createToken(TokenType.PLUS);
            } else if (c == '-') {
                return createToken(TokenType.MINUS);
            } else if (c == '*') {
                return createToken(TokenType.MULT);
            } else if (c == '.') {
                return createToken(TokenType.DOT);
            } else if (c == ';') {
                return createToken(TokenType.SEMI);
            } else if (c == ',') {
                return createToken(TokenType.COMMA);
            } else if (c == '(') {
                return createToken(TokenType.OP_PAR);
            } else if (c == ')') {
                return createToken(TokenType.CL_PAR);
            } else if (c == '{') {
                return createToken(TokenType.OP_CUBR);
            } else if (c == '}') {
                return createToken(TokenType.CL_CUBR);
            } else if (c == '[') {
                return createToken(TokenType.OP_SQBR);
            } else if (c == ']') {
                return createToken(TokenType.OP_SQBR);
            } else if (c == '/') {
                return nextComment();
            } else if (isEOF(c)) {
                return null;
            } else {
                return createToken(TokenType.INVALID_CHAR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String filePath = LexemeAnalyser.class.getResource("/test.txt").getPath();
            System.out.println(filePath);
            LexemeAnalyser lexemeAnalyser = new LexemeAnalyser(filePath);
            Token token = lexemeAnalyser.nextToken();
            while (token != null) {
                System.out.println(token);
                token = lexemeAnalyser.nextToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
