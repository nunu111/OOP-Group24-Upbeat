package com.GAME.UPBEAT.AST.ASTExpr;


import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.Node;

import java.util.Map;

public interface Expr extends Node {
    long eval(Map<String,Long> binding ) throws EvalError;
}

