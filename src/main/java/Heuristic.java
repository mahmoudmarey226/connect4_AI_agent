public class Heuristic implements HeuristicI{
    final int LENGTH = 6;
    final int WIDTH = 7;
    //turn = true → R's turn , else → Y's turn

    private int ConnectOf4(char[][] board, boolean flag) {
        int num = 0;
        char turn;
        if (flag)
            turn = 'R';
        else
            turn = 'Y';
        // horizontal
        for (int i = 0; i < LENGTH; i++)
            for (int j = 0; j < WIDTH - 3; j++)
                if (board[i][j] == turn && board[i][j + 1] == turn && board[i][j + 2] == turn && board[i][j + 3] == turn)
                    num++;
        // vertical
        for (int i = 0; i < LENGTH - 3; i++)
            for (int j = 0; j < WIDTH; j++)
                if (board[i][j] == turn && board[i + 1][j] == turn && board[i + 2][j] == turn && board[i + 3][j] == turn)
                    num++;
        // negative diagonal
        for (int i = 3; i < LENGTH; i++)
            for (int j = 0; j < WIDTH - 3; j++)
                if (board[i][j] == turn && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == turn)
                    num++;
        // positive diagonal
        for (int i = 0; i < LENGTH - 3; i++)
            for (int j = 0; j < WIDTH - 3; j++)
                if (board[i][j] == turn && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == turn)
                    num++;
        return num;
    }
    private int ConnectOf3(char[][] board, boolean flag) {
        char turn;
        int num = 0;
        if (flag)
            turn = 'R';
        else
            turn = 'Y';
        for (int i = 0; i < LENGTH; i++)
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == 'o' && board[i][j + 1] == turn && board[i][j + 2] == turn && board[i][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i][j + 1] == 'o' && board[i][j + 2] == turn && board[i][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i][j + 1] == turn && board[i][j + 2] == 'o' && board[i][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i][j + 1] == turn && board[i][j + 2] == turn && board[i][j + 3] == 'o')
                    num++;

            }
        // vertical
        for (int i = 0; i < LENGTH - 3; i++)
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == 'o' && board[i + 1][j] == turn && board[i + 2][j] == turn && board[i + 3][j] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j] == 'o' && board[i + 2][j] == turn && board[i + 3][j] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j] == turn && board[i + 2][j] == 'o' && board[i + 3][j] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j] == turn && board[i + 2][j] == turn && board[i + 3][j] == 'o')
                    num++;
            }
        // negative diagonal
        for (int i = 3; i < LENGTH; i++)
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == 'o' && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i - 1][j + 1] == 'o' && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == 'o' && board[i - 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == 'o')
                    num++;
            }
        // positive diagonal
        for (int i = 0; i < LENGTH - 3; i++)
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == 'o' && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j + 1] == 'o' && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == 'o' && board[i + 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == 'o')
                    num++;
            }
        return num;
    }

    private int ConnectOf2(char[][] board, boolean flag) {
        char turn;
        int num = 0;
        if (flag)
            turn = 'R';
        else
            turn = 'Y';
        // horizontal
        for (int i = 0; i < LENGTH; i++)
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == 'o' && board[i][j + 1] == 'o' && board[i][j + 2] == turn && board[i][j + 3] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i][j + 1] == turn && board[i][j + 2] == 'o' && board[i][j + 3] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i][j + 1] == turn && board[i][j + 2] == turn && board[i][j + 3] == 'o')
                    num++;
                if (board[i][j] == turn && board[i][j + 1] == 'o' && board[i][j + 2] == turn && board[i][j + 3] == 'o')
                    num++;
                if (board[i][j] == turn && board[i][j + 1] == 'o' && board[i][j + 2] == 'o' && board[i][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i][j + 1] == turn && board[i][j + 2] == 'o' && board[i][j + 3] == 'o')
                    num++;

            }
        // vertical
        for (int i = 0; i < LENGTH - 3; i++)
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == 'o' && board[i + 1][j] == 'o' && board[i + 2][j] == turn && board[i + 3][j] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i + 1][j] == turn && board[i + 2][j] == 'o' && board[i + 3][j] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i + 1][j] == turn && board[i + 2][j] == turn && board[i + 3][j] == 'o')
                    num++;
                if (board[i][j] == turn && board[i + 1][j] == 'o' && board[i + 2][j] == turn && board[i + 3][j] == 'o')
                    num++;
                if (board[i][j] == turn && board[i + 1][j] == 'o' && board[i + 2][j] == 'o' && board[i + 3][j] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j] == turn && board[i + 2][j] == 'o' && board[i + 3][j] == 'o')
                    num++;
            }
        // negative diagonal
        for (int i = 3; i < LENGTH; i++)
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == 'o' && board[i - 1][j + 1] == 'o' && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == 'o' && board[i - 3][j + 3] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == 'o')
                    num++;
                if (board[i][j] == turn && board[i - 1][j + 1] == 'o' && board[i - 2][j + 2] == turn && board[i - 3][j + 3] == 'o')
                    num++;
                if (board[i][j] == turn && board[i - 1][j + 1] == 'o' && board[i - 2][j + 2] == 'o' && board[i - 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i - 1][j + 1] == turn && board[i - 2][j + 2] == 'o' && board[i - 3][j + 3] == 'o')
                    num++;
            }
        // positive diagonal
        for (int i = 0; i < LENGTH - 3; i++)
            for (int j = 0; j < WIDTH - 3; j++) {
                if (board[i][j] == 'o' && board[i + 1][j + 1] == 'o' && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == 'o' && board[i + 3][j + 3] == turn)
                    num++;
                if (board[i][j] == 'o' && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == 'o')
                    num++;
                if (board[i][j] == turn && board[i + 1][j + 1] == 'o' && board[i + 2][j + 2] == turn && board[i + 3][j + 3] == 'o')
                    num++;
                if (board[i][j] == turn && board[i + 1][j + 1] == 'o' && board[i + 2][j + 2] == 'o' && board[i + 3][j + 3] == turn)
                    num++;
                if (board[i][j] == turn && board[i + 1][j + 1] == turn && board[i + 2][j + 2] == 'o' && board[i + 3][j + 3] == 'o')
                    num++;
            }
        return num;
    }
    public float getHeuristicScore(char[][] state ) {
        int score = 0;
        score += (ConnectOf4(state, false)) * 10000;
        score += (ConnectOf3(state, false)) * 100;
        score += (ConnectOf2(state, false)) * 10;
        score -= (ConnectOf4(state, true)) * 10000;
        score -= (ConnectOf3(state, true)) * 100;
        score -= (ConnectOf2(state, true)) * 10;
        return (float) (score/1000.0);
    }
}
