package AST.Statement;

import AST.EvalError;
import AST.Statement.Statement;
import GameProgress.Command;

public class relocateAST implements Statement {
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return Command.instance().relocate();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("relocate");
        sb.append("\n");
    }
}
