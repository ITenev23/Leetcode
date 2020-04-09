package bg.leetcode.exercises.itenev.greedy;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * <p>
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * <p>
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {
    public int candy(int[] ratings) {
        if (ratings.length == 0)
            return 0;

        int ret = 1;
        int up = 0;
        int down = 0;
        int peak = 0;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                peak = ++up;
                down = 0;
                ret += 1 + up;
            } else if (ratings[i - 1] == ratings[i]) {
                peak = up = down = 0;
                ret++;
            } else {
                up = 0;
                down++;
                ret += 1 + down + (peak >= down ? -1 : 0);
            }
        }

        return ret;
    }

    /************************************************************************/

    /**
     * This solution picks each element from the input array only once.
     * First, we give a candy to the first child.
     * Then for each child we have three cases:
     *      Child rating is equal to the previous one -> give 1 candy.
     *      Child rating is greater than the previous one -> give him (previous + 1) candies.
     *      Child rating is less than the previous one -> don't know what to do yet, let's just count the number of such consequent cases.
     */
    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;

        int total = 1;
        int prev = 1;
        int countDown = 0;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                if (countDown > 0) {
                    total += countDown * (countDown + 1) / 2; // arithmetic progression
                    if (countDown >= prev) total += countDown - prev + 1;
                    countDown = 0;
                    prev = 1;
                }
                prev = ratings[i] == ratings[i - 1] ? 1 : prev + 1;
                total += prev;
            } else countDown++;
        }
        if (countDown > 0) { // if we were descending at the end
            total += countDown * (countDown + 1) / 2;
            if (countDown >= prev) total += countDown - prev + 1;
        }
        return total;
    }
}
