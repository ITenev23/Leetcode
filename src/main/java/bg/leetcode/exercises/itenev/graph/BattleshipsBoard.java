package bg.leetcode.exercises.itenev.graph;

/**
 * Given an 2D board, count how many battleships are in it.
 * The battleships are represented with 'X's, empty slots are represented with '.'s.
 * You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically.
 * In other words, they can only be made of the shape 1xN (1 row, N columns)
 * or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships -
 * there are no adjacent battleships.
 * <p>
 * X..X
 * ...X
 * ...X
 * <p>
 * In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 */
public class BattleshipsBoard {

    public int countBattleships(char[][] board) {
        int battleships = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.')
                    continue;
                if (i > 0 && board[i - 1][j] == 'X')
                    continue;
                if (j > 0 && board[i][j - 1] == 'X')
                    continue;

                battleships++;
            }
        }

        return battleships;
    }

    /*****************************************************************/

    public int countBattleships2(char[][] board) {
        int battleships = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    battleships++;
                    sink(board, i, j);
                }
            }
        }

        return battleships;
    }

    private void sink(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != 'X')
            return;

        board[i][j] = '.';
        sink(board, i + 1, j);
        sink(board, i - 1, j);
        sink(board, i, j + 1);
        sink(board, i, j - 1);
    }

}
