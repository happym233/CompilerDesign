package SemanticAnalysis.Visitors;

import ASTGeneration.nodes.ASTTreeLeaf;
import ASTGeneration.nodes.ASTTreeNode;
import LexicalAnalyse.Token;
import SemanticAnalysis.SemanticTreeNode;
import SemanticAnalysis.SymbolTableEntry.ParameterEntry;

import java.util.PriorityQueue;

public class VisitorHelper {

    private PriorityQueue<ErrorMessege> errors;

    public VisitorHelper(PriorityQueue<ErrorMessege> errors) {
        this.errors = errors;
    }

    public String encodeParameters(SemanticTreeNode fParamsNodes) {
        String offset = "";
        for (ASTTreeNode node: fParamsNodes.getChildren()) {
            SemanticTreeNode fParamNode = (SemanticTreeNode) node;
            String paramName = ((ASTTreeLeaf) fParamNode.getChildren()[0]).getToken().getLexeme();
            String paramType = ((ASTTreeLeaf) fParamNode.getChildren()[1]).getToken().getLexeme();
            SemanticTreeNode dimsNodes = (SemanticTreeNode) fParamNode.getChildren()[2];
            Integer[] dims = parseDims(dimsNodes);
            ParameterEntry parameterEntry = new ParameterEntry(paramName, fParamNode.getLocation(),paramType, dims);
            offset += parameterEntry.toOutputStr() + ", ";
        }
        return "(" + ((offset.length() > 0)? offset.substring(0, offset.length() - 2): offset) + ")";
    }

    public FunctionPair createFunctionPair(SemanticTreeNode functionNode) {
        FunctionPair functionPair = new FunctionPair();
        functionPair.node = functionNode;
        SemanticTreeNode functionHead = (SemanticTreeNode) functionNode.getChildren()[0];
        if (functionHead.getName().equals("normalFunctionHead")) {
            functionPair.name = ((ASTTreeLeaf) functionHead.getChildren()[0]).getToken().getLexeme();
            functionPair.funcPos = ((ASTTreeLeaf) functionHead.getChildren()[0]).getToken().getLocation();
            ASTTreeNode[] returnTypeNodes = ((SemanticTreeNode) functionHead.getChildren()[functionHead.getChildren().length - 1]).getChildren();
            if (returnTypeNodes.length == 0) {
                functionPair.returnType = "void";
            } else {
                functionPair.returnType = ((ASTTreeLeaf) returnTypeNodes[0]).getToken().getLexeme();
            }
            SemanticTreeNode fParamsNodes = (SemanticTreeNode) functionHead.getChildren()[1];
            functionPair.funcNameEncoded = functionPair.name + encodeParameters(fParamsNodes);
            functionPair.scope = null;
        } else if (functionHead.getName().equals("scopedFunctionHead")) {
            functionPair.name = ((ASTTreeLeaf) functionHead.getChildren()[1]).getToken().getLexeme();
            functionPair.funcPos = ((ASTTreeLeaf) functionHead.getChildren()[1]).getToken().getLocation();
            ASTTreeNode[] returnTypeNodes = ((SemanticTreeNode) functionHead.getChildren()[functionHead.getChildren().length - 1]).getChildren();
            if (returnTypeNodes.length == 0) {
                functionPair.returnType = "void";
            } else {
                functionPair.returnType = ((ASTTreeLeaf) returnTypeNodes[0]).getToken().getLexeme();
            }
            SemanticTreeNode fParamsNodes = (SemanticTreeNode) functionHead.getChildren()[2];
            functionPair.funcNameEncoded = functionPair.name + encodeParameters(fParamsNodes);
            functionPair.scope = ((ASTTreeLeaf) functionHead.getChildren()[0]).getToken().getLexeme();
        } else { // constructor function head
            functionPair.name = "constructor";
            functionPair.scope = ((ASTTreeLeaf) functionHead.getChildren()[0]).getToken().getLexeme();
            functionPair.returnType = functionPair.scope;
            functionPair.funcPos = ((ASTTreeLeaf) functionHead.getChildren()[0]).getToken().getLocation();
            SemanticTreeNode fParamsNodes = (SemanticTreeNode) functionHead.getChildren()[1];
            functionPair.funcNameEncoded = functionPair.name + encodeParameters(fParamsNodes);
        }
        return functionPair;
    }

    public ClassPair createClassPair(SemanticTreeNode classNode) {
        ClassPair classPair = new ClassPair();
        classPair.node = classNode;
        classPair.name = ((ASTTreeLeaf) classNode.getChildren()[0]).getToken().getLexeme();
        classPair.classPos = ((ASTTreeLeaf) classNode.getChildren()[0]).getToken().getLocation();
        SemanticTreeNode extendClassNode = (SemanticTreeNode) classNode.getChildren()[1];
        classPair.extend_classes = new String[extendClassNode.getChildren().length];
        classPair.extend_classes_tokens = new Token[extendClassNode.getChildren().length];
        for (int i = 0; i < classPair.extend_classes.length; i++) {
            classPair.extend_classes[i] = ((ASTTreeLeaf) extendClassNode.getChildren()[i]).getToken().getLexeme();
            classPair.extend_classes_tokens[i] = ((ASTTreeLeaf) extendClassNode.getChildren()[i]).getToken();
        }
        return classPair;
    }

    public Integer[] parseDims(SemanticTreeNode dimsNodes) {
        Integer[] dims;
        if (dimsNodes.getChildren().length == 0) {
            dims = null;
        } else {
            dims = new Integer[dimsNodes.getChildren().length];
            for (int i = 0; i < dimsNodes.getChildren().length; i++) {
                SemanticTreeNode dimNode = (SemanticTreeNode) dimsNodes.getChildren()[i];
                if (dimNode.getChildren().length == 0) {
                    dims[i] = null;
                } else {
                    try {
                        dims[i] = Integer.valueOf(((ASTTreeLeaf) dimNode.getChildren()[0]).getToken().getLexeme());
                    } catch (Exception e) {
                        errors.add(new ErrorMessege(((ASTTreeLeaf) dimNode.getChildren()[0]).getToken().getLocation(), ErrorLevel.ERROR,
                                "array size should be type integer"));
                    }
                }
            }
        }
        return dims;
    }

}
