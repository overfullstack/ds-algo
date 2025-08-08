package leetcode.greedy

/** [218. The Skyline Problem](https://leetcode.com/problems/the-skyline-problem) */
fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
  val buildingStrips =
    buildings.flatMap {
      listOf(BuildingStrip(it[0], it[2], true), BuildingStrip(it[1], it[2], false))
    }
  val sortedBuildingStrips =
    buildingStrips.sortedWith(compareBy({ it.pt }, { if (it.isStart) -it.ht else it.ht }))

  var prevMaxHt = 0
  // ! Sorted map, groups buildings of similar height together.
  // ! We increment count for start and decrement for end
  val heightToCount = sortedMapOf(0 to 1)
  return sortedBuildingStrips.fold(emptyList()) { skyline, buildingStrip ->
    when {
      buildingStrip.isStart -> heightToCount.merge(buildingStrip.ht, 1, Int::plus)
      else ->
        heightToCount.merge(buildingStrip.ht, 1) { old, _ -> if (old == 1) null else old.dec() }
    }
    val curMaxHt = heightToCount.lastKey() // ! `lastKey()` returns the largest key in `sortedMap`
    when {
      prevMaxHt != curMaxHt -> {
        prevMaxHt = curMaxHt
        skyline.plusElement(listOf(buildingStrip.pt, buildingStrip.ht))
      }
      else -> skyline
    }
  }
}

data class BuildingStrip(val pt: Int, val ht: Int, val isStart: Boolean)
