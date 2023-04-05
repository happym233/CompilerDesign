package CodeGeneration.SymbolTableEntry;

import SemanticAnalysis.SymbolTableEntry.SymbolTableEntry;

public class TmpVarEntry extends SymbolTableEntry {
    private String type;

    public TmpVarEntry (String name, int location, String type) {
        super(name, location);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void updateSpace() {
        if (type.equals("float")) {
            setSpace(8);
        } else {
            setSpace(4);
        }
    }
}
