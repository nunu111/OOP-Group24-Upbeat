package GameProgress;
import AST.PlanAST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private static FileReader Instance;
    private FileReader(){}
    public static FileReader Instance(){
        if(Instance == null) {
            Instance = new FileReader();
        }
        return Instance;
    }
    public PlanAST ParsingFile(String FileDirection){
        try {
            File myFile = new File(FileDirection);
            Scanner myReader = new Scanner(myFile);
            StringBuilder s = new StringBuilder();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                s.append(data);
                s.append("\n"); //make sure
            }
            Parser ReadyToParse = new Parser(new Tokenizer(s.toString()));
            return ReadyToParse.PlanParser();
        }catch (SyntaxError | FileNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}
