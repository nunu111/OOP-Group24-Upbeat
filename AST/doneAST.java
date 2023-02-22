package AST;

import GameProgress.Command;

public class doneAST implements Statement{
    public doneAST(){
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().done();
    }
}
