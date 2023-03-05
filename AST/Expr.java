package AST;

import java.util.Map;

public interface Expr extends Node{
    double eval(Map<String,Double> binding) throws EvalError;
}

