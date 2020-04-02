package bg.leetcode.exercises.itenev.array;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space
 * (size that is greater or equal to m + n) to hold additional elements from nums2.
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        merge2(new int[]{9, 10, 111, 0, 0, 0}, 3, new int[]{2, -6, -9}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0)
            if (m == 0 || nums2[n - 1] > nums1[m - 1])
                nums1[m + n - 1] = nums2[--n];
            else
                nums1[m + n - 1] = nums1[--m];
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1;
        int tail2 = n - 1;
        int length = m + n - 1;

        while (tail1 >= 0 && tail2 >= 0) {
            int current = nums1[tail1];
            int toAdd = nums2[tail2];

            if (current > toAdd) {
                nums1[length--] = current;
                tail1--;
            } else {
                nums1[length--] = toAdd;
                tail2--;
            }
        }


        while (tail2 >= 0)
            nums1[length--] = nums2[tail2--];
    }

}
