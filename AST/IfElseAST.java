package AST;

public class IfElseAST implements Statement{
    private final Expr condition;
    private final Statement ifStatement;
    private final Statement elseStatement;
    public IfElseAST(Expr _condition,Statement _ifStatement,Statement _elseStatement){
        this.condition = _condition;
        this.ifStatement = _ifStatement;
        this.elseStatement = _elseStatement;
    }


    @Override
    public void eval() throws EvalError {
        if(condition.eval(Variable_Storage.instance().GetVariableMap()) >0 ) {
            if(ifStatement != null)ifStatement.eval();
        }
        else {
            if(elseStatement != null)elseStatement.eval();
        }
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("if (");
        condition.prettyPrint(sb);
        sb.append(") then ");
        ifStatement.prettyPrint(sb);
        sb.append("else ");
        elseStatement.prettyPrint(sb);
        sb.append("\n");
    }
}
