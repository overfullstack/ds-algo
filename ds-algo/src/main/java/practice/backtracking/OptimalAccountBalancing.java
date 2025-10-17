package practice.backtracking;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** [Optimal Account Balancing](https://www.naukri.com/code360/problems/settle-debt_1232658) */
public class OptimalAccountBalancing {
	public static int settleDebt(List<List<Integer>> arr) {
		var balances = new HashMap<Integer, Integer>();
		for (var txn : arr) {
			balances.merge(txn.get(0), txn.get(2), Integer::sum);
			balances.merge(txn.get(1), -txn.get(2), Integer::sum);
		}
		@SuppressWarnings("java:S6204")
		var unsettledTxns =
				balances.values().stream()
						.filter(v -> v != 0)
						.collect(Collectors.toList()); // We need mutable list
		return solve(0, unsettledTxns);
	}

	private static int solve(int startIdx, List<Integer> txns) {
		var firstUnsettledTxnIdx = // ! Non-zero
				IntStream.range(startIdx, txns.size()).filter(i -> txns.get(i) != 0).findFirst();
		// ! Either all txns are settled to 0 or startIdx == txn.size()
		if (firstUnsettledTxnIdx.isEmpty()) {
			return 0;
		}
		var result = Integer.MAX_VALUE;
		var unsettledTxnIdx = firstUnsettledTxnIdx.getAsInt();
		// ! Many to Many Cartesian product between +ve and -ve
		// ! Try all combinations of +ve and -ve settlements, by starting at a different unsettledTxnIdx
		for (var i = unsettledTxnIdx + 1; i < txns.size(); i++) {
			if (txns.get(unsettledTxnIdx) * txns.get(i) < 0) {
				txns.set(i, txns.get(i) + txns.get(unsettledTxnIdx)); // ! Settle unsettledTxnIdx
				result = Math.min(result, 1 + solve(unsettledTxnIdx + 1, txns));
				txns.set(i, txns.get(i) - txns.get(unsettledTxnIdx)); // ! backtrack
			}
		}
		return result;
	}

	static void main() {
		System.out.println(settleDebt(List.of(List.of(0, 1, 10), List.of(2, 0, 5)))); // 2
	}
}
