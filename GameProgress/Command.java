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
        return lgame.row+1;
    }

    @Override
    public long GetCols() {
        return lgame.col+1;
    }

    @Override
    public long GetCurrow() {
        return lgame.listofplayer[ lgame.cur_player].city_crew.row+1;
    }

    @Override
    public long GetCurcol() {
        return lgame.listofplayer[ lgame.cur_player].city_crew.col+1;
    }

    @Override
    public long GetBudget() {
        return lgame.listofplayer[ lgame.cur_player].budget;
    }

    @Override
    public long GetDeposit() {
        return (long) lgame.listofplayer[ lgame.cur_player].city_crew.deposit;
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
        int Crewcol = (int) lgame.listofplayer[ lgame.cur_player].city_crew.col;
        int Crewrow = (int) lgame.listofplayer[ lgame.cur_player].city_crew.row;
        long Maxrow =lgame.row;
        long Maxcol =lgame.col;
        boolean found_op = false;
        if (Crewcol % 2 == 1) {
            int round = 1;
            int i = 1;
            int j = 0;
            boolean[] checkEdge = {false,false,false,false,false,false};
            while (!found_op&&(!(checkEdge[0]&&checkEdge[1]&&checkEdge[2]&&checkEdge[3]&&checkEdge[4]&&checkEdge[5]))) {
                if(indexAdjust(Crewrow - (round),Maxrow)==0) checkEdge[0]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol + (round),Maxcol)== Maxcol-1) checkEdge[1]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow-1||indexAdjust(Crewcol + (round),Maxcol)== Maxcol-1) checkEdge[2]=true;
                if(indexAdjust(Crewrow + (round),Maxrow)==Maxrow-1) checkEdge[3]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow-1||indexAdjust(Crewcol - (round),Maxcol)== 0) checkEdge[4]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol - (round),Maxcol)== 0) checkEdge[5]=true;

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
                if ((round % 2) == 0) i++;
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
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol + (round),Maxcol)== Maxcol-1) checkEdge[1]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow-1||indexAdjust(Crewcol + (round),Maxcol)== Maxcol-1) checkEdge[2]=true;
                if(indexAdjust(Crewrow + (round),Maxrow)==Maxrow-1) checkEdge[3]=true;
                if(indexAdjust(Crewrow + (round - j),Maxrow)==Maxrow-1||indexAdjust(Crewcol - (round),Maxcol)== 0) checkEdge[4]=true;
                if(indexAdjust(Crewrow - (round - i),Maxrow)==0||indexAdjust(Crewcol - (round),Maxcol)== 0) checkEdge[5]=true;

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
                if ((round % 2) == 0) i++;
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
        long distance;
        int digit;
        Region opponent = null;
        int Crewcol = (int) lgame.listofplayer[ lgame.cur_player].city_crew.col;
        int Crewrow = (int) lgame.listofplayer[ lgame.cur_player].city_crew.row;
        long Maxrow =lgame.row;
        long Maxcol =lgame.col;
        boolean found_op = false;
        int round = 1 ;
        if(dir.equals(Direction.up)){
            while (!found_op && Crewrow - (round) >= 1){
                if(lgame.field[indexAdjust(Crewrow - (round),Maxrow)][Crewcol].owner != null && !lgame.field[indexAdjust(Crewrow - (round),Maxrow)][Crewcol].owner.equals(lgame.listofplayer[lgame.cur_player])){
                    opponent = lgame.field[Crewrow - (round)][Crewcol];
                    found_op = true;
                }
                round++;
            }
        }else if(dir.equals(Direction.down)) {
            while (!found_op && Crewrow + (round) <= Maxrow ) {
                if (lgame.field[indexAdjust(Crewrow + (round), Maxrow)][Crewcol].owner != null && !lgame.field[indexAdjust(Crewrow - (round), Maxrow)][Crewcol].owner.equals(lgame.listofplayer[lgame.cur_player])) {
                    opponent = lgame.field[Crewrow + (round)][Crewcol];
                    found_op = true;
                }
                round++;
            }

        }else {
            if(Crewcol % 2 == 1){
                if(dir.equals(Direction.upleft)){

                    int i = 1;
                    while (!found_op && Crewrow - (round - i) >= 1 && Crewcol + (round) <= Maxcol-1){
                        if(lgame.field[indexAdjust(Crewrow - (round - i),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow - (round - i),Maxrow)][Crewcol + (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow - (round - i)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }

                } else if (dir.equals(Direction.downleft)) {

                    int j = 0;
                    while (!found_op && Crewrow + (round - j) <= Maxrow-1 && Crewcol + (round) <= Maxcol-1){
                        if(lgame.field[indexAdjust(Crewrow + (round - j),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow + (round - j),Maxrow)][Crewcol + (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow + (round - j)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }
                }else if (dir.equals(Direction.downright)) {

                    int j = 0;
                    while (!found_op && Crewrow + (round - j) <= Maxrow-1 && Crewcol - (round) >= 1){
                        if(lgame.field[indexAdjust(Crewrow + (round - j),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow + (round - j),Maxrow)][Crewcol - (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow + (round - j)][Crewcol - (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }
                }else if (dir.equals(Direction.upright)) {

                    int i = 1;
                    while (!found_op && Crewrow - (round - i) >= 1 && Crewcol - (round) >= 1){
                        if(lgame.field[indexAdjust(Crewrow - (round - i),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow - (round - i),Maxrow)][Crewcol - (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow - (round - i)][Crewcol - (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }
                }

            }else {
                if(dir.equals(Direction.upleft)){

                    int j = 0;
                    while (!found_op && Crewrow - (round - j) >= 1 && Crewcol + (round) <= Maxcol-1){
                        if(lgame.field[indexAdjust(Crewrow - (round - j),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow - (round - j),Maxrow)][Crewcol + (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow - (round - j)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }

                } else if (dir.equals(Direction.downleft)) {

                    int i = 1;
                    while (!found_op && Crewrow + (round - i) <= Maxrow-1 && Crewcol + (round) <= Maxcol-1){
                        if(lgame.field[indexAdjust(Crewrow + (round - i),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow + (round - i),Maxrow)][Crewcol + (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow + (round - i)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }
                }else if (dir.equals(Direction.downright)) {

                    int i = 1;
                    while (!found_op && Crewrow + (round - i) <= Maxrow-1 && Crewcol - (round) >= 1){
                        if(lgame.field[indexAdjust(Crewrow + (round - i),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow + (round - i),Maxrow)][Crewcol - (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow + (round - i)][Crewcol - (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }
                }else if (dir.equals(Direction.upright)) {

                    int j = 0;
                    while (!found_op && Crewrow - (round - j) >= 1 && Crewcol - (round) >= 1){
                        if(lgame.field[indexAdjust(Crewrow - (round - j),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !lgame.field[indexAdjust(Crewrow - (round - j),Maxrow)][Crewcol - (round)].owner.equals(lgame.listofplayer[lgame.cur_player])){
                            opponent = lgame.field[Crewrow - (round - j)][Crewcol - (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }
                }
            }
        }

        if(opponent == null) return 0;
        else {
            distance = round;
            int count = 0;
            int num = (int) opponent.deposit;
            while (num != 0) {
                num /= 10;
                ++count;
            }
            digit = count;
            return 100 * distance + digit;
        }
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
        int Crewcol = (int) lgame.listofplayer[ lgame.cur_player].city_crew.col;
        int Crewrow = (int) lgame.listofplayer[ lgame.cur_player].city_crew.row;
        int newCol = Crewcol;
        int newRow = Crewrow;
        if(lgame.listofplayer[ lgame.cur_player].budget>=1){
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
                lgame.listofplayer[ lgame.cur_player].city_crew.row=newRow;
                lgame.listofplayer[ lgame.cur_player].city_crew.col=newCol;
            }
            lgame.listofplayer[ lgame.cur_player].budget--;
        }else done();
    }

    @Override
    public void invest(double value) {
        lgame.listofplayer[ lgame.cur_player].budget--;
        if(lgame.listofplayer[ lgame.cur_player].budget>=value){
            lgame.listofplayer[ lgame.cur_player].budget-=value;
            lgame.field[(int) lgame.listofplayer[lgame.cur_player].city_crew.row][(int) lgame.listofplayer[lgame.cur_player].city_crew.col].owner=lgame.listofplayer[lgame.cur_player];
            if(lgame.listofplayer[ lgame.cur_player].city_crew.deposit+value<=lgame.max_dep)
            lgame.listofplayer[ lgame.cur_player].city_crew.deposit+=value;
            else lgame.listofplayer[ lgame.cur_player].city_crew.deposit = lgame.max_dep;
        }
    }

    @Override
    public void collect(double value) {
        if(lgame.listofplayer[ lgame.cur_player].budget<1) done();
        else {
            lgame.listofplayer[ lgame.cur_player].budget--;
            if(value<=lgame.listofplayer[ lgame.cur_player].city_crew.deposit){
                lgame.listofplayer[ lgame.cur_player].city_crew.deposit-= value;
                lgame.listofplayer[ lgame.cur_player].budget = (long) value;
                if(value==lgame.listofplayer[ lgame.cur_player].city_crew.deposit) lgame.listofplayer[ lgame.cur_player].city_crew.owner = null;
            }
        }
    }

    @Override
    public void shoot(Direction dir,long value) {
        int Crewcol = (int) lgame.listofplayer[ lgame.cur_player].city_crew.col;
        int Crewrow = (int) lgame.listofplayer[ lgame.cur_player].city_crew.row;
        int newCol = Crewcol;
        int newRow = Crewrow;
        if(lgame.listofplayer[ lgame.cur_player].budget-value>=1){
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
            lgame.listofplayer[ lgame.cur_player].budget -= value;
            Region opp =lgame.field[newRow][newCol];
            if(opp.deposit-value>=1){
                opp.deposit -= value;
            }else {
                for (int i = 0;i<lgame.listofplayer.length;i++){
                    if(lgame.listofplayer[i].city_center.row==newRow&&lgame.listofplayer[i].city_center.col==newCol)lgame.listofplayer[i].lose=true;
                }
                opp.deposit = 0;
                opp.owner = null;

            }
        }else done();
    }

    private int indexAdjust(long x,long max){
        if(x<0)return 0;
        else if(x<max)return (int) x;
        else return (int) max-1;
    }
}
