package program.statement;

import java.util.List;

import program.Expression;

public class PutsStatement extends PrintStatement {

	public PutsStatement(List<Expression> expressions) {
		super(expressions);
	}

    @Override
    public String toUnformattedJavaCode(int indentLevel) {
        return String.format("System.out.println(%s)", expressionsAsJavaCode());
    }
}
