package com.GAME.UPBEAT.Server.ReceiveMessage;

import com.GAME.UPBEAT.AST.ASTStatement.PlanAST;
import com.GAME.UPBEAT.AST.EvalError;
import com.GAME.UPBEAT.GameProgress.Parser;
import com.GAME.UPBEAT.GameProgress.SyntaxError;
import com.GAME.UPBEAT.GameProgress.Tokenizer;
import com.GAME.UPBEAT.Server.SendMessage.ParseResult;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Parsing {
    private boolean IsOK = true;
    private boolean IsSyntaxError =false;
    private Parser CheckingParse;
    private PlanAST CheckingEval;
    public ParseResult parsing(CodeMessage CodeMessage){
        try{
            CheckingParse=new Parser(new Tokenizer(CodeMessage.getCode()));
            CheckingEval = CheckingParse.PlanParser();
            CheckingEval.eval(false);
        }catch (SyntaxError e){
            IsSyntaxError = true;
            IsOK=false;
        } catch (EvalError e) {
            IsOK=false;
            e.printStackTrace();
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
            IsOK=false;
        }
        return new ParseResult(IsOK,IsSyntaxError);
    }
}
