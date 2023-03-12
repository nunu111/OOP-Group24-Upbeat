package com.GAME.UPBEAT.GameProgress;

import com.GAME.UPBEAT.Server.ReceiveMessage.AddPlayer;

import java.util.Random;
public class Command implements AllCommand {
    public GameData gameData;
    private final FileReader ConfigReader = FileReader.Instance();
    private static Command instance;
    private Command(){}
    public static Command instance(){
        if(instance == null) instance = new Command();
        return instance;
    }
    public GameData GameInstaller(AddPlayer Players){ // parameter get player info from front-end(parameter connect with spring but does not create spring now)
        gameData = ConfigReader.ParsingConfigFile("/src/Configuration.txt");
        gameData.AddPlayer(Players.getNumOfPlayers(),Players.getNameOfPlayers());
        gameData.InterestUpdateInterest();
        return gameData;
    }

    @Override
    public long GetRows() {
        return gameData.row+1;
    }

    @Override
    public long GetCols() {
        return gameData.col+1;
    }

    @Override
    public long GetCurrow() {
        return gameData.ListOfPlayer[gameData.cur_player].city_crew.row()+1;
    }

    @Override
    public long GetCurcol() {
        return gameData.ListOfPlayer[gameData.cur_player].city_crew.col()+1;
    }

    @Override
    public long GetBudget() {
        return (long) gameData.ListOfPlayer[gameData.cur_player].budget;
    }

    @Override
    public long GetDeposit() {
        long Deposit = (long) gameData.ListOfPlayer[gameData.cur_player].city_crew.deposit;
        if(!gameData.ListOfPlayer[gameData.cur_player].city_crew.hasOwner()) Deposit*= -1;
        return Deposit;
    }

    @Override
    public long GetInterest() {
        Region CurrentRegion = gameData.ListOfPlayer[gameData.cur_player].city_crew;
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
        double d=  CurrentRegion.deposit;
        double b = (double) gameData.interest_pct;
        double t = CurrentPlayer.Turn;
        double r = b*Math.log10(d) *(Math.log(t));
        return (long)r;
    }

    @Override
    public long GetMaxDeposit() {
        return gameData.max_dep;
    }

    @Override
    public int GetRandom() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    @Override
    public long opponent() {
        Region[] CityCrewPartner = gameData.ListOfPlayer[gameData.cur_player].city_crew.PartnerRegion;
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
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
        Region CityCrewDirectionPartner = gameData.ListOfPlayer[gameData.cur_player].city_crew.PartnerRegion[SetDirection];
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
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
        gameData.newTurn();
        return true;
    }

    @Override
    public boolean relocate() {
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
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
        Region CityCrewDirectionPartner = gameData.ListOfPlayer[gameData.cur_player].city_crew.PartnerRegion[SetDirection];
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
        if(CurrentPlayer.budget >=1){
            CurrentPlayer.budget--;
            if(CityCrewDirectionPartner != null &&( !CityCrewDirectionPartner.hasOwner() || CityCrewDirectionPartner.owner.equals(CurrentPlayer) )){
                CurrentPlayer.city_crew = CityCrewDirectionPartner;
            }
        }else return done();
        return false;
    }

    @Override
    public boolean invest(long value) {
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
        if(CurrentPlayer.budget >= 1) {
            CurrentPlayer.budget--;
            if(CurrentPlayer.budget >= value){
                CurrentPlayer.budget -= value;
                if(CurrentPlayer.city_crew.deposit+value > gameData.max_dep) CurrentPlayer.city_crew.deposit = gameData.max_dep;
                else CurrentPlayer.city_crew.deposit+=value;
                CurrentPlayer.city_crew.owner = CurrentPlayer;
                CurrentPlayer.OwnRegion.add(CurrentPlayer.city_crew);
            }
            return false;
        }else return done();
    }

    @Override
    public boolean collect(long value) {
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
        if(CurrentPlayer.budget < 1) return done();
        else {
            CurrentPlayer.budget--;
            if(CurrentPlayer.city_crew.deposit >= value && CurrentPlayer.OwnRegion.contains(CurrentPlayer.city_crew)){
                CurrentPlayer.city_crew.deposit -= value;
                CurrentPlayer.budget +=  value;
                if(CurrentPlayer.city_crew.deposit < 1) {
                    CurrentPlayer.OwnRegion.remove(CurrentPlayer.city_crew);
                    CurrentPlayer.city_crew.owner = null;
                }
            }
        }
        return false;
    }

    @Override
    public boolean shoot(Direction dir,long value) {
        Player CurrentPlayer = gameData.ListOfPlayer[gameData.cur_player];
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
                Region CityCrewDirectionPartner = gameData.ListOfPlayer[gameData.cur_player].city_crew.PartnerRegion[SetDirection];
                if(CityCrewDirectionPartner.hasOwner()){
                    CityCrewDirectionPartner.deposit = Math.max(0, CityCrewDirectionPartner.deposit- value) ;
                    if(CityCrewDirectionPartner.deposit <1) {
                        if(CityCrewDirectionPartner.owner.city_center.equals(CityCrewDirectionPartner) ) {
                            CityCrewDirectionPartner.owner.lose = true;
                            CheckWining();
                        }
                        CityCrewDirectionPartner.owner.OwnRegion.remove(CityCrewDirectionPartner);
                        CityCrewDirectionPartner.owner =null;
                    }
                }

            }
        }
        return false;
    }
    private void CheckWining(){
        long player = 0;
        for(Player CheckWining: gameData.ListOfPlayer){
            if(!CheckWining.lose)player++ ;
            if(player >=2) return;
        }
        if(player == 1) gameData.winner = gameData.ListOfPlayer[gameData.cur_player];
    }
}
