http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
http://www.practice.geeksforgeeks.org/problem-page.php?pid=392

Longest Palindromic Substring | Set 1
Given a string, find the longest substring which is palindrome.
For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.

Given a string S, find the longest palindromic substring in S.

Substring of string S:

S[ i . . . . j ] where 0 ≤ i ≤ j < len(S)

Palindrome string:

A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S.

Incase of conflict, return the substring which occurs first ( with the least starting index ).

Input:

The first line of input consists number of the test cases. The following T lines consist of a string each.


Output:

In each separate line print the longest palindrome of the string given in the respective test case.


Constraints:

1 ≤T≤ 70
1 ≤ str≤ length of string


Example:

Input:

1
aaaabbaa

Output:

aabbaa