package GameProgress;

import java.util.Random;
public class Command implements AllCommand {

    public Game game;
    private static Command instance;
    private Command(){}
    public static Command instance(){
        if(instance == null) instance = new Command();
        return instance;
    }
    @Override
    public long GetRows() {
        return game.row+1;
    }

    @Override
    public long GetCols() {
        return game.col+1;
    }

    @Override
    public long GetCurrow() {
        return game.ListOfPlayer[ game.cur_player].city_crew.row+1;
    }

    @Override
    public long GetCurcol() {
        return game.ListOfPlayer[ game.cur_player].city_crew.col+1;
    }

    @Override
    public long GetBudget() {
        return game.ListOfPlayer[ game.cur_player].budget;
    }

    @Override
    public long GetDeposit() {
        return (long) game.ListOfPlayer[ game.cur_player].city_crew.deposit;
    }

    @Override
    public long GetInterest() {
        return (long) game.interest_pct;
    }

    @Override
    public long GetMaxDeposit() {
        return game.max_dep;
    }

    @Override
    public int GetRandom() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    @Override
    public long opponent() {
        Region[] CityCrewPartner = game.ListOfPlayer[game.cur_player].city_crew.PartnerRegion;
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        Region[] CheckOpponent = {CityCrewPartner[0],CityCrewPartner[1],CityCrewPartner[2],
                CityCrewPartner[3],CityCrewPartner[4],CityCrewPartner[5]};
        int returnValue =11;
        while(CheckOpponent[0] !=null || CheckOpponent[1] !=null || CheckOpponent[2] !=null || CheckOpponent[3] !=null || CheckOpponent[4] !=null || CheckOpponent[5] !=null){
            if(CheckOpponent[0] != null) {
                if(CheckOpponent[0].hasOwner() && CheckOpponent[0].owner != CurrentPlayer )return returnValue;
                else CheckOpponent[0] = CheckOpponent[0].PartnerRegion[0];
            }
            returnValue +=1;
            if(CheckOpponent[1] != null){
                if(CheckOpponent[1].hasOwner() && CheckOpponent[1].owner != CurrentPlayer ) return returnValue;
                else CheckOpponent[1] = CheckOpponent[1].PartnerRegion[1];

            }
            returnValue +=1;
            if(CheckOpponent[2] != null){
                if(CheckOpponent[2].hasOwner() && CheckOpponent[2].owner != CurrentPlayer ) return returnValue;
                else CheckOpponent[2] = CheckOpponent[2].PartnerRegion[2];
            }
            returnValue +=1;
            if(CheckOpponent[3] != null){
                if(CheckOpponent[3].hasOwner() && CheckOpponent[3].owner != CurrentPlayer) return returnValue;
                else CheckOpponent[3] = CheckOpponent[3].PartnerRegion[3];

            }
            returnValue +=1;
            if(CheckOpponent[4] != null){
                if(CheckOpponent[4].hasOwner() && CheckOpponent[4].owner != CurrentPlayer) return returnValue;
                else CheckOpponent[4] = CheckOpponent[4].PartnerRegion[4];
            }
            returnValue +=1;
            if(CheckOpponent[5] != null){
                if(CheckOpponent[5].hasOwner() && CheckOpponent[5].owner != CurrentPlayer) return returnValue;
                else CheckOpponent[5] = CheckOpponent[5].PartnerRegion[5];
            }
            returnValue += 5;
        }
        return 0;
    }


    @Override
    public long nearby(Direction dir) {
        long distance;
        int digit;
        Region opponent = null;
        int Crewcol = (int) game.ListOfPlayer[ game.cur_player].city_crew.col;
        int Crewrow = (int) game.ListOfPlayer[ game.cur_player].city_crew.row;
        long Maxrow = game.row;
        long Maxcol = game.col;
        boolean found_op = false;
        int round = 1 ;
        if(dir.equals(Direction.up)){
            while (!found_op && Crewrow - (round) >= 1){
                if(game.field[indexAdjust(Crewrow - (round),Maxrow)][Crewcol].owner != null && !game.field[indexAdjust(Crewrow - (round),Maxrow)][Crewcol].owner.equals(game.ListOfPlayer[game.cur_player])){
                    opponent = game.field[Crewrow - (round)][Crewcol];
                    found_op = true;
                }
                round++;
            }
        }else if(dir.equals(Direction.down)) {
            while (!found_op && Crewrow + (round) <= Maxrow ) {
                if (game.field[indexAdjust(Crewrow + (round), Maxrow)][Crewcol].owner != null && !game.field[indexAdjust(Crewrow - (round), Maxrow)][Crewcol].owner.equals(game.ListOfPlayer[game.cur_player])) {
                    opponent = game.field[Crewrow + (round)][Crewcol];
                    found_op = true;
                }
                round++;
            }

        }else {
            if(Crewcol % 2 == 1){
                if(dir.equals(Direction.upleft)){

                    int i = 1;
                    while (!found_op && Crewrow - (round - i) >= 1 && Crewcol + (round) <= Maxcol-1){
                        if(game.field[indexAdjust(Crewrow - (round - i),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow - (round - i),Maxrow)][Crewcol + (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow - (round - i)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }

                } else if (dir.equals(Direction.downleft)) {

                    int j = 0;
                    while (!found_op && Crewrow + (round - j) <= Maxrow-1 && Crewcol + (round) <= Maxcol-1){
                        if(game.field[indexAdjust(Crewrow + (round - j),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow + (round - j),Maxrow)][Crewcol + (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow + (round - j)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }
                }else if (dir.equals(Direction.downright)) {

                    int j = 0;
                    while (!found_op && Crewrow + (round - j) <= Maxrow-1 && Crewcol - (round) >= 1){
                        if(game.field[indexAdjust(Crewrow + (round - j),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow + (round - j),Maxrow)][Crewcol - (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow + (round - j)][Crewcol - (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }
                }else if (dir.equals(Direction.upright)) {

                    int i = 1;
                    while (!found_op && Crewrow - (round - i) >= 1 && Crewcol - (round) >= 1){
                        if(game.field[indexAdjust(Crewrow - (round - i),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow - (round - i),Maxrow)][Crewcol - (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow - (round - i)][Crewcol - (round)];
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
                        if(game.field[indexAdjust(Crewrow - (round - j),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow - (round - j),Maxrow)][Crewcol + (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow - (round - j)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) ;
                        else j++;
                    }

                } else if (dir.equals(Direction.downleft)) {

                    int i = 1;
                    while (!found_op && Crewrow + (round - i) <= Maxrow-1 && Crewcol + (round) <= Maxcol-1){
                        if(game.field[indexAdjust(Crewrow + (round - i),Maxrow)][indexAdjust(Crewcol + (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow + (round - i),Maxrow)][Crewcol + (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow + (round - i)][Crewcol + (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }
                }else if (dir.equals(Direction.downright)) {

                    int i = 1;
                    while (!found_op && Crewrow + (round - i) <= Maxrow-1 && Crewcol - (round) >= 1){
                        if(game.field[indexAdjust(Crewrow + (round - i),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow + (round - i),Maxrow)][Crewcol - (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow + (round - i)][Crewcol - (round)];
                            found_op = true;
                        }
                        round++;
                        if ((round % 2) == 0) i++;
                    }
                }else if (dir.equals(Direction.upright)) {

                    int j = 0;
                    while (!found_op && Crewrow - (round - j) >= 1 && Crewcol - (round) >= 1){
                        if(game.field[indexAdjust(Crewrow - (round - j),Maxrow)][indexAdjust(Crewcol - (round),Maxcol)].owner != null && !game.field[indexAdjust(Crewrow - (round - j),Maxrow)][Crewcol - (round)].owner.equals(game.ListOfPlayer[game.cur_player])){
                            opponent = game.field[Crewrow - (round - j)][Crewcol - (round)];
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
        game.newTurn();
    }

    @Override
    public void relocate() {

    }

    @Override
    public void move(Direction dir) {
        int Crewcol = (int) game.ListOfPlayer[ game.cur_player].city_crew.col;
        int Crewrow = (int) game.ListOfPlayer[ game.cur_player].city_crew.row;
        int newCol = Crewcol;
        int newRow = Crewrow;
        if(game.ListOfPlayer[ game.cur_player].budget>=1){
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
            if(game.field[newRow][newCol].owner==null) {
                game.ListOfPlayer[ game.cur_player].city_crew.row=newRow;
                game.ListOfPlayer[ game.cur_player].city_crew.col=newCol;
            }
            game.ListOfPlayer[ game.cur_player].budget--;
        }else done();
    }

    @Override
    public void invest(double value) {
        game.ListOfPlayer[ game.cur_player].budget--;
        if(game.ListOfPlayer[ game.cur_player].budget>=value){
            game.ListOfPlayer[ game.cur_player].budget-=value;
            game.field[(int) game.ListOfPlayer[game.cur_player].city_crew.row][(int) game.ListOfPlayer[game.cur_player].city_crew.col].owner= game.ListOfPlayer[game.cur_player];
            if(game.ListOfPlayer[ game.cur_player].city_crew.deposit+value<= game.max_dep)
            game.ListOfPlayer[ game.cur_player].city_crew.deposit+=value;
            else game.ListOfPlayer[ game.cur_player].city_crew.deposit = game.max_dep;
        }
    }

    @Override
    public void collect(double value) {
        if(game.ListOfPlayer[ game.cur_player].budget<1) done();
        else {
            game.ListOfPlayer[ game.cur_player].budget--;
            if(value<= game.ListOfPlayer[ game.cur_player].city_crew.deposit){
                game.ListOfPlayer[ game.cur_player].city_crew.deposit-= value;
                game.ListOfPlayer[ game.cur_player].budget = (long) value;
                if(value== game.ListOfPlayer[ game.cur_player].city_crew.deposit) game.ListOfPlayer[ game.cur_player].city_crew.owner = null;
            }
        }
    }

    @Override
    public void shoot(Direction dir,long value) {
        int Crewcol = (int) game.ListOfPlayer[ game.cur_player].city_crew.col;
        int Crewrow = (int) game.ListOfPlayer[ game.cur_player].city_crew.row;
        int newCol = Crewcol;
        int newRow = Crewrow;
        if(game.ListOfPlayer[ game.cur_player].budget-value>=1){
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
            game.ListOfPlayer[ game.cur_player].budget -= value;
            Region opp = game.field[newRow][newCol];
            if(opp.deposit-value>=1){
                opp.deposit -= value;
            }else {
                for (int i = 0; i< game.ListOfPlayer.length; i++){
                    if(game.ListOfPlayer[i].city_center.row==newRow&& game.ListOfPlayer[i].city_center.col==newCol)
                        game.ListOfPlayer[i].lose=true;
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
