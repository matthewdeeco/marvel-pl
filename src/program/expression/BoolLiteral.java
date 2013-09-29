package program.expression;

import program.DataType;
import program.Expression;
import program.SymbolTable;

public class BoolLiteral extends Expression {
    private boolean value;

    public BoolLiteral(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public void analyze(SymbolTable table) {
    }

	@Override
	public String toJavaCode() {
		return String.valueOf(value);
	}

	@Override
	public DataType getType() {
		return DataType.BOOLEAN;
	}
}
