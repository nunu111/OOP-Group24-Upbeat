package AST;

public interface Statement extends Node{
    void eval() throws EvalError;
}
