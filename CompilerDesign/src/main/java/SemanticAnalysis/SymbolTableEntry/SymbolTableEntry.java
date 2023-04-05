package SemanticAnalysis.SymbolTableEntry;

import jdk.jshell.spi.ExecutionControl;

public class SymbolTableEntry {
    private String name;
    private int location;

    private int offset;
    private int space;
    private String encoding;

    public SymbolTableEntry(String name, int location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int updateOffset(int offset) {
        setOffset(offset);
        offset = offset - getSpace();
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void updateSpace() {

    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
