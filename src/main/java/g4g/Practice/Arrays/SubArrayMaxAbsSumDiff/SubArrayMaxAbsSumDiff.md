[http://www.geeksforgeeks.org/maximum-absolute-difference-between-sum-of-two-contiguous-sub-arrays/?utm_source=feedburner&utm_medium=email&utm_campaign=Feed%3A+Geeksforgeeks+%28GeeksforGeeks%29]
[https://practice.geeksforgeeks.org/problems/max-absolute-difference/0]

Maximum absolute difference between sum of two contiguous sub-arrays
Given an array of integers, find two non-overlapping contiguous sub-arrays such that
the absolute difference between the sum of two sub-arrays is maximum.

For example,

Input: [-2, -3, 4, -1, -2, 1, 5, -3]
Output: 12
Two subarrays are [-2, -3] and [4, -1, -2, 1, 5]

Input: [2, -1, -2, 1, -4, 2, 8]
Output: 16
Two subarrays are [-1, -2, 1, -4] and [2, 8]
Expected time complexity is O(n).

Given an array of integers, find two non-overlapping contiguous sub-arrays such that the absolute difference between the sum of two sub-arrays is maximum.

For example,

Input: [-2, -3, 4, -1, -2, 1, 5, -3]
Output: 12
Two subarrays are [-2, -3] and [4, -1, -2, 1, 5]

Input: [2, -1, -2, 1, -4, 2, 8]
Output: 16
Two subarrays are [-1, -2, 1, -4] and [2, 8]

Input:
The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. Each Test case contains an integer N denoting the size of the array. Then in the next line are N space separated values of the array.

Output:
For each test case in a new line print the required output.

Constraints:
1<=T<=100
1<=N<=100
-1000<=A[]<=1000

Example:
Input:
2
7
2 -1 -2 1 -4 2 8
8
-2 -3 4 -1 -2 1 5 -3
Output:
16
12