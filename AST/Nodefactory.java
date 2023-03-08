package AST;

import AST.Expr.*;
import AST.Statement.*;
import GameProgress.AllCommand;

import java.util.ArrayList;

public class Nodefactory {
    private static Nodefactory instance;
    private Nodefactory(){}
    public static Nodefactory instance(){
        if(instance == null) instance = new Nodefactory();
        return instance;
    }
    public BinaryArithAST PlusExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"+",l1);
    }
    public BinaryArithAST MinusExpr(Expr f1, Expr l1){return new BinaryArithAST(f1,"-",l1);}
    public BinaryArithAST MultiplyExpr(Expr f1, Expr l1){return new BinaryArithAST(f1,"*",l1);}
    public BinaryArithAST DivideExpr(Expr f1, Expr l1){return new BinaryArithAST(f1,"/",l1);}
    public BinaryArithAST ModExpr(Expr f1, Expr l1){return new BinaryArithAST(f1,"%",l1);}
    public BinaryArithAST PowExpr(Expr f1, Expr l1){return new BinaryArithAST(f1,"^",l1);}
    public AssignVariableAST AssignVariable(String _assign_var, Expr _var_value){return new AssignVariableAST(_assign_var,_var_value);}
    public doneAST done(){return new doneAST();}
    public relocateAST relocate(){return new relocateAST();}
    public moveAST move(AllCommand.Direction dir){return new moveAST(dir);}
    public investAST invest(Expr value){return new investAST(value);}
    public collectAST collect(Expr value){return new collectAST(value);}
    public shootAST shoot(AllCommand.Direction dir,Expr value){return new shootAST(dir,value);}
    public LongAST Long(long value){return new LongAST(value);}
    public VariableAST Variable(String var){return new VariableAST(var);}
    public opponentAST opponent(){return new opponentAST();}
    public nearbyAST nearby(AllCommand.Direction dir){return new nearbyAST(dir);}
    public IfElseAST IfElse(Expr condition, Statement ifStatement, Statement elseStatement){return new IfElseAST(condition,ifStatement,elseStatement);}
    public WhileAST While(Expr condition,Statement Body){return new WhileAST(condition,Body);}
    public PlanAST Plan(ArrayList<Statement> StatementContainer){return new PlanAST(StatementContainer);}
    public BlockStatementAST BlockStatement(ArrayList<Statement> StatementContainer){return new BlockStatementAST(StatementContainer);}
    public RowsAST Rows(){return new RowsAST();}
    public ColsAST Cols(){return new ColsAST();}
    public CurrowAST Currow(){return new CurrowAST();}
    public CurcolAST Curcol(){return new CurcolAST();}
    public BudgetAST Budget(){return new BudgetAST();}
    public DepositAST Deposit(){return new DepositAST();}
    public IntAST Int(){return new IntAST();}
    public MaxdepositAST Maxdeposit(){return new MaxdepositAST();}
    public RandomAST Random(){return new RandomAST();}
}
