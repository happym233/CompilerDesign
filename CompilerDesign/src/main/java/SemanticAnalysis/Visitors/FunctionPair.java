package SemanticAnalysis.Visitors;

import SemanticAnalysis.SemanticTreeNode;

public class FunctionPair {

    public String name;
    public String funcNameEncoded;
    public String scope;
    public int funcPos;
    public String returnType;
    public SemanticTreeNode node;

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
