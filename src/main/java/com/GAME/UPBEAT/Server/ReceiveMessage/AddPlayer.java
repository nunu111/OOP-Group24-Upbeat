package com.GAME.UPBEAT.Server.ReceiveMessage;

import lombok.Getter;

@Getter
public class AddPlayer {
    private long NumOfPlayers;
    private String[] NameOfPlayers;
}
