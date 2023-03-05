package AST;

import GameProgress.AllCommand;
import GameProgress.Command;

public class shootAST implements Statement{
    private final AllCommand.Direction dir;
    private final Expr ATKValue;
    public shootAST(AllCommand.Direction _dir,Expr _ATKValue){
        this.dir =_dir;
        this.ATKValue = _ATKValue;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().shoot(dir, (long) ATKValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("shoot ");
        ATKValue.prettyPrint(sb);
        sb.append("\n");
    }
}
