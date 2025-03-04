package com.GAME.UPBEAT.GameProgress;

public class Region {
    private final long row;
    private final long col;
    public double deposit;
    protected Player owner;
    protected Region[] PartnerRegion;
    public Region(long _row,long _col){
        this.row = _row ;
        this.col = _col ;
        this.owner = null;
        this.deposit = 0;
        PartnerRegion = new Region[]{null,null,null,null,null,null};
    }
    public boolean hasOwner(){
        if(owner == null) return false;
        else return !owner.lose;
    }
    public long row(){
        return row;
    }
    public long col(){return col;}
    /**
     *
     * @param Partner Region that want to add
     * @param dir 0-up,1-upright,2-downright,3-down,4-downleft,5-upleft
     */
    public void AddPartnerRegion(Region Partner, int dir){
        this.PartnerRegion[dir] =  Partner;
    }
    public void AddDepositToCenter(long init_deposit){
        this.deposit = init_deposit;
    }

}
