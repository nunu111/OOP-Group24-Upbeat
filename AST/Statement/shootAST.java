package AST.Statement;

import AST.EvalError;
import AST.Expr.Expr;
import AST.Statement.Statement;
import AST.Variable_Storage;
import GameProgress.AllCommand;
import GameProgress.Command;

public class shootAST implements Statement {
    private final AllCommand.Direction dir;
    private final Expr ATKValue;
    public shootAST(AllCommand.Direction _dir,Expr _ATKValue){
        this.dir =_dir;
        this.ATKValue = _ATKValue;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return Command.instance().shoot(dir, ATKValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("shoot ");
        ATKValue.prettyPrint(sb);
        sb.append("\n");
    }
}
