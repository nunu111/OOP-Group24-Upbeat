package AST;

import java.util.Map;

public interface Expr {
    public double eval(Map<String,Double> binding) throws EvalError;
}

