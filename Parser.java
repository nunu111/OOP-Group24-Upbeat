import java.util.NoSuchElementException;
import java.util.function.DoubleUnaryOperator;

public class Parser extends Command{
    String[] reservedword = {"collect","done","down","downleft","downright","else","if","invest","(",")"
            ,"move","nearby","opponent","relocate","shoot","then","up","upleft","upright","while","{","}"};
    private Tokenizer tkz;
    public Parser(Tokenizer _tkz){
        this.tkz = _tkz;
    }

    public void PlanParser() throws SyntaxError {
        if(!tkz.hasNextToken()) throw new NoSuchElementException("Plan don't have element");
        else{
            ParseStatement();
            if(tkz.hasNextToken()) {
                while(tkz.hasNextToken())System.out.println(tkz.consume());
                throw new SyntaxError("leftover token");
            }
        }
    }

    private void ParseStatement() throws SyntaxError {
        ParseCommand();
        ParseBlockStatement();
        ParseIfStatement();
        ParseWhileStatement();
    }

    private void ParseCommand() throws SyntaxError {
        ParseAssignmentStatement();
        ParseActionCommand();
    }
    private void ParseAssignmentStatement() throws SyntaxError { //<identifier> = Expression
        if(IsNotReservedWord(tkz.peek())){
            String assign_var = tkz.consume();
            if(tkz.peek("=")){
                tkz.consume();
                double value_of_var = ParseExpression();
                AssignVariable(assign_var,value_of_var);
            }else throw new SyntaxError("AssignmentStatement ERROR (Maybe forget \"=\" )");
        }
    }
    private void ParseActionCommand() throws SyntaxError {
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
        }
    }
    private void ParseMoveCommand() throws SyntaxError {
        if(tkz.peek("move")){
            tkz.consume();
            move(ParseDirection());
        }
    }
    private double ParseDirection() throws SyntaxError {
        if(tkz.peek("up")){
            tkz.consume();
            return Direction(Direction.up);
        }
        else if(tkz.peek("down")){
            tkz.consume();
            return Direction(Direction.down);
        }
        else if(tkz.peek("upleft")){
            tkz.consume();
            return Direction(Direction.upleft);
        }
        else if(tkz.peek("upright")){
            tkz.consume();
            return Direction(Direction.upright);
        }
        else if(tkz.peek("downleft")){
            tkz.consume();
            return Direction(Direction.downleft);
        }
        else if(tkz.peek("downright")){
            tkz.consume();
            return Direction(Direction.downright);
        }
        else throw new SyntaxError("Direction ERROR");
    }
    private void ParseRegionCommand(){
        if(tkz.peek("invest")){
            invest();
        }else if(tkz.peek("collect")){
            collect();
        }
    }
    private void ParseAttackCommand() throws SyntaxError {
        if(tkz.peek("shoot")){
            shoot(ParseDirection());
            ParseExpression();

        }
    }
    private void ParseBlockStatement() throws SyntaxError { //Complete
        if(tkz.peek("{")){
            tkz.consume();
            if(tkz.hasNextToken()) ParseStatement();
            if(tkz.peek("}")) tkz.consume();
            else throw new SyntaxError("Don't have end Block");
        }
    }
    private void ParseIfStatement() throws SyntaxError {
        if(tkz.peek("if")){
            tkz.consume();
            if(tkz.peek("(")){
                tkz.consume();
                ParseExpression();
                if(tkz.peek(")")){
                    tkz.consume();
                    if(tkz.peek("then")){
                        tkz.consume();
                        ParseStatement();
                    }
                    if(tkz.peek("else")){
                        tkz.consume();
                        ParseStatement();
                    }
                }else throw new SyntaxError("Wrong If Statement");
            } else throw new SyntaxError("Wrong If Statement");
        }
    }
    private void ParseWhileStatement() throws SyntaxError {
        if (tkz.peek("while")){
            tkz.consume();
            if(tkz.peek("(")){
                tkz.consume();
                ParseExpression();
                if(tkz.peek(")")) {
                    ParseStatement();
                    }else throw new SyntaxError("Wrong If Statement");
                }else throw new SyntaxError("Wrong If Statement");
            }
        }
    private double ParseExpression() throws SyntaxError {
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
    private double ParseTerm() throws SyntaxError {
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
    private double ParseFactor() throws SyntaxError {
        double P = ParsePower();
        while (tkz.peek("^")){
            if(tkz.peek("^")) {
                tkz.consume();
                P = Math.pow(P,ParseTerm());
            }
        }
        return P;
    }
    private double ParsePower() throws SyntaxError {
        if(isNumber(tkz.peek())){
            return Long.parseLong(tkz.consume());
        }else if(IsNotReservedWord(tkz.peek())){
            double assign_num = GetVariableValue(tkz.consume());
            return assign_num;
        }else if(tkz.peek("(")){
            tkz.consume();
            double calculate = ParseExpression();
            if(tkz.peek(")")) {
                tkz.consume();
                return calculate;
            }
            else throw new SyntaxError(") missing");
        }else return ParseInfoExpression();
    }

    private double ParseInfoExpression() throws SyntaxError {
        if(tkz.peek("opponent")){
            tkz.consume();
            return 0;
        }
        else if(tkz.peek("nearby")){
            tkz.consume();
            return ParseDirection();
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
            double num = Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
