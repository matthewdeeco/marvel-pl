package program.statement;

import java.util.*;
import java.util.Map.Entry;

import parser.ParseException;
import program.*;

public class IfStatement extends Statement {

	private Map<Expression, Block> actionMap;
    private Block elseBlock;

    public IfStatement() {
        actionMap = new LinkedHashMap<Expression, Block>();
    }
    
    public void addCondition(Expression condition, Block action) {
    	actionMap.put(condition, action);
    }
    
    public void setElseBlock(Block elseBlock) {
    	this.elseBlock = elseBlock;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        for (Expression expression : actionMap.keySet())
        	expression.analyze(table);
        for (Block block : actionMap.values())
        	block.analyze(table);
        if (elseBlock != null)
        	elseBlock.analyze(table);
    }

    @Override
    public String toUnformattedJavaCode(int indentLevel) {
    	String result = ""; 
    	int i = 0;
    	for (Entry<Expression, Block> entry: actionMap.entrySet()) {
    		Expression condition = entry.getKey();
    		Block action = entry.getValue();
    		
    		if (i++ > 0) // else if block
    			result += " else ";
    		result += String.format("if (%s) {\r\n%s%s}", condition.toConditionalJavaCode(), action.toJavaCode(indentLevel + 1), indent(indentLevel));
    	}
    	
    	if (elseBlock != null)
    		result += String.format(" else {\r\n%s%s}", elseBlock.toJavaCode(indentLevel + 1), indent(indentLevel));
    	return result;
    }
}