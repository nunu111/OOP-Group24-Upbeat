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

    public double eval(Map<String,Double> binding) throws EvalError {
        double lv =left.eval(binding);
        double rv= right.eval(binding);
        if(op.equals("+")) return lv+rv;
        if(op.equals("-")) return lv-rv;
        if(op.equals("*")) return lv*rv;
        if(op.equals("/")) return lv/rv;
        if(op.equals("%")) return lv%rv;
        if(op.equals("^")) return Math.pow(lv,rv);
        throw new EvalError("unknown op: " + op);

    }
}
