package program.statement;

import parser.ParseException;
import program.Statement;
import program.SymbolTable;

public class FunctionCall extends Statement {

	private String name;
	
	public FunctionCall(String name) {
		this.name = name;
	}
	
	@Override
	public void analyze(SymbolTable table) throws ParseException {
		table.lookupFunction(name);
	}

	@Override
	public String getMainJavaCode(int indentLevel) {
		return String.format("%s()", name);
	}

}
