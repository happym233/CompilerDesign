package SemanticAnalysis.SymbolTableEntry;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, SymbolTableEntry> symTable;

    public SymbolTable() {
        this.symTable = new HashMap<>();
    }

    public void addEntry(String name, SymbolTableEntry symbolTableEntry) {
        symTable.put(name, symbolTableEntry);
    }

    public boolean containsKey(String name) {
        return symTable.containsKey(name);
    }

    public SymbolTableEntry get(String name) {
        return symTable.get(name);
    }

    public HashMap<String, SymbolTableEntry> getSymTable() {
        return symTable;
    }

    public void setSymTable(HashMap<String, SymbolTableEntry> symTable) {
        this.symTable = symTable;
    }

    @Override
    public String toString() {
        String str = "";
        for (String key: symTable.keySet()) {
            str += key + " | " + symTable.get(key) + "\n";
        }
        return str;
    }
}
