package com.GAME.UPBEAT.AST.ASTExpr;


import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.GameProgress.GameState;

import java.util.Map;

public class IntAST implements Expr {
    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return GameState.instance().GetInterest();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("int");
    }
}
