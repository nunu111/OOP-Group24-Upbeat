import java.util.Random;
public class Command implements AllCommand {
    public Game a ;

    @Override
    public long rows() {
        return a.row;
    }

    @Override
    public long cols() {
        return a.col;
    }

    @Override
    public long currow() {
        return a.p[(int) a.cur_player].city_crew.row;
    }

    @Override
    public long curcol() {
        return a.p[(int) a.cur_player].city_crew.col;
    }

    @Override
    public long budget() {
        return a.p[(int) a.cur_player].budget;
    }

    @Override
    public long deposit() {
        return (long) a.p[(int) a.cur_player].city_crew.deposit;
    }

    @Override
    public long interest() {
        return a.interest_pct;
    }

    @Override
    public long maxdeposit() {
        return a.max_dep;
    }

    @Override
    public int random() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    @Override
    public long opponent() {

        return 0;
    }

    @Override
    public long nearby(Direction dir) {
        return 0;
    }

    @Override
    public void done() {

    }

    @Override
    public void relocate() {

    }

    @Override
    public void move(Direction dir) {

    }

    @Override
    public void invest() {

    }

    @Override
    public void collect() {

    }

    @Override
    public void shoot(Direction dir) {

    }

    @Override
    public void AssignVariable(String a, double b) {

    }

    @Override
    public double GetVariableValue(String a) {
        return 0;
    }
}
