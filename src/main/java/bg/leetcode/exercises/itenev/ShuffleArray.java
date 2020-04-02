package bg.leetcode.exercises.itenev;

import java.util.Random;

/**
 * Fisher–Yates shuffle Algorithm
 */
public class ShuffleArray {

    public static int[] shuffle(int[] arr) {
        Random r = new Random();

        // Start from the last element and swap one by one.
        // We don't need to run for the first element that's why i > 0
        for (int i = arr.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

}
