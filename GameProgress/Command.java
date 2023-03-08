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
        return (long)game.ListOfPlayer[game.cur_player].budget;
    }

    @Override
    public long GetDeposit() {
        long Deposit = (long) game.ListOfPlayer[game.cur_player].city_crew.deposit;
        if(!game.ListOfPlayer[game.cur_player].city_crew.hasOwner()) Deposit*= -1;
        return Deposit;
    }

    @Override
    public long GetInterest() { // Problem (┬┬﹏┬┬)
        Region CurrentRegion = game.ListOfPlayer[game.cur_player].city_crew;
        double d=  CurrentRegion.deposit;
        double b = (double) game.interest_pct;
        double t = game.Turn;
        double r = b*Math.log10(d) *(Math.log(t));
        return (long)r;
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
            if( CityCrew.hasOwner() && CityCrew.owner.equals(CurrentPlayer) && CurrentPlayer.budget >= travelValue){
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
            if(CityCrewDirectionPartner != null &&( !CityCrewDirectionPartner.hasOwner() || CityCrewDirectionPartner.owner.equals(CurrentPlayer) )){
//                CurrentPlayer.city_crew.owner = null;
                CurrentPlayer.city_crew = CityCrewDirectionPartner;
//                CurrentPlayer.city_crew.owner = CurrentPlayer;
            }
        }else return done();
        return false;
    }

    @Override
    public boolean invest(long value) {
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        if(CurrentPlayer.budget >= 1) {
            CurrentPlayer.budget--;
            if(CurrentPlayer.budget >= value){
                CurrentPlayer.budget -= value;
                if(CurrentPlayer.city_crew.deposit+value > game.max_dep) CurrentPlayer.city_crew.deposit = game.max_dep;
                else CurrentPlayer.city_crew.deposit+=value;
                CurrentPlayer.city_crew.owner = CurrentPlayer;
            }
            return false;
        }else return done();
    }

    @Override
    public boolean collect(long value) {
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        if(CurrentPlayer.budget < 1) return done();
        else {
            CurrentPlayer.budget--;
            if(CurrentPlayer.city_crew.deposit >= value ){
                CurrentPlayer.city_crew.deposit -= value;
                CurrentPlayer.budget +=  value;
                if(CurrentPlayer.city_crew.deposit == 0) CurrentPlayer.city_crew.owner = null;
            }
        }
        return false;
    }

    @Override
    public boolean shoot(Direction dir,long value) {
        Player CurrentPlayer = game.ListOfPlayer[game.cur_player];
        if(CurrentPlayer.budget < 1) return done();
        else{
            CurrentPlayer.budget--;
            if(CurrentPlayer.budget >=value){
                int SetDirection=0;
                switch (dir) {
                    case up -> {}
                    case upright -> SetDirection = 1;
                    case downright -> SetDirection = 2;
                    case down -> SetDirection = 3;
                    case downleft -> SetDirection = 4;
                    case upleft -> SetDirection = 5;
                }
                CurrentPlayer.budget -= value;
                Region CityCrewDirectionPartner = game.ListOfPlayer[game.cur_player].city_crew.PartnerRegion[SetDirection];
                if(CityCrewDirectionPartner.hasOwner()){
                    CityCrewDirectionPartner.deposit = Math.max(0, CityCrewDirectionPartner.deposit- value) ;
                    if(CityCrewDirectionPartner.deposit <1) {
                        if(CityCrewDirectionPartner.owner.city_center.equals(CityCrewDirectionPartner) ) {
                            CityCrewDirectionPartner.owner.lose = true;
                            CheckWining();
                        }
                        CityCrewDirectionPartner.owner =null;
                    }
                }

            }
        }
        return false;
    }
    private void CheckWining(){
        long player = 0;
        for(Player CheckWining: game.ListOfPlayer){
            if(!CheckWining.lose)player++ ;
            if(player >=2) break;
        }
        if(player == 1) game.winner = game.ListOfPlayer[game.cur_player];
    }
}
