package program;

import java.util.*;

import parser.ParseException;

public class Block {
    private List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void analyze(SymbolTable outer) throws ParseException {
        SymbolTable table = new SymbolTable(outer);
        for (Statement s: statements) {
            s.analyze(table);
        }
    }

	public String toJavaCode(int indentLevel) {
		StringBuilder sb = new StringBuilder();
		for (Statement s: statements) {
			sb.append(s.toJavaCode(indentLevel));
		}
		return sb.toString();
	}
}