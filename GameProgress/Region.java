package GameProgress;

public class Region {
    protected long row;
    protected long col;
    protected double deposit;
    protected String owner;
    public Region(long _row,long _col){
        this.row = _row ;
        this.col = _col ;
        this.owner = null;
        this.deposit = 0;
    }
    public void AddDepositToCenter(long init_deposit){
        this.deposit = init_deposit;
    }

}
