package program.statement;

public class PutsStatement extends PrintStatement {

    @Override
    public String getMainJavaCode(int indentLevel) {
        return String.format("System.out.println(%s)", expressionsAsJavaCode());
    }
}
