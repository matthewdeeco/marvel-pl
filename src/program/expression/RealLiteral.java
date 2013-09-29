package program.expression;

import program.DataType;
import program.Expression;
import program.SymbolTable;

public class RealLiteral extends Expression {

	private double value;
	
	public RealLiteral(String value) {
		this.value = Double.parseDouble(value);
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
		return DataType.REAL;
	}
}
