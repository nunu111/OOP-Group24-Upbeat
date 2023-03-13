package com.GAME.UPBEAT.AST.ASTStatement;

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.AST.Variable_Storage;
import com.GAME.UPBEAT.GameProgress.AllCommand;
import com.GAME.UPBEAT.GameProgress.GameState;

public class shootAST implements Statement {
    private final AllCommand.Direction dir;
    private final Expr ATKValue;
    public shootAST(AllCommand.Direction _dir,Expr _ATKValue){
        this.dir =_dir;
        this.ATKValue = _ATKValue;
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        return GameState.instance().shoot(dir, ATKValue.eval(Variable_Storage.instance().GetVariableMap()));
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("shoot ");
        ATKValue.prettyPrint(sb);
        sb.append("\n");
    }
}
