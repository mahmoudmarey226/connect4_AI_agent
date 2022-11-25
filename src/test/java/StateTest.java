import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;


/*
|o|o|o|o|o|o|Y|
|o|o|o|o|o|o|Y|
|o|o|o|o|Y|o|Y|
|Y|o|o|Y|R|R|Y|
|R|R|o|Y|R|R|Y|
|R|Y|o|Y|R|Y|R|

will be

sign    height, garbage , arrangement
    0   011,ggg,011 010,gggg,10 000,ggggg  011,ggg,000   100,ggg,011   111,000001

g is garbage

the arrangement bits are read from right to left (down to top in grid)

0 is 'Y'     and 1 is 'R'

 */
public class StateTest {
    @Test
    @DisplayName("isolate")
    void IsolateTest() {
//        long l = 0bABCDEF11111111;
//        State state = new State(l);
//        long exCol0 = G;
//        long exCol1 = F;
//        long exCol2 = E;
//        long exCol3 = D;
//        long exCol4 = C;
//        long exCol5 = B;
//        long exCol6 = A;
        long l = 0b011010111100100110011010011101111110001010001000000000111111111L;
        State state = new State(l);
        long exCol0 = 0b111111111;
        long exCol1 = 0b000000000;
        long exCol2 = 0b001010001;
        long exCol3 = 0b101111110;
        long exCol4 = 0b011010011;
        long exCol5 = 0b100100110;
        long exCol6 = 0b011010111;

        long myCol0 = state.isolateState(0);
        long myCol1 = state.isolateState(1);
        long myCol2 = state.isolateState(2);
        long myCol3 = state.isolateState(3);
        long myCol4 = state.isolateState(4);
        long myCol5 = state.isolateState(5);
        long myCol6 = state.isolateState(6);

        Assertions.assertEquals(exCol0 , myCol0);
        Assertions.assertEquals(exCol1 , myCol1);
        Assertions.assertEquals(exCol2 , myCol2);
        Assertions.assertEquals(exCol3 , myCol3);
        Assertions.assertEquals(exCol4 , myCol4);
        Assertions.assertEquals(exCol5 , myCol5);
        Assertions.assertEquals(exCol6 , myCol6);
    }

    void getArrangementBitsTest(){
        //                  |        |        |        |        |        |
        long l = 0b000000000111111111000000000111111111000000000111111111000000000L;


    }


}
