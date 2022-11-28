public class Data {
    private boolean availableMove;
    private int     p1Score; //'R' or true turn , person mostly
    private int     p2Score; //'Y' or false turn , PC mostly
    private boolean gameFinished;
    private char[][] grid;



    public Data(boolean availableMove, int p1Score, int p2Score, boolean  gameFinished,char[][] grid) {
        this.availableMove = availableMove;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.gameFinished = gameFinished;
        this.grid = grid;
    }
    public Data(boolean availableMove, int p1Score, int p2Score , boolean gameFinished, State state) {
        this.availableMove = availableMove;
        this.p1Score = p1Score;
        this.p2Score = p2Score;
        this.gameFinished = gameFinished;
        this.grid = state.toGrid();
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public boolean isAvailableMove() {
        return availableMove;
    }

    public void setAvailableMove(boolean availableMove) {
        this.availableMove = availableMove;
    }

    public int getP1Score() {
        return p1Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void setP2Score(int p2Score) {
        this.p2Score = p2Score;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }
    public void setGrid(State state) {
        this.grid = state.toGrid();
    }
}
