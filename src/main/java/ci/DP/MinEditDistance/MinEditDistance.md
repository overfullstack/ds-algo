Question 32 Implement a function that finds the edit distance of two input strings. There are three types of edit operations: insertion, deletion, and substitution. Edit distance is the minimal number of edit operations to modify a string from one state to the other.
For example, the edit distance between “Saturday” and “Sunday” is 3 since the following three edit operations are required to modify one into another:
(1) Saturday → Sturday (deletion of ‘a’)
(2) Sturday → Surday (deletion of ‘t’)
(3) Surday → Sunday (substitution of ‘n’ for ‘r’)
There is no way to achieve it with fewer than three operations.

When the jth character of the first string is different from the ith character of the second string, there are three options available:
• Insert the ith character of the second string into the first string. In this case, f(i,j)=f(i-1,j)+1.
• Delete the jth character of the first string. In this case, f(i,j)=f(i,j-1)+1.
• Replace the jth character of the first string with the ith character of the second
string. In this case, f(i,j)=f(i-1,j-1)+1.