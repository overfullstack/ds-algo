http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
http://www.practice.geeksforgeeks.org/problem-page.php?pid=367

Minimum Number of Platforms Required for a Railway/Bus Station Given arrival and departure times of
all trains that reach a railway station, find the minimum number of platforms required for the
railway station so that no train waits. We are given two arrays which represent arrival and
departure times of trains that stop

Examples:

Input:  arr[]  = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00} dep[]  = {9:10, 12:00, 11:20, 11:30, 19:00,
20:00} Output: 3 There are at-most three trains at a time (time between 11:00 to 11:20)

Given arrival and departure times of all trains that reach a railway station, find the minimum
number of platforms required for the railway station so that no train waits.

Input:

First line will contain a number T, the number of test cases. Each test case will contain a number
N, the number of trains. Next two lines will consist of N space separated time intervals denoting
arrival and departure times respectively. NOTE: Time intervals are in 24 hour format(hhmm),preceding
zeros are insignificant. Consider the example for better understanding of input.

Output:

Print the required answer in separated line.

Constraints:

1<=T<=80 1<=N<=5000

1<=A[i]<=5000

Example:

INPUT:

1 6 900 940 950 1100 1500 1800 910 1200 1120 1130 1900 2000

OUTPUT:

3
