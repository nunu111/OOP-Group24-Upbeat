package AST;

import java.util.Map;

public interface Expr extends Node{
    long eval(Map<String,Long> binding) throws EvalError;
}

