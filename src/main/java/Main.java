import py4j.GatewayServer;

public class Main {
    private static final int    levels = 5;
    private static final boolean initiator = true; // 'R' will begin first
    private static State state = new State(0);
    /*
       1-initialization
       2-receive data from the GUI
       3-make changes  and calculations according to this data
       4-send data to the GUI back (available,p1Score,p2Score,grid)
            if not available move : send to GUI and then go to 2 again
       5-perform the minimax algorithm to determine where the other player (PC) should play
       6-send the grid after PC's play to GUI
       7-if game not finished yet , go to 2 again ; else exit
     */
    public static int[][] play(int columnToPlayIn){
//        State state = new State(0);//initial state
        boolean turn = initiator;
        Minimax minimax = new Minimax();

        //receive data from GUI
//        int columnToPlayIn = receiveData();

        //make changes and calculations
        boolean availableMove = state.addMove(turn , columnToPlayIn);

        //send data to GUI
        if (!availableMove){
            return dataToMatrix(0);
        }

        //this will be uncommented if we send data for the human move
        //and then perform the algorithm and make PC's move then send data
        //but what actually happens is that we take the human move and
        //then perform the algorithm make PC's move and then send data only once

//            char[][] stateGrid = state.toGrid();
//            Data dataObject = new Data(availableMove , State.getScore(stateGrid , true) , State.getScore(stateGrid , false)
//                    , state.isFinish() , stateGrid);
//            sendData(dataObject);

        //perform the algorithm to determine PC's move

//        columnToPlayIn = minimax.minimax(state , !turn);
//        state.addMove(!turn, columnToPlayIn);
        state = minimax.minimax(state, levels , !turn);

        char[][] stateGrid = state.toGrid();
        Data dataObject = new Data(true , State.getScore(stateGrid , true) , State.getScore(stateGrid , false)
                , state.isFinish() , stateGrid);
        state.printGrid();
        System.out.println("**********************************");
        return dataToMatrix(dataObject);


    }
    public static void main(String[] args) {
        //initialzation//
        //assuming the human always begins
        Main app=new Main();
        GatewayServer server = new GatewayServer(app);
        server.start();
    }



    private static int[][] dataToMatrix(Data dataObject) {
        int[][] dataMatrix = new int[7][7];
        dataMatrix[0][0] = dataObject.isAvailableMove() ? 1 : 0;
        dataMatrix[0][1] = dataObject.getP1Score();
        dataMatrix[0][2] = dataObject.getP2Score();
        dataMatrix[0][3] = dataObject.isGameFinished() ? 1 : 0;

        for (int row = 0; row < State.ROWS; row++) {
            for (int col = 0; col < State.COLUMNS; col++) {
                int c;
                if (dataObject.getGrid()[row][col] == 'R'){
                    c = 1;
                }
                else if (dataObject.getGrid()[row][col] == 'Y'){
                    c = -1;
                }
                else{
                    c = 0;
                }
                dataMatrix[row+1][col] = c;
            }
        }
        return dataMatrix;
    }

    private static int[][] dataToMatrix(int i) {//unavailable move case
        int[][] dataMatrix = new int[7][7];
        dataMatrix[0][0] = 0;
        return dataMatrix;
    }

    private static int receiveData() {
        /*
            some code to receive the data
            and return the column to play in
         */

        return 0;
    }
}
