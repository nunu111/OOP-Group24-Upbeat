package com.GAME.UPBEAT;

import com.GAME.UPBEAT.AST.ASTStatement.PlanAST;
import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.AST.Variable_Storage;
import com.GAME.UPBEAT.GameProgress.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserandASTTest {

    @Test
    public void parsing() throws SyntaxError, EvalError {
        GameData TestGame=FileReader.Instance().ParsingConfigFile("src/main/java/com/GAME/UPBEAT/GameProgress/Configuration.txt");
        String[] name = {"Nu","Fifa","Boegy","Gun","Fong"};
        TestGame.AddPlayerForTest(5,name);
        GameState.instance().gameData = TestGame;
        String ParsMsg;

        ParsMsg = "if(2) then{} else{}";
        Parser TestParsing = new Parser(new Tokenizer(ParsMsg));
        TestParsing.PlanParser();

        ParsMsg = "while(10^100){ done}";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        TestParsing.PlanParser();

        ParsMsg = "t=1+1+2^3+5*8-100%10+1/8";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        PlanAST EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(50), Variable_Storage.instance().GetVariableValue(0,"t"));

        ParsMsg = "t=2^(3+1*8)";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(2048), Variable_Storage.instance().GetVariableValue(1,"t"));

        ParsMsg = "t=(1+1)*(0-9)";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(-18), Variable_Storage.instance().GetVariableValue(2,"t"));

        ParsMsg = "x=1+8\r\ny=1+x\r\nt=y+x";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(19), Variable_Storage.instance().GetVariableValue(3,"t"));

        ParsMsg = "m=rows\r\nn=cols\r\nr=currow\r\nc=curcol\r\nb=budget\r\nd=deposit\r\ni=int\r\nmd=maxdeposit\r\nrand=rondom";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(20), Variable_Storage.instance().GetVariableValue(4,"m"));
        assertEquals((long)(15), Variable_Storage.instance().GetVariableValue(4,"n"));
        assertEquals((long)(1), Variable_Storage.instance().GetVariableValue(4,"r"));
        assertEquals((long)(5), Variable_Storage.instance().GetVariableValue(4,"c"));
        assertEquals((long)(10000), Variable_Storage.instance().GetVariableValue(4,"b"));
        assertEquals((long)(100), Variable_Storage.instance().GetVariableValue(4,"d"));
        assertEquals((long)(0), Variable_Storage.instance().GetVariableValue(4,"i"));
        assertEquals((long)(1000000), Variable_Storage.instance().GetVariableValue(4,"md"));
        double rand =Variable_Storage.instance().GetVariableValue(4,"rand");
        assert (rand >= 0 && rand < 1000);

        ParsMsg = "t=0\r\nwhile(budget){ t=t+1}";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(10000), Variable_Storage.instance().GetVariableValue(0,"t"));

        ParsMsg = "t=100\r\nif(t-10) then t = 0 else{}";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(0), Variable_Storage.instance().GetVariableValue(1,"t"));

        ParsMsg = "t=0\r\n    x=10000 \r\n  z= 0 \r\n y=0 \r\nwhile(budget){ t=t+1 \r\n if(t%2) then{\r\n x=x-2 \r\n if(x-100) then{} else{z=z+1} \r\n}  else{y=y+1} }";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(10000), Variable_Storage.instance().GetVariableValue(2,"t"));
        assertEquals((long)(0), Variable_Storage.instance().GetVariableValue(2,"x"));
        assertEquals((long)(5000), Variable_Storage.instance().GetVariableValue(2,"y"));
        assertEquals((long)(51), Variable_Storage.instance().GetVariableValue(2,"z"));

        ParsMsg = "t=opponent\r\n \r\n y=0 \r\n x=0 \r\n if(t%10-1) then x = 1 else{ y=1 }";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(0), Variable_Storage.instance().GetVariableValue(3,"x"));
        assertEquals((long)(1), Variable_Storage.instance().GetVariableValue(3,"y"));

        ParsMsg = "move down\r\n opponentLoc=opponent \r\n x=(0-1) \r\n if (opponentLoc % 10 - 5) then x=5\r\nelse if (opponentLoc % 10 - 4) then x=4\r\nelse if (opponentLoc % 10 - 3) then x=3\r\nelse if (opponentLoc % 10 - 2) then x=2\r\n    else if (opponentLoc % 10 - 1) then x=1\r\n    else x=0";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(3), Variable_Storage.instance().GetVariableValue(4,"x"));

        ParsMsg = "move down \r\n move down \r\n  \r\n x = nearby upright \r\n y =  nearby up";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(103), Variable_Storage.instance().GetVariableValue(0,"x"));
        assertEquals((long)(303), Variable_Storage.instance().GetVariableValue(0,"y"));

    }

    @Test
    public void parsingCommandTest() throws SyntaxError, EvalError {
        GameData TestGame = FileReader.Instance().ParsingConfigFile("src/main/java/com/GAME/UPBEAT/GameProgress/Configuration.txt");
        String[] name = {"Nu", "Fifa", "Boegy", "Gun", "Fong"};
        TestGame.AddPlayerForTest(5, name);
        GameState.instance().gameData = TestGame;
        String ParsMsg;

        ParsMsg = "x=0  \r\n done \r\n done \r\n  \r\n x = 1 ";
        Parser TestParsing = new Parser(new Tokenizer(ParsMsg));
        PlanAST EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(0), Variable_Storage.instance().GetVariableValue(0,"x"));
        assertEquals(1,TestGame.cur_player);

        ParsMsg = "move upleft \r\n move down \r\n r=currow \r\n c=curcol \r\n invest 97 \r\n b=budget \r\n d=deposit \r\n  relocate \r\n ";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(4), Variable_Storage.instance().GetVariableValue(1,"r"));
        assertEquals((long)(4), Variable_Storage.instance().GetVariableValue(1,"c"));
        assertEquals((long)(9900), Variable_Storage.instance().GetVariableValue(1,"b"));
        assertEquals((long)(97), Variable_Storage.instance().GetVariableValue(1,"d"));

        ParsMsg = "move upright \r\n invest 99 \r\n collect 20 \r\n r=currow \r\n c=curcol \r\n b=budget \r\n d=deposit \r\n ";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(3), Variable_Storage.instance().GetVariableValue(2,"r"));
        assertEquals((long)(7), Variable_Storage.instance().GetVariableValue(2,"c"));
        assertEquals((long)(9918), Variable_Storage.instance().GetVariableValue(2,"b"));
        assertEquals((long)(79), Variable_Storage.instance().GetVariableValue(2,"d"));

        ParsMsg = "move upright \r\n move up \r\n shoot up 79 \r\n \r\n r=currow \r\n c=curcol \r\n b=budget \r\n d=deposit \r\n ";
        TestParsing = new Parser(new Tokenizer(ParsMsg));
        EvalTest= TestParsing.PlanParser();
        EvalTest.eval(false);
        assertEquals((long)(4), Variable_Storage.instance().GetVariableValue(3,"r"));
        assertEquals((long)(7), Variable_Storage.instance().GetVariableValue(3,"c"));
        assertEquals((long)(9918), Variable_Storage.instance().GetVariableValue(3,"b"));
        assertEquals((long)(0), Variable_Storage.instance().GetVariableValue(3,"d"));

        GameState.instance().gameData.ListOfPlayer[1].city_crew=GameState.instance().gameData.field[1][1];
        GameState.instance().done();
        GameState.instance().done();
        GameState.instance().done();
        assertEquals(2,GameState.instance().gameData.cur_player);
        GameState.instance().gameData.ListOfPlayer[2].city_crew=GameState.instance().gameData.field[2][6];
        assertEquals(0,GameState.instance().GetDeposit());
        assertEquals(false,GameState.instance().gameData.field[2][6].hasOwner());
        GameState.instance().done();
        GameState.instance().done();
        GameState.instance().done();
        GameState.instance().done();
        assertEquals(1,GameState.instance().gameData.cur_player);
        assertEquals(3,GameState.instance().gameData.ListOfPlayer[1].city_crew.row());
        assertEquals(3,GameState.instance().gameData.ListOfPlayer[1].city_crew.col());


    }
}
