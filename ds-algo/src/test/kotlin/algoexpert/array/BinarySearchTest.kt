package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BinarySearchTest :
  StringSpec({
    "rightmost binary search" {
      forAll(
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 33, 3),
        row(intArrayOf(1, 5, 23, 111), 111, 3),
        row(intArrayOf(1, 5, 23, 111), 120, -1),
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 73, 9),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 7, 6),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7), 7, 9),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7), 10, -1),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7), 0, -1),
      ) { intArray, valueToSearch, result ->
        intArray.binarySearchRightmost(valueToSearch) shouldBe result
      }
    }
    "leftmost binary search" {
      forAll(
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 33, 3),
        row(intArrayOf(1, 5, 23, 111), 111, 3),
        row(intArrayOf(1, 1, 1, 5, 23, 111), 1, 0),
        row(intArrayOf(1, 5, 23, 111), 120, -1),
        row(intArrayOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 73, 9),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 7, 6),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7), 7, 6),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7), 10, -1),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7), 0, -1),
      ) { intArray, valueToSearch, result ->
        intArray.binarySearchLeftmost(valueToSearch) shouldBe result
      }
    }
  })
