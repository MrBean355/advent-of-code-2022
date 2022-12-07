package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.Puzzle

class Day7(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return loadFileSystem(input).restrictedSize()
    }

    override fun part2(): Number {
        val root = loadFileSystem(input)
        val toReclaim = REQUIRED_SPACE - (TOTAL_SPACE - root.size)
        val dirs = root.findDirectories(toReclaim)

        return dirs.minBy(Directory::size).size
    }
}

private const val TOTAL_SPACE = 70_000_000
private const val REQUIRED_SPACE = 30_000_000

fun Directory.restrictedSize(): Long {
    val s = size
    val mySize = if (s <= 100_000) s else 0
    return mySize + children.filterIsInstance<Directory>()
        .sumOf(Directory::restrictedSize)
}

fun Directory.findDirectories(minSize: Long): List<Directory> {
    val s = size
    val myDir = if (s >= minSize) listOf(this) else emptyList()
    return myDir + children.filterIsInstance<Directory>()
        .flatMap { it.findDirectories(minSize) }
}