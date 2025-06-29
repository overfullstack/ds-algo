package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

/* 31 Aug 2024 11:54 */

class AlienDictionaryTest :
  StringSpec({
    "order alien dictionary" {
      forAll(
        row(
          listOf(
            "mzosr",
            "mqov",
            "xxsvq",
            "xazv",
            "xazau",
            "xaqu",
            "suvzu",
            "suvxq",
            "suam",
            "suax",
            "rom",
            "rwx",
            "rwv",
          ),
          "mzouqxwsvra",
        ),
        row(
          listOf("vanilla", "alpine", "algor", "port", "norm", "nylon", "ophellia", "hidden"),
          "vilertmdapgnoyh",
        ),
        row(listOf("passengers", "to", "the", "unknown"), "pasengrokwthu"),
        row(listOf("alpha", "bravo", "charlie", "delta"), "alphrvoietbcd"),
        row(listOf("jupyter", "ascending"), "jupyterscndiga"),
        row(listOf("ba", "ba", "ba"), "ba"),
      ) { words, result ->
        orderAlienDictionary(words) shouldBe result
      }
    }
  })
