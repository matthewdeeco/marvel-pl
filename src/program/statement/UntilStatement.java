package program.statement;

import program.Block;
import program.Expression;

public class UntilStatement extends WhileStatement {

    public UntilStatement(Expression condition, Block body) {
        super(condition, body);
    }

    @Override
    public String conditionAsJavaCode() {
        return String.format("!(%s)", super.conditionAsJavaCode());
    }
}