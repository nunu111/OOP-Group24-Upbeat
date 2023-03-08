package AST.Expr;

import AST.Expr.Expr;

import java.util.Map;

public class LongAST implements Expr {
    private final long val;
    public LongAST(long _val){
        this.val = _val;
    }
    @Override
    public long eval(Map<String, Long> binding){
        return val;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append(val);
    }
}
