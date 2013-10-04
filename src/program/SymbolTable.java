package program;

import java.util.HashMap;
import java.util.Map;
import parser.ParseException;
import program.statement.*;

public class SymbolTable {

    private Map<String, VariableDeclaration> varmap;
    private Map<String, FunctionDeclaration> funcmap;
    private SymbolTable parent;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
        varmap = new HashMap<String, VariableDeclaration>();
        funcmap = new HashMap<String, FunctionDeclaration>();
    }

    public SymbolTable() {
        this(null);
    }

    public VariableDeclaration lookupVariable(String name) throws ParseException {
        VariableDeclaration variable = null;
        for (SymbolTable table = this; table != null; table = table.getParent()) {
            variable = table.varmap.get(name);
            if (variable != null) {
                return variable;
            }
        }
        throw new ParseException(String.format("Variable used before declaration: %s", name));
    }

    public void addVariable(VariableDeclaration variable) throws ParseException {
    	String name = variable.getName();
        if (varmap.containsKey(name))
            throw new ParseException(String.format("Variable redeclared: %s", name));
        else
            varmap.put(name, variable);
    }

	public void addFunction(FunctionDeclaration function) {
    	String name = function.getName();
        funcmap.put(name, function);
	}

    public SymbolTable getParent() {
        return parent;
    }

	public FunctionDeclaration lookupFunction(String name) throws ParseException {
        FunctionDeclaration function = null;
        for (SymbolTable table = this; table != null; table = table.getParent()) {
            function = table.funcmap.get(name);
            if (function != null)
                return function;
        }
        throw new ParseException(String.format("Function called before declaration: %s", name));
	}
}
