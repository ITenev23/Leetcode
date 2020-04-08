package bg.leetcode.exercises.itenev.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 *
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Input: [3,1,3,4,2]
 * Output: 3
 */
public class FindDuplicateNumber {

    /**
     * Floyd's Tortoise and Hare (Cycle Detection)
     */
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    /**********************************************************/

    public int findDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums ) {
            if(!set.add(n))
                return n;
        }
        return -1;
    }

}
