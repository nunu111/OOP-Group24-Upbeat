package AST;

import GameProgress.Command;

public class doneAST implements Statement{
    public doneAST(){
    }
    @Override
    public void eval() throws EvalError {
        Command.instance().done();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("done");
        sb.append("\n");
    }
}
