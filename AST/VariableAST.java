package AST;

import java.util.Map;

public class VariableAST implements Expr {
    private String VarName;
    public VariableAST(String _VarName){
        this.VarName = _VarName;
    }

    @Override
    public double eval(Map<String, Double> binding) throws EvalError {
        if(binding.containsKey(VarName)) return binding.get(VarName);
        else throw  new EvalError("undefined variable: " + VarName);
    }
}
