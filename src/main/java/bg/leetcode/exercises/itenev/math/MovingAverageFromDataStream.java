package bg.leetcode.exercises.itenev.math;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Calculate the moving average of all ints in the sliding window
 *
 * MovingAverage m = MovingAverage(3);
 * m.next(1) = 1;
 * m.next(10) = (1 + 10) / 2;
 * m.next(3) = (1 + 10 + 3) / 3;
 * m.next(5) = (10 + 3 + 5) / 3;
 * remove the first one from the queue(1), because maxSize is 3
 */
public class MovingAverageFromDataStream {

    private static class MovingAverage {
        private Queue<Integer> queue;
        private int maxSize;
        private double sum;

        public MovingAverage(int size) {
            this.queue = new LinkedList<>();
            this.maxSize = size;
            this.sum = 0.0;
        }

        public double next(int val) {
            if (this.queue.size() == this.maxSize)
                this.sum -= queue.remove();

            this.queue.add(val);
            this.sum += val;

            return this.sum / this.queue.size();
        }
    }

}
