http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
http://www.practice.geeksforgeeks.org/problem-page.php?pid=700437

Duplicates in an array in O(n) and by using O(1) extra space | Set-2 Given an array of n elements
which contains elements from 0 to n-1, with any of these numbers appearing any number of times. Find
these repeating numbers in O(n) and using only constant memory space.

For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer should be 1, 3 and 6. We have
discussed an approach for this question in below post:
Duplicates in an array in O(n) and by using O(1) extra space | Set-2. There is a problem in above
approach. It prints the repeated number more than once. For example: {1, 6, 3, 1, 3, 6, 6} it will
give output as : 3 6 6. So, in this article, another method is discussed to overcome this problem.

---

Given an array A[], Your task is to complete the function printDuplicates which prints the duplicate
elements of the given array. If no duplicate element is found print -1.

Note: Auxiliary Space must be O(1) and time complexity must be O(n).

Input:
The first line of input contains an integer T denoting the no of test cases. Then T test cases
follow. Each test case contains an integer N which denotes number of elements of Array. Second line
of each test case contains N space separated integers denoting elements of Array A[].

Output:
Print the duplicate elements from the array.

Constraints:
1<=T<=100 1<=N<=50 0<=A[]<=N

Example:
Input:
2 4 0 3 1 2 5 2 3 1 2 3 Output:
-1 2 3 
