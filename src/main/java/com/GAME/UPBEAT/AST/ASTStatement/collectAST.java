package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.AST.ASTStatement.Statement;
import com.GAME.UPBEAT.AST.Variable_Storage;
import com.GAME.UPBEAT.GameProgress.Command;

public class collectAST implements Statement {
    private final Expr CollectValue;
    public collectAST(Expr _CollectValue){
        this.CollectValue = _CollectValue;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if (IsDone) return true;
        return Command.instance().collect(CollectValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("collect");
        sb.append("\n");
    }
}
