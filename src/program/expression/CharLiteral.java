package program.expression;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.SymbolTable;

public class CharLiteral extends Expression {

	private String value;
	
	public CharLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public void analyze(SymbolTable table) throws ParseException {
		if (value.length() > 3)
			throw new ParseException("Character type holds more than 1 character");
	}

	@Override
	public String toJavaCode() {
		return value;
	}

	@Override
	public DataType getType() {
		return DataType.CHARACTER;
	}

}
