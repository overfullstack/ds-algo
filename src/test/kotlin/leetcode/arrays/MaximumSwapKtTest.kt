package leetcode.arrays

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MaximumSwapKtTest : StringSpec() {

    init {
        "maximumSwap1" { 
            maximumSwap(2736) shouldBe 7236
        }
        
        "maximumSwap2" {
            maximumSwap(9973) shouldBe 9973
        }

        "maximumSwap3" {
            maximumSwap(10) shouldBe 10
        }
    }

}