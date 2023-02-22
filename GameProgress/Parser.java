package GameProgress;

import AST.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Parser{
    String[] reservedword = {"collect","done","down","downleft","downright","else","if","invest","{","}","(",")"
            ,"move","nearby","opponent","relocate","shoot","then","up","upleft","upright","while",};

    private final Nodefactory NodeCreate= Nodefactory.instance();
    private final Tokenizer tkz;

    public Parser(Tokenizer _tkz){
        this.tkz = _tkz;
    }

    public Statement PlanParser() throws SyntaxError {
        if(!tkz.hasNextToken()) throw new NoSuchElementException("Plan don't have element");
        else{
            Statement statement= ParseStatement();
            if(tkz.hasNextToken()) {
                while(tkz.hasNextToken())System.out.println(tkz.consume());
                throw new SyntaxError("leftover token");
            }
        }
    }

    private Statement ParseStatement() throws SyntaxError {
        while(tkz.hasNextToken()){
//            System.out.println(tkz.peek());
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            ParseCommand();
            ParseBlockStatement();
            ParseIfStatement();
            ParseWhileStatement();
        }

    }

    private Expr ParseCommand() throws SyntaxError {
        ParseAssignmentStatement();
        ParseActionCommand();
    }
    private AssignVariableAST ParseAssignmentStatement() throws SyntaxError { //Pass
        if(IsNotReservedWord(tkz.peek())){
            String assign_var = tkz.consume();
            tkz.consume("=");
            return new AssignVariableAST(assign_var,ParseExpression());
        }
    }
    private Expr ParseActionCommand() throws SyntaxError {
        if(tkz.peek("done")){
            tkz.consume();
            done();
        }
        else if(tkz.peek("relocate")){
            tkz.consume();
            relocate();
        }
        else {
            ParseMoveCommand();
            ParseRegionCommand();
            ParseAttackCommand();
        }
    }
    private void ParseMoveCommand() throws SyntaxError {
        if(tkz.peek("move")){
            tkz.consume();
            move(ParseDirection());
        }
    }
    private AllCommand.Direction ParseDirection() throws SyntaxError {
        if(tkz.peek("up")){
            tkz.consume();
            return AllCommand.Direction.up;
        }
        else if(tkz.peek("down")){
            tkz.consume();
            return AllCommand.Direction.down;
        }
        else if(tkz.peek("upleft")){
            tkz.consume();
            return AllCommand.Direction.upleft;
        }
        else if(tkz.peek("upright")){
            tkz.consume();
            return AllCommand.Direction.upright;
        }
        else if(tkz.peek("downleft")){
            tkz.consume();
            return AllCommand.Direction.downleft;
        }
        else if(tkz.peek("downright")){
            tkz.consume();
            return AllCommand.Direction.downright;
        }
        else throw new SyntaxError("Direction ERROR");
    }
    private void ParseRegionCommand() throws SyntaxError {
        if(tkz.peek("invest")){
            tkz.consume();
            invest();
            ParseExpression();
        }else if(tkz.peek("collect")){
            tkz.consume();
            collect();
            ParseExpression();
        }
    }
    private void ParseAttackCommand() throws SyntaxError {
        if(tkz.peek("shoot")){
            tkz.consume();
            shoot(ParseDirection());
            ParseExpression();
        }
    }
    private Statement ParseBlockStatement() throws SyntaxError { //Complete
        if(tkz.peek("{")){
            tkz.consume();
            ParseStatement();
            tkz.consume("}");
        }
    }
    private void ParseIfStatement() throws SyntaxError {
        if(tkz.peek("if")){
            tkz.consume();
            tkz.consume("(");
            Expr condition = ParseExpression();
            tkz.consume(")");
            tkz.consume("then");
            ParseStatement();
            tkz.consume("else");
            if(condition <= 0){
                ParseStatement();
            }
        }
    }
    private Statement ParseWhileStatement() throws SyntaxError {
        if (tkz.peek("while")){
            tkz.consume();
            tkz.consume("(");
            Expr condition = ParseExpression();
            tkz.consume(")");
            ParseStatement();
            }
        }
    private Expr ParseExpression() throws SyntaxError { //Pass
        Expr T = ParseTerm();
        while (tkz.peek("+") ||tkz.peek("-")){
            if(tkz.peek("+")) {
                tkz.consume();
                T = NodeCreate.PlusExpr(T,ParseTerm());
            }
            else if(tkz.peek("-")) {
                tkz.consume();
                T = NodeCreate.MinusExpr(T,ParseTerm());
            }
        }
        return T;
    }
    private Expr ParseTerm() throws SyntaxError { //Pass
        Expr F = ParseFactor();
        while (tkz.peek("*") ||tkz.peek("/") || tkz.peek("%")){
            if(tkz.peek("*")) {
                tkz.consume();
                F = NodeCreate.MultiplyExpr(F,ParseFactor());
            }
            else if(tkz.peek("/")) {
                tkz.consume();
                F = NodeCreate.DivideExpr(F,ParseFactor());
            }
            else if(tkz.peek("%")) {
                tkz.consume();
                F = NodeCreate.ModExpr(F,ParseFactor());
            }
        }
        return F;
    }
    private Expr ParseFactor() throws SyntaxError { //Pass
        Expr P = ParsePower();
        while (tkz.peek("^")){
            if(tkz.peek("^")) {
                tkz.consume();
                P = NodeCreate.PowExpr(P,ParseTerm());
            }
        }
        return P;
    }
    private Expr ParsePower() throws SyntaxError { //Pass
        if(isNumber(tkz.peek())){
            return new LongAST(Long.parseLong(tkz.consume()));
        }else if(IsNotReservedWord(tkz.peek())){
            return new VariableAST(tkz.consume());
        }else if(tkz.peek("(")){
            tkz.consume();
            Expr calculate = ParseExpression();
            tkz.consume(")");
            return calculate;
        }else return ParseInfoExpression();
    }

    private Expr ParseInfoExpression() throws SyntaxError { //Pass
        if(tkz.peek("opponent")){
            tkz.consume();
            return new opponentAST();
        }
        else if(tkz.peek("nearby")){
            tkz.consume();
            return new nearbyAST(ParseDirection());
        }else throw new SyntaxError("InfoExpression ERROR");
    }

    private boolean IsNotReservedWord(String str) {
        if(Character.isDigit(str.charAt(0))) return false;
        for(int i =0; i< reservedword.length;i++){
            if(reservedword[i].equals(str)) return false;
        }
        return true;
    }

    private boolean isNumber(String str){
        try{
            double num = Long.parseLong(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
