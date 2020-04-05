package bg.leetcode.exercises.itenev.stack;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * 1. Only one letter can be changed at a time.
 * 2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * <p>
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {

    /**
     * Basically I keep two sets of words, one set reached that represents the borders
     * that have been reached with "distance" steps; another set wordDict that has not been reached.
     * In the while loop, for each word in the reached set,
     * I give all variations and check if it matches anything from wordDict, if it has a match,
     * I add that word into toAdd set, which will be my "reached" set in the next loop,
     * and remove the word from wordDict because I already reached it in this step.
     * And at the end of while loop, I check the size of toAdd, which means that if I can't reach any new String
     * from wordDict, I won't be able to reach the endWord, then just return 0.
     * Finally if the endWord is in reached set, I return the current steps "distance".
     * The idea is that reached always contain only the ones we just reached in the last step,
     * and wordDict always contain the ones that haven't been reached.
     * This is pretty much what Dijkstra's algorithm does, or you can see this as some variation of BFS.
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;

        while (!visited.contains(endWord)) {
            Set<String> toAdd = new HashSet<>();

            for (String current : visited) {
                for (int i = 0; i < current.length(); i++) {
                    char[] chars = current.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0)
                return 0;
            visited = toAdd;
        }
        return distance;
    }

    /*******************************************************************************/

    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // Since all words are of same length.
        int length = beginWord.length();
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(word -> {
            for (int i = 0; i < length; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });

        Queue<Pair<String, Integer>> bfs = new LinkedList<>();
        bfs.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!bfs.isEmpty()) {
            Pair<String, Integer> node = bfs.remove();
            String word = node.key;
            int level = node.value;
            for (int i = 0; i < length; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        bfs.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }

    public static class Pair<S, I> {
        S key;
        I value;

        public Pair(S key, I value) {
            this.key = key;
            this.value = value;
        }
    }
}
