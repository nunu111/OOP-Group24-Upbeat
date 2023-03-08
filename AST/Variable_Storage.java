package AST;

import AST.Expr.Expr;
import GameProgress.Command;

import java.util.HashMap;

public class Variable_Storage {

    private static Variable_Storage instance;
    private final HashMap<String,Long>[] PlayerVariable ;
    private Command findPlayer = Command.instance();

    private Variable_Storage(){
        this.PlayerVariable = new HashMap[Command.instance().game.ListOfPlayer.length];
        for(int i = 0; i < Command.instance().game.ListOfPlayer.length ; i++)
        this.PlayerVariable[i] = new HashMap<>();
    }

    public static Variable_Storage instance(){
        if(instance == null){
            instance = new Variable_Storage();
        }
        return instance;
    }

    public void AssignVariable(String _VarName, Expr _VarValue) throws EvalError {
        int curr_player = findPlayer.game.cur_player;
        PlayerVariable[curr_player].put(_VarName,_VarValue.eval(PlayerVariable[curr_player]));
    }
    public void AssignVariable(String _VarName,Long _VarValue){
        int curr_player = findPlayer.game.cur_player;
        PlayerVariable[curr_player].put(_VarName,_VarValue);
    }
    public double GetVariableValue(String key){
        return PlayerVariable[findPlayer.game.cur_player].get(key);
    }
    public HashMap<String,Long> GetVariableMap(){
        return PlayerVariable[findPlayer.game.cur_player];
    }
}
