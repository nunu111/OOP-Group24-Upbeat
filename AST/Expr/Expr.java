package AST.Expr;

import AST.EvalError;
import AST.Node;

import java.util.Map;

public interface Expr extends Node {
    long eval(Map<String,Long> binding ) throws EvalError;
}

