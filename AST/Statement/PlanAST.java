package AST.Statement;

import AST.EvalError;
import AST.Statement.Statement;
import GameProgress.Command;

import java.util.ArrayList;

public class PlanAST implements Statement {
    private final ArrayList<Statement> AllStatement;
    public PlanAST(ArrayList<Statement> _AllStatement ){
        AllStatement = new ArrayList<>();
        AllStatement.addAll(_AllStatement);
    }
    @Override
    public boolean eval(boolean IsDone) throws EvalError {
        for(Statement key : AllStatement) {
            IsDone = key.eval(IsDone);
            if(IsDone) return true;
        }
        if(!IsDone) return Command.instance().done();
        return IsDone;
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        for(Statement key : AllStatement) key.prettyPrint(sb);
    }
}
