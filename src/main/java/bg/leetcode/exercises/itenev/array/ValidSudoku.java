package bg.leetcode.exercises.itenev.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * Input:
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * Output: true
 * <p>
 * Input:
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }

    /**********************************************************/

    public boolean isValidSudoku2(char[][] board) {
        Set<String> set = new HashSet<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char val = board[row][col];
                if (val != '.') {
                    int block = (row / 3 * 3) + (col / 3);
                    if (set.contains("r" + row + val) ||
                            set.contains("c" + col + val) ||
                            set.contains("b" + block + val))
                        return false;
                    else {
                        set.add("r" + row + val);
                        set.add("c" + col + val);
                        set.add("b" + block + val);
                    }
                }
            }
        }

        return true;
    }

    /**********************************************************/

    public boolean isValidSudoku3(char[][] board) {
        for(int i = 0; i < 9; i++) {
            if(!isValid(board, i, i, 0, 8) ||
                    !isValid(board, 0, 8, i, i) ||
                    !isValid(board, i / 3 * 3, i / 3 * 3 + 2, i % 3 * 3, i % 3 * 3 + 2))
                return false;
        }

        return true;
    }

    public boolean isValid(char[][] board, int xStart, int xEnd, int yStart, int yEnd) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int x = xStart; x <= xEnd; x++) {
            for(int y = yStart; y <= yEnd; y++) {
                if(board[x][y] != '.' && !set.add(board[x][y] - '0')) return false;
            }
        }
        return true;
    }

}
