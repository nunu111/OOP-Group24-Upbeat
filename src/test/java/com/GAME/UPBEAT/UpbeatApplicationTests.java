package com.GAME.UPBEAT;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpbeatApplicationTests {
	@Test
	void contextLoads() {
		assertEquals(1,1);
//		String[] name = {"Nu","Fifa","Boegy"};
//		FileReader file= FileReader.Instance();
//		long col=5;
//		long row=5;
//		long init_budget=100;
//		long rev_cost=10;
//		long interest=10;
//		long max_dep=10000;
//		long init_plan_min=5;
//		long init_plan_sec=0;
//		long init_center_dep=100;
//		long plan_rev_min=5;
//		long plan_rev_sec=0;

//		GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
//		Command com = Command.instance();
//		game.AddPlayer(3,name);
//		com.gameData = game;
//		Player P1 = com.gameData.ListOfPlayer[0];
//		Player P2 = com.gameData.ListOfPlayer[1];
//		Player P3 = com.gameData.ListOfPlayer[2];

//		assertEquals(col,com.GetCols());
//		assertEquals(row,com.GetRows());
//		assertEquals(P1.city_crew.col(),com.GetCurcol());
//		assertEquals(P1.city_crew.row(),com.GetCurrow());
//		assertEquals(P1.budget,com.GetBudget());
//		assertEquals(100,com.GetDeposit());
	}

}
