package AST;

public class Nodefactory {
    private static Nodefactory instance;
    private Nodefactory(){

    }
    public static Nodefactory instance(){
        if(instance == null){
            instance = new Nodefactory();
        }
        return instance;
    }
    public BinaryArithAST PlusExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"+",l1);
    }
    public BinaryArithAST MinusExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"-",l1);
    }
    public BinaryArithAST MulExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"*",l1);
    }
    public BinaryArithAST DivideExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"/",l1);
    }
    public BinaryArithAST ModExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"%",l1);
    }
    public BinaryArithAST PowExpr(Expr f1, Expr l1){
        return new BinaryArithAST(f1,"^",l1);
    }
}
