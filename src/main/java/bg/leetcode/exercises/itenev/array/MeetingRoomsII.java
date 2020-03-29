package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times.
 * Find the minimum number of conference rooms required
 *
 * Input: [[0.30],[5,10],[15,20]]
 * Output: 2
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class MeetingRoomsII {

    public static class Interval {
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

    public static int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
        minHeap.add(intervals[0]);

        for (Interval current : intervals) {
            Interval earliest = minHeap.remove();

            if (current.start >= earliest.end)
                earliest.end = current.end;
            else
                minHeap.add(current);

            minHeap.add(earliest);
        }

        return minHeap.size();
    }
}
