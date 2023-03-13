package com.GAME.UPBEAT;

import com.GAME.UPBEAT.GameProgress.FileReader;
import com.GAME.UPBEAT.GameProgress.GameData;
import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.GameProgress.Player;


import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    @Test
    public void tester() {
        String[] name = {"Nu","Fifa","Boegy"};
        FileReader file= FileReader.Instance();
        GameData game = new GameData(5,5,100,10,10,10000,5,0,100,5,0);
        GameState com = GameState.instance();
        game.AddPlayer(3,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];
        Player P2 = com.gameData.ListOfPlayer[1];
        Player P3 = com.gameData.ListOfPlayer[3];

        assertEquals(5,com.GetCols());
        assertEquals(5,com.GetCols());
        assertEquals(P1.city_crew.col(),com.GetCurcol());
        assertEquals(P1.city_crew.row(),com.GetCurrow());
        assertEquals(P1.budget,com.GetBudget());
        assertEquals(100,com.GetDeposit());
//        assertEquals(,);
//        assertEquals(,);
//
//        System.out.println(com.GetCols());
//        System.out.println(com.GetRows());
//        System.out.println(P1.city_crew.col()+" /// "+com.GetCurcol());
//        System.out.println(P1.city_crew.row()+" /// "+com.GetCurrow());


    }

}