package AST;

import GameProgress.Command;

public class investAST implements Statement{
    private Expr InvestValue;
    public investAST(Expr _InvestValue){
        this.InvestValue = _InvestValue;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().invest(InvestValue.eval(Variable_Storage.instance().GetVariableMap()));
    }
}
