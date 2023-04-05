package SemanticAnalysis.SymbolTableEntry;

public class LocalVarEntry extends SymbolTableEntry{

    private String type;
    private Integer[] dims;

    public LocalVarEntry (String name, int location, String type, Integer[] dims) {
        super(name, location);
        this.type = type;
        this.dims = dims;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer[] getDims() {
        return dims;
    }

    public void setDims(Integer[] dims) {
        this.dims = dims;
    }

    @Override
    public void updateSpace() {
        if (dims == null) {
            if (type.equals("float")) {
                setSpace(8);
            } else {
                setSpace(4);
            }
        } else {
            int i = 1;
            for (int j: dims) {
                i *= j;
            }
            if (type.equals("float")) {
                setSpace(8 * i);
            } else {
                setSpace(4 * i);
            }
        }
    }
}
