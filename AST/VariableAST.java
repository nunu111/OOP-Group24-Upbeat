package AST;

import java.util.Map;

public class VariableAST implements Expr {
    private final String VarName;
    public VariableAST(String _VarName){
        this.VarName = _VarName;

    }

    @Override
    public double eval(Map<String, Double> binding) throws EvalError {
        if(!binding.containsKey(VarName)) Variable_Storage.instance().AssignVariable(VarName,0);
        return binding.get(VarName);
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append(VarName);
    }
}
