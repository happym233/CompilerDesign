package ASTGeneration;

import ASTGeneration.nodes.ASTSymbol;
import ASTGeneration.nodes.ASTTreeNode;
import ASTGeneration.nodes.ASTTreeParent;
import SyntacticAnalysis.ParsingTableFactory;
import SyntacticAnalysis.SyntacticAnalyser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class ASTGenerator extends SyntacticAnalyser {
    private ASTParser astParser;

    public ASTGenerator(String fileName) throws IOException {
        super(fileName, new ASTParser(ParsingTableFactory.generate(Config.getAttributeGrammar(), Config.getParsingTablePath())));
        astParser = (ASTParser) getParsingTable();
        parseAll();
    }

    public ASTTreeParent getRoot() {
        return (ASTTreeParent) astParser.getASTNodeStack().peek();
    }


    protected void writeStackTrace() throws IOException {
        getDerivationStr().append("stack: \n");
        getDerivationStr().append(stackToString(getStack()) + "\n");
        getDerivationStr().append("input: \n");
        getDerivationStr().append(getParsedString().toString() + "\n");
        getDerivationStr().append("rule: \n");
        getDerivationStr().append(getParsingTable().getLastRule() + "\n");
        getDerivationStr().append("semantic stack: " + "\n");
        for (ASTSymbol astSymbol: astParser.getASTNodeStack()) {
            getDerivationStr().append("[" + astSymbol.getName() + "]");
        }
        getDerivationStr().append("\n");
        getDerivationStr().append("-----------------------------------------------------------------------\n");
    }
}
