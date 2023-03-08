package AST.Statement;

import AST.EvalError;
import AST.Node;

public interface Statement extends Node {
    boolean eval(boolean IsDone) throws EvalError;
}
