package cci.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.util.*

class StackSortTest : StringSpec({
    "Stack Sort" {
        forAll(
            row(intArrayOf(2, 5, 3, 4)),
            row(intArrayOf(1, 2, 3, 4)),
            row(intArrayOf())
        ) { arr ->
            val stk = Stack<Int>()
            arr.forEach { stk.push(it) }
            val sortedStk = stk.sortWithStack()
            arr.sort()
            arr.forEach { it shouldBe sortedStk.pop() }
        }
    }
})
