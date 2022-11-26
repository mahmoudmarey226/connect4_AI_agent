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
    private static final long col7Mask = 0b111111111L << (9*0);//55 zeros 9 ones 0 zeros
    private static final long col6Mask = 0b111111111L << (9*1);//46 zeros 9 ones 9 zeros
    private static final long col5Mask = 0b111111111L << (9*2);//37 zeros 9 ones 18 zeros
    private static final long col4Mask = 0b111111111L << (9*3);//28 zeros 9 ones 27 zeros
    private static final long col3Mask = 0b111111111L << (9*4);//19 zeros 9 ones 36 zeros
    private static final long col2Mask = 0b111111111L << (9*5);//10 zeros 9 ones 45 zeros
    private static final long col1Mask = 0b111111111L << (9*6);// 1 zero  9 ones 54 zeros
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
            if (highestFullHeight == 0 ){//corner case
                continue;
            }
            long colArrangementBits = getColArrangementBits(isolatedState , highestFullHeight);
            String colArrangementBitsS = Long.toBinaryString(colArrangementBits);

            int i;
            int row;
            for (i = 0; i < colArrangementBitsS.length(); i++) {
                char bit = colArrangementBitsS.charAt((colArrangementBitsS.length() -1) - i);
                row = (ROWS-1) -i;
                if (bit == '1'){
                    grid[row][col] = 'R';
                }
                else{
                    grid[row][col] = 'Y';
                }
            }
            for(; i<highestFullHeight ; i++){
                row = (ROWS-1) -i;
                grid[row][col] = 'Y';
            }


        }


        return grid;
    }

    public static long toLong(char[][] grid){
        int[] cols = new int[7];
        for (int col = 0; col < COLUMNS; col++) {
            int colHeight = getColHeight(grid , col);
            int arrangement = 0;
            for (int row = ROWS-1 , i=0; i < colHeight; row-- , i++) {
                int bit = (grid[row][col] == 'R') ? 1 : 0 ;
                arrangement += bit << i;
            }
            cols[col] = (colHeight << 6) + arrangement;
        }

        long result = 0;
        for (int col = 0; col < COLUMNS; col++) {
            int shift = (9*((COLUMNS-1) - col));
            result += (long)(cols[col]) << shift;
        }

        return result;
    }

    // check if the game finished or not

    public boolean isFinish(){
        return state < 0;
    }
    //returns arrayList of all possible neighbours

    public ArrayList<State> getAllNeighbours(boolean turn){
        ArrayList<State> neighbours = new ArrayList<>();
        for (int col = 0; col < COLUMNS; col++) {
            long neighbor = addDisk(turn , col+1);
            if (neighbor != 0L){
                neighbours.add(new State(neighbor));
            }
        }
        return neighbours;
    }

    public boolean addMove(boolean turn , int colNum){
        long result = addDisk(turn , colNum);
        if (result == 0L){
            return false;
        }
        else{
            this.state = result;
            return true;
        }

    }

    //add disk to a specific column
    //colNum from 1 to 7
    //col#1 is the most left column in grid or long
    private long addDisk(boolean turn , int colNum){
        colNum--;
        long isolatedState = isolateState(colNum);
        long highestFullHeight = isolatedState >> 6;// potential error , -+1

        if (highestFullHeight == 7){
            return 0;
        }
        else{

            //changing the arrangement in the isolated state
            long diskMask ;
            diskMask = 1L << (highestFullHeight);
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


            //assign the  isolated state to its place in resultState
            long resultState = this.state;
            isolatedState = isolatedState << (9*((COLUMNS-1)-colNum)); // return isolated state to its true place
            resultState = resultState & (~colsMasks[colNum]);//clear the 9 bits of the column
            resultState = resultState | isolatedState; // set the new 9 bits of the column

            return resultState;
        }


    }

    //returns the col arrangement bits isolated and shifted
    //private
    public long getColArrangementBits(long isolatedState, long highestFullHeight) {
        long mask = 0b000111111L;
        isolatedState = isolatedState & mask;

        long mask2 = (1L<<(highestFullHeight)) - 1;
        isolatedState = isolatedState & mask2;
        return isolatedState;
    }

    //private
    //takes numbers from 0 to COLUMNS-1
    //0 is the most left col in grid and the most left 9bits in long
    public long isolateState(int colNum) {
        long state = this.state & colsMasks[colNum];
        colNum = (COLUMNS-1)  - colNum;
        state = state >> (9*colNum);
        long clearMask = 0b111111111;
        state = state & clearMask; //clearing other bits
        return state;
    }
    //private

    public static int getColHeight(char[][] grid, int col) {

        for (int row = ROWS-1 ; row >= 0; row--) {
            if (grid[row][col] == 'o'){
                return (ROWS-1) - row;
            }
        }
        return 6;
    }

    private void initializeGrid(char[][] grid) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = 'o';
            }
        }
    }



}
