package com.GAME.UPBEAT.Server.SendMessage;


import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.Server.ReceiveMessage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;

@Controller
public class GameController {

    @Autowired
    private InitializeGameMessage StartingData;
    @Autowired
    private Parsing parsing;
    @Autowired
    private AddPlayer addPlayer;
    @Autowired
    private Playerdata playerdata;
    @Autowired
    private CurrentRegion CurrentRegion;

    @SubscribeMapping("/RowCol")
    public long[] RowCol(){
        return new long[]{GameState.instance().gameData.row,GameState.instance().gameData.col};
    }

    @MessageMapping("/AddPlayers")
    @SendTo("/topic/IsAdded")
    public boolean AddPlayers(AddPlayerMessage players){
        return addPlayer.IsAddedPlayer(players);
    }

    @MessageMapping("/ParseMsg")
    @SendTo("/topic/result")
    public ParseResult Parsing(CodeMessage codemsg){
        System.out.println(codemsg.getCode());
        return parsing.parsing(codemsg);
    }

    @MessageMapping("/Status")
    @SendTo("/topic/GetStatus")
    public CurrentPlayerStatus GetStatus(){
        return playerdata.GetStatus();
    }

    @MessageMapping("/GameStart")
    @SendTo("/topic/GameStart")
    public boolean GameStart(){
        return StartingData.isGameReady();
    }

    @SubscribeMapping("/Hexmap")
    public LinkedList<long[]> Hexmap(){
        return CurrentRegion.GetOwned();
    }
}
