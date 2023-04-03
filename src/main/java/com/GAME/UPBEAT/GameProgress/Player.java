package com.GAME.UPBEAT.GameProgress;

import com.GAME.UPBEAT.AST.ASTStatement.PlanAST;

import java.util.HashSet;
import java.util.Set;

public class Player {
    public String name;
    public double budget;
    public Region city_center;
    public Region city_crew;
    public boolean lose;
    public long Turn;
    public final Set<Region> OwnRegion ;
    public long init_plan_min;
    public long init_plan_sec;
    public long plan_rev_min;
    public long plan_rev_sec;
    public String code;
    public PlanAST OldPlan;
    public Player(String name, long budget, Region city_center, Region city_crew,long init_plan_min,long init_plan_sec,long plan_rev_min,long plan_rev_sec)  {
        this.name = name;
        this.budget = budget;
        this.city_center = city_center;
        this.city_crew = city_crew;
        this.city_center.owner = this;
        this.city_crew.owner =this;
        this.Turn =1;
        this.lose = false;
        this.OwnRegion = new HashSet<>();
        this.init_plan_min=init_plan_min;
        this.init_plan_sec=init_plan_sec;
        this.plan_rev_min=plan_rev_min;
        this.plan_rev_sec=plan_rev_sec;
        this.code = "";
        OwnRegion.add(city_center);
    }
}
