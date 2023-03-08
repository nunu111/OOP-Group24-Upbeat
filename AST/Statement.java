package AST;

public interface Statement extends Node{
    boolean eval(boolean IsDone) throws EvalError;
}
