package AST;

import java.util.ArrayList;

public class PlanAST implements BodyStatement{
    private final ArrayList<Statement> AllStatement;
    public PlanAST(){
        AllStatement = new ArrayList<>();
    }
    public void StatementUpdate(Statement _AllStatement){
        this.AllStatement.add(_AllStatement);
    }
    @Override
    public void eval() throws EvalError {
        for(Statement key : AllStatement) key.eval();
    }
}
