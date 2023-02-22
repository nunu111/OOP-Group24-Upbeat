package GameProgress;

import java.util.HashMap;
import java.util.Random;

public class Game {
    public Region[][] field;
    public long col;
    public long row;
    public Player[] listofplayer;
    public long cur_player;
    public int init_plan_min;
    public int init_plan_sec;
    public long init_budget;
    public long init_center_dep;
    public int plan_rev_min;
    public int plan_rev_sec;
    public long rev_cost;
    public long max_dep;
    public int interest_pct;
    protected final HashMap<Player, HashMap<String,Double>> assign_var = new HashMap<>();

    public Game(long col, long row, long Num_p, String[] name,long init_budget, long rev_cost, int interest_pct, long max_dep, int init_plan_min, int init_plan_sec, long init_center_dep, int plan_rev_min, int plan_rev_sec){
        this.col = col;
        this.row = row;
        this.cur_player=0;
        this.field = new Region[(int) row][(int) col];
        this.init_center_dep = init_center_dep;
        this.init_budget = init_budget;
        for(int i = 0 ; i < row ; i++){
            for(int j = 0; j < col ; j++){
                this.field[i][j] = new Region(i+1,j+1);
            }
        }
        this.listofplayer = new Player[(int) Num_p];
        Region[] city_center = RandomMap(this.row,this.col,Num_p,init_center_dep);
        for (int i = 0 ; i < Num_p ; i++){
            this.listofplayer[i]= new Player(name[i],init_budget,city_center[i],city_center[i]);
        }
        this.rev_cost = rev_cost;
        this.interest_pct = interest_pct;
        this.max_dep = max_dep;
        this.init_plan_min = init_plan_min;
        this.init_plan_sec = init_plan_sec;
        this.plan_rev_min = plan_rev_min;
        this.plan_rev_sec = plan_rev_sec;
    }
    private Region[] RandomMap(long row, long col,long Num,long init_deposit){
        Random rand = new Random();
        Region[] randMap = new Region[(int) Num];
        for(long i = 0 ; i < Num ; i++){
            boolean check_locate_repeat = false;
            randMap[(int) i] = new Region(0,0);
            long y = 0;
            long x = 0;
            do{
                check_locate_repeat = false;
                y = rand.nextLong(row);
                x = rand.nextLong(col);
                for (long j = 0 ; j < i ; j++){
                    if(y == randMap[(int) j].row && x == randMap[(int) j].col ) check_locate_repeat=true;
                }
            }while (check_locate_repeat);
            randMap[(int) i].row = y;
            randMap[(int) i].col = x;
            randMap[(int) i].AddDepositToCenter(init_deposit);
        }
        return randMap;
    }

    //    void newTurn()

}
