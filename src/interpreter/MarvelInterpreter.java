package interpreter;

import java.io.StringReader;
import parser.Parser;
import program.*;
import logger.Logger;

public class MarvelInterpreter implements Interpreter {

	private JavaInterpreter interpreter;
	private Logger logger;

	public MarvelInterpreter(Logger logger) {
		this.logger = logger;
	}

	@Override
	public Object evaluate(String code) throws CodeException, ParseException {
		interpreter = new JavaInterpreter();
		Parser parser = new Parser(new StringReader(code));
		Program program = parser.parse(logger);
		if (program == null)
			return null;
		String declarations = program.getJavaDeclarations();
		String javaCode = program.toJavaCode();
		/*logger.message("======  Declarations  =====");
		logger.message(declarations);
		logger.message("======Java Translation=====");
		logger.message(javaCode); */
		interpreter.evaluate(declarations);
		return interpreter.evaluate(javaCode);
	}

	@Override
	public Object get(String name) throws CodeException, ParseException {
		return interpreter.get(name);
	}

	@Override
	public String translateToJavaSource(String className, String code) throws CodeException, ParseException {
		Parser parser = new Parser(new StringReader(code));
		Program program = parser.parse(logger);

		if (program == null)
			return null;
		else
			return String.format("import javax.swing.JOptionPane;\r\n\r\n" +
					"class %s {\r\n\r\n" +
					"\t%s" +
					"\tpublic static void main(String[] args) {\r\n" +
					"%s\t}\r\n}", 
					className, program.getJavaDeclarations(), program.toJavaCode());
	}

}
