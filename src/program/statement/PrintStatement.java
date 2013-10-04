package program.statement;

import java.util.ArrayList;
import java.util.List;

import parser.ParseException;
import program.Expression;
import program.Statement;
import program.SymbolTable;

public class PrintStatement extends Statement {

    private List<Expression> expressions;

    public PrintStatement() {
        expressions = new ArrayList<Expression>();
    }
    
    public void addExpression(Expression expression) {
    	expressions.add(expression);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        for (Expression expression: expressions) {
            expression.analyze(table);
        }
    }

    @Override
    public String getMainJavaCode(int indentLevel) {
        return String.format("System.out.print(%s)", expressionsAsJavaCode());
    }
    
    protected String expressionsAsJavaCode() {
    	String result = ""; 
    	int size = expressions.size();
    	for (int i = 0; i < size; i++) {
    		Expression e = expressions.get(i);
    		result += e.toJavaCode();
    		if (i < size - 1)
    			result += " + \" \" + ";
    	}
    	return result;
    }
}
