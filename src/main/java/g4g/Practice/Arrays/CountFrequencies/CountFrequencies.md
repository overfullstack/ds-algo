http://www.geeksforgeeks.org/freq-frequencies-elements-array-o1-extra-space-time/
http://www.practice.geeksforgeeks.org/problem-page.php?pid=84

Count frequencies of all elements in array in O(1) extra space and O(n) time
Given an unsorted array of n integers which can contain integers from 1 to n.
Some elements can be repeated multiple times and some other elements can be absent from the array.
Count frequency of all elements that are present and print the missing elements.

Examples:

Input: arr[] = {2, 3, 3, 2, 5}
Output: Below are frequencies of all elements
        1 -> 0
        2 -> 2
        3 -> 2
        4 -> 0
        5 -> 1

Input: arr[] = {4, 4, 4, 4}
Output: Below are frequencies of all elements
        1 -> 0
        2 -> 0
        3 -> 0
        4 -> 4
        

Frequency of Array Elements
array  
Given an unsorted array of N integers which can contain integers from 1 to N. Some elements can be repeated multiple times and some other elements can be absent from the array. Count frequency of all elements from 1 to N.

Input:

The first line contains an integer 'T' denoting the total number of test cases. In each test cases, the first line contains an integer 'N' denoting the size of array. The second line contains N space-separated integers A1, A2, ..., AN denoting the elements of the array.

Output:

For each test case output N space-separated integers denoting the frequency of each element from 1 to N.

Constraints:

1 ≤ T ≤ 100

1 ≤ N ≤ 100

Example:

Input:
2
5
2 3 2 3 5
4
3 3 3 3

Output:
0 2 2 0 1
0 0 4 0