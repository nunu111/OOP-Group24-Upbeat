package AST;

import java.util.Map;

public interface Expr {
    public long eval(Map<String,Long> binding) throws EvalError;
}

