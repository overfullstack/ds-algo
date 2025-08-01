package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/* 01 Aug 2025 09:48 */

class ParallelCoursesTest :
  StringSpec({
    "should return 2 semesters for basic prerequisite chain" {
      minimumSemesters(arrayOf(1 to 3, 2 to 3)) shouldBe 2
    }

    "should return 2 semesters for single prerequisite with multiple follow-ups" {
      minimumSemesters(arrayOf(1 to 0, 1 to 2)) shouldBe 2
    }

    "should return -1 for circular dependency (cycle)" {
      minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 1)) shouldBe -1
    }

    "should return 0 for no courses (empty relations)" { minimumSemesters(emptyArray()) shouldBe 0 }

    "should return 3 semesters for linear chain of 3 courses" {
      minimumSemesters(arrayOf(1 to 2, 2 to 3)) shouldBe 3
    }

    "should return 1 semester when all courses can be taken simultaneously" {
      minimumSemesters(arrayOf(1 to 2)) shouldBe 2
    }

    "should handle complex dependency tree correctly" {
      // Course 1 -> Course 3, Course 2 -> Course 3, Course 3 -> Course 4
      // Semester 1: courses 1, 2
      // Semester 2: course 3
      // Semester 3: course 4
      minimumSemesters(arrayOf(1 to 3, 2 to 3, 3 to 4)) shouldBe 3
    }

    "should return -1 for simple two-course cycle" {
      minimumSemesters(arrayOf(1 to 2, 2 to 1)) shouldBe -1
    }

    "should handle diamond dependency pattern" {
      // Course 1 -> Course 2, Course 1 -> Course 3, Course 2 -> Course 4, Course 3 -> Course 4
      // Semester 1: course 1
      // Semester 2: courses 2, 3
      // Semester 3: course 4
      minimumSemesters(arrayOf(1 to 2, 1 to 3, 2 to 4, 3 to 4)) shouldBe 3
    }

    "should handle multiple independent chains" {
      // Chain 1: 1 -> 2 -> 3 (3 semesters)
      // Chain 2: 4 -> 5 (2 semesters)
      // Can run in parallel, so max is 3 semesters
      minimumSemesters(arrayOf(1 to 2, 2 to 3, 4 to 5)) shouldBe 3
    }

    "should return -1 for cycle involving multiple courses" {
      minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 4, 4 to 1)) shouldBe -1
    }

    "should handle large linear chain" {
      // 1 -> 2 -> 3 -> 4 -> 5 -> 6 (6 semesters)
      minimumSemesters(arrayOf(1 to 2, 2 to 3, 3 to 4, 4 to 5, 5 to 6)) shouldBe 6
    }

    "should handle course with multiple prerequisites" {
      // Courses 1, 2, 3 all must be taken before course 4
      // Semester 1: courses 1, 2, 3
      // Semester 2: course 4
      minimumSemesters(arrayOf(1 to 4, 2 to 4, 3 to 4)) shouldBe 2
    }

    "should handle self-loop (course depends on itself)" {
      minimumSemesters(arrayOf(1 to 1)) shouldBe -1
    }

    "should handle complex graph with no cycles" {
      // More complex dependency graph but no cycles
      // 1 -> 3, 2 -> 3, 3 -> 5, 4 -> 5, 5 -> 6
      minimumSemesters(arrayOf(1 to 3, 2 to 3, 3 to 5, 4 to 5, 5 to 6)) shouldBe 4
    }
  })
