package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a town, there are N people labelled from 1 to N.
 * There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * 1.The town judge trusts nobody.
 * 2.Everybody (except for the town judge) trusts the town judge.
 * 3.There is exactly one person that satisfies properties 1 and 2.
 * <p>
 * You are given trust, an array of pairs trust[i] = [a, b]
 * representing that the person labelled a trusts the person labelled b.
 * If the town judge exists and can be identified, return the label of the town judge.
 * Otherwise, return -1.
 * <p>
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 * <p>
 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 * <p>
 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * Output: 3
 */
public class FindTownJudge {

    public int findJudge(int N, int[][] trust) {
        if (trust.length == 0)
            return N == 1 ? 1 : -1;

        int[] trustCount = new int[N + 1];
        for (int[] p : trust) {
            trustCount[p[0]]--;
            trustCount[p[1]]++;
        }

        for (int i = 1; i < trustCount.length; i++) {
            if (trustCount[i] == N - 1)
                return i;
        }
        return -1;
    }

    /**************************************************************/

    public int findJudge2(int N, int[][] trust) {
        if (trust.length == 0) {
            return 1;
        }
        Map<Integer, List<Integer>> town = new HashMap<>();
        for (int[] t : trust) {
            List<Integer> l = town.getOrDefault(t[0], new ArrayList<>());
            l.add(t[1]);
            town.put(t[0], l);
        }
        Map<Integer, Integer> trustCount = new HashMap<>();
        for (List<Integer> l : town.values()) {
            for (Integer i : l) {
                int num = trustCount.getOrDefault(i, 0) + 1;
                trustCount.put(i, num);
            }
        }
        for (Map.Entry<Integer, Integer> entry : trustCount.entrySet()) {
            if (entry.getValue() == N - 1 && !town.containsKey(entry.getKey())) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
