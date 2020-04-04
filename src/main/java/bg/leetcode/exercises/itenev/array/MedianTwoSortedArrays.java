package bg.leetcode.exercises.itenev.array;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) throws IllegalArgumentException {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException();
        } else if (nums1.length > nums2.length) {
            // ensures 1st array is smaller than 2nd array
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;
        int left = 0;
        int right = x;

        while (left <= right) {
            int partitionX = (left + right) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX  = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY  = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY) * 1.0;
                }
            } else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }

        // can only occur if input arrays were not sorted.
        throw new IllegalArgumentException();
    }

    /*********************************************/

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        int med1 = 0;
        int med2 = 0;
        int midLength = (nums1.length + nums2.length) / 2;

        for (int i = 0; i <= midLength; i++) {
            med1 = med2;
            if (index1 == nums1.length) {
                med2 = nums2[index2];
                index2++;
            } else if (index2 == nums2.length) {
                med2 = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2]) {
                med2 = nums1[index1];
                index1++;
            } else {
                med2 = nums2[index2];
                index2++;
            }
        }

        // the median is the average of two numbers
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (float) (med1 + med2) / 2;
        }

        return med2;
    }

}
