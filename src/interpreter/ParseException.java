package interpreter;

/**
 * Indicates that the exception was caused by a syntactic error in reading the
 * script. The error message will indicate the cause.
 */
public class ParseException extends Exception {
	private String message;
	
	public ParseException(String message) {
		this.message = message;
	}
	
	public ParseException(bsh.ParseException e) {
		message = e.getMessage();
	}
	
	public String getMessage() {
		return message;
	}
}
