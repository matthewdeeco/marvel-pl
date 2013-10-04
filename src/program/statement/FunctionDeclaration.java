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
		body.analyze(table);
	}
	
	@Override
	public String getJavaDeclarations() {
		return String.format("%s\r\nprivate static void %s() {\r\n%s}\r\n\r\n",
				body.getJavaDeclarations(), name, body.toJavaCode(1));
	}

	@Override
	public String getMainJavaCode(int indentLevel) {
		return "";
	}
	
	public String getName() {
		return name;
	}
}
