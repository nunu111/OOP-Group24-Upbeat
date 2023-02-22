package GameProgress;

import java.util.Random;
public class Command implements AllCommand {
    public Game lgame ;

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
    public void invest() {

    }

    @Override
    public void collect() {

    }

    @Override
    public void shoot(Direction dir) {

    }

    @Override
    public void AssignVariable(String var_name, double var_value) {
    }

    @Override
    public long GetVariableValue(String var_name) {
//        return lgame.listofplayer[(int) lgame.cur_player].assign_var.get(var_name);
        return 0;
    }
}
