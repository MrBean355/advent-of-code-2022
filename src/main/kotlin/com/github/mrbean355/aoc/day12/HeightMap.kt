package com.github.mrbean355.aoc.day12

import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge

class HeightMap(
    private val heights: List<Char>,
    private val width: Int,
    private val startIndex: Int,
    private val end: Int,
) {
    private val graph = DefaultDirectedGraph<Int, DefaultEdge>(DefaultEdge::class.java)

    init {
        heights.indices.forEach(graph::addVertex)
        heights.forEachIndexed { index, height ->
            getNeighbours(index)
                .filter { heights[it] - height < 2 }
                .forEach { graph.addEdge(index, it) }
        }
    }

    fun findShortestPath(fromIndex: Int = startIndex): Int {
        val dist = mutableMapOf<Int, Int>()
        val prev = mutableMapOf<Int, Int>()
        val q = mutableListOf<Int>()

        graph.vertexSet().forEach { v ->
            dist[v] = Int.MAX_VALUE
            q += v
        }

        dist[fromIndex] = 0

        while (q.isNotEmpty()) {
            val u = q.minBy(dist::getValue)
            if (u == end) {
                break
            }
            q.remove(u)

            graph.outgoingEdgesOf(u)
                .map(graph::getEdgeTarget)
                .filter { it in q }
                .forEach { v ->
                    val alt = dist.getValue(u) + 1
                    if (alt < dist.getValue(v)) {
                        dist[v] = alt
                        prev[v] = u
                    }
                }
        }

        val s = mutableListOf<Int>()
        var u: Int? = end

        while (u != null) {
            s.add(0, u)
            u = prev[u]
        }

        return s.size - 1
    }

    fun findOverallShortestPath(): Int {
        val startPoints = heights.withIndex()
            .filter { it.value == 'a' }

        return startPoints.minOf {
            findShortestPath(it.index)
        }
    }

    private fun getNeighbours(index: Int): List<Int> {
        val left = if (index % width != 0) index - 1 else null
        val right = if ((index + 1) % width != 0) index + 1 else null
        val above = if (index >= width) index - width else null
        val below = if (index < heights.size - width) index + width else null

        return listOfNotNull(left, right, above, below)
    }

    companion object {

        fun from(input: List<String>): HeightMap {
            val flattened = input.flatMap(String::toList)
            val start = flattened.indexOf('S')
            val end = flattened.indexOf('E')
            val width = input.first().length
            val heights = input.flatMap { row ->
                row.map {
                    when (it) {
                        'S' -> 'a'
                        'E' -> 'z'
                        else -> it
                    }
                }
            }
            return HeightMap(heights, width, start, end)
        }
    }
}