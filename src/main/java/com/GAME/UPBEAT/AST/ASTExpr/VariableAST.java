package com.GAME.UPBEAT.AST.ASTExpr;


import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.Variable_Storage;

import java.util.Map;

public class VariableAST implements Expr {
    private final String VarName;
    public VariableAST(String _VarName){
        this.VarName = _VarName;

    }

    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        if(!binding.containsKey(VarName)) Variable_Storage.instance().AssignVariable(VarName,(long)0);
        return binding.get(VarName);
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append(VarName);
    }
}
