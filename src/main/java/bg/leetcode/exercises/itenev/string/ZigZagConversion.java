package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public String convert(String s, int nRows) {
        if (s.length() <= nRows || nRows <= 1)
            return s;

        StringBuilder[] result = new StringBuilder[nRows];
        for (int i = 0; i < result.length; i++)
            result[i] = new StringBuilder();


        //divide the groups into chunks with size (nRows*2-2).
        int chunk = nRows * 2 - 2; //3->4, 4->6, 5->7, etc.

        for (int i = 0; i < s.length(); i++) {
            int group = i % chunk;

            //if they are less then nRows, this element is vertically aligned from top to buttom
            if (group < nRows) {
                result[group].append(s.charAt(i));
            }
            //otherwise, it falls onto the slope in reversed direction
            else {
                result[chunk - group].append(s.charAt(i));
            }
        }

        //combine the groups into final array.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nRows; i++)
            sb.append(result[i].toString());

        return sb.toString();
    }

    /***************************************************/

    public String convert2(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] buffer = new StringBuffer[nRows];
        for (int i = 0; i < buffer.length; i++) buffer[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                buffer[idx].append(c[i++]);
            for (int idx = nRows - 2; idx >= 1 && i < len; idx--) // obliquely up
                buffer[idx].append(c[i++]);
        }
        for (int idx = 1; idx < buffer.length; idx++)
            buffer[0].append(buffer[idx]);
        return buffer[0].toString();
    }

    /***************************************************/

    public String convert3(String s, int numRows) {
        if (numRows == 1)
            return s;

        List<StringBuilder> rows = new ArrayList<>();
        int minLength = Math.min(numRows, s.length());

        for (int i = 0; i < minLength; i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown;

            curRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows)
            result.append(row);

        return result.toString();
    }

    /***************************************************/

    public String convert4(String s, int numRows) {

        if (numRows == 1)
            return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}
