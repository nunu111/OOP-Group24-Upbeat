package AST;

public class AssignVariableAST implements Statement{
    private String VarName;
    private Expr VarValue;
    private Variable_Storage assign = Variable_Storage.instance();
    private String[] SpecialVariable ={"rows","cols","currow","curcol","budget","deposit","int","maxdeposit","random"};
    public AssignVariableAST(String _VarName,Expr _VarValue){
        this.VarName = _VarName;
        this.VarValue = _VarValue;
    }

    private boolean IsNotSpecialVariable(String str){
    for(String key :SpecialVariable) {
        if(key.equals(str)) return false;
    }
    return true;
    }

    public void eval() throws EvalError {

        if(IsNotSpecialVariable(VarName))assign.AssignVariable(VarName,VarValue);
    }
}
