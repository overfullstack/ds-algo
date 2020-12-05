https://www.geeksforgeeks.org/freq-bst-subtrees-that-lie-in-given-range/
https://practice.geeksforgeeks.org/problems/freq-bst-nodes-that-lie-in-a-given-range/1/?ref=self

Given a Binary Search Tree (BST) of integer values and a range [low, high], return freq of nodes where all the nodes under that node (or subtree rooted with that node) lie in the given range.

Examples:

Input:
        10
      /    \
    5       50
   /       /  \
 1       40   100
Range: [5, 45]
Output:  1
There is only 1 node whose subtree is in the given range.
The node is 40


Input:
        10
      /    \
    5       50
   /       /  \
 1       40   100
Range: [1, 45]
Output:  3
There are three nodes whose subtree is in the given range.
The nodes are 1, 5 and 40

Given a Binary Search Tree (BST) and a range, freq the number of nodes in the BST that lie in the given range. You are required to complete the function getCountOfNode. You should not read any input from stdin/console. There are multiple test cases. For each test case, this method will be called individually.


Input (only to be used for Expected Output):
The first line of the input contains an integer 'T' denoting the number of test cases. Then 'T' test cases follow. Each test case consists of three lines. Description of  test cases is as follows:
The First line of each test case contains an integer 'N' which denotes the no of nodes in the BST.   .
The Second line of each test case contains 'N' space separated values of the nodes in the BST.
The Third line of each test case contains two space separated integers 'l' and 'h' denoting the range, where l < h

Output:
You are required to complete the function getCountOfNode which takes three arguments. The first being the root of the tree, and then two integers l and h, denoting the range. The function returns an integer denoting the no of nodes in the given range.

Constraints:
1 <= T <= 50
1 <= N <= 50
1 <= l < h < 1000

Example:
Input
1
6
10 5 50 1 40 100
5 45

Output
3