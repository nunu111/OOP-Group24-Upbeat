package Test;

import AST.EvalError;
import AST.PlanAST;
import GameProgress.Command;
import GameProgress.FileReader;
import GameProgress.Game;
import GameProgress.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void tester() throws EvalError {
        String[] name = {"Nu","Fifa","Bogey"};
        FileReader file= FileReader.Instance();
        Game game1 = file.ParsingConfigFile("src/GameProgress/Configuration.txt");
        Command.instance().lgame = game1;
        game1.AddPlayer(3,name);
        PlanAST plan = file.ParsingPlayerFile("src/Test/Test.txt");
        plan.eval();
    }

}