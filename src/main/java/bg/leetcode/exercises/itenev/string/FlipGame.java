package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only these two chars: '-' and '+'
 * The game ends when a person can no longer make a move and
 * therefore the other person win.
 *
 * Input: s = "++++"
 * Output:
 * [
 *   "--++",
 *   "+--+",
 *   "++--"
 * ]
 *
 * If no valid move, return [].
 */
public class FlipGame {
    private static final String MOVE = "--";

    public static void main(String[] args) {
        generatePossibleNextMoves("--++-++++----++");
    }

    public static List<String> generatePossibleNextMoves(String s) {
        List<String> possibleStates = new ArrayList<>();
        int i = 0;
        while (i < s.length()){
            int nextMove = i == 0 ? s.indexOf("++") : s.indexOf("++", i);
            if (nextMove == -1) {
                return possibleStates;
            }

            String nextState = s.substring(0, nextMove) + MOVE + s.substring(nextMove + 2);
            possibleStates.add(nextState);
            i = nextMove + 1;
        }

        return possibleStates;
    }
}
