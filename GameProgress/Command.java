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
        return game.ListOfPlayer[game.cur_player].city_crew.row()+1;
    }

    @Override
    public long GetCurcol() {
        return game.ListOfPlayer[game.cur_player].city_crew.col()+1;
    }

    @Override
    public long GetBudget() {
        return game.ListOfPlayer[game.cur_player].budget;
    }

    @Override
    public long GetDeposit() { // Problem (┬┬﹏┬┬)
        return (long) game.ListOfPlayer[game.cur_player].city_crew.deposit;
    }

    @Override
    public long GetInterest() { // Problem (┬┬﹏┬┬)
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
                if(CheckOpponent[0].hasOwner() && !CheckOpponent[0].owner.equals(CurrentPlayer) )return returnValue;
                else CheckOpponent[0] = CheckOpponent[0].PartnerRegion[0];
            }
            returnValue +=1;
            if(CheckOpponent[1] != null){
                if(CheckOpponent[1].hasOwner() && !CheckOpponent[1].owner.equals(CurrentPlayer) ) return returnValue;
                else CheckOpponent[1] = CheckOpponent[1].PartnerRegion[1];
            }
            returnValue +=1;
            if(CheckOpponent[2] != null){
                if(CheckOpponent[2].hasOwner() && !CheckOpponent[2].owner.equals(CurrentPlayer) ) return returnValue;
                else CheckOpponent[2] = CheckOpponent[2].PartnerRegion[2];
            }
            returnValue +=1;
            if(CheckOpponent[3] != null){
                if(CheckOpponent[3].hasOwner() && !CheckOpponent[3].owner.equals(CurrentPlayer)) return returnValue;
                else CheckOpponent[3] = CheckOpponent[3].PartnerRegion[3];

            }
            returnValue +=1;
            if(CheckOpponent[4] != null){
                if(CheckOpponent[4].hasOwner() && !CheckOpponent[4].owner.equals(CurrentPlayer)) return returnValue;
                else CheckOpponent[4] = CheckOpponent[4].PartnerRegion[4];
            }
            returnValue +=1;
            if(CheckOpponent[5] != null){
                if(CheckOpponent[5].hasOwner() && !CheckOpponent[5].owner.equals(CurrentPlayer)) return returnValue;
                else CheckOpponent[5] = CheckOpponent[5].PartnerRegion[5];
            }
            returnValue += 5;
        }
        return 0;
    }


    @Override
    public long nearby(Direction dir) {
        int distance=1;
        int SetDirection=0;
        switch (dir) {
            case up -> {}
            case upright -> SetDirection = 1;
            case downright -> SetDirection = 2;
            case down -> SetDirection = 3;
            case downleft -> SetDirection = 4;
            case upleft -> SetDirection = 5;
        }
        Region CityCrewDirectionPartner = game.ListOfPlayer[game.cur_player].city_crew.PartnerRegion[SetDirection];
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        while(CityCrewDirectionPartner!=null){
            if(CityCrewDirectionPartner.hasOwner() && !CityCrewDirectionPartner.owner.equals(CurrentPlayer) ) {
                long digit = (long)Math.log10(CityCrewDirectionPartner.deposit)+1;
                return (100L*distance)+digit;
            }
            else CityCrewDirectionPartner = CityCrewDirectionPartner.PartnerRegion[SetDirection];
            distance += 1;
        }
        return 0;
    }

    @Override
    public boolean done() {
        game.newTurn();
        return true;
    }

    @Override
    public boolean relocate() {
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        Region CityCrew = CurrentPlayer.city_crew;
        Region CityCenter = CurrentPlayer.city_center;
        long distance = ShortestPathUnweighted.findShortest().shortestPath(CityCenter,CityCrew);
        if(distance != -1) {
            long travelValue = 5*distance+10;
            if( CityCrew.owner.equals(CurrentPlayer) && CurrentPlayer.budget >= travelValue){
                CurrentPlayer.budget -= travelValue;
            }
        }
        return done();
    }

    @Override
    public boolean move(Direction dir) {
        int SetDirection=0;
        switch (dir) {
            case up -> {}
            case upright -> SetDirection = 1;
            case downright -> SetDirection = 2;
            case down -> SetDirection = 3;
            case downleft -> SetDirection = 4;
            case upleft -> SetDirection = 5;
        }
        Region CityCrewDirectionPartner = game.ListOfPlayer[game.cur_player].city_crew.PartnerRegion[SetDirection];
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        if(CurrentPlayer.budget >=1){
            CurrentPlayer.budget--;
            if(CityCrewDirectionPartner !=null &&( !CityCrewDirectionPartner.hasOwner() || CityCrewDirectionPartner.owner.equals(CurrentPlayer) )){
                CurrentPlayer.city_crew.owner = null;
                CurrentPlayer.city_crew = CityCrewDirectionPartner;
                CurrentPlayer.city_crew.owner = CurrentPlayer;
            }
        }else return done();
        return false;
    }

    @Override
    public void invest(long value) {
        game.ListOfPlayer[ game.cur_player].budget--;
        if(game.ListOfPlayer[ game.cur_player].budget>=value){
            game.ListOfPlayer[ game.cur_player].budget-=value;
            game.field[(int) game.ListOfPlayer[game.cur_player].city_crew.row()][(int) game.ListOfPlayer[game.cur_player].city_crew.col()].owner= game.ListOfPlayer[game.cur_player];
            if(game.ListOfPlayer[ game.cur_player].city_crew.deposit+value<= game.max_dep)
            game.ListOfPlayer[ game.cur_player].city_crew.deposit+=value;
            else game.ListOfPlayer[ game.cur_player].city_crew.deposit = game.max_dep;
        }
    }

    @Override
    public boolean collect(long value) {
        if(game.ListOfPlayer[ game.cur_player].budget<1) return done();
        else {
            game.ListOfPlayer[ game.cur_player].budget--;
            if(value<= game.ListOfPlayer[ game.cur_player].city_crew.deposit){
                game.ListOfPlayer[ game.cur_player].city_crew.deposit-= value;
                game.ListOfPlayer[ game.cur_player].budget = (long) value;
                if(value== game.ListOfPlayer[ game.cur_player].city_crew.deposit) game.ListOfPlayer[ game.cur_player].city_crew.owner = null;
            }
        }
        return false;
    }

    @Override
    public boolean shoot(Direction dir,long value) {
        int Crewcol = (int) game.ListOfPlayer[ game.cur_player].city_crew.col();
        int Crewrow = (int) game.ListOfPlayer[ game.cur_player].city_crew.row();
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
                    if(game.ListOfPlayer[i].city_center.row()==newRow&& game.ListOfPlayer[i].city_center.col()==newCol)
                        game.ListOfPlayer[i].lose=true;
                }
                opp.deposit = 0;
                opp.owner = null;

            }
        }else return done();
        return false;
    }

    private int indexAdjust(long x,long max){
        if(x<0)return 0;
        else if(x<max)return (int) x;
        else return (int) max-1;
    }
}
