package bg.leetcode.exercises.itenev.math;

/**
 * Uber is building a Fare Estimator that can tell you how much your ride will cost before you request it.
 * It works by passing approximated ride distance and ride time through this formula:
 * (Cost per minute) * (ride time) + (Cost per mile) * (ride distance)
 * where Cost per minute and Cost per mile depend on the car type.
 * You are one of the engineers building the Fare Estimator,
 * so knowing cost per minute and cost per mile for each car type,
 * as well as ride distance and ride time, return the fare estimates for all car types.
 *
 * ride_time = 30,
 * ride_distance = 7,
 * cost_per_minute = [0.2, 0.35, 0.4, 0.45], and
 * cost_per_mile = [1.1, 1.8, 2.3, 3.5], the output should be
 * fareEstimator(ride_time, ride_distance, cost_per_minute, cost_per_mile) = [13.7, 23.1, 28.1, 38].
 *
 * 30 * 0.2 + 7 * 1.1 = 6 + 7.7 = 13.7
 * 30 * 0.35 + 7 * 1.8 = 10.5 + 12.6 = 23.1
 * 30 * 0.4 + 7 * 2.3 = 12 + 16.1 = 28.1
 * 30 * 0.45 + 7 * 3.5 = 13.5 + 24.5 = 38
 */
public class FareEstimator {
    double[] fareEstimator(int ride_time, int ride_distance, double[] cost_per_minute, double[] cost_per_mile) {
        double[] result = new double[cost_per_minute.length];
        for(int i = 0; i < cost_per_minute.length; i++) {
            result[i] = (ride_time * cost_per_minute[i]) + (ride_distance * cost_per_mile[i]);
        }
        return result;
    }

}
