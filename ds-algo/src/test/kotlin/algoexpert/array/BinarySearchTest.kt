package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BinarySearchTest :
  StringSpec({
    "binary search iterative" {
      forAll(
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 33, 3),
        row(intArrayOf(1, 5, 23, 111), 111, 3),
        row(intArrayOf(1, 5, 23, 111), 120, -1),
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 73, 9),
      ) { intArray, valueToSearch, result ->
        intArray.binarySearchRightMost(valueToSearch) shouldBe result
      }
    }
    "binary search recursive" {
      forAll(
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 33, 3),
        row(intArrayOf(1, 5, 23, 111), 111, 3),
        row(intArrayOf(1, 5, 23, 111), 120, -1),
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 73, 9),
      ) { intArray, valueToSearch, result ->
        intArray.binarySearchLeftMost(valueToSearch) shouldBe result
      }
    }
  })
