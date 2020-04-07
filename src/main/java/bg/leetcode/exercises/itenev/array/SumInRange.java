package bg.leetcode.exercises.itenev.array;

import java.util.HashMap;
import java.util.Map;

/**
 * You have an array of integers nums and an array queries,
 * where queries[i] is a pair of indices (0-based).
 * Find the sum of the elements in nums from the indices at queries[i][0] to queries[i][1] (inclusive) for each query,
 * then add all of the sums for all the queries together. Return that number modulo 109 + 7.
 * <p>
 * For nums = [3, 0, -2, 6, -3, 2] and queries = [[0, 2], [2, 5], [0, 5]], the output should be
 * sumInRange(nums, queries) = 10.
 * <p>
 * The array of results for queries is [1, 3, 6], so the answer is 1 + 3 + 6 = 10.
 */
public class SumInRange {

    private long sumInRange(int[] nums, int[][] queries) {
        long total = 0, mod = (long) 1e9 + 7;
        for (int[] q : queries)
            for (int i = q[0]; i <= q[1]; i++)
                total += nums[i];
        return ((total % mod) + mod) % mod;
    }

    /**********************************************************/

    private int sumInRange2(int[] nums, int[][] queries) {
        int mod = (int) 1e9 + 7;
        int[] s = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++)
            s[i + 1] = s[i] + nums[i];
        long rs = 0;
        for (int[] query : queries) {
            rs += s[query[1] + 1] - s[query[0]];
            rs = (rs + mod) % mod;
        }
        return (int) ((rs + mod) % mod);
    }

    /**********************************************************/

    private int sumInRange3(int[] nums, int[][] queries) {
        int mod = 1000000007;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, nums[0]);
        for (int i = 1; i < nums.length; i++)
            map.put(i, (map.get(i - 1) + nums[i]) % mod);

        for (int[] query : queries) {
            if (query[0] == 0) sum = (sum + map.get(query[1])) % mod;
            else sum = (sum + map.get(query[1]) - map.get(query[0] - 1)) % mod;

        }
        if (sum < 0) sum += mod;
        return sum;
    }

    /**********************************************************/

    final int MOD = 1000000007;

    private int sumInRange4(int[] nums, int[][] queries) {
        int nextPower = getNextPowerOf2(nums.length);
        int[] segTree = new int[nextPower * 2 -1];  // by default all 0's

        buildSegTree(nums, segTree, 0, nums.length -1, 0);
        // System.out.println(Arrays.toString(segTree));            // check the build tree


        long sum = 0;
        for(int [] query : queries){
            int qlow = query[0];
            int qhigh = query[1];

            long val = sumInRange_Query(segTree, qlow, qhigh, nums.length) ;
            sum = sum + val;

        }

        return Math.floorMod(sum, MOD);
    }


    private long sumInRange_Query(int[] segTree, int qlow, int qhigh, int input_len){
        return sumInRange_Query(segTree, qlow, qhigh, 0, input_len-1, 0);
    }

    private long sumInRange_Query(int[] segTree, int qlow, int qhigh, int low, int high, int root){
        // 3 cases to resolve

        if(qlow <= low && qhigh >= high){ // full overlap
            return segTree[root];
        }


        if(qlow > high || qhigh < low){ // no overlap
            return 0; // as 0 will not do any change in the sum
        }

        // if partial overlap   -> get result from both side of the tree & then return
        int mid = (low + high)/ 2;

        long leftAns = sumInRange_Query(segTree, qlow, qhigh, low, mid , (root * 2 + 1));
        long rightAns = sumInRange_Query(segTree, qlow, qhigh, mid+1 , high, (root*2 + 2));

        long sum =  leftAns + rightAns;
        // System.out.println(sum);
        return sum;
    }

    private void buildSegTree(int[] input, int[] segTree, int low, int high, int root ) {
        if(low == high){
            segTree[root] = input[low];
            return;
        }

        int mid = (low + high) / 2;

        // post order travarsal
        buildSegTree(input, segTree, low, mid, (root * 2 + 1));     // left
        buildSegTree(input, segTree, mid+1, high, (root * 2 + 2));  // right

        segTree[root] = segTree[root * 2 + 1] + segTree[root * 2 + 2];
    }

    private int getNextPowerOf2(int n) {
        int val = 1;
        while(val < n){
            val = val << 1;   // left shift by one
        }
        return val;
    }
}
