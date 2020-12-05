http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps/0

Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then cannot move through that element.

Example:

Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3 (1-> 3 -> 8 ->9)
First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.

Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then cannot move through that element.

Input:
The first line contains an integer T, depicting total number of test cases.
Then following T lines contains a number n denoting the size of the array.
Next line contains the sequence of integers a1, a2, ..., an.

Output:
Each seperate line showing the minimum number of jumps. If answer is not possible print -1.

Constraints:
1 ≤ T ≤ 40
1 ≤ N ≤ 100
0<=a[N]<=100

Example:

Input:

1
11
1 3 5 8 9 2 6 7 6 8 9

Output:

3