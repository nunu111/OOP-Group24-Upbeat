package com.GAME.UPBEAT.GameProgress;
import com.GAME.UPBEAT.AST.ASTStatement.PlanAST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private static FileReader Instance;
    private long m,n,init_plan_min,init_plan_sec,init_budget,init_center_dep,plan_rev_min,plan_rev_sec,rev_cost,max_dep,interest_pct;
    private FileReader(){}
    public static FileReader Instance(){
        if(Instance == null) {
            Instance = new FileReader();
        }
        return Instance;
    }
    public GameData ParsingConfigFile(String FileDirection){
        try{
            File myFile = new File(FileDirection);
            Scanner myReader = new Scanner(myFile);
            StringBuilder s = new StringBuilder();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                s.append(data);
            }
            myReader.close();
            Tokenizer config = new Tokenizer(s.toString());
            while(config.hasNextToken()){
                if(config.peek("m")){
                    config.consume();
                    config.consume("=");
                    m = Long.parseLong(config.consume());
                }else if(config.peek("n")){
                    config.consume();
                    config.consume("=");
                    n = Long.parseLong(config.consume());
                }else if(config.peek("init_plan_sec")){
                    config.consume();
                    config.consume("=");
                    init_plan_sec = Long.parseLong(config.consume());
                    if(init_plan_sec >59) throw new SyntaxError("init_plan_sec value not match");
                }else if(config.peek("init_plan_min")){
                    config.consume();
                    config.consume("=");
                    init_plan_min = Long.parseLong(config.consume());
                    if(init_plan_min >59) throw new SyntaxError("init_plan_min value not match");
                }else if(config.peek("init_budget")){
                    config.consume();
                    config.consume("=");
                    init_budget = Long.parseLong(config.consume());
                }else if(config.peek("init_center_dep")){
                    config.consume();
                    config.consume("=");
                    init_center_dep = Long.parseLong(config.consume());
                }else if(config.peek("plan_rev_min")){
                    config.consume();
                    config.consume("=");
                    plan_rev_min = Long.parseLong(config.consume());
                }else if(config.peek("plan_rev_sec")){
                    config.consume();
                    config.consume("=");
                    plan_rev_sec = Long.parseLong(config.consume());
                }else if(config.peek("rev_cost")){
                    config.consume();
                    config.consume("=");
                    rev_cost = Long.parseLong(config.consume());
                }else if(config.peek("max_dep")){
                    config.consume();
                    config.consume("=");
                    max_dep = Long.parseLong(config.consume());
                }else if(config.peek("interest_pct")){
                    config.consume();
                    config.consume("=");
                    interest_pct = Long.parseLong(config.consume());
                }else throw new SyntaxError("Config file not correct");
            }
            return new GameData(n, m,init_budget,rev_cost,interest_pct,
                    max_dep, init_plan_min, init_plan_sec, init_center_dep, plan_rev_min,plan_rev_sec);
        } catch (FileNotFoundException | SyntaxError e) {
            System.err.println(e.getMessage());
            return null;
        }catch (NumberFormatException e){
            System.err.println("Config file not correct");
            return null;
        }
    }

    public PlanAST ParsingPlayerFile(String FileDirection){
        try {
            File myFile = new File(FileDirection);
            Scanner myReader = new Scanner(myFile);
            StringBuilder s = new StringBuilder();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                s.append(data);
                s.append("\n"); //make sure
            }
            myReader.close();
            Parser ReadyToParse = new Parser(new Tokenizer(s.toString()));
            return ReadyToParse.PlanParser();
        }catch (SyntaxError | FileNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}
