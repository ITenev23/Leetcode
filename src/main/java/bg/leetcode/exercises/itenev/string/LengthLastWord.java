package bg.leetcode.exercises.itenev.string;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a maximal substring consisting of non-space characters only.
 * <p>
 * Input: "Hello World"
 * Output: 5
 */
public class LengthLastWord {
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }

    /********************************************************/

    public int lengthOfLastWord2(String s) {
        s = s.trim();

        if (s.length() < 3)
            return s.length();

        char[] array = s.toCharArray();
        int count = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == ' ')
                break;
            else count++;
        }
        return count;
    }
}
