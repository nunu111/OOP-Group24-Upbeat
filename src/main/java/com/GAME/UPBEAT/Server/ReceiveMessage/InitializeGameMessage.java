package com.GAME.UPBEAT.Server.ReceiveMessage;

import com.GAME.UPBEAT.GameProgress.GameState;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class InitializeGameMessage {
    private final GameState Game = GameState.instance();
    private final long col;
    private final long row;
    private final int cur_player;
    private final long init_plan_min;
    private final long init_plan_sec;
    private final long init_budget;
    private final long init_center_dep;
    private final long plan_rev_min;
    private final long plan_rev_sec;
    private final long rev_cost;
    private final long max_dep;
    private final long interest_pct;
    private boolean GameReady = false;
    public InitializeGameMessage(){
        Game.GameStaring();
        this.col = Game.gameData.col;
        this.row = Game.gameData.row;
        this.cur_player=Game.gameData.cur_player;
        this.init_plan_min=Game.gameData.init_plan_min;
        this.init_plan_sec=Game.gameData.init_plan_sec;
        this.init_budget=Game.gameData.init_budget;
        this.init_center_dep=Game.gameData.init_center_dep;
        this.plan_rev_min=Game.gameData.init_plan_min;
        this.plan_rev_sec=Game.gameData.init_plan_sec;
        this.rev_cost=Game.gameData.rev_cost;
        this.max_dep=Game.gameData.max_dep;
        this.interest_pct=Game.gameData.interest_pct;
        this.GameReady = true;
    }
}
