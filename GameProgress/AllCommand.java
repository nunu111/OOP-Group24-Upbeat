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
    long GetRows();
    long GetCols();
    long GetCurrow();
    long GetCurcol();
    long GetBudget();
    long GetDeposit();
    long GetInterest();
    long GetMaxDeposit();
    int GetRandom();
    long opponent();
    long nearby(Direction dir);
    void done();
    void relocate();
    void move(Direction dir);
    void invest(long value);
    void collect(long value);
    void shoot(Direction dir,long value);
}
