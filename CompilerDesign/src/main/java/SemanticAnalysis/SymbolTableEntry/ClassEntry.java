package SemanticAnalysis.SymbolTableEntry;

import java.util.List;

public class ClassEntry extends SymbolTableEntry{
    private SymbolTable classTable;

    public ClassEntry(String name, int location, SymbolTable classTable) {
        super(name, location);
        this.classTable = classTable;
    }

    public SymbolTable getClassTable() {
        return classTable;
    }

    public void setClassTable(SymbolTable classTable) {
        this.classTable = classTable;
    }

    @Override
    public void updateSpace() {
        setSpace(0);
    }

}
