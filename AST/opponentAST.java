package AST;
import GameProgress.Command;

import java.util.Map;

public class opponentAST implements Expr{

    @Override
    public double eval(Map<String, Double> binding) throws EvalError {
        return Command.instance().opponent();
    }
}
