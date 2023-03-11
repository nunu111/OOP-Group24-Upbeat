package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.Variable_Storage;

public class WhileAST implements Statement {
    private final Expr condition;
    private final Statement statement;
    public WhileAST(Expr _condition,Statement _statement){
        this.condition = _condition;
        this.statement = _statement;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(statement == null) throw new EvalError("While statement has empty body");
        if (IsDone) return true;
        for(int counter = 0; counter<10000 && condition.eval(Variable_Storage.instance().GetVariableMap())>0; counter++){
            if(IsDone) return true;
            IsDone = this.statement.eval(IsDone);
        }
        return IsDone;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("while(");
        condition.prettyPrint(sb);
        sb.append(")");
        statement.prettyPrint(sb);
        sb.append("\n");
    }
}
