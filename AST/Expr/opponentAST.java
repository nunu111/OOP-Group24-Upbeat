package AST.Expr;
import AST.EvalError;
import AST.Expr.Expr;
import GameProgress.Command;

import java.util.Map;

public class opponentAST implements Expr {

    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return Command.instance().opponent();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("opponent");
        sb.append("\n");
    }
}
