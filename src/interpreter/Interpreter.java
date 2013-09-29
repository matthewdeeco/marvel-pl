package interpreter;

public interface Interpreter {
	/**
	 * @param code
	 * @return last evaluated expression
	 */
	public abstract Object evaluate(String code) throws CodeException, ParseException;
	
	/**
	 * @param className TODO
	 * @param code
	 * @return a java translation of code
	 */
	public abstract String translateToJavaSource(String className, String code) throws CodeException, ParseException;

	/**
	 * @param name variable name
	 * @return variable value
	 */
	public abstract Object get(String name) throws CodeException, ParseException;

}