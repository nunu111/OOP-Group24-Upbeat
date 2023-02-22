package AST;

public class IfElseAST {
    private Expr condition;
    private Expr ifStatement,elseStatement;
    public IfElseAST(Expr _condition,Expr _ifStatement,Expr _elseStatement){
        this.condition = _condition;
        this.ifStatement = _ifStatement;
        this.elseStatement = _elseStatement;
    }


}
