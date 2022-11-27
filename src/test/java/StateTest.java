import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;


/*
|o|o|o|o|o|o|Y|
|o|o|o|o|o|o|Y|
|o|o|o|o|Y|o|Y|
|Y|o|o|Y|R|R|Y|
|R|R|o|Y|R|R|Y|
|R|Y|o|Y|R|Y|R|

will be

sign    height, garbage , arrangement
    0   011,ggg,011 010,gggg,10 000,ggggg  011,ggg,000   100,gg,0111  011,ggg,110   111,000001

g is garbage

the arrangement bits are read from right to left (down to top in grid)

0 is 'Y'     and 1 is 'R'

 */


//        |R|Y|R|o|o|o|o|
//        |Y|Y|R|o|o|o|o|
//        |R|Y|R|o|o|o|o|
//        |Y|Y|R|o|R|Y|R|
//        |R|Y|R|o|R|R|Y|
//        |Y|Y|R|o|R|Y|R|
//
//        char[][] orgGrid = {
//                {'R','Y','R','o','o','o','o',},
//                {'Y','Y','R','o','o','o','o',},
//                {'R','Y','R','o','o','o','o',},
//                {'Y','Y','R','o','R','Y','R',},
//                {'R','Y','R','o','R','R','Y',},
//                {'Y','Y','R','o','R','Y','R',}
//        };

public class StateTest {
    @Test
    @DisplayName("isolate")
    void IsolateTest() {
        /*
        |o|o|o|o|o|o|Y|
        |o|o|o|o|o|o|Y|
        |o|o|o|o|Y|o|Y|
        |Y|o|o|Y|R|R|Y|
        |R|R|o|Y|R|R|Y|
        |R|Y|o|Y|R|Y|R|

        will be

        sign    height, garbage , arrangement
            0   011,ggg,011 010,gggg,10 000,ggggg  011,ggg,000   100,gg,0111  011,ggg,110   111,000001

         */

        // 0   011000011 010000010 000000000  011000000 100000111 011000110 111000001 L
        long l = 0b0011000011010000010000000000011000000100000111011000110111000001L;
//        long l = 0b011010111100100110011010011101111110001010001000000000111111111L;
        State state = new State(l);
        long exCol0 = 0b011000011;
        long exCol1 = 0b010000010;
        long exCol2 = 0b000000000;
        long exCol3 = 0b011000000;
        long exCol4 = 0b100000111;
        long exCol5 = 0b011000110;
        long exCol6 = 0b111000001;

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

    @Test
    @DisplayName("get arrangement bits")
    void GetArrangementBitsTest(){
        /*
        |o|o|o|o|o|o|Y|
        |o|o|o|o|o|o|Y|
        |o|o|o|o|Y|o|Y|
        |Y|o|o|Y|R|R|Y|
        |R|R|o|Y|R|R|Y|
        |R|Y|o|Y|R|Y|R|

         */

        long l = 0b011010111100100110011010011101111110001010001000000000110111111L;
//        long l = 0b011010111 100100110 011010011 101111110 001010001 000000000 110111111 L;
        State state = new State(l);

        long exCol0 = 0b111;
        long exCol1 = 0b0110;
        long exCol2 = 0b011;
        long exCol3 = 0b11110;
        long exCol4 = 0b1;
        long exCol5 = 0b0L;
        long exCol6 = 0b111111;

        Assertions.assertEquals(exCol0 , state.getColArrangementBits(state.isolateState(0) , 3));
        Assertions.assertEquals(exCol1 , state.getColArrangementBits(state.isolateState(1) , 4));
        Assertions.assertEquals(exCol2 , state.getColArrangementBits(state.isolateState(2) , 3));
        Assertions.assertEquals(exCol3 , state.getColArrangementBits(state.isolateState(3) , 5));
        Assertions.assertEquals(exCol4 , state.getColArrangementBits(state.isolateState(4) , 1));
        Assertions.assertEquals(exCol5 , state.getColArrangementBits(state.isolateState(5) , 0));
        Assertions.assertEquals(exCol6 , state.getColArrangementBits(state.isolateState(6) , 6));
    }

    @Test
    @DisplayName("state conversion test")
    void stateConversionTest(){
//        |o|o|o|o|o|o|Y|
//        |o|o|o|o|o|o|Y|
//        |o|o|o|o|Y|o|Y|
//        |Y|o|o|Y|R|R|Y|
//        |R|R|o|Y|R|R|Y|
//        |R|Y|o|Y|R|Y|R|
        char[][] writtenGrid = {
                {'o','o','o','o','o','o','Y',},
                {'o','o','o','o','o','o','Y',},
                {'o','o','o','o','Y','o','Y',},
                {'Y','o','o','Y','R','R','Y',},
                {'R','R','o','Y','R','R','Y',},
                {'R','Y','o','Y','R','Y','R',}
        };
//        long l = 0b011000011010000010000000000011000000100000111011000110111000001L;
//        long l = 110000001 011000110  100000111  011000000 000000000 010000010  011000011  L;
//        long l = 011000011 010000010  000000000  011000000 100000111 011000110   110000001     L;
        long l = 0b011000011010000010000000000011000000100000111011000110110000001L;
//        long l = 0b0110000001011000110100000111011000000000000000010000010011000011L;
        State writtenState = new State(l);



        long longFromGrid = State.toLong(writtenGrid);
        State stateFromGrid = new State(longFromGrid);
        char[][] gridFromState =writtenState.toGrid();

        Assertions.assertTrue(Arrays.deepEquals(writtenGrid , gridFromState ));
        Assertions.assertEquals(writtenState.state , stateFromGrid.state);

        char[][] gridFromStateFromGrid = stateFromGrid.toGrid();
        Assertions.assertTrue(Arrays.deepEquals(writtenGrid , gridFromStateFromGrid));
        Assertions.assertEquals(writtenState.state , State.toLong(gridFromState) );
    }

    @Test
    @DisplayName("add move test")
    void addMoveTest(){
        //assume we begin when turn = true , 'R'
        char[][] grid = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',}
        };
        char[][] grid1 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',}
        };
        char[][] grid2 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','R','o','o','o','o','o',}
        };
        char[][] grid7 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','R',}
        };
        char[][] grid12 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'R','Y','o','o','o','o','o',}
        };
        char[][] grid17 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','Y',}
        };
        char[][] grid11 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',}
        };
        char[][] grid111 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',}
        };
        char[][] grid1111 = {
                {'o','o','o','o','o','o','o',},
                {'o','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',}
        };
        char[][] grid11111 = {
                {'o','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',}
        };
        char[][] grid111111 = {
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',}
        };
        char[][] grid1111112 = {
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','o','o','o','o','o','o',},
                {'Y','o','o','o','o','o','o',},
                {'R','R','o','o','o','o','o',}
        };
        State state = new State(State.toLong(grid));
        State state1 = new State(State.toLong(grid1));
        State state2 = new State(State.toLong(grid2));
        State state7 = new State(State.toLong(grid7));
        State state12 = new State(State.toLong(grid12));
        State state17 = new State(State.toLong(grid17));
        State state11 = new State(State.toLong(grid11));
        State state111 = new State(State.toLong(grid111));
        State state1111 = new State(State.toLong(grid1111));
        State state11111 = new State(State.toLong(grid11111));
        State state111111 = new State(State.toLong(grid111111));
        State state1111112 = new State(State.toLong(grid1111112));

        boolean turn = true;

        state = new State(State.toLong(grid));
        Assertions.assertTrue(state.addMove(turn , 1));
        Assertions.assertEquals(state1.state , state.state);

//        turn = !turn;

        state = new State(State.toLong(grid));
        Assertions.assertTrue(state.addMove(turn , 2));
        Assertions.assertEquals(state2.state ,state.state );
//        turn = !turn;

        state = new State(State.toLong(grid));
        Assertions.assertTrue(state.addMove(turn , 7));
        Assertions.assertEquals(state7.state , state.state);
        turn = !turn;

        state = new State(State.toLong(grid1));
        Assertions.assertTrue(state.addMove(turn , 2));
        Assertions.assertEquals(state12.state , state.state);
//        turn = !turn;

        state = new State(State.toLong(grid1));
        Assertions.assertTrue(state.addMove(turn , 7));
        Assertions.assertEquals(state17.state , state.state);
//        turn = !turn;

        state = new State(State.toLong(grid1));
        Assertions.assertTrue(state.addMove(turn , 1));
        Assertions.assertEquals(state11.state , state.state);
        turn = !turn;

        Assertions.assertTrue(state.addMove(turn , 1));
        Assertions.assertEquals(state111.state , state.state);
        turn = !turn;

        Assertions.assertTrue(state.addMove(turn , 1));
        Assertions.assertEquals(state1111.state , state.state);
        turn = !turn;

        Assertions.assertTrue(state.addMove(turn , 1));
        Assertions.assertEquals(state11111.state , state.state);
        turn = !turn;

        Assertions.assertTrue(state.addMove(turn , 1));
        Assertions.assertEquals(state111111.state , state.state);
        turn = !turn;

        Assertions.assertTrue(state.addMove(turn , 2));
        Assertions.assertEquals(state1111112.state , state.state);
        turn = !turn;

        State temp = new State(state.state);
        Assertions.assertFalse(state.addMove(turn , 1));
        Assertions.assertEquals(state.state , temp.state);


    }

    @Test
    @DisplayName("Get all neighbours")
    void getAllNeighboursTest(){
        char[][] grid = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };

        State state = new State(State.toLong(grid));

        char[][] nr1 = {
                {'R','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] nr2 = {
                {'o','o','o','o','o','R','o',},
                {'R','R','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] nr3 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','R','Y','R','R','Y',}
        };
        char[][] nr4 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','R','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] nr5 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','R','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] nr7 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','R',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };


        char[][] ny1 = {
                {'Y','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] ny2 = {
                {'o','o','o','o','o','R','o',},
                {'R','Y','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] ny3 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','Y','Y','R','R','Y',}
        };
        char[][] ny4 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','Y','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] ny5 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','o',},
                {'Y','R','o','R','Y','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };
        char[][] ny7 = {
                {'o','o','o','o','o','R','o',},
                {'R','o','o','o','o','Y','o',},
                {'Y','R','o','o','o','Y','o',},
                {'R','Y','o','Y','o','Y','Y',},
                {'Y','R','o','R','o','R','Y',},
                {'R','R','o','Y','R','R','Y',}
        };

        ArrayList<char[][]> rNeighbours = new ArrayList<>();
        rNeighbours.add(nr1);
        rNeighbours.add(nr2);
        rNeighbours.add(nr3);
        rNeighbours.add(nr4);
        rNeighbours.add(nr5);
        rNeighbours.add(nr7);
        ArrayList<char[][]> yNeighbours = new ArrayList<>();
        yNeighbours.add(ny1);
        yNeighbours.add(ny2);
        yNeighbours.add(ny3);
        yNeighbours.add(ny4);
        yNeighbours.add(ny5);
        yNeighbours.add(ny7);

        ArrayList<State> exRneighbours = state.getAllNeighbours(true);
        ArrayList<State> exYneighbours = state.getAllNeighbours(false);




        Assertions.assertTrue(Arrays.deepEquals(exRneighbours.toArray() , rNeighbours.toArray()));
        Assertions.assertTrue(Arrays.deepEquals(exYneighbours.toArray() , yNeighbours.toArray()));

    }


    @Test
    @DisplayName("get column height from the grid")
    void getColHeightTest() {
        char[][] grid = {
                {'o','o','o','o','o','o','Y',},
                {'o','o','o','o','o','o','Y',},
                {'o','o','o','o','Y','o','Y',},
                {'Y','o','o','Y','R','R','Y',},
                {'R','R','o','Y','R','R','Y',},
                {'R','Y','o','Y','R','Y','R',}
        };
        int ex0 = 3;
        int ex1 = 2;
        int ex2 = 0;
        int ex3 = 3;
        int ex4 = 4;
        int ex5 = 3;
        int ex6 = 6;

        Assertions.assertEquals(ex0 , State.getColHeight(grid , 0));
        Assertions.assertEquals(ex1 , State.getColHeight(grid , 1));
        Assertions.assertEquals(ex2 , State.getColHeight(grid , 2));
        Assertions.assertEquals(ex3 , State.getColHeight(grid , 3));
        Assertions.assertEquals(ex4 , State.getColHeight(grid , 4));
        Assertions.assertEquals(ex5 , State.getColHeight(grid , 5));
        Assertions.assertEquals(ex6 , State.getColHeight(grid , 6));
    }


}
