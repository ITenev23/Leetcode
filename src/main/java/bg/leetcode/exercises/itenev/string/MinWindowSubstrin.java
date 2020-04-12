package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string S and a string T,
 * find the minimum window in S which will contain all the characters in T
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 */
public class MinWindowSubstrin {

    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) return "";
        int i = 0, j = 0;
        int[] Tmap = new int[256];
        int[] Smap = new int[256];
        for (int k = 0; k < t.length(); k++) {
            Tmap[t.charAt(k)]++;
        }
        int found = 0;
        int length = Integer.MAX_VALUE;
        String res = "";
        while (j < s.length()) {
            if (found < t.length()) {
                if (Tmap[s.charAt(j)] > 0) {
                    Smap[s.charAt(j)]++;
                    if (Smap[s.charAt(j)] <= Tmap[s.charAt(j)]) {
                        found++;
                    }
                }
                j++;
            }
            while (found == t.length()) {
                if (j - i < length) {
                    length = j - i;
                    res = s.substring(i, j);
                }
                if (Tmap[s.charAt(i)] > 0) {
                    Smap[s.charAt(i)]--;
                    if (Smap[s.charAt(i)] < Tmap[s.charAt(i)]) {
                        found--;
                    }
                }
                i++;
            }
        }
        return res;
    }

    /*******************************************************************************************/


    public String minWindow2(String s, String t) {
        if (s.length() == 0 || t.length() == 0)
            return "";

        Map<Character, Integer> dictT = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static class Pair<S, I> {
        S key;
        I value;

        public Pair(S key, I value) {
            this.key = key;
            this.value = value;
        }

        public S getKey() {
            return key;
        }

        public I getValue() {
            return value;
        }
    }
}
