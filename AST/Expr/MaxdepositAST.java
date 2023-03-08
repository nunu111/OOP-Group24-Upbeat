package AST.Expr;

import AST.EvalError;
import GameProgress.Command;

import java.util.Map;

public class MaxdepositAST implements Expr {
    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return Command.instance().GetMaxDeposit();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("maxdeposit");
    }
}
