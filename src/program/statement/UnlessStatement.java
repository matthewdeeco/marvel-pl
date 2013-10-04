
package program.statement;

import java.util.*;
import java.util.Map.Entry;

import parser.ParseException;
import program.*;

public class UnlessStatement extends Statement {

	private Map<Expression, Block> actionMap;

    public UnlessStatement() {
        actionMap = new LinkedHashMap<Expression, Block>();
    }
    
    public void addCondition(Expression condition, Block action) {
    	actionMap.put(condition, action);
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        for (Expression expression : actionMap.keySet())
        	expression.analyze(table);
        for (Block block : actionMap.values())
        	block.analyze(table);
    }

    @Override
    public String toUnformattedJavaCode(int indentLevel) {
    	String result = "";
    	for (Entry<Expression, Block> entry: actionMap.entrySet()) {
    		Expression condition = entry.getKey();
    		Block action = entry.getValue();
    		result += String.format("if (!%s) {\r\n%s%s}", condition.toConditionalJavaCode(), action.toJavaCode(indentLevel + 1), indent(indentLevel));
    	}
    	
    	return result;
    }
}