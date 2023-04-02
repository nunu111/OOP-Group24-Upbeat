package com.GAME.UPBEAT.Server.SendMessage;

import lombok.Getter;

@Getter
public class HexagonGridRegion {
    private int[][] RegionTariff;
    public HexagonGridRegion(int[][] hex){
        RegionTariff = hex;
    }
}
