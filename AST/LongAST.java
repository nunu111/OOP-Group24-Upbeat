package AST;

import java.util.Map;

public class LongAST implements Expr{
    private long val;
    public LongAST(long _val){
        this.val = _val;
    }
    @Override
    public long eval(Map<String, Long> binding){
        return val;
    }
}
