package CodeGeneration;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerationDriver {

    public static void writeOutput(String fileName) throws IOException {
        String outputFilePath = fileName.replace(".src", ".m");
        FileWriter moonFileWriter = new FileWriter(outputFilePath);
        CodeGeneration codeGeneration = new CodeGeneration(fileName);
        codeGeneration.run();
        // System.out.println(codeGeneration.getTableStr());
        System.out.println(codeGeneration.getAssemblyCode());
        moonFileWriter.write(codeGeneration.getAssemblyCode());
        moonFileWriter.close();
    }

    public static void main(String[] args) {
        try {
            // CodeGenerationDriver.writeOutput("tests/CodeGeneration/example-bubblesort.src");
             CodeGenerationDriver.writeOutput("tests/CodeGeneration/example-polynomial.src");
            // CodeGenerationDriver.writeOutput("tests/CodeGeneration/example-simplemain.src");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
