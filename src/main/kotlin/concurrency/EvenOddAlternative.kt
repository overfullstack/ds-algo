/* gakshintala created on 5/27/20 */
package concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private val lock = java.lang.Object()

internal class Printer {
    private var printOddNow = true // Switch-2, this is common switch and gets switched by threads

    @Synchronized
    fun printOdd(numToPrint: Int) {
        while (!printOddNow) {
            try {
                lock.wait()
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
        println("Odd: $numToPrint")
        printOddNow = false
        lock.notifyAll()
    }

    @Synchronized
    fun printEven(numToPrint: Int) {
        while (printOddNow) {
            try {
                lock.wait()
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
        println("Even: $numToPrint")
        printOddNow = true
        lock.notifyAll()
    }
}

fun main() = runBlocking(Dispatchers.Default) {
    val max = 10
    val printer = Printer()
    
}
