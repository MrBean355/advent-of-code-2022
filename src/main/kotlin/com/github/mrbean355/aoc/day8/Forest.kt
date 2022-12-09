package com.github.mrbean355.aoc.day8

class Forest private constructor(
    private val trees: List<List<Tree>>
) {
    private val rows = trees.size
    private val columns = trees[0].size

    fun countTreesVisibleFromOutside(): Int {
        val uniqueTrees = hashSetOf<Tree>()

        trees.forEach { row ->
            uniqueTrees += getTreesVisibleFromOutside(row)
            uniqueTrees += getTreesVisibleFromOutside(row.reversed())
        }

        repeat(columns) { index ->
            val column = getColumn(index)
            uniqueTrees += getTreesVisibleFromOutside(column)
            uniqueTrees += getTreesVisibleFromOutside(column.reversed())
        }

        return uniqueTrees.size
    }

    fun calculateBestScenicScore(): Int {
        var bestScore = 0

        repeat(rows) { y ->
            repeat(columns) { x ->
                bestScore = maxOf(bestScore, calculateScenicScore(x, y))
            }
        }

        return bestScore
    }

    private fun getTreesVisibleFromOutside(line: List<Tree>): List<Tree> {
        var highest = -1

        return line.filter { tree ->
            if (tree.height > highest) {
                highest = tree.height
                true
            } else {
                false
            }
        }
    }

    private fun calculateScenicScore(x: Int, y: Int): Int {
        val height = trees[y][x].height
        val left = calculateViewingDistance(
            maxHeight = height,
            xRange = (x - 1) downTo 0,
            yRange = y..y
        )
        val right = calculateViewingDistance(
            maxHeight = height,
            xRange = (x + 1) until rows,
            yRange = y..y
        )
        val above = calculateViewingDistance(
            maxHeight = height,
            xRange = x..x,
            yRange = (y - 1) downTo 0
        )
        val below = calculateViewingDistance(
            maxHeight = height,
            xRange = x..x,
            yRange = (y + 1) until columns
        )
        return left * right * above * below
    }

    private fun calculateViewingDistance(maxHeight: Int, xRange: Iterable<Int>, yRange: Iterable<Int>): Int {
        var count = 0

        for (y in yRange) {
            for (x in xRange) {
                if (trees[y][x].height < maxHeight) {
                    ++count
                } else {
                    // Encountered a tree of equal or greater height.
                    // We can still see it though, so count it before stopping.
                    return ++count
                }
            }
        }

        return count
    }

    private fun getColumn(index: Int): List<Tree> {
        return trees.map { it[index] }
    }

    private class Tree(val height: Int)

    companion object {

        fun from(input: List<String>): Forest {
            return Forest(input.map { row ->
                row.map { height ->
                    Tree(height.digitToInt())
                }
            })
        }
    }
}