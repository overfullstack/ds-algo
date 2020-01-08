http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
https://practice.geeksforgeeks.org/problems/peak-element/1

Find a peak element

Given an array of integers. Find a peak element in it. An array element is peak if it is NOT smaller than its neighbors.
For corner elements, we need to consider only one neighbor.
For example, for input array {5, 10, 20, 15}, 20 is the only peak element.
For input array {10, 20, 15, 2, 23, 90, 67}, there are two peak elements: 20 and 90.
Note that we need to return any one peak element.

Following corner cases give better idea about the problem.
1) If input array is sorted in strictly increasing order, the last element is always a peak element. For example, 50 is peak element in {10, 20, 30, 40, 50}.
2) If input array is sorted in strictly decreasing order, the first element is always a peak element. 100 is the peak element in {100, 80, 60, 50, 20}.
3) If all elements of input array are same, every element is a peak element.

Peak element (Function Problem)

Given an array of integers. The task is to find a peak element in it. An array element is peak if it is not smaller than its neighbors. For corner elements, we need to consider only one neighbor. For example, for input array {5, 10, 20, 15}, 20 is the only peak element. For input array {10, 20, 15, 2, 23, 90, 67}, there are two peak elements: 20 and 90.

Note: There may be multiple peak element possible, in that case you may return any valid index.

Input:
The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. Each test case contains an integer N. Then in the next line are N space separated values of the array.

Output:
For each test case output will be 1 if the index returned by the function is an peak index.

Constraints:
1<=T<=100
1<=N<=100
1<=A[]<=1000

Example:
Input:
2
3
1 2 3
2
3 4
Output:
1
1
