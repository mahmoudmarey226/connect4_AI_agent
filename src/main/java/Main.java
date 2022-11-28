public class Main {
    private static final boolean initiator = true; // 'R' will begin first
    /*
       1-initialization
       2-receive data from the GUI
       3-make changes  and calculations according to this data
       4-send data to the GUI back (available,p1Score,p2Score,grid)
            if not available move : go to 2 again
       5-perform the minimax algorithm to determine where the other player (PC) should play
       6-send the grid after PC's play to GUI
       7-if game not finished yet , go to 2 again ; else exit
     */
    public static void main(String[] args) {
        //initialzation//
        //assuming the human always begins
        State state = new State(0);//initial state
        boolean turn = initiator;

        //receive data from GUI
        int columnToPlayIn = receiveData();

    }

    private static int receiveData() {
        return 0;
    }
}
