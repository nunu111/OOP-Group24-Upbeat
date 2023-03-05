package AST;

import GameProgress.Command;

public class relocateAST implements Statement{
    @Override
    public void eval() throws EvalError {
        Command.instance().relocate();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("relocate");
        sb.append("\n");
    }
}
