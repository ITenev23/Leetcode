package bg.leetcode.exercises.itenev.trie;

import bg.leetcode.exercises.itenev.common.Trie;

import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {
    Set<String> res = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;

        str += board[x][y];
        if (!trie.startsWith(str)) return;

        if (trie.search(str)) {
            res.add(str);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }

    /*******************************************************************************/

    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs2(board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs2(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null)
            return;

        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';

        if (i > 0)
            dfs2(board, i - 1, j, p, res);
        if (j > 0)
            dfs2(board, i, j - 1, p, res);
        if (i < board.length - 1)
            dfs2(board, i + 1, j, p, res);
        if (j < board[0].length - 1)
            dfs2(board, i, j + 1, p, res);

        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }


    /*******************************************************************************/

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public List<String> findWords3(char[][] board, String[] words) {
        Set<String> foundWords = new HashSet<>();
        if (board == null || words == null || board.length == 0 || words.length == 0)
            return new ArrayList<>(foundWords);

        TrieSW trie = new TrieSW();
        trie.addWords(words);
        StringBuilder currentWord = new StringBuilder();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (trie.children.containsKey(board[i][j]))
                    search(board, i, j, visited, trie, currentWord, foundWords);
            }
        }
        return new ArrayList<>(foundWords);
    }

    public void search(char[][] board, int y, int x, boolean[][] visited, TrieSW trie, StringBuilder currentWord, Set<String> foundWords) {
        char c = board[y][x];
        currentWord.append(c);
        TrieSW current = trie.children.get(c);

        if (current.isWord)
            foundWords.add(currentWord.toString());
        visited[y][x] = true;
        for (Move m : possMoves(board, y, x, visited, current)) {
            search(board, m.y, m.x, visited, current, currentWord, foundWords);
        }
        visited[y][x] = false;
        currentWord.deleteCharAt(currentWord.length() - 1);
    }

    public List<Move> possMoves(char[][] board, int y, int x, boolean[][] visited, TrieSW trie) {
        List<Move> moves = new ArrayList<>();
        for (int d = 0; d < dx.length; d++) {
            int newX = x + dx[d];
            int newY = y + dy[d];
            if (isInBound(newY, newX, board) && trie.children.containsKey(board[newY][newX]) && !visited[newY][newX])
                moves.add(new Move(newX, newY));
        }
        return moves;
    }

    private boolean isInBound(int y, int x, char[][] board) {
        return x >= 0 && y >= 0 && y < board.length && x < board[y].length;
    }

    private static class Move {
        int x;
        int y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class TrieSW {
        Map<Character, TrieSW> children;
        boolean isWord;

        public TrieSW() {
            this.children = new HashMap<>();
            this.isWord = false;
        }

        public void addWord(String word) {
            char[] chars = word.toCharArray();
            TrieSW current = this;
            for (char c : chars) {
                if (!current.children.containsKey(c)) current.children.put(c, new TrieSW());
                current = current.children.get(c);
            }
            current.isWord = true;
        }

        public void addWords(String[] words) {
            for (String w : words) this.addWord(w);
        }
    }

}
