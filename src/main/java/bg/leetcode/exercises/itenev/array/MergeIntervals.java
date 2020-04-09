package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();

        for (int[] interval : intervals)
            //if the current interval does not overlap with the previous
            if (merged.isEmpty() || merged.getLast()[1] < interval[0])
                merged.add(interval);
            else
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);

        return merged.toArray(new int[merged.size()][]);
    }

    /**********************************************************************************************/

    private Map<int[], List<int[]>> graph;
    private Map<Integer, List<int[]>> nodesInComp;
    private Set<int[]> visited;

    public int[][] merge2(int[][] intervals) {
        buildGraph(intervals);
        buildComponents(intervals);

        // for each component, merge all intervals into one interval.
        List<int[]> merged = new LinkedList<>();
        for (int comp = 0; comp < nodesInComp.size(); comp++) {
            merged.add(mergeNodes(nodesInComp.get(comp)));
        }

        return merged.toArray(new int[merged.size()][]);
    }

    // return whether two intervals overlap (inclusive)
    private boolean overlap(int[] a, int[] b) {
        return a[0] <= b[1] && b[0] <= a[1];
    }

    // build a graph where an undirected edge between intervals u and v exists
    // iff u and v overlap.
    private void buildGraph(int[][] intervals) {
        graph = new HashMap<>();
        for (int[] interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }

        for (int[] interval1 : intervals) {
            for (int[] interval2 : intervals) {
                if (overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

    // merges all of the nodes in this connected component into one interval.
    private int[] mergeNodes(List<int[]> nodes) {
        int minStart = nodes.get(0)[0];
        for (int[] node : nodes) {
            minStart = Math.min(minStart, node[0]);
        }

        int maxEnd = nodes.get(0)[1];
        for (int[] node : nodes) {
            maxEnd = Math.max(maxEnd, node[1]);
        }

        return new int[]{minStart, maxEnd};
    }

    // use depth-first search to mark all nodes in the same connected component
    // with the same integer.
    private void markComponentDFS(int[] start, int compNumber) {
        Stack<int[]> stack = new Stack<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            int[] node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);

                nodesInComp.computeIfAbsent(compNumber, k -> new LinkedList<>());
                nodesInComp.get(compNumber).add(node);

                stack.addAll(graph.get(node));
            }
        }
    }

    // gets the connected components of the interval overlap graph.
    private void buildComponents(int[][] intervals) {
        nodesInComp = new HashMap<>();
        visited = new HashSet<>();
        int compNumber = 0;

        for (int[] interval : intervals) {
            if (!visited.contains(interval)) {
                markComponentDFS(interval, compNumber);
                compNumber++;
            }
        }
    }

}
