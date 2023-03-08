package AST.Expr;

import AST.EvalError;
import GameProgress.Command;

import java.util.Map;

public class CurcolAST implements Expr {
    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return Command.instance().GetCurcol();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("curcol");
    }
}
