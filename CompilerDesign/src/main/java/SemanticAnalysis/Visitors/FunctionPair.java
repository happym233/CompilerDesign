package SemanticAnalysis.Visitors;

import SemanticAnalysis.SemanticTreeNode;

public class FunctionPair {

    String name;
    String funcNameEncoded;
    String scope;
    int funcPos;
    String returnType;
    SemanticTreeNode node;

    public FunctionPair() {
    }

    @Override
    public String toString() {
        return "FunctionPair{" +
                "name='" + name + '\'' +
                ", scope='" + scope + '\'' +
                ", returnType='" + returnType + '\'' +
                ", node=" + node +
                '}';
    }
}
