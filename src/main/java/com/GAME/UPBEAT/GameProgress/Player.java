package com.GAME.UPBEAT.GameProgress;

import java.util.HashSet;
import java.util.Set;

public class Player {
    public String name;
    public double budget;
    protected Region city_center;
    public Region city_crew;
    public boolean lose;
    protected long Turn;
    protected final Set<Region> OwnRegion ;

    public Player(String name, long budget, Region city_center, Region city_crew){
        System.out.println(budget);
        this.name = name;
        this.budget = budget;
        this.city_center = city_center;
        this.city_crew = city_crew;
        this.city_center.owner = this;
        this.city_crew.owner =this;
        this.Turn =1;
        this.lose = false;
        this.OwnRegion = new HashSet<>();
        OwnRegion.add(city_center);
        OwnRegion.add(city_crew);
    }
}
