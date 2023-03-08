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
    boolean done();
    boolean relocate();
    boolean move(Direction dir);
    boolean invest(long value);
    boolean collect(long value);
    boolean shoot(Direction dir,long value);
}
