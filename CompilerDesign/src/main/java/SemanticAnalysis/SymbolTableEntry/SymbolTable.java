package SemanticAnalysis.SymbolTableEntry;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SymbolTable {
    private HashMap<String, SymbolTableEntry> symTable;

    private int variableSize;
    private int totalSize;

    public SymbolTable() {
        this.symTable = new LinkedHashMap<>();
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

    public int getVariableSize() {
        return variableSize;
    }

    public void setVariableSize(int variableSize) {
        this.variableSize = variableSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
