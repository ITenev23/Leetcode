package bg.leetcode.exercises.itenev.string;

/**
 * Implement function ToLowerCase() that has a string parameter str,
 * and returns the same string in lowercase.
 * <p>
 * Input: "LOVELY"
 * Output: "lovely"
 */
public class ToLowerCase {

    /** ASCII
     * A → 65，B → 66，C → 67，D → 68，E → 69， F → 70，G → 71，H → 72，I → 73，J → 74，
     * K → 75，L → 76，M → 77，N → 78，O → 79， P → 80，Q → 81，R → 82，S → 83，T → 84， U →85，
     * V → 86，W → 87，X → 88，Y → 89，Z → 90
     * a → 97，b → 98，c → 99，d → 100，e →101， f → 102， g → 103，h → 104，i → 105，
     * j → 106，k → 107，l → 108， m → 109，n →110，o → 111，p→ 112，q → 113，r → 114， s → 115，
     * t → 116，u → 117，v → 118，w → 119，x → 120，y → 121，z → 122
     */
    public String toLowerCase(String str) {
        StringBuilder temp = new StringBuilder();
        char ch;
        int value = 0;

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            value = ch;
            if (value >= 65 && value <= 90) {
                value = str.charAt(i) + 32;
                ch = (char) value;
            }
            temp.append(ch);
        }
        return temp.toString();
    }

    /****************************/

    public String toLowerCase2(String str) {
//        return str.toLowerCase();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            sb.append(Character.toLowerCase(str.charAt(i)));
        }
        return sb.toString();
    }

}
