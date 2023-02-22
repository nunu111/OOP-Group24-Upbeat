package AST;
import GameProgress.Command;

import java.util.Map;

public class opponentAST implements Expr{

    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return new Command().opponent();
    }
}
