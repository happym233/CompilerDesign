package SemanticAnalysis.Visitors;

import LexicalAnalyse.Token;
import SemanticAnalysis.SemanticTreeNode;

import java.util.Arrays;

public class ClassPair {

    String name;
    String[] extend_classes;
    Token[] extend_classes_tokens;
    SemanticTreeNode node;
    int classPos;

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
