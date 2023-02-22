package GameProgress;

import AST.*;

import java.util.NoSuchElementException;

public class Parser{
    String[] reservedword = {"collect","done","down","downleft","downright","else","if","invest","{","}","(",")"
            ,"move","nearby","opponent","relocate","shoot","then","up","upleft","upright","while",};
    String[] SpecialVariable ={"rows","cols","currow","curcol","budget","deposit","int","maxdeposit","random"};
    private final Nodefactory NodeCreate= Nodefactory.instance();
    private final Tokenizer tkz;

    public Parser(Tokenizer _tkz){
        this.tkz = _tkz;
    }

    public PlanAST PlanParser() throws SyntaxError {
        Statement statement= ParseStatement();

        if(statement == null) throw new NoSuchElementException("Plan don't have element");
        else{
            PlanAST plan = new PlanAST();
            while(statement != null ){
                System.out.println("...");
                plan.StatementUpdate(statement);
                if(!tkz.hasNextToken()) break;
                statement= ParseStatement();

            }
            if(tkz.hasNextToken()) {
                while(tkz.hasNextToken())System.out.println(tkz.consume()+".");
                throw new SyntaxError("leftover token");
            }
            return plan;
        }
    }

    private Statement ParseStatement() throws SyntaxError {
        Statement statement = null;
            statement =ParseCommand();
            if(statement == null)statement =ParseBlockStatement();
            if(statement == null)statement =ParseIfStatement();
            if(statement == null)statement = ParseWhileStatement();
        return statement;
    }

    private Statement ParseCommand() throws SyntaxError {
        Statement statement =ParseAssignmentStatement();
        if(statement == null) statement =ParseActionCommand();
        return statement;
    }
    private Statement ParseAssignmentStatement() throws SyntaxError {
        if(IsNotReservedWord(tkz.peek()) && IsNotSpecialVariable(tkz.peek())){
            String assign_var = tkz.consume();
            tkz.consume("=");
            return new AssignVariableAST(assign_var,ParseExpression());
        }else return null;
    }
    private Statement ParseActionCommand() throws SyntaxError {
        Statement statement;
        if(tkz.peek("done")){
            tkz.consume();
            statement = new doneAST();
        }
        else if(tkz.peek("relocate")){
            tkz.consume();
            statement = new relocateAST();
        }
        else {
            statement =ParseMoveCommand();
            if(statement == null)ParseRegionCommand();
            if(statement == null)ParseAttackCommand();
        }
        return statement;
    }
    private moveAST ParseMoveCommand() throws SyntaxError {
        if(tkz.peek("move")){
            tkz.consume();
            return new moveAST(ParseDirection());
        }
        return null;
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
        }else if(tkz.peek("right")){
            tkz.consume();
            return AllCommand.Direction.right;
        }else if(tkz.peek("left")){
            tkz.consume();
            return AllCommand.Direction.left;
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
            return new investAST(ParseExpression());
        }else if(tkz.peek("collect")){
            tkz.consume();
            return new collectAST(ParseExpression());
        }
        return null;
    }
    private Statement ParseAttackCommand() throws SyntaxError {
        if(tkz.peek("shoot")){
            tkz.consume();
            return new shootAST(ParseDirection(),ParseExpression());
        }else return null;
    }
    private Statement ParseBlockStatement() throws SyntaxError {
        if(tkz.peek("{")){
            tkz.consume();
            Statement statement = ParseStatement();
            BodyStatement Body = new BlockStatementAST();
            while(statement != null){
                Body.StatementUpdate(statement);
                if(!tkz.hasNextToken()) break;
                statement = ParseStatement();
            }
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
            return new IfElseAST(condition,TrueCondition,FalseCondition);
        }else return null;
    }
    private Statement ParseWhileStatement() throws SyntaxError {
        if (tkz.peek("while")){
            tkz.consume();
            tkz.consume("(");
            Expr condition = ParseExpression();
            tkz.consume(")");
            return new WhileAST(condition,ParseStatement());
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
        }else if (tkz.peek("rows")){
            tkz.consume();
            return new LongAST(Command.instance().GetRows());
        }
        else if(tkz.peek("cols")){
            tkz.consume();
            return new LongAST(Command.instance().GetCols());
        }else if(tkz.peek("currow")){
            tkz.consume();
            return new LongAST(Command.instance().GetCurrow());
        }else if(tkz.peek("curcol")){
            tkz.consume();
            return new LongAST(Command.instance().GetCurcol());
        }else if(tkz.peek("budget")){
            tkz.consume();
            return new LongAST(Command.instance().GetBudget());
        }else if(tkz.peek("deposit")){
            tkz.consume();
            return new LongAST(Command.instance().GetDeposit());
        }else if(tkz.peek("int")){
            tkz.consume();
            return new LongAST(Command.instance().GetInterest());
        }else if(tkz.peek("maxdeposit")){
            tkz.consume();
            return new LongAST(Command.instance().GetMaxDeposit());
        }else if (tkz.peek("random")) {
            tkz.consume();
            return new LongAST(Command.instance().GetRandom()); // do on purpose
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
    private boolean IsNotSpecialVariable(String str){
        for(String key :SpecialVariable) {
            if(key.equals(str)) return false;
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
