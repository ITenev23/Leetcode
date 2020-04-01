package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;

/**
 * Determine if a person could attend all meetings
 * <p>
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 */
public class MeetingRooms {

    private static class Interval {
        int start;
        int end;

        public Interval() {
            this.start = 0;
            this.end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static boolean canAttendMeetings(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int i = 0; i < starts.length; i++) {
            if (starts[i + 1] < ends[i])
                return false;
        }

        return true;
    }
}
