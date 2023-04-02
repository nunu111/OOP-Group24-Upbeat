package com.GAME.UPBEAT.Server.ReceiveMessage;

import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.GameProgress.Player;
import com.GAME.UPBEAT.GameProgress.Region;
import com.GAME.UPBEAT.Server.SendMessage.HexagonGridRegion;
import lombok.Getter;
import org.springframework.stereotype.Component;


import java.util.LinkedList;

@Component
@Getter
public class CurrentRegion {
    GameState game = GameState.instance();
    LinkedList<long[]> rowcol = new LinkedList<>();

    public LinkedList<long[]> GetOwned(){
        rowcol = new LinkedList<>();
        Player CurrPlayer = game.gameData.ListOfPlayer[game.gameData.cur_player];
        for (Region stackR : CurrPlayer.OwnRegion) {
            rowcol.add(new long[]{stackR.row(), stackR.col()});
        }
        return rowcol;
    }
}
