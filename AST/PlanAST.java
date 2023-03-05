package AST;

import java.util.ArrayList;

public class PlanAST implements Statement{
    private final ArrayList<Statement> AllStatement;
    public PlanAST(ArrayList<Statement> _AllStatement ){
        AllStatement = new ArrayList<>();
        AllStatement.addAll(_AllStatement);
    }
    @Override
    public void eval() throws EvalError {
        for(Statement key : AllStatement) key.eval();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        for(Statement key : AllStatement) key.prettyPrint(sb);
    }
}
