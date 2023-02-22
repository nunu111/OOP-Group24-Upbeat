package GameProgress;
import AST.Expr;

import java.util.Random;
public class Command implements AllCommand {

    public Game lgame ;
    private static Command instance;

    private Command(){}
    public static Command instance(){
        if(instance == null) instance = new Command();
        return instance;
    }

    @Override
    public long rows() {
        return lgame.row;
    }

    @Override
    public long cols() {
        return lgame.col;
    }

    @Override
    public long currow() {
        return lgame.listofplayer[(int) lgame.cur_player].city_crew.row;
    }

    @Override
    public long curcol() {
        return lgame.listofplayer[(int) lgame.cur_player].city_crew.col;
    }

    @Override
    public long budget() {
        return lgame.listofplayer[(int) lgame.cur_player].budget;
    }

    @Override
    public long deposit() {
        return (long) lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit;
    }

    @Override
    public long interest() {
        return lgame.interest_pct;
    }

    @Override
    public long maxdeposit() {
        return lgame.max_dep;
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
    public void invest(double value) {

    }

    @Override
    public void collect(double value) {

    }

    @Override
    public void shoot(Direction dir) {

    }
}
