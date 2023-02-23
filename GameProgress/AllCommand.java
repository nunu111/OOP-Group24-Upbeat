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
    public long GetRows();
    public long GetCols();
    public long GetCurrow();
    public long GetCurcol();
    public long GetBudget();
    public long GetDeposit();
    public long GetInterest();
    public long GetMaxDeposit();
    public int GetRandom();
    public long opponent();
    public long nearby(Direction dir);
    public void done();
    public void relocate();
    public void move(Direction dir);
    public void invest(double value);
    public void collect(double value);
    public void shoot(Direction dir,long value);
}
