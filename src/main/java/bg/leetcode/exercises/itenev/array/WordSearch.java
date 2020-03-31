package bg.leetcode.exercises.itenev.array;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, 0, word))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int count, String word) {
        if (count == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word.charAt(count))
            return false;

        char temp = board[i][j];
        board[i][j] = ' ';
        boolean found = dfs(board, i + 1, j, count + 1, word) ||
                dfs(board, i - 1, j, count + 1, word) ||
                dfs(board, i, j + 1, count + 1, word) ||
                dfs(board, i, j - 1, count + 1, word);

        board[i][j] = temp;
        return found;
    }

    public boolean dfs2(char[][] b, String w, int r, int c, int index){
        /* once we get past word.length, we are done. */
        if(index == w.length()) return true;

        /* if off bounds, letter is seen, letter is unequal to word.charAt(start) return false */
        if(r<0 || r>=b.length || c<0 || c>=b[0].length || w.charAt(index)!=b[r][c]) return false;

        /* set this board position to seen. (Because we can use this postion) */
        b[r][c] = '0';

        /* recursion on all 4 sides for next letter, if works: return true */
        if( dfs2(b,w,r-1,c,index+1) ||
                dfs2(b,w,r+1,c,index+1) ||
                dfs2(b,w,r,c-1,index+1) ||
                dfs2(b,w,r,c+1,index+1) )
            return true;

        /* Set back to unseen */
        b[r][c] = w.charAt(index);

        return false;
    }

    /******************************************************************/


    public boolean exist2(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x + 1, word, i + 1)
                || exist(board, y, x - 1, word, i + 1)
                || exist(board, y + 1, x, word, i + 1)
                || exist(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;
        return exist;
    }

}
