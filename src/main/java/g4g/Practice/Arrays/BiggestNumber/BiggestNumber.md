https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
https://practice.geeksforgeeks.org/problems/largest-number-formed-from-an-array/0

Given an array of numbers, arrange them in a way that yields the largest value. 
For example, if the given numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the largest value. 
And if the given numbers are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives the largest value.

Given a list of non negative integers, arrange them in such a manner that they form the largest number possible.

The result is going to be very large, hence return the result in the form of a string.

Input:

The first line of input consists number of the test cases. The description of T test cases is as follows:

The first line of each test case contains the size of the array, and the second line has the elements of the array.


Output:

In each separate line print the largest number formed by arranging the elements of the array in the form of a string.


Constraints:

1 ≤ T ≤ 70
1 ≤ N ≤ 100
0 ≤ A[i] ≤ 1000


Example:

Input:

2
5
3 30 34 5 9
4
54 546 548 60

Output:

9534330
6054854654
