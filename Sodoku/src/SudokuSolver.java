public class SudokuSolver {
    // load sudoku from file
    private static boolean solve(int[][] board, int row, int col) {
        //int row = pos/9;
        //int col = pos % 9;
        if(col == 9) {
            //return solve(board, row+1, 0);
            row ++;
            col = 0;
        }
        if(row == 9) {
            return true;
        }
        //if( space not empty )
        return false;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0,0,0,2,0,0,6,0,0},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
        };
    }
}
