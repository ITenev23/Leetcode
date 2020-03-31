package bg.leetcode.exercises.itenev.pointers;

import java.util.Arrays;

/**
 * The i-th person has weight people[i],
 * and each boat can carry a maximum weight of limit.
 * <p>
 * Each boat carries at most 2 people at the same time,
 * provided the sum of the weight of those people is at most limit.
 * <p>
 * Return the minimum number of boats to carry every given person.
 * (It is guaranteed each person can be carried by a boat.)
 * <p>
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * <p>
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 * <p>
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 */
public class BoatsToSavePeople {

    /**
     * Let people[i] to the currently lightest person, and people[j] to the heaviest.
     * Then, as described above,
     * if the heaviest person can share a boat with the lightest person
     * (if people[j] + people[i] <= limit) then do so;
     * otherwise, the heaviest person sits in their own boat.
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int boats = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[left] + people[right] <= limit)
                left++;

            right--;
            boats++;
        }

        return boats;
    }

}
