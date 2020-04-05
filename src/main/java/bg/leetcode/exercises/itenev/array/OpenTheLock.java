package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * <p>
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 * <p>
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * <p>
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 */
public class OpenTheLock {

    public int openLock(String[] deadends, String target) {
        Set<String> deadEnds = new HashSet<>(List.of(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                String lock = queue.poll();

                if (deadEnds.contains(lock)) {
                    size--;
                    continue;
                }
                if (lock.equals(target))
                    return level;

                StringBuilder sb = new StringBuilder(lock);
                for (int i = 0; i < 4; i++) {
                    char current = sb.charAt(i);
                    String s1 = sb.substring(0, i) +
                            (current == '9' ? 0 : current - '0' + 1) +
                            sb.substring(i + 1);
                    String s2 = sb.substring(0, i) +
                            (current == '0' ? 9 : current - '0' - 1) +
                            sb.substring(i + 1);

                    if (!visited.contains(s1) && !deadEnds.contains(s1)) {
                        queue.add(s1);
                        visited.add(s1);
                    }
                    if (!visited.contains(s2) && !deadEnds.contains(s2)) {
                        queue.add(s2);
                        visited.add(s2);
                    }
                }
                size--;
            }
            level++;
        }
        return -1;
    }

    /********************************************************************************/

    public int openLock2(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<String>();
        for(int i = 0; i < deadends.length; i++)
            deadSet.add(deadends[i]);

        // Use set instead of queue to accelerate search
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();
        beginSet.add("0000");
        endSet.add(target);
        // Search from begin and end simultaneously
        return deadSet.contains("0000") || deadSet.contains(target) ? -1 : traverse(beginSet, endSet, deadSet);
    }

    public int traverse(Set<String> beginSet, Set<String> endSet, Set<String> deadSet) {
        Set<String> newSet = new HashSet<String>();
        for(String current: beginSet) {
            StringBuilder sb = new StringBuilder(current);
            for(int i = 0; i < 4; i++) {
                char originalChar = current.charAt(i);
                sb.setCharAt(i, (char)((originalChar - '0' + 1) % 10 + '0'));
                if( endSet.contains(sb.toString()) )
                    return 1;
                if( !deadSet.contains(sb.toString()) ) {
                    newSet.add(sb.toString());
                    deadSet.add(sb.toString()); // the searched elements are taken as deadend
                }

                sb.setCharAt(i, (char)((originalChar - '0' + 9) % 10 + '0'));
                if( endSet.contains(sb.toString()) )
                    return 1;
                if( !deadSet.contains(sb.toString())) {
                    newSet.add(sb.toString());
                    deadSet.add(sb.toString());
                }

                sb.setCharAt(i, originalChar);
            }
        }
        if( newSet.size() == 0 )
            return -1;
        beginSet = newSet;
        // Each time search from the set with less elements
        int result = beginSet.size() > endSet.size() ?
                traverse(endSet, beginSet, deadSet) : traverse(beginSet, endSet, deadSet);
        return result == -1 ? -1 : result + 1;
    }

    /********************************************************************************/

    public int openLock3(String[] deadends, String target) {
        LockStep locks = new LockStep("0000", target);
        Set<String> s = new HashSet<>(Arrays.asList(deadends));

        if (s.contains("0000"))
            return -1;

        if (locks.solved())
            return 0;


        PriorityQueue<LockStep> minHeap = new PriorityQueue<>();
        minHeap.offer(locks);

        while (!minHeap.isEmpty()) {
            LockStep current = minHeap.poll();
            for (int i = 0; i < 4; i++) {
                LockStep lockUp = current.rotateNth(i, 1);
                if (lockUp.solved()) {
                    return lockUp.getLevel();
                }
                LockStep lockDown = current.rotateNth(i, -1);

                if (lockDown.solved()) {
                    return lockDown.getLevel();
                }

                if (!s.contains(lockUp.getStep())) {
                    minHeap.offer(lockUp);
                    s.add(lockUp.getStep());
                }
                if (!s.contains(lockDown.getStep())) {
                    minHeap.offer(lockDown);
                    s.add(lockDown.getStep());
                }
            }
        }

        return -1;
    }

    private static class LockStep implements Comparable<LockStep> {
        private String step;
        private String target;
        private int distance;
        private LockStep cameFrom;

        public LockStep(String s, String t) {
            this.step = s;
            this.target = t;
            this.cameFrom = null;
            this.distance = calcDistance(s, t);
        }

        public LockStep(String s, String t, LockStep cf) {
            this.step = s;
            this.target = t;
            this.cameFrom = cf;
            this.distance = calcDistance(s, t);
        }

        public int calcDistance(String start, String target) {
            return _calcDistance(start, target) + this.getLevel();
        }

        private int _calcDistance(String start, String target) {
            int distance = 0;

            for (int i = 0; i < 4; i++) {
                int d = Math.abs(target.charAt(i) - start.charAt(i));
                d = (d <= 5) ? d : 10 - d;
                distance += d;
            }

            return distance;
        }

        public LockStep rotateNth(int nth, int n) {
            String t = this.step;
            n = ((n % 10) + 10) % 10;
            if (n == 0) {
                return new LockStep(this.step, this.target, this);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                if (i == nth) {
                    char c = t.charAt(i);
                    int d;
                    if (c == '9') {
                        d = 47 + n;
                    } else {
                        d = c - '0';
                        d = 48 + (d + n) % 10;
                    }
                    char cc = (char) d;
                    sb.append(cc);
                } else {
                    sb.append(t.charAt(i));
                }
            }

            return new LockStep(sb.toString(), target, this);
        }

        public int getLevel() {
            LockStep that = this;
            int l = 0;
            while (that.cameFrom != null) {
                that = that.cameFrom;
                l++;
            }
            return l;
        }

        public boolean solved() {
            return step.equals(target);
        }

        public int getDistance() {
            return this.distance;
        }

        public String getStep() {
            return this.step;
        }

        @Override
        public int compareTo(LockStep ls) {
            if (this.getDistance() == ls.getDistance()) {
                return 0;
            }

            return (this.getDistance() > ls.getDistance()) ? 1 : -1;
        }
    }


}
