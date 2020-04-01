package bg.leetcode.exercises.itenev.string;

/**
 * There is a robot starting at position (0, 0), the origin, on a 2D plane.
 * Given a sequence of its moves,
 * judge if this robot ends up at (0, 0) after it completes its moves.
 * <p>
 * The move sequence is represented by a string, and the character moves[i] represents its ith move.
 * Valid moves are R (right), L (left), U (up), and D (down).
 * If the robot returns to the origin after it finishes all of its moves, return true.
 * Otherwise, return false.
 * <p>
 * Note: The way that the robot is "facing" is irrelevant.
 * "R" will always make the robot move to the right once,
 * "L" will always make it move left, etc.
 * Also, assume that the magnitude of the robot's movement is the same for each move.
 * <p>
 * Input: "UD"
 * Output: true
 * Explanation: The robot moves up once, and then down once.
 * All moves have the same magnitude, so it ended up at the origin where it started.
 * Therefore, we return true.
 */
public class RobotReturnsToOrigin {

    public static boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'U')
                x++;
            else if (c == 'D')
                x--;
            else if (c == 'L')
                y++;
            else if (c == 'R')
                y--;
        }

        return x == 0 && y == 0;
    }

}
