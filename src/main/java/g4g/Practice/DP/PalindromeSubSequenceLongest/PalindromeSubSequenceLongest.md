https://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
https://practice.geeksforgeeks.org/problems/longest-palindromic-subsequence/0

Dynamic Programming | Set 12 (Longest Palindromic Subsequence)
Given a sequence, find the length of the longest palindromic subsequence in it.
For example, if the given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it.
“BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.

The naive solution for this problem is to generate all subsequences of the given sequence and find the longest palindromic subsequence.
This solution is exponential in term of time complexity.
Let us see how this problem possesses both important properties of a Dynamic Programming (DP) Problem and can efficiently solved using Dynamic Programming.

Given a String, find the longest palindromic subsequence

Input:
The first line of input contains an integer T, denoting no of test cases. The only line of each test case consists of a string S(only lowercase)

Output:
Print the Maximum length possible for palindromic subsequence.

Constraints:
1<=T<=100
1<=|Length of String|<=1000


Examples:
Input:
2
bbabcbcab
abbaab
Output:
7
4