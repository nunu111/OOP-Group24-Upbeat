package AST;

import GameProgress.Command;

public class collectAST implements Statement{
    private Expr CollectValue;
    public collectAST(Expr _CollectValue){
        this.CollectValue = _CollectValue;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().collect(CollectValue.eval(Variable_Storage.instance().GetVariableMap()));
    }
}
