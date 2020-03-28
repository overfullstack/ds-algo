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
        // this is both prepended and appended, counter is to check for firstOccurrence
        return arrayOf(this) + children.fold(
            emptyArray<NArrayEulerTour>(),
            { tourResult, child ->
                tourResult + (child?.eulersTour(counter + tourResult.size + 1) ?: emptyArray()) + this
            })
    }
}

fun lcaMultipleNodes(
    nodes: List<Int>,
    treeGraph: Map<Int, NArrayEulerTour>,
    depthsSparseTableRMQ: SparseTableRMQ,
    eulersTourArr: Array<NArrayEulerTour>
) = nodes.reduce { first, second ->
    eulersTourArr[depthsSparseTableRMQ.rmqIndex( // Find min depth in the range. DepthArr is in same sequence as eulerTourArr.
        treeGraph[first]?.firstOccurrence!!,
        treeGraph[second]?.firstOccurrence!!
    )].value
}


fun main() {
    val delimiters = " "
    readLine()!!.toInt() // val noOfEdges
    val treeGraph = mutableMapOf<Int, NArrayEulerTour>()
    readLine()!!.trim().split(delimiters)
        .map { it.toInt() }
        .forEachIndexed { index, value ->
            treeGraph.getOrPut(value, { NArrayEulerTour(value) })
                .addEdge(treeGraph.getOrPut(index + 1, { NArrayEulerTour(index + 1) }))
        }
    readLine()!!.trim().toInt() // val noOfNodes
    val nodes = readLine()!!.trim().split(delimiters).map { it.toInt() }

    val eulersTourArr = treeGraph[0]?.eulersTour()
    val depthArr = eulersTourArr!!.map { it.depth }.toIntArray()

    val depthsSparseTableRMQ = SparseTableRMQ(depthArr)
    depthsSparseTableRMQ.process()

    println(lcaMultipleNodes(nodes, treeGraph, depthsSparseTableRMQ, eulersTourArr))
}
