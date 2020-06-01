http://www.geeksforgeeks.org/find-subarray-with-given-sum/ 
http://www.practice.geeksforgeeks.org/problem-page.php?pid=590

Find subarray with given sum | Set 1 (Nonnegative Numbers)
Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
Ouptut: Sum found between indexes 1 and 4

Input: arr[] = {1, 4}, sum = 0
Output: No subarray found
There may be more than one subarrays with sum as the given sum. The following solutions print first such subarray.

Subarray with given sum array      Amazon    Google    Visa
Given an unsorted array of non-negative integers, find a continous subarray which adds to a given number.

Input:

The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. Each test case consists of two lines. The first line of each test case is N and S, where N is the size of array and S is the sum. The second line of each test case contains N space separated integers denoting the array elements.

Output:

Corresponding to each test case, in a new line, print the starting and ending positions of first such occuring subarray if sum equals to subarray, else print -1.

Note: Position of 1st element of the array should be considered as 1.

Expected Time Complexity: O(n)

Constraints:
1 ≤ T ≤ 50
1 ≤ N ≤ 100
1 ≤ an array element ≤ 200

Example:

Input:
2
5 12
1 2 3 7 5
10 15
1 2 3 4 5 6 7 8 9 10

Output:
2 4
1 5

Explanation : 
In first test case, sum of elements from 2nd position to 4th position is 12
In second test case, sum of elements from 1st position to 5th position is 15