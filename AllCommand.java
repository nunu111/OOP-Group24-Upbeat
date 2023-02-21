public interface AllCommand {
    enum Direction {
        up,
        down,
        upleft,
        upright,
        downleft,
        downright
    }
    public int rows();
    public int cols();
    public int currow(Region city_crew);
    public int curcol(Region city_crew);
    public int budget(Player p);
    public int deposit(Region cur);
    public int interest();
    public int maxdeposit();
    public int random();
    public int opponent(Region city_crew);
    public int nearby(Region city_crew);
    public void done();
    public void relocate();
    public void move(Direction dir);
    public void invest(Region cur);
    public void collect(Region cur);
    public void shoot(Direction dir);
    public void AssignVariable(String a,double b);
    public int GetVariableValue(String key);
}
