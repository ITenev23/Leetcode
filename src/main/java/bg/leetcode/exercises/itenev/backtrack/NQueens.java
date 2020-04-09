package bg.leetcode.exercises.itenev.backtrack;

import java.util.*;

/**
 *
 */
public class NQueens {

    public static List<String[]> solveNQueens(int n) {
        List<String[]> res = new ArrayList<>();
        helper(0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
        return res;
    }

    private static void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2,
                               String[] board, List<String[]> res) {
        if (r == board.length) res.add(board.clone());
        else {
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true;
                    d1[id1] = true;
                    d2[id2] = true;
                    helper(r + 1, cols, d1, d2, board, res);
                    cols[c] = false;
                    d1[id1] = false;
                    d2[id2] = false;
                }
            }
        }
    }

    /**************************************************************************/

    private Set<Integer> col = new HashSet<>();
    private Set<Integer> diag1 = new HashSet<>();
    private Set<Integer> diag2 = new HashSet<>();

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, n);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> list, int row, int n) {
        if (row == n) {
            res.add(new ArrayList<String>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (col.contains(i) || diag1.contains(row + i) || diag2.contains(row - i)) continue;

            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);
            col.add(i);
            diag1.add(row + i);
            diag2.add(row - i);

            dfs(res, list, row + 1, n);

            list.remove(list.size() - 1);
            col.remove(i);
            diag1.remove(row + i);
            diag2.remove(row - i);
        }
    }


    /**************************************************************************/


    public static List<List<String>> solveNQueens3(int n) {
        List<List<String>> solutions = new ArrayList<>();

        if (n < 0)
            return solutions;

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        Set<Integer> cols = new HashSet<>(); // columns   |
        Set<Integer> d1 = new HashSet<>();   // diagonals \
        Set<Integer> d2 = new HashSet<>();   // diagonals /

        placeQueens(board, n, solutions, 0, cols, d1, d2);
        return solutions;
    }

    private static void placeQueens(char[][] board, int n, List<List<String>> solutions, int row,
                                    Set<Integer> cols, Set<Integer> d1, Set<Integer> d2) {
        if (row == n) {
            solutions.add(makeSolutionBoard(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            int diag1 = col - row;
            int diag2 = col + row;
            if (cols.contains(col) || d1.contains(diag1) || d2.contains(diag2)) {
                continue;
            }
            // put queen on board
            board[row][col] = 'Q';
            cols.add(col);
            d1.add(diag1);
            d2.add(diag2);

            placeQueens(board, n, solutions, row + 1, cols, d1, d2);

            // remove queen from board
            cols.remove(col);
            d1.remove(diag1);
            d2.remove(diag2);
            board[row][col] = '.';
        }
    }

    private static List<String> makeSolutionBoard(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

}
