package com.GAME.UPBEAT.Server.ReceiveMessage;

import com.GAME.UPBEAT.GameProgress.GameState;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Getter
public class AddPlayer {
    public boolean IsAddedPlayer(AddPlayerMessage players){
        GameState.instance().gameData.ClearData();
        System.out.println(Arrays.toString(players.getPlayerlist()));
        System.out.println(players.getNumofplayers());
        GameState.instance().gameData.AddPlayer(players.getNumofplayers(),players.getPlayerlist());
        return true;
    }
}
