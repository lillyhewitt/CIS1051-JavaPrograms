public class Sudoku {

    static boolean isValid(int[][] board, int row, int col, int num) {
        // checks if num is in that row
        for (int i = 0; i <= 8; i++)
            if (board[row][i] == num)
                return false;
        // checks if num is in that column
        for (int i = 0; i <= 8; i++)
            if (board[i][col] == num)
                return false;

        // https://www.geeksforgeeks.org/sudoku-backtracking-7/
            // used to help me understand how to divide array in 3x3 boxes
        // find starting positions (3x3 box)
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        // checks if num is in the 3x3 box
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    static boolean solve(int board[][], int row, int col) {
        // checks if puzzle is completed
        if ((row == 8) && (col == 9))
            return true;
        if (col == 9) {
            row++;
            col = 0;
        }

        // skips fill spots
        if (board[row][col] != 0) {
            return solve(board, row, col + 1);
        }
        // fills empty spots
        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solve(board, row, col + 1)) {
                    return true;
                }
            }
            board[row][col] = 0;
        }

        // backtracking
        return false;
    }

    public static void main(String[] args)
    {
        // initalizing the puzzle
        int board[][] = { {9,0,0,1,0,0,0,0,5},
                {0,0,5,0,9,0,2,0,1},
                {8,0,0,0,4,0,0,0,0},
                {0,0,0,0,8,0,0,0,0},
                {0,0,0,7,0,0,0,0,0},
                {0,0,0,0,2,6,0,0,9},
                {2,0,0,3,0,0,0,0,6},
                {0,0,0,2,0,0,9,0,0},
                {0,0,1,9,0,4,5,7,0} };

        // printing the puzzle
        if (solve(board, 0, 0)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}

