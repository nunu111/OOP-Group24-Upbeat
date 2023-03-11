package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.AST.ASTStatement.Statement;
import com.GAME.UPBEAT.AST.Variable_Storage;
import com.GAME.UPBEAT.GameProgress.Command;

public class investAST implements Statement {
    private final Expr InvestValue;
    public investAST(Expr _InvestValue){
        this.InvestValue = _InvestValue;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return Command.instance().invest(InvestValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("invest ");
        InvestValue.prettyPrint(sb);
        sb.append("\n");
    }
}
