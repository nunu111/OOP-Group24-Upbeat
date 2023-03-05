package AST;

import GameProgress.Command;

public class investAST implements Statement{
    private final Expr InvestValue;
    public investAST(Expr _InvestValue){
        this.InvestValue = _InvestValue;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().invest(InvestValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("invest ");
        InvestValue.prettyPrint(sb);
        sb.append("\n");
    }
}
