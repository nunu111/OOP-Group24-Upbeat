package AST;

public class IfElseAST {
    private double condition;
    private Expr ifStatement,elseStatement;
    public IfElseAST(double _condition,Expr _ifStatement,Expr _elseStatement){
        this.condition = _condition;
        this.ifStatement = _ifStatement;
        this.elseStatement = _elseStatement;
    }


}
