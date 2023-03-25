package com.GAME.UPBEAT;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.Variable_Storage;
import com.GAME.UPBEAT.AST.ASTStatement.PlanAST;
import com.GAME.UPBEAT.GameProgress.GameState;
import com.GAME.UPBEAT.GameProgress.FileReader;
import com.GAME.UPBEAT.GameProgress.GameData;
import com.GAME.UPBEAT.GameProgress.Parser;
import com.GAME.UPBEAT.GameProgress.Player;
import com.GAME.UPBEAT.GameProgress.SyntaxError;
import com.GAME.UPBEAT.GameProgress.Tokenizer;

public class main {

    public static void main(String[] args) throws SyntaxError, EvalError {
        System.out.println("lol");
        e();
//        g();
//        c();
//        a();
    }

    public static void g() {
        GameState test = GameState.instance();
        test.gameData = new GameData(20L, 20L, 1000000L, 0L, 0L, 0L, 0L, 0L, 10000L, 0L, 0L);
        test.gameData.AddPlayer(1L, new String[]{"nu"});
        Player CurrentPlayer = test.gameData.ListOfPlayer[test.gameData.cur_player];
        System.out.print(test.GetCurrow() + ",");
        System.out.println(test.GetCurcol());
        System.out.println(CurrentPlayer.budget);

        for(int i = 0; i < 5; ++i) {
        }

        System.out.println(CurrentPlayer.budget);
        System.out.print(test.GetCurrow() + ",");
        System.out.println(test.GetCurcol());
        System.out.println(CurrentPlayer.budget);
    }

    public static void f() {
    }

    public static void e() throws SyntaxError, EvalError {
        String[] name = new String[]{"Nu"};
        FileReader file = FileReader.Instance();
        GameData game1 = file.ParsingConfigFile("src/GameProgress/Configuration.txt");
        GameState.instance().gameData = game1;
        game1.AddPlayer(1L, name);
        PlanAST plan = file.ParsingPlayerFile("src/Test/SemiTest.txt");
        System.out.println();
        StringBuilder s = new StringBuilder();
        plan.prettyPrint(s);
        plan.eval(false);
        System.out.println(GameState.instance().gameData.ListOfPlayer[GameState.instance().gameData.cur_player].name);
        System.out.println(GameState.instance().gameData.ListOfPlayer[GameState.instance().gameData.cur_player].budget);
        System.out.println(Variable_Storage.instance().GetVariableMap());
    }

    public static int max(int[] arr) {
        int max = arr[0];

        for(int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static void c() throws SyntaxError {
        Parser a = new Parser(new Tokenizer("if (budget - 100) then {} else {}  # too poor to do anything else\n"));
        a.PlanParser();
    }

    public static void a() throws SyntaxError {
        Tokenizer a = new Tokenizer("t = t + 1  # keeping track of the turn number\nm = 0  # number of random moves\nwhile (deposit) { # still our region\n  if (deposit - 100)\n  then collect (deposit / 4)  # collect 1/4 of available deposit\n  else if (budget - 25) then invest 25\n  else {}\n  if (budget - 100) then {} else done  # too poor to do anything else\n  opponentLoc = opponent\n  if (opponentLoc / 10 - 1)\n  then  # opponent afar\n    if (opponentLoc % 10 - 5) then move downleft\n    else if (opponentLoc % 10 - 4) then move down\n    else if (opponentLoc % 10 - 3) then move downright\n    else if (opponentLoc % 10 - 2) then move right\n    else if (opponentLoc % 10 - 1) then move upright\n    else move up\n  else if (opponentLoc)\n  then  # opponent adjacent to city crew\n    if (opponentLoc % 10 - 5) then {\n      cost = 10 ^ (nearby upleft % 100 + 1)\n      if (budget - cost) then shoot upleft cost else {}\n    }\n    else if (opponentLoc % 10 - 4) then {\n      cost = 10 ^ (nearby downleft % 100 + 1)\n      if (budget - cost) then shoot downleft cost else {}\n    }\n    else if (opponentLoc % 10 - 3) then {\n      cost = 10 ^ (nearby down % 100 + 1)\n      if (budget - cost) then shoot down cost else {}\n    }\n    else if (opponentLoc % 10 - 2) then {\n      cost = 10 ^ (nearby downright % 100 + 1)\n      if (budget - cost) then shoot downright cost else {}\n    }\n    else if (opponentLoc % 10 - 1) then {\n      cost = 10 ^ (nearby upright % 100 + 1)\n      if (budget - cost) then shoot upright cost else {}\n    }\n    else {\n      cost = 10 ^ (nearby up % 100 + 1)\n      if (budget - cost) then shoot up cost else {}\n    }\n  else {  # no visible opponent; move in a random direction\n    dir = random % 6\n    if (dir - 4) then move upleft\n    else if (dir - 3) then move downleft\n    else if (dir - 2) then move down\n    else if (dir - 1) then move downright\n    else if (dir) then move upright\n    else move up\n    m = m + 1\n  }\n}  # end while\n# city crew on a region belonging to nobody, so claim it\nif (budget - 1) then invest 1 else {}\n");
        Parser b = new Parser(a);
        b.PlanParser();
    }
}
