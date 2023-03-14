package com.GAME.UPBEAT;

import com.GAME.UPBEAT.GameProgress.FileReader;
import com.GAME.UPBEAT.GameProgress.GameData;
import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.GameProgress.Player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    @Test
    public void tester() {
        String[] name = {"Nu","Fifa","Boegy"};
        FileReader file= FileReader.Instance();
        long col=5;
        long row=5;
        long init_budget=100;
        long rev_cost=10;
        long interest=10;
        long max_dep=10000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=100;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        file.ParsingConfigFile("src/main/java/com/GAME/UPBEAT/GameProgress/Configuration.txt");
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayer(3,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];
        Player P2 = com.gameData.ListOfPlayer[1];
        Player P3 = com.gameData.ListOfPlayer[2];

        assertEquals(col,com.GetCols());
        assertEquals(row,com.GetRows());
        assertEquals(P1.city_crew.col()+1,com.GetCurcol());
        assertEquals(P1.city_crew.row()+1,com.GetCurrow());
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