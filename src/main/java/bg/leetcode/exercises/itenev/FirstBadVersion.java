package bg.leetcode.exercises.itenev;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version.
 * You should minimize the number of calls to the API.
 * <p>
 * Given n = 5, and version = 4 is the first bad version.
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * <p>
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

    /**
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     */
    public int firstBadVersionWithBS(int n) {
        int leftPointer = 0;
        int rightPointer = n;

        while (leftPointer < rightPointer) {
            int mid = leftPointer + (rightPointer - leftPointer) / 2;

            if (!isBadVersion(mid))
                leftPointer = mid + 1;
            else
                rightPointer = mid;
        }

        return leftPointer;
    }

    public int firstBadVersion(int n) {
        for (int i = 1; i < n; i++) {
            if (isBadVersion(i)) {
                return i;
            }
        }
        return n;
    }

    private boolean isBadVersion(int version) {
        return version % 2 == 0;
    }
}
