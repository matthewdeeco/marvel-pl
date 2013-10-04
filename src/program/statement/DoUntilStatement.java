package program.statement;

import program.Block;
import program.Expression;

public class DoUntilStatement extends DoWhileStatement{
	
	public DoUntilStatement(Expression condition, Block body) {
		super(condition, body);
	}

    @Override
    public String conditionAsJavaCode() {
        return String.format("!(%s)", super.conditionAsJavaCode());
    }
}
