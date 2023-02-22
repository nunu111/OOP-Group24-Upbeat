package AST;

public class WhileAST implements Statement{
    private Expr condition;
    private Statement statement;
    public WhileAST(Expr _condition,Statement _statement){
        this.condition = _condition;
        this.statement = _statement;
    }
    @Override
    public void eval() throws EvalError {
        if(statement == null) throw new EvalError("While statement has empty body");
        for(int counter=0;counter<10000 && condition.eval(Variable_Storage.instance().GetVariableMap())>0;counter++){
            this.statement.eval();
        }
    }
}
