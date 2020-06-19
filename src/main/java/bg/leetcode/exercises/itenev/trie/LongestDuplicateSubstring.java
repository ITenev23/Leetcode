package bg.leetcode.exercises.itenev.trie;

/**
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
 * Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
 * <p>
 * Example 1:
 * Input: "banana"
 * Output: "ana"
 * <p>
 * Example 2:
 * Input: "abcd"
 * Output: ""
 */
public class LongestDuplicateSubstring {

    public String longestDupSubstring(String S) {
        class Trie {
            Trie[] children;
            final int startPos;
            final int depth;

            Trie(int startPos, int depth) {
                this.startPos = startPos;
                this.depth = depth;
            }

            boolean isLeaf() {
                return children == null;
            }

            int childIndex(int start) {
                return S.charAt(start + depth) - 'a';
            }

            int addNew(int start) {
                if (start + depth == S.length())
                    return depth;
                if (isLeaf()) {
                    children = new Trie[28];
                    children[childIndex(startPos)] = new Trie(startPos, depth + 1);
                }
                int newIndex = childIndex(start);
                Trie child = children[newIndex];
                if (child == null) {
                    children[newIndex] = new Trie(start, depth + 1);
                    return depth;
                }
                return child.addNew(start);
            }
        }

        int maxStart = 0, maxLength = 0;
        int length = S.length();
        Trie root = new Trie(0, 0);
        for (int i = 1; i + maxLength < length; ++i) {
            int len = root.addNew(i);
            if (len > maxLength) {
                maxLength = len;
                maxStart = i;
            }
        }

        return S.substring(maxStart, maxStart + maxLength);
    }
}
