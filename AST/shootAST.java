package AST;

import GameProgress.AllCommand;
import GameProgress.Command;

public class shootAST implements Statement{
    private AllCommand.Direction dir;
    private Expr ATKValue;
    public shootAST(AllCommand.Direction _dir,Expr _ATKValue){
        this.dir =_dir;
        this.ATKValue = _ATKValue;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().shoot(dir, (long) ATKValue.eval(Variable_Storage.instance().GetVariableMap()));
    }
}
