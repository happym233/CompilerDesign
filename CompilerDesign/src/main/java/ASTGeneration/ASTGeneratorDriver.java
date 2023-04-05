package ASTGeneration;

import ASTGeneration.nodes.ASTTreeNode;
import ASTGeneration.nodes.ASTTreeParent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ASTGeneratorDriver {

    public static String wrapASTDot(String graphName, String inside) {
        String str = "";
        str += "digraph " + graphName + " {\n";
        str += "node [shape=record];\n";
        str += "node [fontname=Sans];charset=\"UTF-8\" splines=true splines=spline rankdir =LR\n";
        str += inside + "\n";
        str += "}\n";
        return str;
    }

    public static String toDotString(String ASTName, ASTTreeParent astTreeParent) {
        String str = "";
        int idx = 1;
        Queue<Integer> parentIdx = new LinkedList<>();
        Queue<ASTTreeNode> nodes = new LinkedList<>();
        str += idx + "[label=\"" + astTreeParent.getDotStr() + "\"];\n";
        if (astTreeParent.getChildren().length == 0) {
            str += "none" + idx + "[shape=point];\n";
        } else {
            for (ASTTreeNode node: astTreeParent.getChildren()) {
                parentIdx.add(idx);
                nodes.add(node);
            }
        }
        while (!nodes.isEmpty()) {
            idx++;
            ASTTreeNode node = nodes.remove();
            str += parentIdx.remove() + "->" + idx + ";\n";
            str += idx + "[label=\"" + node.getDotStr() + "\"];\n";
            if (node instanceof ASTTreeParent) {
                if (((ASTTreeParent) node).getChildren().length == 0) {
                    str += "none" + idx + "[shape=point];\n";
                    str += idx + "->" + "none" + idx + ";\n";
                } else {
                    for (ASTTreeNode child: ((ASTTreeParent) node).getChildren()) {
                        parentIdx.add(idx);
                        nodes.add(child);
                    }
                }
            }
        }
        return wrapASTDot(ASTName, str);
    }

    public static void writeOutput(String graphName, String inputFilePath) throws IOException {
        String outAstPath = inputFilePath.replace(".src", ".ast.outast");
        String outDotPath = inputFilePath.replace(".src", ".dot.outast");
        ASTGenerator astGenerator = new ASTGenerator(inputFilePath);
        FileWriter outAstWriter = new FileWriter(outAstPath);
        FileWriter outDotWriter = new FileWriter(outDotPath);
        outAstWriter.write(astGenerator.getRoot().toASTString(""));
        outDotWriter.write(toDotString(graphName, astGenerator.getRoot()));
        outAstWriter.close();
        outDotWriter.close();
    }

    public static void main(String[] args) {
        try {
            writeOutput("bubblesort", "tests/AST/example-bubblesort.src");
            writeOutput("ploynomial", "tests/AST/example-polynomial.src");
            writeOutput("test2", "tests/AST/test2.src");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
