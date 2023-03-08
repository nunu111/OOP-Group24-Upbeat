package AST.Expr;

import AST.EvalError;
import AST.Expr.Expr;
import AST.Variable_Storage;

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