package com.GAME.UPBEAT.GameProgress;

public class ArrayToHexagonGrid {
    private static ArrayToHexagonGrid Refactor;
    private ArrayToHexagonGrid(){

    }
    public static ArrayToHexagonGrid Refactor(){
        if(Refactor == null) Refactor = new ArrayToHexagonGrid();
        return Refactor;
    }

    public void ArrayTOHex(Region[][] RegionArr,int row,int col){
        //i-แถว , j-หลัก
        for(int i=0;i<row ; i++){
            for(int j=0;j<col ; j++){
                Region CurRegion = RegionArr[i][j];
                boolean IsColEvenNum = (j+1)%2 ==0 ;
                if(i-1 >= 0) CurRegion.AddPartnerRegion(RegionArr[i-1][j],0); //╰(*°▽°*)╯
                if(j+1 < col){ //╰(*°▽°*)╯
                    if(IsColEvenNum && i-1 >= 0) CurRegion.AddPartnerRegion(RegionArr[i-1][j+1],1);
                    else if(!IsColEvenNum) CurRegion.AddPartnerRegion(RegionArr[i][j+1],1);
                }
                if(j+1 < col ){ //╰(*°▽°*)╯
                    if(IsColEvenNum) CurRegion.AddPartnerRegion(RegionArr[i][j+1],2);
                    else if(i +1 <row) CurRegion.AddPartnerRegion(RegionArr[i+1][j+1],2);
                }
                if(i+1 < row) CurRegion.AddPartnerRegion(RegionArr[i+1][j],3); //╰(*°▽°*)╯
                if(j-1 >=0 ) { //╰(*°▽°*)╯
                    if(IsColEvenNum) CurRegion.AddPartnerRegion(RegionArr[i][j-1],4);
                    else if(i+1 < row) CurRegion.AddPartnerRegion(RegionArr[i+1][j-1],4);
                }
                if(j-1 >=0 ) { //╰(*°▽°*)╯
                    if(IsColEvenNum && i-1 >=0) CurRegion.AddPartnerRegion(RegionArr[i-1][j-1],5);
                    else if(!IsColEvenNum)CurRegion.AddPartnerRegion(RegionArr[i][j-1],5);
                }
            }
        }
    }
}
