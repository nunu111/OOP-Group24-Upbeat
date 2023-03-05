package AST;

import java.util.Map;

public class BinaryArithAST implements Expr{
    private final Expr left,right;
    private final String op;
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

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("(");
        left.prettyPrint(sb);
        sb.append(op);
        right.prettyPrint(sb);
        sb.append(")");
    }
}
