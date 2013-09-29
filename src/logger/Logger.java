package logger;

import java.io.PrintStream;
import java.io.PrintWriter;

public class Logger {
	private PrintWriter out, err;
    private int errorCount = 0;
	
	public Logger(PrintWriter out, PrintWriter err) {
		this.out = out;
		this.err = err;
	}
	
	public Logger(PrintStream out, PrintStream err) {
		this(new PrintWriter(out, true), new PrintWriter(err, true));
	}
	
	public int getErrorCount() {
        return errorCount;
    }

    public void message(String message, Object...args) {
        out.println(String.format(message, args));
    }

    public void error(String message, Object...args) {
        errorCount++;
        err.println(String.format(message, args));
    }

    public void exception(Throwable t) {
    	if (t.getMessage() != null)
    		error(t.getMessage());
    }
    
    public void setErr(PrintStream err) {
    	this.err = new PrintWriter(err, true);
    }
    
    public void setOut(PrintStream out) {
    	this.out = new PrintWriter(out, true);
    }
}
