Given a Binary Search Tree (BST) and a positive integer k, find the k’th largest element in the
Binary Search Tree.

For example, in the following BST, if k = 3, then output should be 14, and if k = 5, then output
should be 10.

![Graph](../../../../../images/BST.jpg)

We have discussed two methods in this post. The method 1 requires O(n) time. The method 2 takes O(h)
time where h is height of BST, but requires augmenting the BST (storing freq of nodes in left
subtree with every node).

Can we find k’th largest element in better than O(n) time and no augmentation?
