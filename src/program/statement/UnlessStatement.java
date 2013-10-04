
package program.statement;

import parser.ParseException;
import program.*;

public class UnlessStatement extends Statement {

	private Expression condition;
	private Block action;

    public UnlessStatement(Expression condition, Block action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        condition.analyze(table);
        action.analyze(table);
    }

    @Override
    public String getMainJavaCode(int indentLevel) {
    	return String.format("if (!(%s)) {%s\t}",
    			condition.toConditionalJavaCode(),
    			action.toJavaCode(indentLevel + 1));
    }
    
    @Override
    public String getJavaDeclarations() {
		return action.getJavaDeclarations();
	}
}