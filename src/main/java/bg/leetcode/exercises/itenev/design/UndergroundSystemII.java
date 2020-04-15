package bg.leetcode.exercises.itenev.design;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystemII {

    Map<Integer, PassengerCheckIn> passengerTrip;
    Map<String, RouteStatistic> tripMap;

    public UndergroundSystemII() {
        passengerTrip = new HashMap<>();
        tripMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        passengerTrip.put(id, new PassengerCheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        PassengerCheckIn trip = passengerTrip.get(id);
        String route = trip.boardingStation + "_" + stationName;
        RouteStatistic routeStatistic = tripMap.getOrDefault(route, new RouteStatistic());
        routeStatistic.update(t - trip.boardingTime);
        tripMap.put(route, routeStatistic);
        passengerTrip.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        RouteStatistic trip = tripMap.get(startStation + "_" + endStation);
        return trip.totalTimeTaken / trip.numberOfCompletedTrips;
    }

    static class PassengerCheckIn {
        String boardingStation;
        double boardingTime;

        public PassengerCheckIn(String fromStation, double t2) {
            boardingStation = fromStation;
            boardingTime = t2;
        }
    }

    static class RouteStatistic {
        double numberOfCompletedTrips;
        double totalTimeTaken;

        public void update(double timeTakenInThisTrip) {
            totalTimeTaken += timeTakenInThisTrip;
            numberOfCompletedTrips++;
        }
    }
}
