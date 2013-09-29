package program.expression;

import program.DataType;
import program.Expression;
import program.SymbolTable;

public class IntLiteral extends Expression {
    private int value;

    public IntLiteral(String value) {
        this.value = Integer.parseInt(value);
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
		return DataType.INTEGER;
	}
}
