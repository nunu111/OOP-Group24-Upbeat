public interface AllCommand {
    int rows();
    int cols();
    int currow(Region city_crew);
    int curcol(Region city_crew);
    int budget(Player p);
    int deposit(Region cur);
    int interest();
    int maxdeposit();
    int random();
    int opponent(Region city_crew);

    int nearby(Region city_crew);

    void done();
    void relocate(Region city_crew);
    void move(String command,Region city_crew);
    void invest(Region cur);
    void collect(Region cur);
    void shoot(String command,Region city_crew);
    void AssignVariable(String a,double b);
    int GetVariableValue(String a);
}
