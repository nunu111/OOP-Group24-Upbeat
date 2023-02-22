package AST;

public class AssignVariableAST implements Statement{
    private String VarName;
    private Expr VarValue;
    private Variable_Storage assign = Variable_Storage.instance();
    public AssignVariableAST(String _VarName,Expr _VarValue){
        this.VarName = _VarName;
        this.VarValue = _VarValue;
    }

    public void eval() {
        assign.AssignVariable(VarName,VarValue);
    }
}
