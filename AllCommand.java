public interface AllCommand {
    enum Direction {
        up,
        down,
        upleft,
        upright,
        downleft,
        downright
    }
    public int rows();
    public int cols();
    public int currow();
    public int curcol();
    public int budget();
    public int deposit();
    public int interest();
    public int maxdeposit();
    public int random();
    public int opponent();
    public int nearby();
    public void done();
    public void relocate();
    public void move(Direction dir);
    public void invest();
    public void collect();
    public void shoot(Direction dir);
    public void AssignVariable(String a,double b);
    public int GetVariableValue(String key);
}
