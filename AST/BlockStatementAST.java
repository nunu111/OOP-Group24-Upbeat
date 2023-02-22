package AST;

import java.util.ArrayList;

public class BlockStatementAST implements BodyStatement{
    private final ArrayList<Statement> AllBodyStatement;

    public BlockStatementAST(){
        this.AllBodyStatement = new ArrayList<>();
    }
    @Override
    public void StatementUpdate(Statement _Statement) {
        this.AllBodyStatement.add(_Statement);
    }
    @Override
    public void eval() throws EvalError {
        for(Statement key : AllBodyStatement) key.eval();
    }
}
