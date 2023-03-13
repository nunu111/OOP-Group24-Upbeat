package com.GAME.UPBEAT.AST;

import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.GameProgress.GameState;

import java.util.HashMap;

public class Variable_Storage {

    private static Variable_Storage instance;
    private final HashMap<String,Long>[] PlayerVariable ;
    private GameState findPlayer = GameState.instance();

    private Variable_Storage(){
        this.PlayerVariable = new HashMap[GameState.instance().gameData.ListOfPlayer.length];
        for(int i = 0; i < GameState.instance().gameData.ListOfPlayer.length ; i++)
        this.PlayerVariable[i] = new HashMap<>();
    }

    public static Variable_Storage instance(){
        if(instance == null){
            instance = new Variable_Storage();
        }
        return instance;
    }

    public void AssignVariable(String _VarName, Expr _VarValue) throws EvalError {
        int curr_player = findPlayer.gameData.cur_player;
        PlayerVariable[curr_player].put(_VarName,_VarValue.eval(PlayerVariable[curr_player]));
    }
    public void AssignVariable(String _VarName,Long _VarValue){
        int curr_player = findPlayer.gameData.cur_player;
        PlayerVariable[curr_player].put(_VarName,_VarValue);
    }
    public double GetVariableValue(String key){
        return PlayerVariable[findPlayer.gameData.cur_player].get(key);
    }
    public HashMap<String,Long> GetVariableMap(){
        return PlayerVariable[findPlayer.gameData.cur_player];
    }
}
