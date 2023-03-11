package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.Node;

public interface Statement extends Node {
    boolean eval(boolean IsDone) throws EvalError;
}
