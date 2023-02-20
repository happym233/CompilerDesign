package LexicalAnalyse;

import java.util.HashMap;
import java.util.Map;

public enum TokenType {
    ID("id"),
    INT_NUM("intnum"),
    FLOAT_NUM("floatnum"),
    INVALID_NUM("invalidnum"),
    INVALID_ID("invalidid"),
    INVALID_CHAR("invalidchar"),
    PLUS("plus"),
    MINUS("minus"),
    MULT("mult"),
    DIV("div"),
    SEMI("semi"),
    COMMA("comma"),
    DOT("dot"),
    EQ("eq"),
    RE_TYPE("returntype"),
    ASSIGN("assign"),
    LT("lt"),
    LEQ("leq"),
    NOT_EQ("noteq"),
    GT("gt"),
    GEQ("geq"),
    COLON("colon"),
    SCOPEOP("scopeop"),
    OP_PAR("openpar"),
    CL_PAR("closepar"),
    OP_CUBR("opencubr"),
    CL_CUBR("closecubr"),
    OP_SQBR("opensqbr"),
    CL_SQBR("closesqbr"),
    IN_CMT("inlinecmt"),
    BK_CMT("blockcmt"),
    INVALID_CMT("invalidcmt"),
    AND("and"),
    OR("or"),
    NOT("not"),
    INTEGER("integer"),
    FLOAT("float"),
    VOID("void"),
    CLASS("class"),
    SELF("self"),
    ISA("isa"),
    WHILE("while"),
    IF("if"),
    THEN("then"),
    ELSE("else"),
    READ("read"),
    WRITE("write"),
    RETURN("return"),
    LOCAL_VAR("localvar"),
    CONSTRUCTOR("constructor"),
    ATTRIBUTE("attribute"),
    FUNCTION("function"),
    PUBLIC("public"),
    PRIVATE("private"),
    EOF("EOF");
    public final String tokenTypeStr;

    private static final Map<String, TokenType> map = new HashMap<String, TokenType>(){{
        put("id", TokenType.ID);
        put("intnum", TokenType.INT_NUM);
        put("floatnum", TokenType.FLOAT_NUM);
        put("invalidnum", TokenType.INVALID_NUM);
        put("invalidid", TokenType.INVALID_ID);
        put("invalidchar", TokenType.INVALID_CHAR);
        put("plus", TokenType.PLUS);
        put("minus", TokenType.MINUS);
        put("mult", TokenType.MULT);
        put("div", TokenType.DIV);
        put("semi", TokenType.SEMI);
        put("comma", TokenType.COMMA);
        put("dot", TokenType.DOT);
        put("eq", TokenType.EQ);
        put("returntype", TokenType.RE_TYPE);
        put("assign", TokenType.ASSIGN);
        put("lt", TokenType.LT);
        put("leq", TokenType.LEQ);
        put("noteq", TokenType.NOT_EQ);
        put("gt", TokenType.GT);
        put("geq", TokenType.GEQ);
        put("colon", TokenType.COLON);
        put("scopeop", TokenType.SCOPEOP);
        put("openpar", TokenType.OP_PAR);
        put("closepar", TokenType.CL_PAR);
        put("opencubr", TokenType.OP_CUBR);
        put("closecubr", TokenType.CL_CUBR);
        put("opensqbr", TokenType.OP_SQBR);
        put("closesqbr", TokenType.CL_SQBR);
        put("inlinecmt", TokenType.IN_CMT);
        put("blockcmt", TokenType.BK_CMT);
        put("invalidcmt", TokenType.INVALID_CMT);
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
        put("public", TokenType.PUBLIC);
        put("private", TokenType.PRIVATE);
        put("EOF", TokenType.EOF);
    }};

    private TokenType(String tokenTypeStr) {
        this.tokenTypeStr = tokenTypeStr;
    }

    public static TokenType create(String str) {
        return map.get(str);
    }
}
