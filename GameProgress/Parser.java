package GameProgress;

import AST.Expr;
import AST.LongAST;
import AST.VariableAST;
import AST.opponentAST;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Parser extends Command{
    String[] reservedword = {"collect","done","down","downleft","downright","else","if","invest","{","}","(",")"
            ,"move","nearby","opponent","relocate","shoot","then","up","upleft","upright","while",};
//    char[] overlapword = {'{','}','(',')'};
    private Tokenizer tkz;
    public Parser(Tokenizer _tkz){
        this.tkz = _tkz;
    }

    public Expr PlanParser() throws SyntaxError {
        if(!tkz.hasNextToken()) throw new NoSuchElementException("Plan don't have element");
        else{
            Expr statement= ParseStatement();
            if(tkz.hasNextToken()) {
                while(tkz.hasNextToken())System.out.println(tkz.consume());
                throw new SyntaxError("leftover token");
            }
        }
    }

    private Expr ParseStatement() throws SyntaxError {
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
    private Expr ParseAssignmentStatement() throws SyntaxError { //<identifier> = Expression
        if(IsNotReservedWord(tkz.peek())){
            String assign_var = tkz.consume();
            if(tkz.peek("=")){
                tkz.consume();
                AssignVariable(assign_var,ParseExpression());
            }else throw new SyntaxError("AssignmentStatement ERROR (Maybe forget \"=\" )");
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
    private Direction ParseDirection() throws SyntaxError {
        if(tkz.peek("up")){
            tkz.consume();
            return Direction.up;
        }
        else if(tkz.peek("down")){
            tkz.consume();
            return Direction.down;
        }
        else if(tkz.peek("upleft")){
            tkz.consume();
            return Direction.upleft;
        }
        else if(tkz.peek("upright")){
            tkz.consume();
            return Direction.upright;
        }
        else if(tkz.peek("downleft")){
            tkz.consume();
            return Direction.downleft;
        }
        else if(tkz.peek("downright")){
            tkz.consume();
            return Direction.downright;
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
    private void ParseBlockStatement() throws SyntaxError { //Complete
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
            double condition = ParseExpression();
            tkz.consume(")");
            tkz.consume("then");
            ParseStatement();
            tkz.consume("else");
            if(condition <= 0){
                ParseStatement();
            }
        }
    }
    private void ParseWhileStatement() throws SyntaxError {
        if (tkz.peek("while")){
            tkz.consume();
            if(tkz.peek("(")){
                tkz.consume();
                double condition = ParseExpression();
                if(tkz.peek(")")) {
                    tkz.consume();
                    for(int i = 0; i<10000 && condition >0;i++) ParseStatement();
                    }else throw new SyntaxError("Wrong while Statement");
                }else throw new SyntaxError("Wrong while Statement");
            }
        }
    private Expr ParseExpression() throws SyntaxError {
        double T = ParseTerm();
        while (tkz.peek("+") ||tkz.peek("-")){
            if(tkz.peek("+")) {
                tkz.consume();
                T += ParseTerm();
            }
            else if(tkz.peek("-")) {
                tkz.consume();
                T -= ParseTerm();
            }
        }
        return T;
    }
    private Expr ParseTerm() throws SyntaxError {
        double F = ParseFactor();
        while (tkz.peek("*") ||tkz.peek("/") || tkz.peek("%")){
            if(tkz.peek("*")) {
                tkz.consume();
                F *= ParseFactor();
            }
            else if(tkz.peek("/")) {
                tkz.consume();
                F /= ParseFactor();
            }
            else if(tkz.peek("%")) {
                tkz.consume();
                F %= ParseFactor();
            }
        }
        return F;
    }
    private Expr ParseFactor() throws SyntaxError {
        double P = ParsePower();
        while (tkz.peek("^")){
            if(tkz.peek("^")) {
                tkz.consume();
                P = Math.pow(P,ParseTerm());
            }
        }
        return P;
    }
    private Expr ParsePower() throws SyntaxError {
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

    private Expr ParseInfoExpression() throws SyntaxError {
        if(tkz.peek("opponent")){
            tkz.consume();
            new opponentAST();
            return 0;
        }
        else if(tkz.peek("nearby")){
            tkz.consume();
            return nearby(ParseDirection());
        }else throw new SyntaxError("InfoExpression ERROR");
    }

    private boolean IsNotReservedWord(String str) {
        if(Character.isDigit(str.charAt(0))) return false;
        for(int i =0; i< reservedword.length;i++){
            if(reservedword[i].equals(str)) return false;
        }
//        for(int i =0; i < str.length();i++){
//            for(int j=0;j <overlapword.length;j++){
//                if(str.charAt(i) == overlapword[j])return false;
//            }
//        }
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
