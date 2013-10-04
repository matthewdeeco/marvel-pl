package program.statement;

import parser.ParseException;
import program.*;

import program.Block;

public class FunctionDeclaration extends Statement {
	private String name;
	private Block body;
	
	public FunctionDeclaration(String name, Block body) {
		this.name = name;
		this.body = body;
	}

	@Override
	public void analyze(SymbolTable table) throws ParseException {
		table.addFunction(this);
	}

	@Override
	public String getMainJavaCode(int indentLevel) {
		return "";
	}
	
	public String getName() {
		return name;
	}
}
