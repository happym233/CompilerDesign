package SemanticAnalysis.SymbolTableEntry;

public class FunctionEntry extends SymbolTableEntry{
    private String returnType;

    private SymbolTable functionTable;

    public FunctionEntry(String name, int location, String returnType, SymbolTable symbolTable) {
        super(name, location);
        this.returnType = returnType;
        this.functionTable = new SymbolTable();
        this.functionTable = symbolTable;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public SymbolTable getFunctionTable() {
        return functionTable;
    }

    public void setFunctionTable(SymbolTable functionTable) {
        this.functionTable = functionTable;
    }


    public String toOuputStr() {
        String str = "(";
        int i = 0;
        for (String etr: functionTable.getSymTable().keySet()) {
            SymbolTableEntry symbolTableEntry = functionTable.get(etr);
            if (symbolTableEntry instanceof ParameterEntry) {
                if (i > 0) str += ", ";
                ParameterEntry parameterEntry = (ParameterEntry) symbolTableEntry;
                str += parameterEntry.toOutputStr();
                i++;
            }
        }
        str += "): ";
        str += returnType;
        return str;
    }
}
