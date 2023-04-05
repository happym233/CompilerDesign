package SemanticAnalysis.SymbolTableEntry;

public class MemberFuncEntry extends SymbolTableEntry{
    private String returnType;

    private Visibility visibility;

    private SymbolTable functionTable;

    public MemberFuncEntry(String name, int location, Visibility visibility, String returnType, SymbolTable symbolTable) {
        super(name, location);
        this.visibility = visibility;
        this.returnType = returnType;
        this.functionTable = symbolTable;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
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

    @Override
    public String toString() {
        return "MemberFuncEntry{" +
                "name: " + getName() +
                "returnType='" + returnType + '\'' +
                ", visibility=" + visibility +
                ", functionTable=" + functionTable +
                '}';
    }

    @Override
    public void updateSpace() {
        setSpace(0);
    }
}
