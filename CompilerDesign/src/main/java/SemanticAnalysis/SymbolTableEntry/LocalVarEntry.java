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

    public boolean isBasicType() {
        return (type.equals("integer") || type.equals("float") || type.equals("pointer")) && dims == null;
    }

    @Override
    public void updateSpace() {
        if (dims == null) {
            if (type.equals("float")) {
                setSpace(8);
            } else if (type.equals("integer") || type.equals("pointer")) {
                setSpace(4);
            } else {
                setSpace(getSpace());
            }
        } else {
            int i = 1;
            for (int j: dims) {
                i *= j;
            }
            if (type.equals("float")) {
                setSpace(8 * i);
            } else if (type.equals("integer")) {
                setSpace(4 * i);
            } else {
                setSpace(getSpace() * i);
            }
        }
    }
}
