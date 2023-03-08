package AST;

import GameProgress.Command;

public class investAST implements Statement{
    private final Expr InvestValue;
    public investAST(Expr _InvestValue){
        this.InvestValue = _InvestValue;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        Command.instance().invest(InvestValue.eval(Variable_Storage.instance().GetVariableMap()));
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("invest ");
        InvestValue.prettyPrint(sb);
        sb.append("\n");
    }
}
