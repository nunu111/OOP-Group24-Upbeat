package AST.Statement;

import AST.EvalError;
import GameProgress.AllCommand;
import GameProgress.Command;

public class moveAST implements Statement {
    AllCommand.Direction dir ;
    public moveAST(AllCommand.Direction _dir){
        this.dir = _dir;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return Command.instance().move(dir);
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("move ");
        sb.append(dir.toString());
        sb.append("\n");
    }
}
