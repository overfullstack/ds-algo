http://www.geeksforgeeks.org/trapping-rain-water/
http://practice.geeksforgeeks.org/problems/trapping-rain-water/0

Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

Examples:

Input: arr[]   = {2, 0, 2} Output: 2 Structure is like below | | |_| We can trap 2 units of water in
the middle gap.

Input: arr[]   = {3, 0, 0, 2, 0, 4} Output: 10 Structure is like below | | | | | | |__|_| We can
trap "3*2 units" of water between 3 an 2,
"1 unit" on top of bar 2 and "3 units" between 2 and 4. See below diagram also.

Input: arr[] = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
Output: 6 | | || |
_|_||_|||||| Trap "1 unit" between first 1 and 2, "4 units" between first 2 and 3 and "1 unit"
between second last 1 and last 2

Example
![WaterTrap](../../../../../images/watertrap.png)

Given n non-negative integers in array representing an elevation map where the width of each bar is
1, compute how much water it is able to trap after raining. For example:
Input:
3 2 0 2 Output:
2 Structure is like below | | |_| We can trap 2 units of water in the middle gap.

Input:
The first line of input contains an integer T denoting the number of test cases. The description of
T test cases follows. Each test case contains an integer N followed by N numbers to be stored in
array.

Output:
Print trap units of water in the middle gap.

Constraints:
1<=T<=100 3<=N<=100 0<=Arr[i]<10

Example:
Input:
2 4 7 4 0 9 3 6 9 9

Output:
10 0
