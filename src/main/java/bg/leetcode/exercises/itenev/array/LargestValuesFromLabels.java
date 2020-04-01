package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 * Then, we choose a subset S of these items, such that:
 *      |S| <= num_wanted
 *      For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 *
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 *
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 *
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 */
public class LargestValuesFromLabels {

    static class Number{
        int value;
        int label;

        public Number(int v, int l) {
            value = v;
            label = l;
        }
    }

    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        Number[] numArray = new Number[values.length];
        for(int i = 0; i < values.length; i++)
            numArray[i] = new Number(values[i], labels[i]);

        Arrays.sort(numArray, (n1, n2) -> n2.value - n1.value);

        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (Number number : numArray) {
            map.putIfAbsent(number.label, 0);
            if (map.get(number.label) >= use_limit)
                continue;

            result += number.value;
            map.put(number.label, map.get(number.label) + 1);
            num_wanted--;

            if (num_wanted == 0)
                return result;

        }
        return result;
    }

    /********/

    public int largestValsFromLabels2(int[] values, int[] labels, int num_wanted, int use_limit) {
        List<Number> items = new ArrayList<>();
        for(int i = 0; i < values.length; i++)
            items.add(new Number(values[i], labels[i]));

        PriorityQueue<Number> maxHeap = new PriorityQueue<>(
                (a,b) -> b.value - a.value
        );
        maxHeap.addAll(items);

        int value = 0;
        Map<Integer, Integer> counts = new HashMap<>();

        while (!maxHeap.isEmpty() && num_wanted > 0){
            Number current = maxHeap.remove();
            counts.put(current.label, counts.getOrDefault(current.label, 0) + 1);
            if (counts.get(current.label) <= use_limit){
                value += current.value;
                num_wanted--;
            }
        }
        return value;
    }

}
