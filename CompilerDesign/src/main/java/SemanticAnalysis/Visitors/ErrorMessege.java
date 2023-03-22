package SemanticAnalysis.Visitors;

public class ErrorMessege {
    private int lineNum;
    private ErrorLevel errorLevel;
    private String message;

    public ErrorMessege(int lineNum, ErrorLevel errorLevel, String message) {
        this.lineNum = lineNum;
        this.errorLevel = errorLevel;
        this.message = message;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(ErrorLevel errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[line " + lineNum + "]" + errorLevel.getErrorStr() + ": " + message;
    }
}
