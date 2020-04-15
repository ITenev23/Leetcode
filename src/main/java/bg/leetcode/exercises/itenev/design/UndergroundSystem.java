package bg.leetcode.exercises.itenev.design;

import bg.leetcode.exercises.itenev.common.Pair;
import bg.leetcode.exercises.itenev.stack.WordLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement the class UndergroundSystem that supports three methods:
 * <p>
 * 1. checkIn(int id, string stationName, int t)
 *      A customer with id card equal to id, gets in the station stationName at time t.
 *      A customer can only be checked into one place at a time.
 * 2. checkOut(int id, string stationName, int t)
 *      A customer with id card equal to id, gets out from the station stationName at time t.
 * 3. getAverageTime(string startStation, string endStation)
 *      Returns the average time to travel between the startStation and the endStation.
 *      The average time is computed from all the previous traveling from startStation to endStation that happened directly.
 *      Call to getAverageTime is always valid.
 * <p>
 * You can assume all calls to checkIn and checkOut methods are consistent. That is,
 * if a customer gets in at time t1 at some station,
 * then it gets out at time t2 with t2 > t1. All events happen in chronological order.
 */
public class UndergroundSystem {

    Map<String, Pair<Integer, Integer>> checkoutMap = new HashMap<>(); // Route - {TotalTime, Count}
    Map<Integer, Pair<String, Integer>> checkInMap = new HashMap<>();  // Uid - {StationName, Time}

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkIn = checkInMap.get(id);
        String route = checkIn.getKey() + "_" + stationName;
        int totalTime = t - checkIn.getValue();
        Pair<Integer, Integer> checkout = checkoutMap.getOrDefault(route, new Pair<>(0, 0));
        checkoutMap.put(route, new Pair<>(checkout.getKey() + totalTime, checkout.getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        Pair<Integer, Integer> checkout = checkoutMap.get(startStation + "_" + endStation);
        return (double) checkout.getKey() / checkout.getValue();
    }
}
