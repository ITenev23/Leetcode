package bg.leetcode.exercises.itenev.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.
 * If not possible, return the empty string.
 * <p>
 * Input: S = "aab"
 * Output: "aba"
 * <p>
 * Input: S = "aaab"
 * Output: ""
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        Map<Character, Integer> chars = new HashMap<>();
        for (char c : S.toCharArray()) {
            chars.put(c, chars.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
                (a, b) -> chars.get(b) - chars.get(a)
        );

        maxHeap.addAll(chars.keySet());
        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() > 1) {
            char current = maxHeap.remove();
            char next = maxHeap.remove();

            sb.append(current).append(next);

            chars.put(current, chars.get(current) - 1);
            chars.put(current, chars.get(next) - 1);

            if (chars.get(current) > 0)
                maxHeap.add(current);
            if (chars.get(next) > 0)
                maxHeap.add(next);
        }

        if (!maxHeap.isEmpty()) {
            char last = maxHeap.remove();
            if (chars.get(last) > 1) {
                return "";
            }
            sb.append(last);
        }

        return sb.toString();
    }

    /**************************************************************************************/

    public String reorganizeString2(String S) {
        int N = S.length();
        int[] count = new int[26];
        for (char c : S.toCharArray()) count[c - 'a']++;
        PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
                a.count == b.count ? a.letter - b.letter : b.count - a.count);

        for (int i = 0; i < 26; ++i)
            if (count[i] > 0) {
                if (count[i] > (N + 1) / 2) return "";
                pq.add(new MultiChar(count[i], (char) ('a' + i)));
            }

        StringBuilder ans = new StringBuilder();
        while (pq.size() >= 2) {
            MultiChar mc1 = pq.poll();
            MultiChar mc2 = pq.poll();
            /*This code turns out to be superfluous, but explains what is happening
            if (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
            } else {
                ans.append(mc2.letter);
                ans.append(mc1.letter);
            }*/
            ans.append(mc1.letter);
            ans.append(mc2.letter);
            if (--mc1.count > 0) pq.add(mc1);
            if (--mc2.count > 0) pq.add(mc2);
        }

        if (pq.size() > 0) ans.append(pq.poll().letter);
        return ans.toString();
    }

    static class MultiChar {
        int count;
        char letter;

        MultiChar(int ct, char ch) {
            count = ct;
            letter = ch;
        }
    }


    /**************************************************************************************/

    /**
     * Find the count of each character, and use it to sort the string by count.
     * If at some point the number of occurrences of some character is greater than (N + 1) / 2, the task is impossible.
     * Otherwise, interleave the characters in the order described above.
     */
    public String reorganizeString3(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }

}
