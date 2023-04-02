package com.GAME.UPBEAT.Server.SendMessage;

import lombok.Getter;

@Getter
public class ParseResult {
    private boolean IsOK;
    private boolean IsError;
    public ParseResult(boolean IsOK,boolean ISError){
        this.IsOK = IsOK;
        this.IsError = ISError;
    }
}
