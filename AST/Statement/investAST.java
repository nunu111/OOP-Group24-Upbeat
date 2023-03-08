package AST.Statement;

import AST.EvalError;
import AST.Expr.Expr;
import AST.Statement.Statement;
import AST.Variable_Storage;
import GameProgress.Command;

public class investAST implements Statement {
    private final Expr InvestValue;
    public investAST(Expr _InvestValue){
        this.InvestValue = _InvestValue;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return Command.instance().invest(InvestValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("invest ");
        InvestValue.prettyPrint(sb);
        sb.append("\n");
    }
}
