http://www.practice.geeksforgeeks.org/problem-page.php?pid=1572

Given a snake and ladder board of order 5x6 , find the minimum number of dice throws required to reach the destination or last cell (30th cell) from source (1st cell) . 

Example
![Snakes & Ladders](../../../../../../../images/snakesladders.jpg)


​For the above board output will be 3 
For 1st throw get a 3
For 2nd throw get a 6
For 3rd throw get a 2

Input:
The first line of input contains an integer T denoting the no of test cases. Then T test cases follow. Each test case contains two lines. The first line of input contains an integer N denoting the no of ladders and snakes present. Then in the next line are 2*N space separated values a,b which denotes a ladder or a snake at position 'a' which takes to a position 'b'.

Output:
For each test case in a new line print the required answer denoting the min no of dice throws.

Constraints:
1<=T<=100
1<=N<=10
1<=a<30
1<=b<=30

Example:
Input:
2
6
11 26 3 22 5 8 20 29 27 1 21 9
1
2 30
Output:
3
1