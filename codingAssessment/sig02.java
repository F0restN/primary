import java.util.HashSet;

public class sig02 {

    public boolean isValidSudoku(int[][] board) {
        for(int i = 0; i<9; i++){
            HashSet<Integer> rows = new HashSet<>();
            HashSet<Integer> columns = new HashSet<>();
            HashSet<Integer> cube = new HashSet<>();
            for (int j = 0; j < 9;j++){
                // Row
                if(!rows.add(board[i][j])) return false;

                // Column
                if(!columns.add(board[j][i])) return false;

                // Cube
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(!cube.add(board[RowIndex + j/3][ColIndex + j%3])) return false;
            }
        }
        return true;
    }
}
