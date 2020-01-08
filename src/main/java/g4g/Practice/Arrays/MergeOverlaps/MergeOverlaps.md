http://www.geeksforgeeks.org/merging-intervals/
http://practice.geeksforgeeks.org/problems/overlapping-intervals/0

Given a set of time intervals in any order, merge all overlapping intervals into one and output the result which should have only mutually exclusive intervals. Let the intervals be represented as pairs of integers for simplicity. 
For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }. The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}. Similarly {5, 7} and {6, 8} should be merged and become {5, 8}

-----

Given a collection of Intervals,merge all the overlapping Intervals.
For example:

Given [1,3], [2,6], [8,10], [15,18],

return [1,6], [8,10], [15,18].

Make sure the returned intervals are sorted.

Input:

The first line contains an integer T, depicting total number of test cases. 
Then following T lines contains an integer N depicting the number of Intervals and next line followed by the intervals starting and ending positions 'x' and 'y' respectively.


Output:

Print the intervals after overlapping in sorted manner.  There should be a newline at the end of output of every test case.

Constraints:

1 ≤ T ≤ 50
1 ≤ N ≤ 100
0 ≤ x ≤ y ≤ 100


Example:

Input
2
4
1 3 2 4 6 8 9 10
4
6 8 1 9 2 4 4 7

Output
1 4 6 8 9 10
1 9