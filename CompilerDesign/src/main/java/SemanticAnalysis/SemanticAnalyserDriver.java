package SemanticAnalysis;

import SemanticAnalysis.Visitors.ErrorMessege;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SemanticAnalyserDriver {

    public static void writeOutput(String fileName) throws IOException {
        String outputFilePath = fileName.replace(".src", ".outsymboltables");
        String errFilePath = fileName.replace(".src", ".outsemerrors");
        FileWriter tableFileWriter = new FileWriter(outputFilePath);
        FileWriter errFileWriter = new FileWriter(errFilePath);
        SemanticAnalyser semanticAnalyser = new SemanticAnalyser(fileName);
        semanticAnalyser.run();
        tableFileWriter.write(semanticAnalyser.getSymbolTableStr());
        tableFileWriter.close();
        LinkedList<String> errors = new LinkedList<>();
        int i = 1, cnt = 1;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        PriorityQueue<ErrorMessege> errorMesseges = semanticAnalyser.getErrorMesseges();
        while (!errorMesseges.isEmpty() && errorMesseges.peek().getLineNum() == 0) {
            errFileWriter.write("//" + cnt++ );
            errors.add(errorMesseges.remove().toString());
        }
        String line = reader.readLine();
        while (line != null) {
            line = reader.readLine();
            if (line == null) break;
            errFileWriter.write(line);
            if (!errorMesseges.isEmpty() && errorMesseges.peek().getLineNum() == i + 1) {
                errFileWriter.write(" // " + cnt++);
                errors.add(errorMesseges.remove().toString());
                while (!errorMesseges.isEmpty() && errorMesseges.peek().getLineNum() == i + 1) {
                    errorMesseges.remove();
                }
            }
            errFileWriter.write("\n");
            i++;
        }
        for (int j = 0; j < errors.size(); j++) {
            errFileWriter.write("// " + (j + 1) + ": " + errors.get(j) + "\n");
        }
        tableFileWriter.close();
        errFileWriter.close();
    }

    public static void main(String[] args) {

        try {

            SemanticAnalyserDriver.writeOutput("tests/semantic/example-bubblesort.src");
            SemanticAnalyserDriver.writeOutput("tests/semantic/example-polynomial.src");
            SemanticAnalyserDriver.writeOutput("tests/semantic/polynomialsemanticerrors.src");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
