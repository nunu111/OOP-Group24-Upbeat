package com.GAME.UPBEAT.Server.SendMessage;

import lombok.Getter;

@Getter
public class CurrentPlayerStatus {
    private String PlayerName;
    private String Code;
    private long RegionLen;
    private long Budget;
    public CurrentPlayerStatus(String PlayerName,String Code,long amount,long budget){
        this.PlayerName = PlayerName;
        this.Code = Code;
        this.RegionLen = amount;
        this.Budget= budget;
    }

}
