package program;

public enum DataType {
    INTEGER("int", "0", "Integer"),
    REAL("double", "0.0", "Double"),
    BOOLEAN("boolean", "false", "Boolean"),
    CHARACTER("char", "'\u0000'", "Character"),
    STRING("String", "\"\"", "String");

    private String javaType;
    private String initialValue;
    private String javaClassName;

    private DataType(String javaType, String initialValue, String className) {
        this.javaType = javaType;
        this.initialValue = initialValue;
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
}