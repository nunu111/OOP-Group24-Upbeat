package com.GAME.UPBEAT;

import com.GAME.UPBEAT.GameProgress.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    @Test
    public void testerVariable() {
        String[] name = {"Nu", "Fifa", "Boegy"};
        FileReader file = FileReader.Instance();
        long col = 9;
        long row = 7;
        long init_budget = 100;
        long rev_cost = 10;
        long interest = 10;
        long max_dep = 10000;
        long init_plan_min = 5;
        long init_plan_sec = 0;
        long init_center_dep = 100;
        long plan_rev_min = 5;
        long plan_rev_sec = 0;
        file.ParsingConfigFile("src/main/java/com/GAME/UPBEAT/GameProgress/Configuration.txt");
        GameData game = new GameData(col, row, init_budget, rev_cost, interest, max_dep, init_plan_min, init_plan_sec, init_center_dep, plan_rev_min, plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayer(3, name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];
        Player P2 = com.gameData.ListOfPlayer[1];
        Player P3 = com.gameData.ListOfPlayer[2];


        System.out.println("P1 : " + com.GetCurcol() + "///" + com.GetCurrow());
        System.out.println("P1 : " + (P1.city_crew.col() + 1) + "///" + (P1.city_crew.row() + 1));


        assert (com.GetCurcol() >= 1 && com.GetCurcol() < col + 1);
        //GetCols&Rows
        assertEquals(9, com.GetCols());
        assertEquals(7, com.GetRows());
        //GetCurcol&row
        assertEquals(P1.city_crew.col() + 1, com.GetCurcol());
        assertEquals(P1.city_crew.row() + 1, com.GetCurrow());
        //GetBudget
        assertEquals(100, com.GetBudget());
        //GetDeposit
        assertEquals(100, com.GetDeposit());
        //GetInterest
        assertEquals(0, com.GetInterest());
        //GetMaxDeposit
        assertEquals(10000, com.GetMaxDeposit());
        //GetRandom
        assertEquals(10000, com.GetMaxDeposit());
        //GetRandom
        int rand = com.GetRandom();
        assert (rand >= 0 && rand < 1000);

        //hasOwner
        assertEquals(true,P1.city_crew.hasOwner());
        assertEquals(true,P2.city_crew.hasOwner());
        assertEquals(true,P3.city_crew.hasOwner());



    }

    @Test //opponent
    public void testerOpponent() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        FileReader file= FileReader.Instance();
        long col=9;
        long row=7;
        long init_budget=100;
        long rev_cost=10;
        long interest=10;
        long max_dep=10000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=100;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];
        Player P2 = com.gameData.ListOfPlayer[1];
        Player P3 = com.gameData.ListOfPlayer[2];
        Player P4 = com.gameData.ListOfPlayer[3];
        Player P5 = com.gameData.ListOfPlayer[4];

        System.out.println("P1 : " + com.GetCurrow() + "///" + com.GetCurcol());
        System.out.println("P2 : " + (P2.city_crew.row() + 1) + "///" + (P2.city_crew.col() + 1));
        System.out.println("P3 : " + (P3.city_crew.row() + 1) + "///" + (P3.city_crew.col() + 1));
        System.out.println("P4 : " + (P4.city_crew.row() + 1) + "///" + (P4.city_crew.col() + 1));
        System.out.println("P5 : " + (P5.city_crew.row() + 1) + "///" + (P5.city_crew.col() + 1));
        assertEquals(true,com.gameData.field[3][4].hasOwner());
        for (int i = 0 ; i < row ; i++){
            for (int j = 0 ; j < col ; j++){
                if ((i==3&&j==4)||(i==2&&j==4)||(i==3&&j==5)||(i==5&&j==5)||(i==0&&j==4)){
                    assertEquals(true,com.gameData.field[i][j].hasOwner());
                }else {
                    assertEquals(false,com.gameData.field[i][j].hasOwner());
                }
            }
        }

        assertEquals(11,com.opponent());    //P1
        com.gameData.cur_player++;
        assertEquals(13,com.opponent());    //P2
        com.gameData.cur_player++;
        assertEquals(15,com.opponent());    //P3
        com.gameData.cur_player++;
        assertEquals(21,com.opponent());    //P4
        com.gameData.cur_player++;
        assertEquals(24,com.opponent());    //P5


        //set cur_player=0
        com.gameData.cur_player=0;
    }

    @Test
    public void testerNearby() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1080001;
        long rev_cost=10;
        long interest=10;
        long max_dep=1000000000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=10000;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;

        assertEquals(105,com.nearby(AllCommand.Direction.up));
        assertEquals(105,com.nearby(AllCommand.Direction.upright));
        assertEquals(0,com.nearby(AllCommand.Direction.downright));
        assertEquals(0,com.nearby(AllCommand.Direction.down));
        assertEquals(0,com.nearby(AllCommand.Direction.downleft));
        assertEquals(0,com.nearby(AllCommand.Direction.upleft));

        com.gameData.cur_player++;
        System.out.println(com.GetDeposit()+"=");
        System.out.println(com.GetBudget()+"=");
        System.out.println(com.invest(1080000));
        System.out.println(com.GetDeposit());
        System.out.println(com.GetBudget());
        assertEquals(205,com.nearby(AllCommand.Direction.up));

        com.gameData.cur_player=0;
        assertEquals(107,com.nearby(AllCommand.Direction.up));


    }

    @Test
    public void testerDoneAndInt() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1000;
        long rev_cost=10;
        long interest=10;
        long max_dep=1000000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=1000;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;


        assertEquals(0,com.gameData.cur_player);
        assertEquals(1000,com.GetDeposit());
        assertEquals(0,com.GetInterest());
        com.done();
        assertEquals(1,com.gameData.cur_player);
        assertEquals(1000,com.GetDeposit());
        assertEquals(0,com.GetInterest());
        com.done();
        assertEquals(2,com.gameData.cur_player);
        assertEquals(1000,com.GetDeposit());
        assertEquals(0,com.GetInterest());
        com.done();
        assertEquals(3,com.gameData.cur_player);
        assertEquals(1000,com.GetDeposit());
        assertEquals(0,com.GetInterest());
        com.done();
        assertEquals(4,com.gameData.cur_player);
        assertEquals(1000,com.GetDeposit());
        assertEquals(0,com.GetInterest());

        //Turn=2
        com.done();
        assertEquals(0,com.gameData.cur_player);
        assertEquals(1207,com.GetDeposit());
        assertEquals(21,com.GetInterest());
        com.done();
        assertEquals(1,com.gameData.cur_player);
        assertEquals(1207,com.GetDeposit());
        assertEquals(21,com.GetInterest());
        com.done();
        assertEquals(2,com.gameData.cur_player);
        assertEquals(1207,com.GetDeposit());
        assertEquals(21,com.GetInterest());
        com.done();
        assertEquals(3,com.gameData.cur_player);
        assertEquals(1207,com.GetDeposit());
        assertEquals(21,com.GetInterest());
        com.done();
        assertEquals(4,com.gameData.cur_player);
        assertEquals(1207,com.GetDeposit());
        assertEquals(21,com.GetInterest());

        //Turn = 3
        com.done();
        assertEquals(0,com.gameData.cur_player);
        assertEquals(1616,com.GetDeposit());
        assertEquals(35,com.GetInterest());
        com.done();
        assertEquals(1,com.gameData.cur_player);
        assertEquals(1616,com.GetDeposit());
        assertEquals(35,com.GetInterest());
        com.done();
        assertEquals(2,com.gameData.cur_player);
        assertEquals(1616,com.GetDeposit());
        assertEquals(35,com.GetInterest());
        com.done();
        assertEquals(3,com.gameData.cur_player);
        assertEquals(1616,com.GetDeposit());
        assertEquals(35,com.GetInterest());
        com.done();
        assertEquals(4,com.gameData.cur_player);
        assertEquals(1616,com.GetDeposit());
        assertEquals(35,com.GetInterest());
    }

    @Test
    public void testerMove() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1000;
        long rev_cost=10;
        long interest=10;
        long max_dep=1000000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=1000;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;

        //Move : down left
        assertEquals(com.gameData.field[3][4],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        com.move(AllCommand.Direction.downleft);
        assertEquals(com.gameData.field[4][3],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(999,com.GetBudget());

        //Move : up left
        com.move(AllCommand.Direction.upleft);
        assertEquals(com.gameData.field[3][2],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(998,com.GetBudget());


        //Move : up
        com.move(AllCommand.Direction.up);
        assertEquals(com.gameData.field[2][2],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(997,com.GetBudget());


        //Move : down
        com.move(AllCommand.Direction.down);
        assertEquals(com.gameData.field[3][2],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(996,com.GetBudget());


        //Move : up right
        com.move(AllCommand.Direction.upright);
        assertEquals(com.gameData.field[3][3],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(995,com.GetBudget());


        //Move : down right
        com.move(AllCommand.Direction.downright);
        assertEquals(com.gameData.field[3][4],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(994,com.GetBudget());

        //Move to RegionOpponent

        //Move : up
        com.move(AllCommand.Direction.up);
        assertEquals(com.gameData.field[3][4],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(993,com.GetBudget());

        //Move to map edge
        com.move(AllCommand.Direction.upleft);
        com.move(AllCommand.Direction.up);
        com.move(AllCommand.Direction.up);
        com.move(AllCommand.Direction.up);
        com.move(AllCommand.Direction.up);
        assertEquals(com.gameData.field[0][3],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(988,com.GetBudget());
        com.move(AllCommand.Direction.up);
        assertEquals(com.gameData.field[0][3],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(987,com.GetBudget());
        com.move(AllCommand.Direction.upright);
        assertEquals(com.gameData.field[0][3],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(986,com.GetBudget());
        com.move(AllCommand.Direction.downright);
        assertEquals(com.gameData.field[0][3],com.gameData.ListOfPlayer[com.gameData.cur_player].city_crew);
        assertEquals(985,com.GetBudget());

        //have no money enough

        com.invest(984);
        com.move(AllCommand.Direction.down);
        assertEquals(com.gameData.field[0][3],com.gameData.ListOfPlayer[0].city_crew);
        assertEquals(1,com.gameData.cur_player);

    }


    @Test
    public void testerRelocate() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1000;
        long rev_cost=10;
        long interest=10;
        long max_dep=1000000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=1000;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];
        Player P2 = com.gameData.ListOfPlayer[1];
        Player P3 = com.gameData.ListOfPlayer[2];
        Player P4 = com.gameData.ListOfPlayer[3];
        Player P5 = com.gameData.ListOfPlayer[4];

        //กรณีย้ายเมืองปกติ : เช็คตอนย้ายเมืองหลวง
        P1.city_crew=com.gameData.field[1][4];
        com.invest(9);
        com.relocate();
        assertEquals(970,P1.budget);
        assertEquals(1,P1.city_crew.row());
        assertEquals(4,P1.city_crew.col());
        assertEquals(1,com.gameData.cur_player);

        P2.city_crew=com.gameData.field[0][5];
        com.invest(9);
        com.relocate();
        assertEquals(965,P2.budget);
        assertEquals(0,P2.city_crew.row());
        assertEquals(5,P2.city_crew.col());
        assertEquals(2,com.gameData.cur_player);

        P3.city_crew=com.gameData.field[2][0];
        com.invest(9);
        com.relocate();
        assertEquals(955,P3.budget);
        assertEquals(2,P3.city_crew.row());
        assertEquals(0,P3.city_crew.col());
        assertEquals(3,com.gameData.cur_player);

        P4.city_crew=com.gameData.field[0][2];
        com.invest(9);
        com.relocate();
        assertEquals(950,P4.budget);
        assertEquals(0,P4.city_crew.row());
        assertEquals(2,P4.city_crew.col());
        assertEquals(4,com.gameData.cur_player);

        com.relocate();
        assertEquals(990,P5.budget);
        assertEquals(0,P5.city_crew.row());
        assertEquals(4,P5.city_crew.col());
        assertEquals(0,com.gameData.cur_player);

        //กรณีย้ายเมืองปกติ : เช็คว่าเมืองหลวงย้ายมารึยัง
        P1.city_crew=com.gameData.field[3][2];
        P2.city_crew=com.gameData.field[1][1];
        P3.city_crew=com.gameData.field[6][1];
        P4.city_crew=com.gameData.field[6][2];
        P5.city_crew=com.gameData.field[0][4];
        com.done();
        com.done();
        com.done();
        com.done();
        com.done();
        //เช็ค City center ของ P1
        assertEquals(1,P1.city_crew.row());
        assertEquals(4,P1.city_crew.col());
        //เช็ค City center ของ P2
        assertEquals(0,P2.city_crew.row());
        assertEquals(5,P2.city_crew.col());
        //เช็ค City center ของ P3
        assertEquals(2,P3.city_crew.row());
        assertEquals(0,P3.city_crew.col());
        //เช็ค City center ของ P4
        assertEquals(0,P4.city_crew.row());
        assertEquals(2,P4.city_crew.col());
        //เช็ค City center ของ P5
        assertEquals(0,P5.city_crew.row());
        assertEquals(4,P5.city_crew.col());

        //กรณีย้ายเมืองแต่เราไม่เป็นเจ้าของ Region ตรงนั้น
        P1.city_crew=com.gameData.field[5][4];
        com.relocate();
        assertEquals(970,P1.budget);
        assertEquals(1,P1.city_crew.row());
        assertEquals(4,P1.city_crew.col());
        assertEquals(1,com.gameData.cur_player);

        //กรณีย้ายเมืองแต่เรามีเงินไม่พอ
        P2.city_crew=com.gameData.field[0][6];
        com.invest(950);
        com.relocate();
        assertEquals(14,P2.budget);
        assertEquals(0,P2.city_crew.row());
        assertEquals(5,P2.city_crew.col());
        assertEquals(2,com.gameData.cur_player);

    }
    @Test
    public void testerInvest() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1000;
        long rev_cost=10;
        long interest=10;
        long max_dep=500;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=100;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];

        //ฝากเงินปกติที่Regionที่เราเป็นเจ้าของ
        com.invest(99);
        assertEquals(900,com.GetBudget());
        assertEquals(199,com.GetDeposit());

        //ฝากเงินปกติที่Regionที่เราไม่เป็นเจ้าของ
        P1.city_crew = com.gameData.field[3][3];
        assertEquals(false,com.gameData.field[3][3].hasOwner());
        com.invest(9);
        assertEquals(true,com.gameData.field[3][3].hasOwner());
        assertEquals(890,com.GetBudget());
        assertEquals(9,com.GetDeposit());

        //ฝากเงินที่เกิน Max_dep
        com.invest(510);
        assertEquals(true,com.gameData.field[3][3].hasOwner());
        assertEquals(379,com.GetBudget());
        assertEquals(500,com.GetDeposit());

        //ฝากเงินที่มากกว่าเงินที่มีอยู่
        P1.city_crew = com.gameData.field[4][1];
        assertEquals(false,com.gameData.field[4][1].hasOwner());
        com.invest(380);
        assertEquals(false,com.gameData.field[4][1].hasOwner());
        assertEquals(378,com.GetBudget());
        assertEquals(0,com.GetDeposit());

        //ฝากเงินตอนที่ไม่มีเงิน
        com.invest(377);
        assertEquals(0,com.GetBudget());
        assertEquals(377,com.GetDeposit());
        com.invest(99);
        assertEquals(1,com.gameData.cur_player);
        assertEquals(0,P1.budget);
        com.gameData.cur_player=0;
        P1.city_crew = com.gameData.field[4][1];
        assertEquals(377,com.GetDeposit());
    }

    @Test
    public void testerCollect() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1000;
        long rev_cost=10;
        long interest=10;
        long max_dep=50000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=1000;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];

        //ถอนเงินแบบปกติ
        com.collect(200);
        assertEquals(1199,com.GetBudget());
        assertEquals(800,com.GetDeposit());

        //ถอนเงินในจำนวนเงินที่มากว่าเงิน Deposit ของ Region นั้นๆ
        P1.city_crew = com.gameData.field[1][2];
        com.invest(200);
        com.collect(400);
        assertEquals(997,com.GetBudget());
        assertEquals(200,com.GetDeposit());

        //ถอนเงินจนหมด
        com.collect(200);
        assertEquals(1196,com.GetBudget());
        assertEquals(0,com.GetDeposit());
        assertEquals(false,com.gameData.field[1][2].hasOwner());

        //ถอนเงินเมื่อตัวเองไม่มีเงิน
        com.invest(1195);
        assertEquals(0,com.GetBudget());
        assertEquals(1195,com.GetDeposit());
        com.collect(100);
        assertEquals(1,com.gameData.cur_player);
        com.gameData.cur_player = 0;
        P1.city_crew = com.gameData.field[1][2];
        assertEquals(1195,com.GetDeposit());

    }

    @Test
    public void testerShoot() {
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        long col=9;
        long row=7;
        long init_budget=1000;
        long rev_cost=10;
        long interest=10;
        long max_dep=50000;
        long init_plan_min=5;
        long init_plan_sec=0;
        long init_center_dep=1000;
        long plan_rev_min=5;
        long plan_rev_sec=0;
        GameData game = new GameData(col,row,init_budget,rev_cost,interest,max_dep,init_plan_min,init_plan_sec,init_center_dep,plan_rev_min,plan_rev_sec);
        GameState com = GameState.instance();
        game.AddPlayerForTest(5,name);
        com.gameData = game;
        Player P1 = com.gameData.ListOfPlayer[0];
        Player P2 = com.gameData.ListOfPlayer[1];
        Player P3 = com.gameData.ListOfPlayer[2];
        Player P4 = com.gameData.ListOfPlayer[3];
        Player P5 = com.gameData.ListOfPlayer[4];


        //เงินหมด
        P1.city_crew = com.gameData.field[3][3];
        com.invest(999);
        com.shoot(AllCommand.Direction.upright,100);
        assertEquals(1000,com.GetDeposit());
        assertEquals(1,com.gameData.cur_player);

        //มีเงินไม่พอ
        com.shoot(AllCommand.Direction.downleft,1000);
        assertEquals(999,com.GetBudget());
        com.gameData.cur_player=0;
        P1.city_crew = com.gameData.field[3][3];
        assertEquals(999,com.GetDeposit());
        com.gameData.cur_player=1;

        //โจมตีใส่Region
        com.shoot(AllCommand.Direction.downleft,599);
        assertEquals(399,com.GetBudget());
        com.gameData.cur_player=0;
        P1.city_crew = com.gameData.field[3][3];
        assertEquals(400,com.GetDeposit());
        com.gameData.cur_player=1;
        com.done();

        //โจมตีใส่จน เงินฝากRegion หมด
        P3.city_crew = com.gameData.field[2][3];
        com.shoot(AllCommand.Direction.down,499);
        assertEquals(500,com.GetBudget());
        com.gameData.cur_player=0;
        P1.city_crew = com.gameData.field[3][3];
        assertEquals(false,com.gameData.field[3][3].hasOwner());
        assertEquals(0,com.GetDeposit());
        com.gameData.cur_player=2;

        //โจมตี Region เปล่า
        com.shoot(AllCommand.Direction.up,99);
        assertEquals(400,com.GetBudget());
        P3.city_crew = com.gameData.field[1][3];
        assertEquals(false,com.gameData.field[1][3].hasOwner());
        assertEquals(0,com.GetDeposit());

        //โจมตี Region ของตนเอง
        com.invest(99);
        P3.city_crew = com.gameData.field[1][4];
        com.shoot(AllCommand.Direction.upleft,19);
        assertEquals(280,com.GetBudget());
        P3.city_crew = com.gameData.field[1][3];
        assertEquals(true,com.gameData.field[1][3].hasOwner());
        assertEquals(80,com.GetDeposit());

        //โจมตีใส่ City center ตาย
        P3.city_crew = com.gameData.field[3][5];
        com.collect(501);
        com.done();
        P4.city_crew = com.gameData.field[4][5];
        com.shoot(AllCommand.Direction.up,600);
        assertEquals(399,com.GetBudget());
        assertEquals(false,com.gameData.field[3][5].hasOwner());
        assertEquals(false,com.gameData.field[1][3].hasOwner());
        P4.city_crew = com.gameData.field[1][3];
        assertEquals(-80,com.GetDeposit());        //เงินฝากของRegionที่ไม่มีเจ้าของ
        com.invest(1);
        assertEquals(true,com.gameData.field[1][3].hasOwner());
        assertEquals(81,com.GetDeposit());
    }
}