package testcase

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class TestCasesTest :
  StringSpec({
    "decodes numbered slots into caller-requested types" {
      val raw =
        """{"testcases":[{"name":"t","inputs":[{"1":[1,2,3]},{"2":2}],"output":[{"1":6}]}]}"""
      val cases = TestCases.json.decodeFromString<TestCases>(raw).testcases
      cases shouldHaveSize 1
      val case = cases.first()
      case.name shouldBe "t"
      case.input<List<Int>>(1) shouldBe listOf(1, 2, 3)
      case.input<Int>(2) shouldBe 2
      case.output<Int>(1) shouldBe 6
    }

    "load reads a fixture file and flattens testcases" {
      val cases = TestCases.load("educative/fusion/MeetingRooms2/test-cases-1.json")
      cases shouldHaveSize 5
      // ListPairToInt-shaped: input slot 1 is List<List<Int>>, output slot 1 is Int
      cases.first().input<List<List<Int>>>(1).first() shouldBe listOf(2, 8)
      cases.first().output<Int>(1) shouldBe 3
    }
  })
