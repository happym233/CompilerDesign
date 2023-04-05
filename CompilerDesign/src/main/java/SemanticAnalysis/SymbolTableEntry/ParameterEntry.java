package SemanticAnalysis.SymbolTableEntry;

import java.util.Arrays;

public class ParameterEntry extends SymbolTableEntry{
    private String type;
    private Integer[] dims;

    public ParameterEntry(String name, int location, String type, Integer[] dims) {
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

    public String toEncodeString() {
        return type  + "_" + Arrays.toString(dims);
    }
    public String toOutputStr() {
        String str = type;
        if (dims == null) return str;
        else {
            for (Integer dim: dims) {
               str += "[]";
            }
            return str;
        }
    }

    @Override
    public String toString() {
        return "ParameterEntry{" +
                "type='" + type + '\'' +
                ", dims=" + Arrays.toString(dims) +
                '}';
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
            setSpace(4);
        }
    }
}
