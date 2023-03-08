package GameProgress;

import java.util.HashSet;
import java.util.Random;

public class Game {
    public Region[][] field;
    public long col;
    public long row;
    public Player[] ListOfPlayer;
    public int cur_player;
    public long init_plan_min;
    public long init_plan_sec;
    public long init_budget;
    public long init_center_dep;
    public long plan_rev_min;
    public long plan_rev_sec;
    public long rev_cost;
    public long max_dep;
    public double interest_pct;


    public Game(long col, long row,long init_budget, long rev_cost, double interest_pct,
                long max_dep, long init_plan_min, long init_plan_sec, long init_center_dep, long plan_rev_min, long plan_rev_sec){
        this.col = col;
        this.row = row;
        this.cur_player=0;
        this.field = new Region[(int) row][(int) col];
        this.init_center_dep = init_center_dep;
        this.init_budget = init_budget;
        for(int i = 0 ; i < row ; i++){
            for(int j = 0; j < col ; j++){
                this.field[i][j] = new Region(i,j);
            }
        }
        ArrayToHexagonGrid.Refactor().ArrayTOHex(this.field,(int)row,(int)col);
        this.rev_cost = rev_cost;
        this.interest_pct = interest_pct;
        this.max_dep = max_dep;
        this.init_plan_min = init_plan_min;
        this.init_plan_sec = init_plan_sec;
        this.plan_rev_min = plan_rev_min;
        this.plan_rev_sec = plan_rev_sec;
    }
    private Region[] RandomMap(long row, long col,long PlayerNum,long init_deposit){
        Random rand = new Random();
        HashSet<Region> randMap = new HashSet<>();
        for(int i =0;i<PlayerNum;i++){
            long x = rand.nextLong(col);
            long y = rand.nextLong(row);
            while(randMap.contains(field[(int) y][(int) x])){
                x = rand.nextLong(col);
                y = rand.nextLong(row);

            }
            field[(int)y][(int)x].AddDepositToCenter(init_deposit);
            randMap.add(field[(int)y][(int)x]);
        }
        return randMap.toArray(new Region[0]);
    }
    public void AddPlayer( long Num_p, String[] name){
        this.ListOfPlayer = new Player[(int) Num_p];
        Region[] city_center = RandomMap(this.row,this.col,Num_p,init_center_dep);
        for (int i = 0 ; i < Num_p ; i++){
            this.ListOfPlayer[i]= new Player(name[i],init_budget,city_center[i],city_center[i]);
        }
    }
    public Player GetCurrentPlayer(){
        return ListOfPlayer[cur_player];
    }
    void newTurn(){
        if(cur_player < ListOfPlayer.length-1) cur_player++;
        else cur_player=0;
    }


}
