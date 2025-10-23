package practice.stack;

import ds.util.Pair;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 23 Oct 2025 16:15 */

/**
 * [1249. Minimum Remove to Make Valid
 * Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)
 */
public class MinimumRemoveToMakeValidParentheses {
	public String minRemoveToMakeValid(String s) {
		var idxToRemove = new HashSet<Integer>();
		var stk = new ArrayDeque<Pair<Integer, Character>>();
		for (var i = 0; i < s.length(); i++) {
			var ch = s.charAt(i);
			if (ch == '(') {
				stk.push(new Pair<>(i, ch));
			} else if (ch == ')' && (stk.isEmpty() || stk.peek().second() != '(')) {
				idxToRemove.add(i);
			} else if (ch == ')' && stk.peek().second() == '(') {
				stk.pop();
			}
		}
		while (!stk.isEmpty()) {
			idxToRemove.add(stk.pop().first());
		}
		return IntStream.range(0, s.length())
				.filter(i -> !idxToRemove.contains(i))
				.mapToObj(index -> Character.toString(s.charAt(index)))
				.collect(Collectors.joining());
	}

	static void main() {
		var minimumRemoveToMakeValidParentheses = new MinimumRemoveToMakeValidParentheses();
		System.out.println(
				minimumRemoveToMakeValidParentheses.minRemoveToMakeValid("lee(t(c)o)de")); // "lee(t(c)o)de"
		System.out.println(
				minimumRemoveToMakeValidParentheses.minRemoveToMakeValid("a)b(c)d")); // "ab(c)d)"
		System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValid("))((")); // ""
	}
}
