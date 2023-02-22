package AST;

import GameProgress.Command;

public class relocateAST implements Statement{
    public relocateAST(){

    }
    @Override
    public void eval() throws EvalError {
        Command.instance().relocate();
    }
}
