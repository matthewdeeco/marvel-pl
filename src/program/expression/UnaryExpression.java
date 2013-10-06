package program.expression;

import parser.ParseException;
import program.DataType;
import program.Expression;
import program.SymbolTable;

public class UnaryExpression extends Expression {

    public static enum Operator {
        POSITIVE("+"), NEGATIVE("-"), NOT("!");

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
    private Expression operand;
    private DataType type;

    public UnaryExpression(Operator op, Expression operand) {
        this.operator = op;
        this.operand = operand;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        operand.analyze(table);
        type = operand.getType();
        checkCompatibility();
    }
    
    private void checkCompatibility() throws ParseException {
        if (! isTypeCompatible())
        	throw new ParseException(String.format("%s -> Unsupported operation: %s and '%s'", toJavaCode(), type.toString(), operator.toString()));
    }

	private boolean isTypeCompatible() {
		switch(operator) {
			case POSITIVE:
			case NEGATIVE:
				return (type == DataType.INTEGER || type == DataType.REAL);
			case NOT:
				return true;
			default:
				return false;
		}
	}
    
    @Override
    public String toJavaCode() {
    	if (operator == Operator.NOT)
    		return String.format("%s(%s)", operator.toString(), operand.toConditionalJavaCode());
    	else
    		return String.format("%s(%s)", operator.toString(), operand.toJavaCode());
    }

	@Override
	public DataType getType() {
		if (operator == Operator.NOT)
			return DataType.BOOLEAN;
		else
			return type;
	}
}