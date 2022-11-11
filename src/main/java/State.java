import java.util.ArrayList;

public class State {
    long state;

    // columns masks , 511 is nine ones
    private static final long col1Mask = 511L << (9*0);//55 zeros 9 ones 0 zeros
    private static final long col2Mask = 511L << (9*1);//46 zeros 9 ones 9 zeros
    private static final long col3Mask = 511L << (9*2);//37 zeros 9 ones 18 zeros
    private static final long col4Mask = 511L << (9*3);//28 zeros 9 ones 27 zeros
    private static final long col5Mask = 511L << (9*4);//19 zeros 9 ones 36 zeros
    private static final long col6Mask = 511L << (9*5);//10 zeros 9 ones 45 zeros
    private static final long col7Mask = 511L << (9*6);// 1 zero  9 ones 54 zeros
    private static final long colStateMask = 511L << (9*6);// 1 zero  9 ones 54 zeros
    private static final long[] colsMasks = {col1Mask , col2Mask , col3Mask , col4Mask , col5Mask , col6Mask , col7Mask};

    public State(long state){
        this.state = state;
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
        long colMask = colsMasks[colNum];
        long highestFullHeight = getHighestFullHeight(colNum);
        if (highestFullHeight == 7){
            return false;
        }
        else{
            highestFullHeight++;
            changeHighestFullIndex(colNum , highestFullHeight);

            return true;
        }


    }

    private void changeHighestFullIndex(int colNum, long newHighestFullHeight) {
        long mask = newHighestFullHeight << (colNum*9 + 5);


    }

    //from 1 to 7
    private long getHighestFullHeight(int colNum) {
        long height = this.state >> (9*colNum + 5);
        return height+1;
    }





}
