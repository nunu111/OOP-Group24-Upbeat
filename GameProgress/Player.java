package GameProgress;

public class Player {
    protected String name;
    protected long budget;
    protected Region city_center;
    protected Region city_crew;

    Player(String name, long budget, Region city_center, Region city_crew){
        this.name = name;
        this.budget = budget;
        this.city_center = city_center;
        this.city_crew = city_crew;
    }
}
