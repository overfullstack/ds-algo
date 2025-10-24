package practice;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/** [690. Employee Importance](https://leetcode.com/problems/employee-importance/) */
public class EmployeeImportance {

	public int getImportance(List<Employee> employees, int id) {
		var employeeRegistry =
				employees.stream().collect(Collectors.toMap(e -> e.id, Function.identity()));
		return dfs(id, employeeRegistry);
	}

	private static int dfs(Integer eId, Map<Integer, Employee> employeeRegistry) {
		var employee = employeeRegistry.get(eId);
		return employee.importance
				+ employee.subordinates.stream().mapToInt(seId -> dfs(seId, employeeRegistry)).sum();
	}

	private record Employee(int id, int importance, List<Integer> subordinates) {}

	static void main() {
		var employeeImportance = new EmployeeImportance();
		var employees =
				List.of(
						new Employee(1, 5, List.of(2, 3)),
						new Employee(2, 3, List.of()),
						new Employee(3, 3, List.of()));
		System.out.println(employeeImportance.getImportance(employees, 1));
		var employees2 = List.of(new Employee(1, 2, List.of(5)), new Employee(5, -3, List.of()));
		System.out.println(employeeImportance.getImportance(employees2, 5));
	}
}
