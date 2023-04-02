package com.GAME.UPBEAT.Server.ReceiveMessage;

import lombok.Getter;

@Getter
public class AddPlayerMessage {
    private long numofplayers;
    private String[] playerlist;

}
