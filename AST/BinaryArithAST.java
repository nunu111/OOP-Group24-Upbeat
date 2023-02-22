package AST;

import java.util.Map;

public class BinaryArithAST implements Expr{
    private Expr left,right;
    private String op;
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
}
