package com.GAME.UPBEAT.GameProgress;

import com.GAME.UPBEAT.AST.*;
import com.GAME.UPBEAT.AST.ASTExpr.Expr;
import com.GAME.UPBEAT.AST.ASTStatement.PlanAST;
import com.GAME.UPBEAT.AST.ASTStatement.Statement;
import com.GAME.UPBEAT.AST.ASTStatement.moveAST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Parser{
    private final HashSet<String> ReservedWordSet;
    private final Nodefactory NodeCreate= Nodefactory.instance();
    private final Tokenizer tkz;

    public Parser(Tokenizer _tkz){
        this.tkz = _tkz;
        ReservedWordSet = new HashSet<>();
        ReservedWordSet.addAll(Arrays.asList("collect", "done", "down", "downleft", "downright", "else", "if", "invest", "{", "}", "(", ")"
                                , "move", "nearby", "opponent", "relocate", "shoot", "then", "up", "upleft", "upright", "while"));
    }

    public PlanAST PlanParser() throws SyntaxError {
        if(!tkz.hasNextToken()) throw new NoSuchElementException("Plan doesn't have element");
        Statement statement= ParseStatement();
        ArrayList<Statement> StatementContainer = new ArrayList<>();
        while(statement != null ){
            StatementContainer.add(statement);
            if(!tkz.hasNextToken()) break;
            statement= ParseStatement();
        }
        PlanAST plan = NodeCreate.Plan(StatementContainer);
        if(tkz.hasNextToken()) {
            while(tkz.hasNextToken())System.out.println(tkz.consume()+".");
            throw new SyntaxError("leftover token");
        }
        return plan;
    }

    private Statement ParseStatement() throws SyntaxError {
        Statement statement=null;
        if(tkz.peek("done") || tkz.peek("relocate") || tkz.peek("move") ||tkz.peek("invest")|| tkz.peek("collect") || tkz.peek("shoot") ||
                (IsNotReservedWord(tkz.peek()))) statement =ParseCommand();
        else if(tkz.peek("{")) statement = ParseBlockStatement();
        else if(tkz.peek("if")) statement =ParseIfStatement();
        else if(tkz.peek("while"))statement = ParseWhileStatement();
        return statement;
    }

    private Statement ParseCommand() throws SyntaxError {
        Statement statement =null;
        if(IsNotReservedWord(tkz.peek())) statement =ParseAssignmentStatement();
        else if(tkz.peek("done") || tkz.peek("relocate") || tkz.peek("move") ||tkz.peek("invest")|| tkz.peek("collect") || tkz.peek("shoot")) statement =ParseActionCommand();
        return statement;
    }
    private Statement ParseAssignmentStatement() throws SyntaxError {
        if(IsNotReservedWord(tkz.peek())){
            String assign_var = tkz.consume();
            tkz.consume("=");
            return NodeCreate.AssignVariable(assign_var,ParseExpression());
        }else return null;
    }
    private Statement ParseActionCommand() throws SyntaxError {
        Statement statement=null;
        if(tkz.peek("done")){
            tkz.consume();
            statement = NodeCreate.done();
        }
        else if(tkz.peek("relocate")){
            tkz.consume();
            statement = NodeCreate.relocate();
        }
        else if(tkz.peek("move")){
            tkz.consume();
            statement =ParseMoveCommand();
        }
        else if(tkz.peek("invest")||tkz.peek("collect")) {
            statement =ParseRegionCommand();
        }
        else if(tkz.peek("shoot")) {
            tkz.consume();
            statement =ParseAttackCommand();
        }
        return statement;
    }
    private moveAST ParseMoveCommand() throws SyntaxError {
        return NodeCreate.move(ParseDirection());
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
    private Statement ParseRegionCommand() throws SyntaxError {
        if(tkz.peek("invest")){
            tkz.consume();
            return NodeCreate.invest(ParseExpression());
        }else if(tkz.peek("collect")){
            tkz.consume();
            return NodeCreate.collect(ParseExpression());
        }else return null;
    }
    private Statement ParseAttackCommand() throws SyntaxError {
        return NodeCreate.shoot(ParseDirection(),ParseExpression());
    }
    private Statement ParseBlockStatement() throws SyntaxError {
        if(tkz.peek("{")){
            tkz.consume();
            Statement statement = ParseStatement();
            ArrayList<Statement> statementContainer = new ArrayList<>();
            while(statement != null){
                statementContainer.add(statement);
                statement = ParseStatement();
            }
            Statement Body = NodeCreate.BlockStatement(statementContainer);
            tkz.consume("}");
            return Body;
        }else return null;
    }
    private Statement ParseIfStatement() throws SyntaxError {
        if(tkz.peek("if")){
            tkz.consume();
            tkz.consume("(");
            Expr condition = ParseExpression();
            tkz.consume(")");
            tkz.consume("then");
            Statement TrueCondition = ParseStatement();
            tkz.consume("else");
            Statement FalseCondition=  ParseStatement();
            return NodeCreate.IfElse(condition,TrueCondition,FalseCondition);
        }else return null;
    }
    private Statement ParseWhileStatement() throws SyntaxError {
        if (tkz.peek("while")){
            tkz.consume();
            tkz.consume("(");
            Expr condition = ParseExpression();
            tkz.consume(")");
            return NodeCreate.While(condition,ParseStatement());
            }
        else return null;
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
    private Expr ParseFactor() throws SyntaxError {
        Expr P = ParsePower();
        while (tkz.peek("^")){
            tkz.consume();
            P = NodeCreate.PowExpr(P,ParsePower());
        }
        return P;
    }
    private Expr ParsePower() throws SyntaxError {
        if(isNumber(tkz.peek())){
            return NodeCreate.Long(Long.parseLong(tkz.consume()));
        }else if (tkz.peek("rows")){
            tkz.consume();
            return NodeCreate.Rows();
        } else if(tkz.peek("cols")){
            tkz.consume();
            return NodeCreate.Cols();
        }else if(tkz.peek("currow")){
            tkz.consume();
            return NodeCreate.Currow();
        }else if(tkz.peek("curcol")){
            tkz.consume();
            return NodeCreate.Curcol();
        }else if(tkz.peek("budget")){
            tkz.consume();
            return NodeCreate.Budget();
        }else if(tkz.peek("deposit")){
            tkz.consume();
            return NodeCreate.Deposit();
        }else if(tkz.peek("int")){
            tkz.consume();
            return NodeCreate.Int();
        }else if(tkz.peek("maxdeposit")){
            tkz.consume();
            return NodeCreate.Maxdeposit();
        }else if (tkz.peek("random")) {
            tkz.consume();
            return NodeCreate.Random();
        }else if(IsNotReservedWord(tkz.peek())){
            return NodeCreate.Variable(tkz.consume());
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
            return NodeCreate.opponent();
        }
        else if(tkz.peek("nearby")){
            tkz.consume();
            return NodeCreate.nearby(ParseDirection());
        }else throw new SyntaxError("InfoExpression ERROR");
    }

    private boolean IsNotReservedWord(String str) {
        return !ReservedWordSet.contains(str);
    }
    private boolean isNumber(String str){
        try{
            Long.parseLong(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
