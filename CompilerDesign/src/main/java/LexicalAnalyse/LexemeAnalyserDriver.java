package LexicalAnalyse;

import java.io.FileWriter;
import java.io.IOException;

public class LexemeAnalyserDriver {
    private LexemeAnalyser lexemeAnalyser;
    private FileWriter fileWriter;

    private FileWriter errWriter;

    public LexemeAnalyserDriver(String inputPath, String outputFilePath, String errFilePath) throws IOException {
        this.lexemeAnalyser = new LexemeAnalyser(inputPath);
        this.fileWriter = new FileWriter(outputFilePath);
        this.errWriter = new FileWriter(errFilePath);
    }


    public LexemeAnalyserDriver(String inputPath) throws IOException {
        String outputFilePath = inputPath.replace(".src", ".outlextokens");
        String errFilePath = inputPath.replace(".src", ".outlexerrors");
        this.lexemeAnalyser = new LexemeAnalyser(inputPath);
        this.fileWriter = new FileWriter(outputFilePath);
        this.errWriter = new FileWriter(errFilePath);
    }

    public void run() throws IOException {
        int line = 1;
        boolean k = false;
        Token t;
        while ((t = lexemeAnalyser.nextToken()) != null) {
            if (t.getLocation() != line) {
                fileWriter.write("\n");
                line = t.getLocation();
            } else if (k) fileWriter.write(" ");
            k = true;
            fileWriter.write(t.toString());
            if (t.isError()) errWriter.write(t.errMessage() + "\n");
        }
        fileWriter.close();
        errWriter.close();
    }

    public static void main(String[] args) {
        try {
            LexemeAnalyserDriver lexemeAnalyserDriverPos = new LexemeAnalyserDriver("tests/lexical/lexpositivegrading.src");
            LexemeAnalyserDriver lexemeAnalyserDriverNeg = new LexemeAnalyserDriver("tests/lexical/lexnegativegrading.src");
            LexemeAnalyserDriver lexemeAnalyserDriverBubble = new LexemeAnalyserDriver("tests/lexical/example-bubblesort.src");
            LexemeAnalyserDriver lexemeAnalyserDriverPoly = new LexemeAnalyserDriver("tests/lexical/example-polynomial.src");
            lexemeAnalyserDriverPos.run();
            lexemeAnalyserDriverNeg.run();
            lexemeAnalyserDriverBubble.run();
            lexemeAnalyserDriverPoly.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
