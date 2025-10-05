package practice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	public int minTransfers(int[][] transactions) {
		// Calculate net balance for each person (max 12 people based on constraints)
		int[] netBalance = new int[12];
		for (int[] transaction : transactions) {
			int giver = transaction[0];
			int receiver = transaction[1];
			int amount = transaction[2];
			netBalance[giver] -= amount;
			netBalance[receiver] += amount;
		}

		// Collect all non-zero balances (people who owe or are owed money)
		List<Integer> nonZeroBalances = new ArrayList<>();
		for (int balance : netBalance) {
			if (balance != 0) {
				nonZeroBalances.add(balance);
			}
		}

		int numPeople = nonZeroBalances.size();

		// Dynamic programming array where dp[mask] represents minimum transfers
		// needed to settle debts for the subset of people represented by mask
		int[] dp = new int[1 << numPeople];
		Arrays.fill(dp, Integer.MAX_VALUE / 2); // Use large value to avoid overflow
		dp[0] = 0; // Base case: no people means no transfers needed

		// Iterate through all possible subsets of people
		for (int mask = 1; mask < (1 << numPeople); mask++) {
			// Calculate the sum of balances for current subset
			int sumOfBalances = 0;
			for (int personIndex = 0; personIndex < numPeople; personIndex++) {
				if ((mask >> personIndex & 1) == 1) {
					sumOfBalances += nonZeroBalances.get(personIndex);
				}
			}

			// If sum is 0, this subset can be settled internally
			if (sumOfBalances == 0) {
				// Maximum transfers needed is (number of people - 1)
				dp[mask] = Integer.bitCount(mask) - 1;

				// Try partitioning current subset into two groups
				// and use previously computed results
				for (int subset = (mask - 1) & mask; subset > 0; subset = (subset - 1) & mask) {
					int complement = mask ^ subset;
					dp[mask] = Math.min(dp[mask], dp[subset] + dp[complement]);
				}
			}
		}

		// Return minimum transfers for all people
		return dp[(1 << numPeople) - 1];
	}
}
