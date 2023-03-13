package com.GAME.UPBEAT.AST.ASTExpr;


import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.GameProgress.AllCommand;
import com.GAME.UPBEAT.GameProgress.GameState;

import java.util.Map;

public class nearbyAST implements Expr {
    AllCommand.Direction dir;
    public nearbyAST(AllCommand.Direction _dir){
        this.dir = _dir;
    }

    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return GameState.instance().nearby(dir);
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("nearby");
        sb.append(dir.toString());
    }
}
