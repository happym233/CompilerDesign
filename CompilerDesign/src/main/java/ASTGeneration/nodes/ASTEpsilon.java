package ASTGeneration.nodes;

public class ASTEpsilon implements ASTSymbol {
    private static ASTEpsilon astEpsilon;

    private ASTEpsilon() {

    }

    public static ASTEpsilon getInstance() {
        if (astEpsilon == null) {
            astEpsilon = new ASTEpsilon();
        }
        return astEpsilon;
    }

    public String getName() {
        return "epsilon";
    }
}
