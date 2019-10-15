/* gakshintala created on 9/4/19 */
package tree

import queryoptmization.SparseTableRMQ

data class NArrayEulerTour(var value: Int) {
    private val children: MutableList<NArrayEulerTour?> = mutableListOf()
    private var parent: NArrayEulerTour? = null
    var depth: Int = 0
    var firstOccurrence: Int? = null

    fun addEdge(childNode: NArrayEulerTour?) {
        childNode?.parent = this
        childNode?.depth = this.depth + 1
        children.add(childNode)
    }

    fun eulersTour(): Array<NArrayEulerTour> {
        return eulersTour(0)
    }

    private fun eulersTour(counter: Int): Array<NArrayEulerTour> {
        firstOccurrence = firstOccurrence ?: counter
        return arrayOf(this) + children.fold(
            emptyArray(),
            { tourResult, child ->
                tourResult + (child?.eulersTour(counter + tourResult.size + 1) ?: emptyArray()) + this
            })
    }
}

fun lcaNNodes(
    nodes: List<Int>,
    treeGraph: Map<Int, NArrayEulerTour>,
    sparseTableRMQ: SparseTableRMQ,
    eulersTourArr: Array<NArrayEulerTour>
): Int {
    return nodes.reduce { first, second ->
        eulersTourArr[sparseTableRMQ.rmqIndex(
            treeGraph[first]?.firstOccurrence!!,
            treeGraph[second]?.firstOccurrence!!
        )].value
    }
}

fun main() {
    val delimiters = " "
    val noOfEdges = readLine()!!.toInt()
    val treeGraph = mutableMapOf<Int, NArrayEulerTour>()
    readLine()!!.trim().split(delimiters)
        .map { it.toInt() }
        .forEachIndexed { index, value ->
            treeGraph.getOrPut(value, { NArrayEulerTour(value) })
                .addEdge(treeGraph.getOrPut(index + 1, { NArrayEulerTour(index + 1) }))
        }
    val noOfNodes = readLine()!!.trim().toInt()
    val nodes = readLine()!!.trim().split(delimiters).map { it.toInt() }

    val eulersTourArr = treeGraph[0]?.eulersTour()
    val depthArr = eulersTourArr!!.map { it.depth }.toIntArray()

    val sparseTableRMQ = SparseTableRMQ(depthArr)
    sparseTableRMQ.process()

    println(lcaNNodes(nodes, treeGraph, sparseTableRMQ, eulersTourArr))
}
