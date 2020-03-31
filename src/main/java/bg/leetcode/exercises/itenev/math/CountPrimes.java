package bg.leetcode.exercises.itenev.math;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {

    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];

        for (int i = 2; i * i < primes.length; i++) {
            if (!primes[i]) {
                for (int j = i; j * i < primes.length; j++) {
                    primes[i * j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < primes.length; i++) {
            if (!primes[i])
                count++;
        }

        return count;
    }

    /****************************************************************/

    public int countPrimes2(int n) {
        if (n < 3)
            return 0;

        boolean[] f = new boolean[n];
        int count = n / 2;

        for (int i = 3; i * i < n; i += 2) {
            if (f[i])
                continue;

            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
}
