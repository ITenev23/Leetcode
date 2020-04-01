package bg.leetcode.exercises.itenev.string;

/**
 * Given a string, determine if it is a palindrome,
 * considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem,
 * we define empty string as valid palindrome.
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * <p>
 * Input: "race a car"
 * Output: false
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        //left & right pointers
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }

            if (l < r && Character.toLowerCase(s.charAt(l++)) !=
                    Character.toLowerCase(s.charAt(r--)))
                return false;
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        s = s.replaceAll("[^\\w]+", "").toLowerCase();
        int left = s.length() / 2 - 1;
        int right = s.length() % 2 == 0 ? s.length() / 2 : s.length() / 2 + 1;
        while (left >= 0) {
            if (s.charAt(left--) != s.charAt(right++)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome3(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            while ((i <= arr.length - 1) && !((arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z') || (arr[i] >= '0' && arr[i] <= '9')))
                i++;
            while ((j >= 0) && !((arr[j] >= 'a' && arr[j] <= 'z') || (arr[j] >= 'A' && arr[j] <= 'Z') || (arr[j] >= '0' && arr[j] <= '9')))
                j--;
            if (i >= j)
                break;
            if (arr[i] >= 'A' && arr[i] <= 'Z')
                arr[i] += 'a' - 'A';
            if (arr[j] >= 'A' && arr[j] <= 'Z')
                arr[j] += 'a' - 'A';
            if (arr[i++] != arr[j--])
                return false;
        }
        return true;
    }

}
