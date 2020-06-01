https://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
https://practice.geeksforgeeks.org/problems/connect-nodes-at-same-level/1

Connect nodes at same level using constant extra space
Write a function to connect all the adjacent nodes at the same level in a binary tree.

Write a function to connect all the adjacent nodes at the same level in a binary tree. Structure of the given Binary Tree node is like following.


struct Node{

  int data;

  Node* left;

  Node* right;

  Node* nextRight;

}


Initially, all the nextRight pointers point to garbage values. Your function should set these pointers to point next right for each node.

Example:

Input Tree
       10
      / \
     3   5
    / \   \
   4   1   2


Output Tree
       10--->NULL
      / \
     3-->5-->NULL
    / \   \
   4-->1-->2-->NULL


Input:

The task is to complete the method which takes one argument, root of Binary Tree. There are multiple test cases. For each test case, this method will be called individually.

Output:
The function should update nextRight pointers of all nodes.

Constraints:
1 <=T<= 30
1 <=Number of nodes<= 100
1 <=Data of a node<= 1000

Example:
Input:
2
2
3 1 L 3 2 R
4
10 20 L 10 30 R 20 40 L 20 60 R

Output:
3 1 2
1 3 2
10 20 30 40 60
40 20 60 10 30

There are two test casess.  First case represents a tree with 3 nodes and 2 edges where root is 3, left child of 3 is 1 and right child of 3 is 2.   Second test case represents a tree with 4 edges and 5 nodes.

The output contains level order and inorder traversals of the modified tree.  Level order traversal is done using nextRight pointers.