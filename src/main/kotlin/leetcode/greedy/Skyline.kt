package leetcode.greedy

data class BuildingStrip(val pt: Int, val ht: Int, val isStart: Boolean)

fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
    val buildingStrips =
        buildings.flatMap { listOf(BuildingStrip(it[0], it[2], true), BuildingStrip(it[1], it[2], false)) }
    val sortedBuildingStrips = buildingStrips.sortedWith(compareBy({ it.pt }, { if (it.isStart) -it.ht else it.ht }))
    val map = sortedMapOf(0 to 1)

    var prevMaxHt = 0
    val results = mutableListOf<List<Int>>()
    for (buildingStrip in sortedBuildingStrips) {
        when {
            buildingStrip.isStart -> map.merge(buildingStrip.ht, 1) { old, _ -> old.inc() }
            else -> map.merge(buildingStrip.ht, 1) { old, _ -> if (old == 1) null else old.dec() }
        }
        val curMaxHt = map.lastKey()
        if (prevMaxHt != curMaxHt) {
            results.add(listOf(buildingStrip.pt, buildingStrip.ht))
            prevMaxHt = curMaxHt
        }
    }
    return results
}
