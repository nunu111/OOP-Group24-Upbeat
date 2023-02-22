package AST;

import GameProgress.Command;
import GameProgress.Player;

import java.util.HashMap;

public class Variable_Storage {

    private static Variable_Storage instance;
    private final HashMap<String,Double>[] PlayerVariable ;
    private Command findPlayer = Command.instance();

    private Variable_Storage(){
        this.PlayerVariable = new HashMap[Command.instance().lgame.listofplayer.length];
        for(int i =0; i < Command.instance().lgame.listofplayer.length ;i++)
        this.PlayerVariable[i] = new HashMap<>();
    }
    public static Variable_Storage instance(){
        if(instance == null){
            instance = new Variable_Storage();
        }
        return instance;
    }

    public void AssignVariable(String _VarName,Expr _VarValue) throws EvalError {
        PlayerVariable[(int)findPlayer.lgame.cur_player].put(_VarName,_VarValue.eval(PlayerVariable[(int)findPlayer.lgame.cur_player]));
    }
    public void AssignVariable(String _VarName,double _VarValue){
        PlayerVariable[(int)findPlayer.lgame.cur_player].put(_VarName,_VarValue);
    }
    public double GetVariableValue(String key){
        return PlayerVariable[(int)findPlayer.lgame.cur_player].get(key);
    }
    public HashMap<String,Double> GetVariableMap(){
        return PlayerVariable[(int)findPlayer.lgame.cur_player];
    }
}
