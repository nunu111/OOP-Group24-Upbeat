package AST;

import GameProgress.Command;

public class doneAST implements Statement{
    public doneAST(){
    }

    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if (IsDone) return true;
        return Command.instance().done();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("done");
        sb.append("\n");
    }
}
