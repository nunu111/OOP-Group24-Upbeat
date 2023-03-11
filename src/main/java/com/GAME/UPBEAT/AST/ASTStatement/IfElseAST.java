package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.AST.ASTStatement.Statement;
import com.GAME.UPBEAT.AST.Variable_Storage;

public class IfElseAST implements Statement {
    private final Expr condition;
    private final Statement ifStatement;
    private final Statement elseStatement;
    public IfElseAST(Expr _condition,Statement _ifStatement,Statement _elseStatement){
        this.condition = _condition;
        this.ifStatement = _ifStatement;
        this.elseStatement = _elseStatement;
    }


    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        if(condition.eval(Variable_Storage.instance().GetVariableMap()) >0 ) {
            if(ifStatement != null) return ifStatement.eval(IsDone);
        }
        else {
            if(elseStatement != null) return elseStatement.eval(IsDone);
        }
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("if (");
        condition.prettyPrint(sb);
        sb.append(") then ");
        ifStatement.prettyPrint(sb);
        sb.append("else ");
        elseStatement.prettyPrint(sb);
        sb.append("\n");
    }
}
