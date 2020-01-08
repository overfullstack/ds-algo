###Input Format
First line contains two space separated integers, M, N, where M is the number of rows, N is number of columns in 
matrix
Then M lines follow, where each line contains N space separated positive integers. These M lines represent the matrix.
####Sample Input #01
`3 4`<br/>
`0 1 0 0`<br/>
`0 0 1 0`<br/>
`1 1 0 1`<br/>

###Output Format
Print all the possible output paths in format [i,j] -> [k,l] -> ....
###Sample Output #01
`Door In: [2,0] ***************************`<br/>
 `[2,0] -> [2,1] -> [1,2] -> [2,3] -> Out!!!`<br/>
 `[2,0] -> [2,1] -> [1,2] -> [0,1] -> Out!!!`<br/>
 `[2,0] -> [2,1] -> Out!!!`<br/>
 `Door In: [2,3] ***************************`<br/>
 `[2,3] -> [1,2] -> [2,1] -> [2,0] -> Out!!!`<br/>
 `[2,3] -> [1,2] -> [0,1] -> Out!!!`<br/>
 `[2,3] -> [1,2] -> [2,1] -> Out!!!`<br/>
 `Door In: [0,1] ***************************`<br/>
 `[0,1] -> [1,2] -> [2,1] -> [2,0] -> Out!!!`<br/>
 `[0,1] -> [1,2] -> [2,3] -> Out!!!`<br/>
 `[0,1] -> [1,2] -> [2,1] -> Out!!!`<br/>
 `Door In: [2,1] ***************************`<br/>
 `[2,1] -> [2,0] -> Out!!!`<br/>
 `[2,1] -> [1,2] -> [2,3] -> Out!!!`<br/>
 `[2,1] -> [1,2] -> [0,1] -> Out!!!`<br/>

