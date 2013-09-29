package program;

import parser.ParseException;

public abstract class Statement {
	private static final int INDENT_SIZE = 4;
	public abstract void analyze(SymbolTable table) throws ParseException;
	
	/** Is neither indented nor newline-terminated. */
	public abstract String toUnformattedJavaCode(int indentLevel);

	public String toJavaCode(int indentLevel) {
		String code = indent(indentLevel);
		code += toUnformattedJavaCode(indentLevel) + ";\r\n";
		return code;
	}
    
    protected String indent(int indentLevel) {
    	String indent = "";
		for (int i = 0; i < indentLevel; i++)
			for (int j = 0; j < INDENT_SIZE; j++)
				indent += " ";
		return indent;
    }
}
