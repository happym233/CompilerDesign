package SemanticAnalysis.SymbolTableEntry;

public class GlobalTable extends SymbolTable{
    private String name;

    public GlobalTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
