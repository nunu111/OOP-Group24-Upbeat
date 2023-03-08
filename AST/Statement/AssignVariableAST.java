package AST.Statement;

import AST.EvalError;
import AST.Expr.Expr;
import AST.Variable_Storage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AssignVariableAST implements Statement {
    private final String VarName;
    private final Expr VarValue;
    private final Variable_Storage assign = Variable_Storage.instance();
    private final Set<String> SpecialVariableSet=new HashSet<>();
    public AssignVariableAST(String _VarName,Expr _VarValue){
        SpecialVariableSet.addAll(Arrays.asList("rows", "cols", "currow", "curcol", "budget", "deposit", "int", "maxdeposit", "random"));
        this.VarName = _VarName;
        this.VarValue = _VarValue;
    }

    public boolean eval(boolean IsDone) throws EvalError {
        if(IsDone) return true;
        if(!SpecialVariableSet.contains(VarName)) assign.AssignVariable(VarName,VarValue);
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append(VarName);
        sb.append(" = ");
        VarValue.prettyPrint(sb);
        sb.append("\n");
    }
}
