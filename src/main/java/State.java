import java.util.ArrayList;

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
public class State {
    long state;

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    // columns masks , 511 is nine ones
    private static final long col1Mask = 0b111111111L << (9*0);//55 zeros 9 ones 0 zeros
    private static final long col2Mask = 0b111111111L << (9*1);//46 zeros 9 ones 9 zeros
    private static final long col3Mask = 0b111111111L << (9*2);//37 zeros 9 ones 18 zeros
    private static final long col4Mask = 0b111111111L << (9*3);//28 zeros 9 ones 27 zeros
    private static final long col5Mask = 0b111111111L << (9*4);//19 zeros 9 ones 36 zeros
    private static final long col6Mask = 0b111111111L << (9*5);//10 zeros 9 ones 45 zeros
    private static final long col7Mask = 0b111111111L << (9*6);// 1 zero  9 ones 54 zeros
    private static final long[] colsMasks = {col1Mask , col2Mask , col3Mask , col4Mask , col5Mask , col6Mask , col7Mask};

    public State(long state){
        this.state = state;
    }

    public void printGrid(){
        char[][] grid = toGrid();
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(grid[i][j]+ "|");
            }
            System.out.println();
        }
    }

    public char[][] toGrid(){
        char[][] grid = new char[ROWS][COLUMNS];
        initializeGrid(grid);
        for (int col = 0; col < COLUMNS; col++) {
            long isolatedState = isolateState(col);
            long highestFullHeight = isolatedState >> 6;
            long colArrangementBits = getColArrangementBits(isolatedState , highestFullHeight);
            String colArrangementBitsS = Long.toBinaryString(colArrangementBits);

            for (int i = 0; i < colArrangementBitsS.length(); i++) {
                char bit = colArrangementBitsS.charAt(i);
                int row = (ROWS-1) -i;
                if (bit == '1'){
                    grid[row][col] = 'R';
                }
                else{
                    grid[row][col] = 'Y';
                }
            }


        }


        return grid;
    }

    public static long toLong(char[][] grid){


        return 0;
    }

    //private
    public void initializeGrid(char[][] grid) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = 'o';
            }
        }
    }

    // check if the game finished or not
    public boolean isFinish(){
        return state < 0;
    }

    //returns arrayList of all possible neighbours
    public ArrayList<State> getAllNeighbours(boolean turn){
        return null;
    }

    //add disk to a specific column
    //colNum from 1 to 7
    public boolean addDisk(boolean turn , int colNum){
        colNum--;
        long isolatedState = isolateState(colNum);
        long highestFullHeight = isolatedState >> 6;// potential error , -+1

        if (highestFullHeight == 7){
            return false;
        }
        else{

            //changing the arrangement in the isolated state
            long diskMask ;
            diskMask = 1L << (highestFullHeight+1);
            if (turn){
                isolatedState = isolatedState | diskMask;
            }
            else{
                diskMask = ~diskMask;
                isolatedState = isolatedState & diskMask;
            }

            //incrementing the highest full in the isolated state
            highestFullHeight++;
            long highestFullHeightSetMask = highestFullHeight << 6;
            long highestFullHeightClearMask = ~0b111000000L;

            isolatedState = isolatedState & highestFullHeightClearMask; //clear
            isolatedState = isolatedState | highestFullHeightSetMask; //set


            //assign the  isolated state to its place in this.state
            isolatedState = isolatedState << (9*colNum); // return isolated state to its true place
            this.state = this.state & (~colsMasks[colNum]);//clear the 9 bits of the column
            this.state = this.state | isolatedState; // set the new 9 bits of the column

            return true;
        }


    }

    //returns the col arrangement bits isolated and shifted
    //private
    public long getColArrangementBits(long isolatedState, long highestFullHeight) {
        long mask = 0b000111111;
        isolatedState = isolatedState & mask;
        isolatedState = isolatedState >> (6-highestFullHeight);
        return isolatedState;
    }

    //private
    public long isolateState(int colNum) {
        long state = this.state & colsMasks[colNum];
        state = state >> (9*colNum);
        long clearMask = 0b111111111;
        state = state & clearMask; //clearing other bits
        return state;
    }

    //private







}
