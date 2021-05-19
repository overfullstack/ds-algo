https://www.geeksforgeeks.org/sort-elements-by-frequency/
https://practice.geeksforgeeks.org/problems/sorting-elements-of-an-array-by-frequency/0

Print the elements of an array in the decreasing frequency if 2 numbers have same frequency then
print the one which came first.

Examples:

Input:  arr[] = {2, 5, 2, 8, 5, 6, 8, 8} Output: arr[] = {8, 8, 8, 2, 2, 5, 5, 6}

Input: arr[] = {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8} Output: arr[] = {8, 8, 8, 2, 2, 5, 5, 6, -1,
9999999}

Given an array of integers, sort the array according to frequency of elements. For example, if the
input array is {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}, then modify the array to {3, 3, 3, 3, 2, 2, 2,
12, 12, 4, 5}.

If frequencies of two elements are same, print them in increasing order.

Input:

The first line of input contains an integer T denoting the number of test cases. The description of
T test cases follows. The first line of each test case contains a single integer N denoting the size
of array. The second line contains N space-separated integers A1, A2, ..., AN denoting the elements
of the array.

Output:

Print each sorted array in a separate line. For each array its numbers should be seperated by space.

Constraints:

1 ≤ T ≤ 70 30 ≤ N ≤ 130 1 ≤ A [ i ] ≤ 60

Example:

Input:

1 5 5 5 4 6 4

Output:

4 4 5 5 6
