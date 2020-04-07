package bg.leetcode.exercises.itenev.dp;

/**
 * A top secret message containing uppercase letters from 'A' to 'Z'
 * has been encoded as numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * You are an FBI agent and you need to determine the total number of ways that the message can be decoded.
 * Since the answer could be very large, take it modulo 109 + 7.
 * <p>
 * For message = "123", the output should be
 * mapDecoding(message) = 3.
 * "123" can be decoded as "ABC" (1 2 3), "LC" (12 3) or "AW" (1 23), so the total number of ways is 3.
 */
public class FBIDecoding {

    int mapDecoding(String message) {
        if (message.length() == 0) return 1;
        int m = (int) (Math.pow(10, 9) + 7);
        int[] dp = new int[message.length() + 1];
        dp[0] = 1;
        dp[1] = (message.charAt(0) == '0') ? 0 : 1;

        for (int i = 1; i < message.length(); i++) {
            char curr = message.charAt(i);
            char prev = message.charAt(i - 1);
            if (curr != '0') {
                dp[i + 1] = dp[i];
            }
            if (prev == '1' || (prev == '2' && curr <= '6')) {
                dp[i + 1] += dp[i - 1];
            }

            dp[i + 1] = dp[i + 1] % m;
        }

        return dp[message.length()] % m;
    }

}
