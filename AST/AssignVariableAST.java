package AST;

public class AssignVariableAST implements Statement{
    private final String VarName;
    private final Expr VarValue;
    private final Variable_Storage assign = Variable_Storage.instance();

    public AssignVariableAST(String _VarName,Expr _VarValue){
        this.VarName = _VarName;
        this.VarValue = _VarValue;
    }

    public void eval() throws EvalError {
        assign.AssignVariable(VarName,VarValue);
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append(VarName);
        sb.append(" = ");
        VarValue.prettyPrint(sb);
        sb.append("\n");
    }
}
