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
        if (leftType == DataType.BOOLEAN)
        	return;
        if (! isTypeCompatible())
        	throw new ParseException(String.format("%s -> Incompatible types: %s and %s", getMainJavaCode(0), leftType.toString(), rightType.toString()));
    }
    
    private boolean isTypeCompatible() {
    	if (leftType == rightType)
    		return true;
    	else if ((leftType == DataType.INTEGER && rightType == DataType.REAL) ||
    		(leftType == DataType.REAL && rightType == DataType.INTEGER))
    		return true;
    	else if (leftType == DataType.BOOLEAN)
    		return true;
    	else
    		return false;
    }

	@Override
	public String getMainJavaCode(int indentLevel) {
		String cast = "";
		if (leftType == DataType.INTEGER && rightType == DataType.REAL)
			cast = String.format("(%s)", leftType.toJavaCode());
		if (leftType == DataType.BOOLEAN)
			return String.format("%s = (%s)", variableReference.toJavaCode(), expression.toConditionalJavaCode());
		else
			return String.format("%s = %s%s", variableReference.toJavaCode(), cast, expression.toJavaCode());
	}
}
