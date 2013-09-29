package program.expression;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.SymbolTable;

public class StringLiteral extends Expression {

	private String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public void analyze(SymbolTable table) throws ParseException {
	}

	@Override
	public String toJavaCode() {
		return value;
	}

	@Override
	public DataType getType() {
		return DataType.STRING;
	}
}
