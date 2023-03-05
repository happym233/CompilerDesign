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
        getOutDerivationWriter().write("stack: \n");
        getOutDerivationWriter().write(stackToString(getStack()) + "\n");
        getOutDerivationWriter().write("input: \n");
        getOutDerivationWriter().write(getParsedString().toString() + "\n");
        getOutDerivationWriter().write("rule: \n");
        getOutDerivationWriter().write(getParsingTable().getLastRule() + "\n");
        getOutDerivationWriter().write("semantic stack: " + "\n");
        for (ASTSymbol astSymbol: astParser.getASTNodeStack()) {
            getOutDerivationWriter().write("[" + astSymbol.getName() + "]");
        }
        getOutDerivationWriter().write("\n");
        getOutDerivationWriter().write("-----------------------------------------------------------------------\n");
    }
}
