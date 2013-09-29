package program.expression;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.SymbolTable;
import program.statement.VariableDeclaration;


public class VariableReference extends Expression {

    private String name;
    private VariableDeclaration referent;

    public VariableReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public VariableDeclaration getReferent() {
        return referent;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        referent = table.lookupVariable(name);
    }

	@Override
	public String toJavaCode() {
		return name;
	}

	@Override
	public DataType getType() {
		return referent.getType();
	}
}