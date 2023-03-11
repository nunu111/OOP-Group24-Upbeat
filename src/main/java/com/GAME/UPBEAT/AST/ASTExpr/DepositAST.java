package com.GAME.UPBEAT.AST.ASTExpr;


import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.GameProgress.Command;

import java.util.Map;

public class DepositAST implements Expr {
    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return Command.instance().GetDeposit();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("deposit");
    }
}
