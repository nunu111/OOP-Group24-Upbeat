package AST;

import GameProgress.Game;

public class WhileAST implements Statement{
    private Expr condition;
    private Statement statement;
    @Override
    public void eval() throws EvalError {
        for(int counter=0;counter<10000 && condition.eval()>0;counter++){
            statement.eval();
        }
    }
}
