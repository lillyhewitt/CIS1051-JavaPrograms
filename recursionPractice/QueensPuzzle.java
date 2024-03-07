public class QueensPuzzle {
    /* place 8 queens on 8 x 8 int[][] array
    queens can move in same row,column,or any diagonal */
    boolean isValid(int board[][], int row, int col)
    {
        // checks if there's queen is in a row
        for (int i = 0; col -i  >=0 ; i++) {
            if(board[row][col-i] == 1 ) {
                return false;
            }
        }

        for (int i = 0; col -i  >=0 && row - i >= 0; i++) {
            if(board[row -i][col-i] == 1 ) {
                return false;
            }
        }


        // https://www.codesdope.com/blog/article/backtracking-explanation-and-n-queens-problem/
            // used this website to understand how to loop through all diagonals
        // checks if there's a queen in its diagonal
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i + j) == (row + col)) || ((i - j) == (row - col))) {
                    if (board[i][j] == 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    boolean solve(int[][] board, int numQueens){
        // checks if all queens have been places
        if(numQueens >= 8){
            return true;
        }
        // checks if queen can be placed and places it if so
        for (int i = 0; i < 8; i++) {
            if(isValid(board, i, numQueens)){
                board[i][numQueens] = 1;
                if(solve(board, numQueens + 1) == true){
                    return true;
                }
                board[i][numQueens] = 0;
            }
        }
        //clear any choices entered at pos on board
        return false; // backtrack
    }

    public static void main(String[] args) {
        // initializing board
        int[][]board = {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        // solves 8 Queens Puzzle Problem
        QueensPuzzle queen = new QueensPuzzle();
        queen.solve(board, 0);

        // prints out the board with placed queens
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
