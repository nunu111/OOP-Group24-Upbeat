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


    }
}
