package bg.leetcode.exercises.itenev.tree;

/**
 * Consider a special family of Engineers and Doctors. This family has the following rules:
 * Everybody has two children.
 * The first child of an Engineer is an Engineer and the second child is a Doctor.
 * The first child of a Doctor is a Doctor and the second child is an Engineer.
 * All generations of Doctors and Engineers start with an Engineer.
 * We can represent the situation using this diagram:
 *
 *                 E
 *            /         \
 *           E           D
 *         /   \        /  \
 *        E     D      D    E
 *       / \   / \    / \   / \
 *      E   D D   E  D   E E   D
 * Given the level and position of a person in the ancestor tree above, find the profession of the person.
 * Note: in this tree first child is considered as left child, second - as right.
 */
public class FindProfession {

    public String findProfession(int level, int pos) {
        boolean inverted = false;

        while (level > 1) {
            if (pos % 2 == 0) {
                inverted = !inverted;
            }

            pos = (pos + 1) / 2;
            level--;
        }

        return inverted? "Doctor" : "Engineer";
    }

    /************************************************************/


    String findProfession2(int level, int pos) {
        return getProf(level-1, pos, "Engineer");
    }

    String getProf(int level, int pos, String current) {
        if(level == 0)
            return current;

        int number = (int)Math.pow(2, level);
        int half = number / 2;
        System.out.println("Current: " + current + " level: " + level + " number: " + number );
        if(pos <= half) {
            // go left
            return getProf(level-1, pos, current);
        }
        // go right
        String next = (current == "Engineer" ? "Doctor" : "Engineer");
        return getProf(level-1, pos-half, next);
    }
}
