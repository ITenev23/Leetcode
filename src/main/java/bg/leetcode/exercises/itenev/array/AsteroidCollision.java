package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions.
 * If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 * <p>
 * Input: [5, 10, -5]
 * Output: [5, 10]
 * Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * <p>
 * Input: [8, -8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * <p>
 * Input: [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        int[] first = asteroidCollision(new int[]{-2, -1, 1, 2});
        int[] secont = asteroidCollision(new int[]{5, 10, -5});
        int[] t = asteroidCollision(new int[]{5, -5});
        System.out.println();
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        asteroids:
        for (int ast : asteroids) {
            while (!stack.isEmpty() && ast < 0 && stack.peek() > 0)
                if (-ast < stack.peek() || -ast == stack.pop())
                    continue asteroids;

            stack.push(ast);
        }

        return stack.stream().mapToInt(i -> i).toArray();
    }

    /**********************************************************************/

    public int[] asteroidCollision2(int[] asteroids) {
        int i, j;
        for (i = j = 0; i < asteroids.length; i++) {
            for (; j > 0 && asteroids[j - 1] > 0 && asteroids[j - 1] < -asteroids[i]; j--) ;
            if (j == 0 || asteroids[i] > 0 || asteroids[j - 1] < 0) {
                asteroids[j++] = asteroids[i];
            } else if (asteroids[i] == -asteroids[j - 1]) {
                j--;
            }
        }
        return Arrays.copyOf(asteroids, j);
    }
}
