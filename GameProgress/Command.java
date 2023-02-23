package GameProgress;
import AST.Expr;

import java.util.Random;
public class Command implements AllCommand {
    public Game lgame ;
    private static Command instance;
    private Command(Game lgame){
        this.lgame=lgame;
    }
    public static Command instance(Game lgame){
        if(instance == null) instance = new Command(lgame);
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
        long distance;
        long direction;
        Region opponent = null;
        int Crewcol = (int) lgame.listofplayer[(int) lgame.cur_player].city_crew.col-1;
        int Crewrow = (int) lgame.listofplayer[(int) lgame.cur_player].city_crew.row-1;
        long Maxrow =lgame.row-1;
        long Maxcol =lgame.col-1;
        boolean found_op = false;
        if (Crewcol % 2 == 1) {
            int round = 1;
            int i = 1;
            int j = 0;
            boolean[] checkEdge = {false,false,false,false,false,false};
            while (!found_op&&(!(checkEdge[0]&&checkEdge[1]&&checkEdge[2]&&checkEdge[3]&&checkEdge[4]&&checkEdge[5]))) {
                if(indexAdjust(Crewrow - (round),Maxrow)==0) checkEdge[0]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol + (round),Maxcol)== Maxcol) checkEdge[1]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow||indexAdjust(Crewcol + (round),Maxcol)== Maxcol) checkEdge[2]=true;
                if(indexAdjust(Crewrow + (round),Maxrow)==0) checkEdge[3]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow||indexAdjust(Crewcol - (round),Maxcol)== Maxcol) checkEdge[4]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol - (round),Maxcol)== Maxcol) checkEdge[5]=true;

                if (lgame.field[indexAdjust(Crewrow - (round),Maxrow)][Crewcol].owner != null) {
                    opponent = lgame.field[Crewrow - (round)][Crewcol];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow - (round - i),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow - (round - i)][Crewcol + (round)];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow + (round - j),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow + (round - j)][Crewcol + (round)];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow + (round),Maxrow)][Crewcol].owner != null) {
                    opponent = lgame.field[Crewrow + (round)][Crewcol];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow + (round - j),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow + (round - j)][Crewcol - (round)];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow - (round - i),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow - (round - i)][Crewcol - (round)];
                    found_op = true;
                }
                if ((round & 2) == 0) i++;
                else j++;
                round++;
            }
        } else {
            int round = 1;
            int i = 1;
            int j = 0;
            boolean[] checkEdge = {false,false,false,false,false,false};
            while (!found_op&&(!(checkEdge[0]&&checkEdge[1]&&checkEdge[2]&&checkEdge[3]&&checkEdge[4]&&checkEdge[5]))) {
                if(indexAdjust(Crewrow - (round),Maxrow)==0) checkEdge[0]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol + (round),Maxcol)== Maxcol) checkEdge[1]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow||indexAdjust(Crewcol + (round),Maxcol)== Maxcol) checkEdge[2]=true;
                if(indexAdjust(Crewrow + (round),Maxrow)==0) checkEdge[3]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow||indexAdjust(Crewcol - (round),Maxcol)== Maxcol) checkEdge[4]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol - (round),Maxcol)== Maxcol) checkEdge[5]=true;

                if (lgame.field[indexAdjust(Crewrow - (round),Maxrow)][Crewcol].owner != null) {
                    opponent = lgame.field[Crewrow - (round)][Crewcol];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow - (round - j),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow - (round - j)][Crewcol + (round)];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow + (round - i),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow + (round - i)][Crewcol + (round)];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow + (round),Maxrow)][Crewcol].owner != null) {
                    opponent = lgame.field[Crewrow + (round)][Crewcol];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow + (round - i),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow + (round - i)][Crewcol - (round)];
                    found_op = true;
                } else if (lgame.field[indexAdjust(Crewrow - (round - j),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null) {
                    opponent = lgame.field[Crewrow - (round - j)][Crewcol - (round)];
                    found_op = true;
                }
                if ((round & 2) == 0) i++;
                else j++;
                round++;
            }
        }
            if (opponent==null) {
                if (Crewcol == opponent.col) {
                    distance = Math.abs(Crewrow - opponent.row);
                    if (Crewrow >= opponent.row) direction = 1;
                    else direction = 4;
                } else {
                    distance = Math.abs(Crewcol - opponent.col);
                    if (Crewcol >= opponent.col) {
                        if (Crewcol % 2 == 1) {
                            if (opponent.row > Crewrow) direction = 3;
                            else direction = 2;
                        } else {
                            if (opponent.row >= Crewrow) direction = 3;
                            else direction = 2;
                        }
                    } else {
                        if (Crewcol % 2 == 1) {
                            if (opponent.row > Crewrow) direction = 5;
                            else direction = 6;
                        } else {
                            if (opponent.row >= Crewrow) direction = 5;
                            else direction = 6;
                        }
                    }
                }
            } else {
                return 0;
            }
            return distance * 10 + direction;
    }


    @Override
    public long nearby(Direction dir) {
        return 0;
    }

    @Override
    public void done() {
        lgame.newTurn();
    }

    @Override
    public void relocate() {

    }

    @Override
    public void move(Direction dir) {
        int Crewcol = (int) lgame.listofplayer[(int) lgame.cur_player].city_crew.col;
        int Crewrow = (int) lgame.listofplayer[(int) lgame.cur_player].city_crew.row;
        int newCol = Crewcol;
        int newRow = Crewrow;
        if(lgame.listofplayer[(int) lgame.cur_player].budget>=1){
            if(dir.equals(Direction.up))newRow--;
            else if(dir.equals(Direction.down))newRow++;
            else if(Crewcol%2==1){
                if(dir.equals(Direction.upright)){
                    newCol++;
                }else if(dir.equals(Direction.downright)){
                    newRow++;
                    newCol++;
                }else if(dir.equals(Direction.downleft)){
                    newRow++;
                    newCol--;
                }else if(dir.equals(Direction.upleft)){
                    newCol--;
                }
            }else{
                if(dir.equals(Direction.upright)){
                    newRow--;
                    newCol++;
                }else if(dir.equals(Direction.downright)){
                    newCol++;
                }else if(dir.equals(Direction.downleft)){
                    newCol--;
                }else if(dir.equals(Direction.upleft)){
                    newRow--;
                    newCol--;
                }                
            }
            if(lgame.field[newRow][newCol].owner==null) {
                lgame.listofplayer[(int) lgame.cur_player].budget--;
                lgame.listofplayer[(int) lgame.cur_player].city_crew.row=newRow;
                lgame.listofplayer[(int) lgame.cur_player].city_crew.col=newCol;
            }
        }else done();
    }

    @Override
    public void invest(double value) {
        lgame.listofplayer[(int) lgame.cur_player].budget--;
        if(lgame.listofplayer[(int) lgame.cur_player].budget>=value){
            lgame.listofplayer[(int) lgame.cur_player].budget-=value;
            if(lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit+value<=lgame.max_dep)
            lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit+=value;
            else lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit = lgame.max_dep;
        }
    }

    @Override
    public void collect(double value) {
        if(lgame.listofplayer[(int) lgame.cur_player].budget<1) done();
        else {
            lgame.listofplayer[(int) lgame.cur_player].budget--;
            if(value<=lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit){
                lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit-= value;
                lgame.listofplayer[(int) lgame.cur_player].budget = (long) value;
                if(value==lgame.listofplayer[(int) lgame.cur_player].city_crew.deposit) lgame.listofplayer[(int) lgame.cur_player].city_crew.owner = null;
            }
        }
    }

    @Override
    public void shoot(Direction dir,long value) {
        int Crewcol = (int) lgame.listofplayer[(int) lgame.cur_player].city_crew.col;
        int Crewrow = (int) lgame.listofplayer[(int) lgame.cur_player].city_crew.row;
        int newCol = Crewcol;
        int newRow = Crewrow;
        if(lgame.listofplayer[(int) lgame.cur_player].budget-value>=1){
            if(dir.equals(Direction.up))newRow--;
            else if(dir.equals(Direction.down))newRow++;
            else if(Crewcol%2==1){
                if(dir.equals(Direction.upright)){
                    newCol++;
                }else if(dir.equals(Direction.downright)){
                    newRow++;
                    newCol++;
                }else if(dir.equals(Direction.downleft)){
                    newRow++;
                    newCol--;
                }else if(dir.equals(Direction.upleft)){
                    newCol--;
                }
            }else{
                if(dir.equals(Direction.upright)){
                    newRow--;
                    newCol++;
                }else if(dir.equals(Direction.downright)){
                    newCol++;
                }else if(dir.equals(Direction.downleft)){
                    newCol--;
                }else if(dir.equals(Direction.upleft)){
                    newRow--;
                    newCol--;
                }
            }
            lgame.listofplayer[(int) lgame.cur_player].budget -= value;
            Region opp =lgame.field[newRow][newCol];
            if(opp.deposit-value>=1){
                opp.deposit -= value;
            }else {
                opp.deposit = 0;
                opp.owner = null;

            }
        }else done();
    }

    private int indexAdjust(long x,long max){
        if(x<0)return 0;
        else if(x<=max)return (int) x;
        else return (int) max;
    }
}
