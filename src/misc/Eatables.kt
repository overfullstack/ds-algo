/* gakshintala created on 9/22/19 */
package misc

fun waysToEat(n: Int): Int {
    if(n<=0) 
        return 2
    if(n==1) 
        return 3

    return waysToEat(n-1) + waysToEat(n-2) + waysToEat(n-3)
}

fun main() {
    print(waysToEat(3))
}