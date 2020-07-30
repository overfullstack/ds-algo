http://www.geeksforgeeks.org/validity-of-a-given-tic-tac-toe-board-configuration/ 

A Tic-Tac-Toe board is given after some moves are played. Find out if the given board is valid, i.e., is it possible to reach this board position after some moves or not.

Note that every arbitrary filled grid of 9 spaces isn’t valid e.g. a grid filled with 3 X and 6 O isn’t valid situation because each player needs to take alternate turns.

tictactoe

Input is given as a 1D array of size 9.

Input: board[] =  {'X', 'X', 'O', 
                   'O', 'O', 'X',
                   'X', 'O', 'X'};
Output: Valid

Input: board[] =  {'O', 'X', 'X', 
                   'O', 'X', 'X',
                   'O', 'O', 'X'};
Output: Invalid
(Both X and O cannot win)

Input: board[] =  {'O', 'X', ' ', 
                   ' ', ' ', ' ',
                   ' ', ' ', ' '};
Output: Valid
(Valid board with only two moves played)

