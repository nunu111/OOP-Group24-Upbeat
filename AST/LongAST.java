package AST;

import java.util.Map;

public class LongAST implements Expr{
    private final long val;
    public LongAST(long _val){
        this.val = _val;
    }
    @Override
    public double eval(Map<String, Double> binding){
        return val;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append(val);
    }
}
