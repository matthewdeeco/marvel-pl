package program.expression;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.SymbolTable;

public class BinaryExpression extends Expression {

    public static enum Operator {
        PLUS("+"), MINUS("-"), TIMES("*"), DIVIDE("/"),
        AND("&&"), OR("||"), NOT("!"),
        EQUALS("=="), NOT_EQUALS("!="), LESS_THAN("<"), LESS_THAN_OR_EQUAL_TO("<="), GREATER_THAN(">"), GREATER_THAN_OR_EQUAL_TO(">=");

        private String text;

        private Operator(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private Operator operator;
    private Expression left, right;
    private DataType leftType, rightType;

    public BinaryExpression(Operator op, Expression x, Expression y) {
        this.operator = op;
        this.left = x;
        this.right = y;
    }

    public Operator getOperator() {
        return operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        left.analyze(table);
        right.analyze(table);
        leftType = left.getType();
        rightType = right.getType();
        checkCompatibility();
    }
    
    private void checkCompatibility() throws ParseException {
    	/* if (leftType == DataType.STRING && operator == Operator.TIMES && rightType == DataType.INTEGER)
    		return;
    	else */ if (!leftType.isCompatibleWith(rightType))
        	throw new ParseException(String.format("%s -> Incompatible types: %s and %s", toJavaCode(), leftType.toString(), rightType.toString()));
        else if (!leftType.isCompatibleWith(operator))
        	throw new ParseException(String.format("%s -> Unsupported operation: %s and '%s'", toJavaCode(), leftType.toString(), operator.toString()));
    }
    
    @Override
    public String toJavaCode() {
    	String cast = "";
    	if (operator == Operator.DIVIDE && leftType == DataType.INTEGER && rightType == DataType.INTEGER)
    		cast = String.format("(%s)", DataType.REAL.toJavaCode());
    	if (operator == Operator.AND || operator == Operator.OR)
    		return String.format("(%s %s %s)", left.toConditionalJavaCode(), operator.toString(), right.toConditionalJavaCode());
    	else if (operator == Operator.EQUALS && leftType == DataType.STRING)
    		return String.format("(%s.equals(%s))", left.toJavaCode(), right.toJavaCode());
    	else if (operator == Operator.NOT_EQUALS && leftType == DataType.STRING)
    		return String.format("(!%s.equals(%s))", left.toJavaCode(), right.toJavaCode());
    	else
    		return String.format("(%s%s %s %s)", cast, left.toJavaCode(), operator.toString(), right.toJavaCode());
    }

	@Override
	public DataType getType() {
		if (operator == Operator.AND || operator == Operator.OR)
			return DataType.BOOLEAN;
		else if (operator == Operator.DIVIDE && leftType == DataType.INTEGER && rightType == DataType.INTEGER)
			return DataType.REAL;
		else if (rightType == DataType.REAL)
			return rightType;
		else
			return leftType;
	}
}