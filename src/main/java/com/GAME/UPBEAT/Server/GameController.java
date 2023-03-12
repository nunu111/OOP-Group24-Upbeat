package com.GAME.UPBEAT.Server;

import com.GAME.UPBEAT.GameProgress.Command;
import com.GAME.UPBEAT.GameProgress.GameData;
import com.GAME.UPBEAT.Server.ReceiveMessage.AddPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
    @Autowired
    private GameData gameData;

    @MessageMapping("/AddPlayer")
    @SendTo("/topic/filed")
    public GameData getGameData(AddPlayer addPlayer) {
        return Command.instance().GameInstaller(addPlayer);
    }

}