package program;

import java.util.HashMap;
import java.util.Map;
import parser.ParseException;
import program.statement.VariableDeclaration;

public class SymbolTable {

    private Map<String, VariableDeclaration> map;
    private SymbolTable parent;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
        map = new HashMap<String, VariableDeclaration>();
    }

    public SymbolTable() {
        this(null);
    }

    public VariableDeclaration lookupVariable(String name) throws ParseException {
        VariableDeclaration variable = null;
        for (SymbolTable table = this; table != null; table = table.getParent()) {
            variable = table.map.get(name);
            if (variable != null) {
                return variable;
            }
        }
        throw new ParseException(String.format("Variable used before declaration: %s", name));
    }

    public void addVariable(VariableDeclaration variable) throws ParseException {
    	String name = variable.getName();
        if (map.containsKey(name)) {
            throw new ParseException(String.format("Variable redeclared: %s", name));
        }
        else
            map.put(name, variable);
    }

    public SymbolTable getParent() {
        return parent;
    }
}
