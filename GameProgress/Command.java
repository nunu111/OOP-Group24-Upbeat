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
    public long GetRows() {
        return lgame.row;
    }

    @Override
    public long GetCols() {
        return lgame.col;
    }

    @Override
    public long GetCurrow() {
        return lgame.listofplayer[(int) lgame.cur_player].city_crew.row;
    }

    @Override
    public long GetCurcol() {
        return lgame.listofplayer[(int) lgame.cur_player].city_crew.col;
    }

    @Override
    public long GetBudget() {
        return lgame.listofplayer[(int) lgame.cur_player].budget;
    }

    @Override
    public long GetDeposit() {
        return (long) lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit;
    }

    @Override
    public long GetInterest() {
        return (long) lgame.interest_pct;
    }

    @Override
    public long GetMaxDeposit() {
        return lgame.max_dep;
    }

    @Override
    public int GetRandom() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    @Override
    public long opponent() {
        long distance ;
        long direction ;
        boolean found_op = false;
        while(!found_op){
            if(lgame.listofplayer[(int) lgame.cur_player].city_crew.col%2==1){

            }else{

            }
        }

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
