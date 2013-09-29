package interpreter;
import bsh.TargetError;

/**
 * Indicates that the exception was not related to the evaluation of the script,
 * but caused the by script itself. For example, the script may have explicitly
 * thrown an exception or it may have caused an application level exception such
 * as a NullPointer exception or an ArithmeticException.
 **/
public class CodeException extends Exception {
	private Throwable error;
	
	public CodeException(TargetError te) {
		error = te.getTarget();
	}
	
	@Override
	public String getMessage() {
		return error.getMessage();
	}
}