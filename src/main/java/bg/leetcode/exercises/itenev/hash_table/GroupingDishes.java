package bg.leetcode.exercises.itenev.hash_table;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * You are given a list dishes, where each element consists of a list of strings beginning with the name of the dish,
 * followed by all the ingredients used in preparing it. You want to group the dishes by ingredients,
 * so that for each ingredient you'll be able to find all the dishes that contain it
 * (if there are at least 2 such dishes).
 * <p>
 * Return an array where each element is a list beginning with the ingredient name,
 * followed by the names of all the dishes that contain this ingredient.
 * The dishes inside each list should be sorted lexicographically,
 * and the result array should be sorted lexicographically by the names of the ingredients.
 * <p>
 * Input: [["Salad", "Tomato", "Cucumber", "Salad", "Sauce"],
 * ["Pizza", "Tomato", "Sausage", "Sauce", "Dough"],
 * ["Quesadilla", "Chicken", "Cheese", "Sauce"],
 * ["Sandwich", "Salad", "Bread", "Tomato", "Cheese"]]
 * <p>
 * Output: [["Cheese", "Quesadilla", "Sandwich"],
 * ["Salad", "Salad", "Sandwich"],
 * ["Sauce", "Pizza", "Quesadilla", "Salad"],
 * ["Tomato", "Pizza", "Salad", "Sandwich"]]
 */
public class GroupingDishes {

    public String[][] groupingDishes(String[][] dishes) {
        return Arrays.stream(dishes)
                .flatMap(d ->
                        Arrays.stream(d)
                                .skip(1)
                                .map(i -> new AbstractMap.SimpleEntry(i, d[0]))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        TreeMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e ->
                        Stream.concat(
                                Stream.of(e.getKey()),
                                e.getValue().stream().sorted()).toArray(String[]::new)
                )
                .toArray(String[][]::new);
    }

    /******************************************************************/

    public String[][] groupingDishes2(String[][] dishes) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String[] recipe : dishes) {
            for (int i = 1; i < recipe.length; i++) {
                if (map.containsKey(recipe[i])) {
                    ArrayList<String> foods = map.get(recipe[i]);
                    foods.add(recipe[0]);
                    map.put(recipe[i], foods);
                } else {
                    ArrayList<String> foods = new ArrayList<>();
                    foods.add(recipe[0]);
                    map.put(recipe[i], foods);
                }
            }
        }

        ArrayList<String[]> result = new ArrayList<>();

        for (String ingredient : map.keySet()) {
            ArrayList<String> foods = map.get(ingredient);

            if (foods.size() > 1) {
                Collections.sort(foods);
                String[] fin = new String[foods.size() + 1];
                fin[0] = ingredient;
                for (int i = 0; i < foods.size(); i++)
                    fin[i + 1] = foods.get(i);
                result.add(fin);
            }
        }

        result.sort(Comparator.comparing(a -> a[0]));
        return result.toArray(new String[0][]);
    }

}
