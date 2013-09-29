package program;

import program.expression.BinaryExpression;
import program.expression.UnaryExpression;

public enum DataType {
    INTEGER("int", "0", "nextInt()", "Integer"),
    REAL("double", "0.0", "nextDouble()", "Double"),
    BOOLEAN("boolean", "false", "nextBoolean()", "Boolean"),
    CHARACTER("char", "'\u0000'", "nextLine().charAt(0)", "Character"),
    STRING("String", "\"\"", "nextLine()", "String");

    private String javaType;
    private String initialValue;
    private String javaScannerMethod;
    private String javaClassName;

    private DataType(String javaType, String initialValue, String scannerMethod, String className) {
        this.javaType = javaType;
        this.initialValue = initialValue;
        this.javaScannerMethod = scannerMethod;
        this.javaClassName = className;
    }
    
    public String toJavaClass() {
    	return javaClassName;
    }

    public String toJavaCode() {
        return javaType;
    }
    
    @Override
    public String toString() {
    	return this.name().toLowerCase();
    }
    
    public String getInitialValue() {
    	return initialValue;
    }
    
    public String getScannerMethod() {
    	return javaScannerMethod;
    }
    
    public boolean isCompatibleWith(DataType other) {
    	if (this == null || other == null)
    		return false;
    	else if (this == other)
    		return true;
    	else if ((this == INTEGER && other == REAL) || (other == INTEGER && this == REAL))
    		return true;
    	else
    		return false;
    }
    
    public boolean isCompatibleWith(BinaryExpression.Operator operator) {
    	switch (operator) {
    		case PLUS:
    			return (this == INTEGER || this == REAL || this == STRING);
    		case MINUS:
    		case TIMES:
    		case DIVIDE:
    			return (this == INTEGER || this == REAL);
			case AND:
			case OR:
			case NOT:
			case EQUALS:
			case NOT_EQUALS:
				return true;
			case GREATER_THAN_OR_EQUAL_TO:
			case GREATER_THAN:
			case LESS_THAN:
			case LESS_THAN_OR_EQUAL_TO:
    			return (this == INTEGER || this == REAL);
			default:
				return false;
    	}
    }

	public boolean isCompatibleWith(UnaryExpression.Operator operator) {
		switch(operator) {
			case POSITIVE:
				return (this == INTEGER || this == REAL);
			case NEGATIVE:
				return (this == INTEGER || this == REAL);
			case NOT:
				return true;
			default:
				return false;
		}
	}
}