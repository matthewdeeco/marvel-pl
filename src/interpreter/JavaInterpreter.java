package interpreter;

import bsh.*;

public class JavaInterpreter implements Interpreter {
	private bsh.Interpreter interpreter;

	public JavaInterpreter() {
		interpreter = new bsh.Interpreter();
		interpreter.setStrictJava(true);
	}
	
	@Override
	public Object evaluate(String code) throws CodeException, ParseException {
		try {
			return interpreter.eval(code);
		} catch (TargetError e) { // exception is thrown by codeBody
			throw new CodeException(e);
		} catch (bsh.ParseException e) {
			throw new ParseException(e);
		} catch (EvalError e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Object get(String name) throws CodeException, ParseException {
		try {
			return interpreter.get(name);
		} catch (TargetError e) { // exception is thrown by codeBody
			throw new CodeException(e);
		} catch (bsh.ParseException e) {
			throw new ParseException(e);
		} catch (EvalError e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String translateToJavaSource(String className, String code) throws CodeException, ParseException {
		return String.format("class %s {public static void main(String[] args){%s}}", className, code);
	}
}