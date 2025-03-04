package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.GameProgress.GameState;

public class doneAST implements Statement {
    public doneAST(){
    }

    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if (IsDone) return true;
        return GameState.instance().done();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("done");
        sb.append("\n");
    }
}
