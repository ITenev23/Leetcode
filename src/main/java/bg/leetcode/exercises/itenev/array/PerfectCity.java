package bg.leetcode.exercises.itenev.array;

/**
 * Consider a city where the streets are perfectly laid out to form an infinite square grid.
 * In this city finding the shortest path between two given points (an origin and a destination)
 * is much easier than in other more complex cities. As a new Uber developer,
 * you are tasked to create an algorithm that does this calculation.
 *
 * Given user's departure and destination coordinates, each of them located on some street,
 * find the length of the shortest route between them assuming that cars can only move along the streets.
 * Each street can be represented as a straight line defined by the x = n or y = n formula, where n is an integer.
 *
 * For departure = [0.4, 1] and destination = [0.9, 3], the output should be
 * perfectCity(departure, destination) = 2.7.
 *
 * 0.6 + 2 + 0.1 = 2.7, which is the answer.
 */
public class PerfectCity {

    double perfectCity(double[] departure, double[] destination) {
        double minorVert = 0;
        double minorHor = 0;

        minorVert = minorDistance(departure[1], destination[1]);
        minorHor = minorDistance(departure[0], destination[0]);

        return minorVert + minorHor;
    }
    double minorDistance(double a, double b) {
        if (a == b) return 0;

        if (!isInBetween(a, b)) {
            double left = floor(a);
            left = (a - left) + (b - left);
            double right = ceil(a);
            right = (right - a) + (right - b);

            double result = left < right?left:right;
            //result = result % 1.0;

            return result;
        } else {
            double min = min(a, b);
            double max = max(a,b);
            double minRight = ceil(min);

            double result = (minRight - min) + (max - minRight);
            //result = result = result % 1.0;

            return result;
        }
    }

    double min(double a, double b) {
        return a < b?a:b;
    }

    double max(double a, double b) {
        return a > b?a:b;
    }

    /**
     * Check wether there is road between them.
     * @return true if there is road(s) between a and b.
     */
    boolean isInBetween(double a, double b) {
        if (a < 0.0 && b > 0.0) return true;
        if (a > 0.0 && b < 0.0) return true;
        if (floor(a) != floor(b)) return true;
        return false;
    }

    double abs(double input) {
        if (input > 0.0) return input;
        return 0.0 - input;
    }

    double floor(double input) {
        double result = input - (input % 1.0);
        return result;
    }

    double ceil(double input) {
        if (input % 1.0 == 0.0) {
            return input;
        }
        return floor(input) + 1;
    }
}
