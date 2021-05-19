http://www.practice.geeksforgeeks.org/problem-page.php?pid=433
http://www.geeksforgeeks.org/maximum-product-subarray/

Maximum Product Subarray Given an array that contains both positive and negative integers, find the
product of the maximum product subarray. Expected Time complexity is O(n) and only O(1) extra space
can be used.

Examples:

Input: arr[] = {6, -3, -10, 0, 2} Output:   180 // The subarray is {6, -3, -10}

Input: arr[] = {-1, -3, -10, 0, 60} Output:   60 // The subarray is {60}

Input: arr[] = {-2, -3, 0, -2, -40} Output:   80 // The subarray is {-2, -40}

Given an array that contains both positive and negative integers, find the product of the maximum
product subarray. Assumption: There is always a positive product possible, i.e., no array of this
form: {0,-20,0,0} or {-20}.

Input:
First line of input contain number of test cases T. First line of test case contain the size of
array and second line of test case contain the array elements.

Output:
Maximum product of subarray is displayed to the user.

Constraints:
1 <=T<= 50 1 <=N<= 9 -10 <=arr[i]<= 10

Example:

Input:

3 5 6 -3 -10 0 2 6 2 3 4 5 -1 0 10 8 -2 -2 0 8 0 -6 -8 -6 -1

Output:
180 120 288
