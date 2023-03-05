package AST;

import java.util.ArrayList;

public class BlockStatementAST implements Statement{
    private final ArrayList<Statement> AllBodyStatement;

    public BlockStatementAST(ArrayList<Statement> _AllBodyStatement){
        this.AllBodyStatement = new ArrayList<>();
        AllBodyStatement.addAll(_AllBodyStatement);
    }
    @Override
    public void eval() throws EvalError {
        for(Statement key : AllBodyStatement) key.eval();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("{\n");
        for(Statement key : AllBodyStatement) {
            sb.append("\t");
            key.prettyPrint(sb);
        }
        sb.append("}");

    }
}
