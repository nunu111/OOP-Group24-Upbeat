package GameProgress;

import java.util.Objects;

public class Player {
    protected String name;
    protected long budget;
    protected Region city_center;
    public Region city_crew;
    protected boolean lose;

    public Player(String name, long budget, Region city_center, Region city_crew){
        this.name = name;
        this.budget = budget;
        this.city_center = city_center;
        this.city_crew = city_crew;
        this.city_center.owner = this;
        this.city_crew.owner =this;
        this.lose = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return budget == player.budget && lose == player.lose && Objects.equals(name, player.name) && Objects.equals(city_center, player.city_center) && Objects.equals(city_crew, player.city_crew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, budget, city_center, city_crew, lose);
    }
}
