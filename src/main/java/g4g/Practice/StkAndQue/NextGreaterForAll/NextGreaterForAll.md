http://www.geeksforgeeks.org/next-greater-element/
http://www.practice.geeksforgeeks.org/problem-page.php?pid=1095

Next Greater Element
Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an element x is the first greater element on the right side of x in array. Elements for which no greater element exist, consider next greater element as -1.

Examples:
a) For any array, rightmost element always has next greater element as -1.
b) For an array which is sorted in decreasing order, all elements have next greater element as -1.
c) For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.

Element       NGE
   4      -->   5
   5      -->   25
   2      -->   25
   25     -->   -1
d) For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.

  Element        NGE
   13      -->    -1
   7       -->     12
   6       -->     12
   12     -->     -1

   ---

   Given an array A [ ] having distinct elements, the task is to find the next greater element for each element of the array in order of their appearance in the array. If no such element exists, output -1

   Input:
   The first line of input contains a single integer T denoting the number of test cases.Then T test cases follow. Each test case consists of two lines. The first line contains an integer N denoting the size of the array. The Second line of each test case contains N space separated positive integers denoting the values/elements in the array A[ ].

   Output:
   For each test case, print in a new line, the next greater element for each array element separated by space in order.

   Constraints:
   1<=T<=100
   1<=N<=1000
   1<=A[i]<=1000

   Example:
   Input
   1
   4
   1 3 2 4
   14
   10 3 12 4 2 9 13 0 8 11 1 7 5 6

   Output
   3 4 4 -1
   12 12 13 9 9 13 -1 8 11 -1 7 -1 6 -1

   Explanation
   In the array, the next larger element to 1 is 3 , 3 is 4 , 2 is 4 and for 4 ? since it doesn't exist hence -1.