package program.statement;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.Statement;
import program.SymbolTable;
import program.expression.VariableReference;

public class VariableDeclaration extends Statement {
    
	private DataType type;
	private String name;
	private AssignmentStatement assignment;

	public VariableDeclaration(DataType type, String name, Expression initialValue) {
		this.type = type;
		this.name = name;
		if (initialValue != null)
			assignment = new AssignmentStatement(new VariableReference(name), initialValue);
	}

	@Override
	public void analyze(SymbolTable table) throws ParseException {
		table.addVariable(this);
		if (assignment != null)
			assignment.analyze(table);
	}
	
	@Override
	public String getJavaDeclarations() {
		return String.format("private static %s %s;\r\n", type.toJavaCode(), name);		
	}

	@Override
	public String getMainJavaCode(int indentLevel) {
		if (assignment != null)
			return assignment.getMainJavaCode(indentLevel);
		else
			return String.format("%s = %s;\r\n", name, type.getInitialValue());
	}
	
	public DataType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
}