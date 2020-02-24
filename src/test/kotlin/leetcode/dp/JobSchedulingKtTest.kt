package leetcode.dp

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class JobSchedulingKtTest : StringSpec() {

    init {
        "jobScheduling" {
            forall(
                row(intArrayOf(1, 2, 3, 3), intArrayOf(3, 4, 5, 6), intArrayOf(50, 10, 40, 70), 120),
                row(intArrayOf(1, 2, 3, 4, 6), intArrayOf(3, 5, 10, 6, 9), intArrayOf(20, 20, 100, 70, 60), 150),
                row(intArrayOf(1, 1, 1), intArrayOf(2, 3, 4), intArrayOf(5, 6, 4), 6)
            ) { startTimeArr, endTimeArr, profitArr, result ->
                jobScheduling(startTimeArr, endTimeArr, profitArr) shouldBe result
            }
        }
    }

}
