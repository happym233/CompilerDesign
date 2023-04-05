package SemanticAnalysis;

import ASTGeneration.nodes.ASTTreeNode;
import ASTGeneration.nodes.ASTTreeParent;
import SemanticAnalysis.SymbolTableEntry.SymbolTable;
import SemanticAnalysis.SymbolTableEntry.SymbolTableEntry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SemanticTreeNode extends ASTTreeParent {
    private SymbolTable symbolTable;
    private SymbolTableEntry symbolTableEntry;
    private List<String> tmpSymbol;
    private String type;
    private int dims;

    private int location;

    private List<String> scopes;

    private int typeDimsNum;

    public SemanticTreeNode(ASTTreeParent astTreeParent) {
        super(astTreeParent.getName(), new ASTTreeNode[astTreeParent.getChildren().length]);
        tmpSymbol = new LinkedList<>();
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public int getDims() {
        return dims;
    }

    public void setDims(int dims) {
        this.dims = dims;
    }

    public int getTypeDimsNum() {
        return typeDimsNum;
    }

    public void setTypeDimsNum(int typeDimsNum) {
        this.typeDimsNum = typeDimsNum;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<String> getTmpSymbol() {
        return tmpSymbol;
    }

    public int getOffset() {
        return symbolTable.get(tmpSymbol.get(0)).getOffset();
    }

    public void setTmpSymbol(List<String> tmpSymbol) {
        this.tmpSymbol = tmpSymbol;
    }

    public SymbolTableEntry getSymbolTableEntry() {
        return symbolTableEntry;
    }

    public void setSymbolTableEntry(SymbolTableEntry symbolTableEntry) {
        this.symbolTableEntry = symbolTableEntry;
    }
}
