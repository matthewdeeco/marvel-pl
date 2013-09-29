package program.statement;

import parser.ParseException;
import program.Block;
import program.Expression;
import program.Statement;
import program.SymbolTable;

public class WhileStatement extends Statement {

    private Expression condition;
    private Block body;

    public WhileStatement(Expression condition, Block body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void analyze(SymbolTable table) throws ParseException {
        condition.analyze(table);
        body.analyze(table);
    }

    @Override
    public String toUnformattedJavaCode(int indentLevel) {
        return String.format("while (%s) {\r\n%s%s}", condition.toConditionalJavaCode(), body.toJavaCode(indentLevel + 1), indent(indentLevel));
    }
}