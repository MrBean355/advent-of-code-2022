package com.github.mrbean355.aoc.day12

import com.github.mrbean355.aoc.base.Puzzle

class Day12(private val input: List<String>) : Puzzle {

    override fun part1(): Int {
        return HeightMap.from(input).findShortestPath()
    }

    override fun part2(): Number {
        return HeightMap.from(input).findOverallShortestPath()
    }
}