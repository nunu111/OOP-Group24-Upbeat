package GameProgress;

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
    public int random();
    public long opponent();
    public long nearby(Direction dir);
    public void done();
    public void relocate();
    public void move(Direction dir);
    public void invest();
    public void collect();
    public void shoot(Direction dir);
}
