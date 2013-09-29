package program;

import parser.ParseException;


public class Program {

    private Block block;

    public Program(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void analyze(SymbolTable table) throws ParseException {
        block.analyze(table);
    }

	public String toJavaCode() {
		return block.toJavaCode(0);
	}

	public void analyze() throws ParseException {
		analyze(new SymbolTable());
	}
}
