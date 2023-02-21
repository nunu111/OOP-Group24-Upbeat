public interface AllCommand {
    enum Direction {
        up,
        down,
        upleft,
        upright,
        downleft,
        downright
    }
    public long rows();
    public long cols();
    public long currow();
    public long curcol();
    public long budget();
    public long deposit();
    public long interest();
    public long maxdeposit();
    public long random();
    public long opponent();
    public long nearby();
    public void done();
    public void relocate();
    public void move(Direction dir);
    public void invest();
    public void collect();
    public void shoot(Direction dir);
    public void AssignVariable(String a,double b);
    public int GetVariableValue(String key);
}
