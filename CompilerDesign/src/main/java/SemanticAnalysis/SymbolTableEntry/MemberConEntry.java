package SemanticAnalysis.SymbolTableEntry;

public class MemberConEntry extends SymbolTableEntry {

    private Visibility visibility;

    private SymbolTable functionTable;

    public MemberConEntry(String name, int location, Visibility visibility, SymbolTable functionTable) {
        super(name, location);
        this.visibility = visibility;
        this.functionTable = functionTable;
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
        str += ")";
        return str;
    }

    @Override
    public void updateSpace() {
        setSpace(0);
    }

    @Override
    public String toString() {
        return "MemberConEntry{" +
                "visibility=" + visibility +
                ", functionTable=" + functionTable +
                '}';
    }
}
