package SemanticAnalysis.Visitors;

public enum ErrorLevel {
    ERROR("Error"),
    WARNING("Warning");

    private String errorStr;

    private ErrorLevel(String errorStr) {
        this.errorStr = errorStr;
    }

    public String getErrorStr() {
        return errorStr;
    }
}
