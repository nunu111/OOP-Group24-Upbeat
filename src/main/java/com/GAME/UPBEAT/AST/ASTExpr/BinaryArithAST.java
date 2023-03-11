package com.GAME.UPBEAT.AST.ASTExpr;

import com.GAME.UPBEAT.AST.EvalError;

import java.util.Map;

public class BinaryArithAST implements Expr {
    private final Expr left,right;
    private final String op;
    public BinaryArithAST(Expr _left,String _op,Expr _right){
        this.left = _left;
        this.op = _op;
        this.right = _right;
    }

    public long eval(Map<String,Long> binding) throws EvalError {
        long lv =left.eval(binding);
        long rv= right.eval(binding);
        if(op.equals("+")) return lv+rv;
        if(op.equals("-")) return lv-rv;
        if(op.equals("*")) return lv*rv;
        if(op.equals("/")) return lv/rv;
        if(op.equals("%")) return lv%rv;
        if(op.equals("^")) return (long)Math.pow(lv,rv);
        throw new EvalError("unknown op: " + op);

    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("(");
        left.prettyPrint(sb);
        sb.append(op);
        right.prettyPrint(sb);
        sb.append(")");
    }
}
