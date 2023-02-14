public class Region {
    protected long row;
    protected long col;
    protected double deposit;
    protected String owner;
    protected long rate;
    Region(long row,long col,long rate){
        this.row = row;
        this.col = col;
        this.deposit = 0;
        this.owner = null;
        this.rate=rate;
    }
}
