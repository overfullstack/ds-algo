package practice;

/* 31 Aug 2025 20:40 */

import java.util.ArrayDeque;

/** [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences/) */
public class ValidateStackSequences {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		var stk = new ArrayDeque<Integer>();
		var i = 0;
		for (var push : pushed) {
			stk.add(push);
			while (!stk.isEmpty() && stk.peekLast() == popped[i]) {
				stk.removeLast();
				i++;
			}
		}
		return stk.isEmpty();
	}

	static void main() {
		var obj = new ValidateStackSequences();
		System.out.println(
				obj.validateStackSequences(new int[] {1, 2, 3, 4, 5}, new int[] {4, 5, 3, 2, 1}));
		System.out.println(
				obj.validateStackSequences(new int[] {1, 2, 3, 4, 5}, new int[] {4, 3, 5, 1, 2}));
	}
}
