package bg.leetcode.exercises.itenev.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegerToEnglishWords {
    private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String HUNDRED = " Hundred ";
    private final String THOUSAND = " Thousand ";
    private final String MILLION = " Million ";
    private final String BILLION = " Billion ";
    private final String SPACE = " ";

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return helper(num);
    }

    private String helper(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < 10)
            sb.append(belowTen[num]);
        else if (num < 20)
            sb.append(belowTwenty[num -10]);
        else if (num < 100)
            sb.append(belowHundred[num/10]).append(SPACE).append(helper(num % 10));
        else if (num < 1000)
            sb.append(helper(num/100)).append(HUNDRED).append(helper(num % 100));
        else if (num < 1000000)
            sb.append(helper(num/1000)).append(THOUSAND).append(helper(num % 1000));
        else if (num < 1000000000)
            sb.append(helper(num/1000000)).append(MILLION).append(helper(num % 1000000));
        else
            sb.append(helper(num/1000000000)).append(BILLION).append(helper(num % 1000000000));

        return sb.toString().trim();
    }


    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords2(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = helper2(num % 1000) + THOUSANDS[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper2(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return LESS_THAN_20[num] + SPACE;
        else if (num < 100)
            return TENS[num / 10] + SPACE + helper2(num % 10);
        else
            return LESS_THAN_20[num / 100] + HUNDRED + helper2(num % 100);
    }


    private static final Map<Integer, String> map = new HashMap<>();
    private static final int BILLION_ = 1_000_000_000;
    private static final int MILLION_ = 1_000_000;
    private static final int THOUSAND_ = 1_000;

    public String numberToWords3(int num) {
        initMap();
        if (num < 10)
            return map.get(num);
        StringBuilder ans = new StringBuilder();
        if (num >= BILLION_) {
            ans.append(convert(num / BILLION_)).append(" Billion");
            num %= BILLION_;
        }
        if (MILLION_ <= num) {
            ans.append(convert(num / MILLION_)).append(" Million");
            num %= MILLION_;
        }
        if (THOUSAND_ <= num) {
            ans.append(convert(num / THOUSAND_)).append(" Thousand");
            num %= THOUSAND_;
        }
        ans.append(convert(num));
        return ans.toString().trim();
    }

    private String convert(int t) {
        StringBuilder ans = new StringBuilder();
        int hun = t / 100;
        if (hun != 0)
            ans.append(" ").append(map.get(hun)).append(" Hundred");
        t %= 100;
        int ten = t / 10;
        if (ten != 0)
            ans.append(" ").append(ten == 1 ? map.get(t) : map.get(ten * 10));
        t %= 10;
        if (ten != 1 && t != 0)
            ans.append(" ").append(map.get(t));
        return ans.toString();
    }

    private void initMap() {
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
    }

}
