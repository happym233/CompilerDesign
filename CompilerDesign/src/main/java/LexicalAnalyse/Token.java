package LexicalAnalyse;

public class Token {
    private TokenType type;
    private String lexeme;
    private int location;

    public Token(TokenType type, String lexeme, int location) {
        this.type = type;
        this.lexeme = lexeme;
        this.location = location;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isError() {
        return type == TokenType.INVALID_ID || type == TokenType.INVALID_CMT
                || type ==TokenType.INVALID_CHAR || type == TokenType.INVALID_NUM;
    }

    public String errMessage() {
        String s = "";
        if (type == TokenType.INVALID_ID) {
            s = "Invalid identifier";
        } else if (type == TokenType.INVALID_CMT) {
            s = "Invalid comment";
        } else if (type ==TokenType.INVALID_CHAR) {
            s = "Invalid character";
        } else if (type == TokenType.INVALID_NUM) {
            s = "Invalid number";
        }
        return "Lexical error: " + s + ": \"" + lexeme + "\": line " + location + ".";
    }

    @Override
    public String toString() {
        String sb = "[" +
                type.tokenTypeStr +
                ", " +
                lexeme +
                ", " +
                location +
                "]";
        return sb;
    }
}
