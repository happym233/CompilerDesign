package SemanticAnalysis.SymbolTableEntry;

import java.util.Arrays;

public class MemberVarEntry extends SymbolTableEntry{
    private String type;
    private Visibility visibility;
    private Integer[] dims;

    public MemberVarEntry(Visibility visibility, int location,  String name, String type, Integer[] dims) {
        super(name, location);
        this.visibility = visibility;
        this.type = type;
        this.dims = dims;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Integer[] getDims() {
        return dims;
    }

    public void setDims(Integer[] dims) {
        this.dims = dims;
    }

    @Override
    public String toString() {
        return "MemberVarEntry{" +
                "name='" + getName() +
                ", type='" + type + '\'' +
                ", visibility=" + visibility +
                ", dims=" + Arrays.toString(dims) +
                '}';
    }
}
