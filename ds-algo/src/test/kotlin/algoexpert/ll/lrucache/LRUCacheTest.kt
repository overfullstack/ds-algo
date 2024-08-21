package algoexpert.ll.lrucache

import algoexpert.ll.lrucache.LRUCacheArgs.Companion.parseJsonFileToLRUCacheArgs
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "algoexpert/ll/LRUCache"

class LRUCacheTest :
  StringSpec({
    "lru cache" {
      forAll(
        row(
          parseJsonFileToLRUCacheArgs("$PKG_PATH/lru-cache-args1.json"),
          listOf("c", "1", "a", null, "5") to listOf("a" to 5, "d" to 4, "c" to 3)
        ),
        row(
          parseJsonFileToLRUCacheArgs("$PKG_PATH/lru-cache-args2.json"),
          listOf(null, "1", "9001", null, "2", null, null, "3") to listOf("c" to 3)
        ),
        row(
          parseJsonFileToLRUCacheArgs("$PKG_PATH/lru-cache-args3.json"),
          listOf("1", "2", "3", "4", null, "2", "3", "4", "5") to
            listOf("e" to 5, "d" to 4, "c" to 3, "b" to 2)
        ),
      ) { lruArgs, result ->
        lruCache(lruArgs) shouldBe result
      }
    }
  })
