package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.Puzzle

class Day8(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val trees = input.map { row ->
            row.map { height ->
                Tree(height.digitToInt())
            }
        }

        val visibleTrees = hashSetOf<Tree>()

        trees.forEach {
            visibleTrees += getVisibleTrees(it)
            visibleTrees += getVisibleTrees(it.reversed())
        }

        repeat(trees[0].size) {
            val column = trees.getColumn(it)
            visibleTrees += getVisibleTrees(column)
            visibleTrees += getVisibleTrees(column.reversed())
        }

        return visibleTrees.size
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private class Tree(
        val height: Int
    )

    private fun List<List<Tree>>.getColumn(index: Int): List<Tree> {
        return map { it[index] }
    }

    private fun getVisibleTrees(trees: List<Tree>): List<Tree> {
        var highest = -1

        return trees.filter { tree ->
            if (tree.height > highest) {
                highest = tree.height
                true
            } else {
                false
            }
        }
    }
}