package program.statement;
import parser.ParseException;
import program.DataType;
import program.Statement;
import program.SymbolTable;
import program.expression.VariableReference;

public class InputStatement extends Statement {
    private VariableReference reference;

    public InputStatement(VariableReference reference) {
        this.reference = reference;
    }

    public VariableReference getReference() {
        return reference;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        reference.analyze(table);
    }
    
    public String getMainJavaCode(int indentLevel) {
    	// return String.format("%s = new Scanner(System.in).%s", reference.toJavaCode(), reference.getType().getScannerMethod());
    	String prompt = String.format("\"Enter %s %s: \"", reference.getType().toString(), reference.getName());
    	String commandLinePrompt = String.format("System.out.print(%s)", prompt);
    	String jOptionPanePrompt = String.format("JOptionPane.showInputDialog(null, %s)", prompt);
    	String valueAssignment;
    	if (reference.getType() == DataType.CHARACTER)
    		valueAssignment = String.format("%s = %s.charAt(0)",reference.toJavaCode(), jOptionPanePrompt);
    	else
    		valueAssignment = String.format("%s = %s.valueOf(%s)", reference.toJavaCode(), reference.getType().toJavaClass(), jOptionPanePrompt);
    	
    	return String.format("%s;\r\n\t%s;\r\n\tSystem.out.println(%s)", 
    			commandLinePrompt, valueAssignment, reference.getName());
    }
}
