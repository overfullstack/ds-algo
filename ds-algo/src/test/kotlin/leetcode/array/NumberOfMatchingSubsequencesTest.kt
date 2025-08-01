package leetcode.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/* 01 Aug 2025 14:05 */

class NumberOfMatchingSubsequencesTest :
  StringSpec({
    "number of matching-subsequences" {
      numMatchingSubseq(
        "rwpddkvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvjubjgloeofnpjqlkdsqvruvabjrikfwronbrdyyjnakstqjac",
        arrayOf(
          "wpddkvbnn",
          "lnagtva",
          "kvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvju",
          "rwpddkvbnnugln",
          "gloeofnpjqlkdsqvruvabjrikfwronbrdyyj",
          "vbgeinupkvgmgxeaaiuiyojmoqkahwvbpwugdainxciedbdkos",
          "mspuhbykmmumtveoighlcgpcapzczomshiblnvhjzqjlfkpina",
          "rgmliajkiknongrofpugfgajedxicdhxinzjakwnifvxwlokip",
          "fhepktaipapyrbylskxddypwmuuxyoivcewzrdwwlrlhqwzikq",
          "qatithxifaaiwyszlkgoljzkkweqkjjzvymedvclfxwcezqebx",
        ),
      ) shouldBe 5
    }
  })
