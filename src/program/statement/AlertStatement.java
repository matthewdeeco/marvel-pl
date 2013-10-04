package program.statement;

public class AlertStatement extends PrintStatement {
	
	@Override
	public String getMainJavaCode(int indentLevel) {
		return String.format("JOptionPane.showMessageDialog(null, %s)", expressionsAsJavaCode());
	}

}
