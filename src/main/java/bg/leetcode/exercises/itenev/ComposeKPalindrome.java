package bg.leetcode.exercises.itenev;

/**
 * Given a string s, can you compose exactly K non-empty palindromes using all letters of s?
 * <p>
 * Note that you can use only letters from s.
 * Each letter from s that you use has to appear exactly the same number of times in your palindromes as it does in s.
 * Also note that you can rearrange string letters as you want.
 * <p>
 * Example: s = 'abracadabra' and k = 3 output of composeKPalindrome(s, k) = true
 * <p>
 * The answer is true since you are able to compose 3 palindromes using each letter of 'abracadabra' once.
 * 3 possible palindromes that fulfill this requirement for example can be: 'raaaaar', 'bcb' and 'd'.
 * <p>
 * For s = 'abracadabra' and k = 2, the output should be composeKPalindrome(s, k) = false
 */
public class ComposeKPalindrome {

    public static void test() {
        System.out.println(composeKPalindrome("abracadabra", 3));
        System.out.println(composeKPalindrome("abracadabra", 2));
    }

    private static boolean composeKPalindrome(String str, int k) {
        if (str.length() < k)
            return false;

        int[] count = new int[26];

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                count[ch - 'a']++;
            }
        }
        int odds = 0;
        for (int c : count) {
            if (c % 2 != 0) ++odds;
        }
        return odds <= k;
    }

}
