package bg.leetcode.exercises.itenev.math;

import java.util.Arrays;

public class CenturyFromYear {

    public int getCentury(int year) {
        if(year <= 0)
            return 0;
        else if (year <= 100)
            return 1;
        else if (year % 100 == 0)
            return year / 100;
        else
            return year / 100 + 1;
    }

}
