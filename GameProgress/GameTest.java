package GameProgress;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void tester(){
        Game a =new Game(5,5,100,1,5,1000,5,20,50,5,20);
        Command x = Command.instance(a);
        String[] name={"a","b"};
        a.AddPlayer(2,name);



//        var a = new Game(new Game(44,52,4,"boegy",7,9,3,7,2,7,6,9,3,));
//        assertEquals(44,a.row);
//        assertEquals(52,a.col);
//        assertEquals(4,);
//        assertEquals("boegy",a.name[0]);
//        Player b = new Game(new Player("boegy",400,1,1));
//        Game b1 =  new Game(b);
//        assertEquals("boegy",b.getName());
    }



    @Test
    public void tester1(){

    }
}