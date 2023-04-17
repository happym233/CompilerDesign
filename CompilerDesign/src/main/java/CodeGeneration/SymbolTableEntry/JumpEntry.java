package CodeGeneration.SymbolTableEntry;

import SemanticAnalysis.SymbolTableEntry.SymbolTableEntry;

public class JumpEntry extends SymbolTableEntry {

    public JumpEntry (int location) {
        super("jump", location);
    }

    @Override
    public void updateSpace() {
        setSpace(4);
    }
}
