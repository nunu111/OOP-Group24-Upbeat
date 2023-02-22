package AST;

import GameProgress.AllCommand;
import GameProgress.Command;

public class moveAST implements Statement{
    AllCommand.Direction dir ;
    public moveAST(AllCommand.Direction _dir){
        this.dir = _dir;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().move(dir);
    }
}
