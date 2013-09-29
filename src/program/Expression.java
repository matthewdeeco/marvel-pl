package program;

import parser.ParseException;

public abstract class Expression {
	protected DataType type;
	
	public abstract void analyze(SymbolTable table) throws ParseException;
	public abstract DataType getType();
	
	/** @return Java code string equivalent to running this in our own PL */
	public abstract String toJavaCode();
	
	/** @return Java code string that evaluates to a boolean. */
	public String toConditionalJavaCode() {
		switch (getType()) {
			case INTEGER:
				return String.format("%s != 0", toJavaCode());
			case REAL:
				return String.format("%s != 0.0", toJavaCode());
			case BOOLEAN:
				return toJavaCode();
			case CHARACTER:
				return String.format("%s != ''", toJavaCode());
			case STRING:
				return String.format("!%s.isEmpty()", toJavaCode());
			default:
				throw new RuntimeException("Unsupported type: " + getType().toString());
		}
	}
}
