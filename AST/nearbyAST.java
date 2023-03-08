package AST;

import GameProgress.AllCommand;
import GameProgress.Command;

import java.util.Map;

public class nearbyAST implements Expr{
    AllCommand.Direction dir;
    public nearbyAST(AllCommand.Direction _dir){
        this.dir = _dir;
    }

    @Override
    public long eval(Map<String, Long> binding) throws EvalError {
        return Command.instance().opponent();
    }

    @Override
    public void prettyPrint(StringBuilder sb) {
        sb.append("nearby");
        sb.append(dir.toString());
    }
}
