public class Player {
    protected long budget;
    protected Region city_center;
    protected Region city_crew;

    Player(long budget, Region city_center,Region city_crew){
        this.budget = budget;
        this.city_center = city_center;
        this.city_crew = city_crew;
    }
}
