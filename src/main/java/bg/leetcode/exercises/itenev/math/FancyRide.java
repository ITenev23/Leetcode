package bg.leetcode.exercises.itenev.math;

/**
 * Being a new Uber user, you have $20 off your first ride.
 * You want to make the most out of it and drive in the fanciest car you can afford,
 * without spending any out-of-pocket money.
 * There are 5 options, from the least to the most expensive: "UberX", "UberXL", "UberPlus", "UberBlack" and "UberSUV".
 *
 * You know the length l of your ride in miles and how much one mile costs for each car. Find the best car you can afford.
 *
 * Input:  l = 30 and fares = [0.3, 0.5, 0.7, 1, 1.3]
 * Output: "UberXL".
 *
 * The cost for the ride in this car would be $15,
 * which you can afford, but "UberPlus" would cost $21, which is too much for you.
 */
public class FancyRide {

    String fancyRide(int l, double[] fares) {
        int uber = 0;
        for(int i = fares.length - 1; i >= 0; i--) {
            int current = (int)(l * fares[i]);
            if(current <= 20) {
                uber = i + 1;
                break;
            }
        }

        switch(uber) {
            case 5: return "UberSUV";
            case 4: return "UberBlack";
            case 3: return "UberPlus";
            case 2: return "UberXL";
            case 1: return "UberX";
            default: return "Uber";
        }
    }

}
