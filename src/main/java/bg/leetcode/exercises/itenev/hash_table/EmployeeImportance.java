package bg.leetcode.exercises.itenev.hash_table;

import bg.leetcode.exercises.itenev.common.Employee;

import java.util.*;

/**
 * You are given a data structure of employee information, which includes the employee's unique id,
 * his importance value and his direct subordinates' id.
 * <p>
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively.
 * Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]],
 * and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1,
 * the relationship is not direct.
 * <p>
 * Now given the employee information of a company, and an employee id,
 * you need to return the total importance value of this employee and all his subordinates.
 * <p>
 * <p>
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3.
 * They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 */
public class EmployeeImportance {

    Map<Integer, Employee> map;

    public int getImportance(List<Employee> employees, int queryid) {
        map = new HashMap<>();
        for (Employee e : employees)
            map.put(e.id, e);

        return dfs(queryid);
    }

    public int dfs(int eid) {
        Employee employee = map.get(eid);
        int ans = employee.importance;
        for (Integer subid : employee.subordinates)
            ans += dfs(subid);
        return ans;
    }

    /************************************************************/

    public int getImportanceBFS(List<Employee> employees, int id) {
        int importance = 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees)
            map.put(employee.id, employee);


        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));

        while (!queue.isEmpty()) {
            Employee current = queue.poll();
            importance += current.importance;

            for (int subordinate : current.subordinates) {
                queue.offer(map.get(subordinate));
            }
        }

        return importance;
    }

    /************************************************************/

    public int getImportanceREC(List<Employee> employees, int id) {
        int result = 0;

        for (int i = 0; i < employees.size(); i++)
            if (id == employees.get(i).id) {
                result += employees.get(i).importance;

                for (int j = 0; j < employees.get(i).subordinates.size(); j++)
                    result += getImportanceREC(employees, employees.get(i).subordinates.get(j));

            }

        return result;
    }
}
