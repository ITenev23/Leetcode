package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;

/**
 * You have an initial power P, an initial score of 0 points, and a bag of tokens.
 *
 * Each token can be used at most once, has a value token[i], and has potentially two ways to use it.
 *      If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
 *      If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
 * Return the largest number of points we can have after playing any number of tokens.
 *
 * Input: tokens = [100], P = 50
 * Output: 0
 *
 * Input: tokens = [100,200], P = 150
 * Output: 1
 *
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 */
public class BagOfTokens {

    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int maxPoints = 0;
        int points = 0;
        int left = 0;
        int right = tokens.length - 1;
        while (left < right) {
            if (P >= tokens[left]) {
                points++;
                P -= tokens[left++];
                maxPoints = Math.max(maxPoints, points);
            }else if (points > 0) {
                points--;
                P += tokens[right--];
            } else {
                return maxPoints;
            }
        }

        return maxPoints;
    }

}
