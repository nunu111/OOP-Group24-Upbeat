package AST;

public class IfElseAST implements Statement{
    private Expr condition;
    private Statement ifStatement,elseStatement;
    public IfElseAST(Expr _condition,Statement _ifStatement,Statement _elseStatement){
        this.condition = _condition;
        this.ifStatement = _ifStatement;
        this.elseStatement = _elseStatement;
    }


    @Override
    public void eval() throws EvalError {
        if(condition.eval(Variable_Storage.instance().GetVariableMap()) >0 ) {
            if(ifStatement != null)ifStatement.eval();
        }
        else {
            if(elseStatement != null)elseStatement.eval();
        }
    }
}
