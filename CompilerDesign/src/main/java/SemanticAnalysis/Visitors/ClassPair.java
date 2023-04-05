package SemanticAnalysis.Visitors;

import LexicalAnalyse.Token;
import SemanticAnalysis.SemanticTreeNode;

import java.util.Arrays;

public class ClassPair {

    public String name;
    public String[] extend_classes;
    public Token[] extend_classes_tokens;
    public SemanticTreeNode node;
    public int classPos;

    public ClassPair() {
    }

    @Override
    public String toString() {
        return "ClassPair{" +
                "name='" + name + '\'' +
                ", extend_classes=" + Arrays.toString(extend_classes) +
                ", node=" + node +
                '}';
    }
}
