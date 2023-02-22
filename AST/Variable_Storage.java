package AST;

import GameProgress.Command;
import GameProgress.Player;

import java.util.HashMap;

public class Variable_Storage {

    private static Variable_Storage instance;
    private HashMap<String,Long> Variable = new HashMap<>();
    protected HashMap<String,Long>[] PlayerVariable ;
    private Command findPlayer = Command.instance();
    private Variable_Storage(){}
    public static Variable_Storage instance(){
        if(instance == null){
            instance = new Variable_Storage();
        }
        return instance;
    }

    public void AssignVariable(String _VarName,Expr _VarValue){
        Variable.put(_VarName,_VarValue.eval())
        PlayerVariable.put(findPlayer.lgame.GetCurrentPlayer(), );
    }
    public long GetVariableValue(String key){

    }
}
