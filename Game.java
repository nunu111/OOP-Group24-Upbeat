import java.util.Random;

public class Game {
    public Region[][] field;
    public long col;
    public long row;
    public Player[] p;
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


    Game(long col, long row, long Num_p, String[] name,long init_budget, long rev_cost, int interest_pct, long max_dep){
        this.col = col;
        this.row = row;
        this.cur_player=0;
        this.field = new Region[(int) row][(int) col];
        for(int i = 0 ; i < row ; i++){
            for(int j = 0; j < col ; j++){
                this.field[i][j] = new Region(i+1,j+1);
            }
        }
        this.p = new Player[(int) Num_p];
        Region[] city_center = RandomMap(this.row,this.col,Num_p);
        for (int i = 0 ; i < Num_p ; i++){
            this.p[i]= new Player(name[i],init_budget,city_center[i],city_center[i]);
        }
        this.rev_cost = rev_cost;
        this.interest_pct = interest_pct;
        this.max_dep = max_dep;




    }
    private Region[] RandomMap(long row, long col,long Num){
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
        }
        return randMap;
    }

    //    void newTurn()

}
