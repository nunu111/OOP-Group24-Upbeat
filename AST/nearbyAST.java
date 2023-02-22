package AST;

import GameProgress.AllCommand;
import GameProgress.Command;

import java.util.Map;

public class nearbyAST implements Expr{
    AllCommand.Direction dir;
    public nearbyAST(AllCommand.Direction _dir){
        this.dir = _dir;
    }

    @Override
    public double eval(Map<String, Double> binding) throws EvalError {
        return Command.instance().opponent();
    }
}
