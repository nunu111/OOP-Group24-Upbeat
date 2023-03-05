package AST;

import GameProgress.Command;

public class collectAST implements Statement{
    private final Expr CollectValue;
    public collectAST(Expr _CollectValue){
        this.CollectValue = _CollectValue;
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().collect(CollectValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("collect");
        sb.append("\n");
    }
}
