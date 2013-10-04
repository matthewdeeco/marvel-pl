package program.statement;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.Statement;
import program.SymbolTable;
import program.expression.*;

public class AssignmentStatement extends Statement {

    private VariableReference variableReference;
    private Expression expression;
    private DataType leftType, rightType;

    public AssignmentStatement(VariableReference v, Expression e) {
        this.variableReference = v;
        this.expression = e;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        variableReference.analyze(table);
        expression.analyze(table);
        leftType = variableReference.getType();
        rightType = expression.getType();
        if (! leftType.isCompatibleWith(rightType))
        	throw new ParseException(String.format("%s -> Incompatible types: %s and %s", getMainJavaCode(0), leftType.toString(), rightType.toString()));
    }

	@Override
	public String getMainJavaCode(int indentLevel) {
		String cast = "";
		if (leftType == DataType.INTEGER && rightType == DataType.REAL)
			cast = String.format("(%s)", leftType.toJavaCode());
		return String.format("%s = %s%s", variableReference.toJavaCode(), cast, expression.toJavaCode());
	}
}
