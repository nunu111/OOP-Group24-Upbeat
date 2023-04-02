package com.GAME.UPBEAT.Server.ReceiveMessage;


import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.GameProgress.Player;
import com.GAME.UPBEAT.Server.SendMessage.CurrentPlayerStatus;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Playerdata {
    GameState game = GameState.instance();

    public CurrentPlayerStatus GetStatus(){
        Player CurrPlayer = game.gameData.ListOfPlayer[game.gameData.cur_player];
        return new CurrentPlayerStatus(CurrPlayer.name,CurrPlayer.code,CurrPlayer.OwnRegion.size(),(long)CurrPlayer.budget);
    }
}
