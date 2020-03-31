package bg.leetcode.exercises.itenev.string;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 * Input: "USA"
 * Output: True
 *
 * Input: "FlaG"
 * Output: False
 */
public class DetectCapital {

    public boolean detectCapitalUse(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i)))
                count++;
        }
        return count == word.length() || count == 0 ||
                count == 1 && Character.isUpperCase(word.charAt(0));
    }

    /******************************************************/

    public boolean detectCapitalUse3(String word) {
        String upperCase = word.toUpperCase();
        String lowerCase = word.substring(1).toLowerCase();

        return word.equals(upperCase) || word.substring(1).equals(lowerCase);
    }

    /******************************************************/

    public boolean detectCapitalUseRegex(String word) {
        if (word == null) {
            return false;
        }
        return word.matches("[A-Z]*|.[a-z]*");
    }
}
