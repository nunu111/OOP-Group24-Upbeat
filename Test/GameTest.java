package Test;

import AST.EvalError;
import AST.PlanAST;
import AST.Variable_Storage;
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
        Command.instance();
        Command.instance().lgame.AddPlayer(3,name);
        PlanAST plan = file.ParsingPlayerFile("src/Test/Test.txt");
        plan.eval();
//        System.out.println(Command.instance().lgame.cur_player);
//        System.out.println(Variable_Storage.instance().GetVariableMap());

    }

}