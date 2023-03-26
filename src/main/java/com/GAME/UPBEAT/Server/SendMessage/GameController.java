package com.GAME.UPBEAT.Server.SendMessage;


import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.Server.ReceiveMessage.InitializeGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @Autowired
    private InitializeGame StartingData;

    @SubscribeMapping("/RowCol")
    public long[] RowCol(){
        return new long[]{GameState.instance().gameData.row,GameState.instance().gameData.col};
    }

//    @MessageMapping("/RowCol")
//    @SendTo("/topic/GameData")
//    public InitializeGame Gamefield(){
//        return StartingData;
//    }

    @MessageMapping("/GameStart")
    @SendTo("/topic/GameStart")
    public boolean GameStart(){
        return StartingData.isGameReady();
    }
}
