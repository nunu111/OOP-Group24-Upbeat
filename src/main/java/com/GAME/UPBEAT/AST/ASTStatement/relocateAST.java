package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.ASTStatement.Statement;
import com.GAME.UPBEAT.GameProgress.Command;

public class relocateAST implements Statement {
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return Command.instance().relocate();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("relocate");
        sb.append("\n");
    }
}
