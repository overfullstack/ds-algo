https://www.geeksforgeeks.org/topological-sorting/
https://practice.geeksforgeeks.org/problems/topological-sort/1

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one topological sorting for a graph. For example, another topological sorting of the following graph is “4 5 2 3 1 0”. The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with no in-coming edges).

![Topology](../../../../../../../images/topologicalSort.png)

Topological Sorting vs Depth First Traversal (DFS):
In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. 
In topological sorting, we need to print a vertex before its adjacent vertices. 
For example, in the given graph, the vertex ‘5’ should be printed before vertex ‘0’, but unlike DFS, the vertex ‘4’ should also be printed before vertex ‘0’. 
So Topological sorting is different from DFS. For example, a DFS of the shown graph is “5 2 3 1 0 4”, but it is not a topological sorting


Given a directed graph you need to complete the function topoSort which returns an array having the topologically sorted elements of the array and takes two arguments . The first argument is the Graph graph represented as adjacency list and the second is the number of vertices N .

Note : There can be multiple topological sorts of a Graph.  The driver program that calls your function doesn't match your output element by element, but checks whether the output produced by your function is a valid topological sort or not.

Input:
The first line of input takes the no of test cases then T test cases follow . Each test case contains two lines . The first  line of each test case  contains two integers E and N representing no of edges and the no of vertices . Then in the next line are E  pairs of integers u v representing an edge from u to v in the graph.

Output:
For each test case output will be 1 if the topological sort is done correctly else it will be 0 .

Constraints:
1<=T<=50
1<=E,N<=50
0<=u,v

Example:

Input
1
6 6 
5 0 5 2 2 3 4 0 4 1 1 3

Output
1

The output 1 denotes that the order is valid.  So if you have implemented your function correctly, then output would be 1 for all test cases.