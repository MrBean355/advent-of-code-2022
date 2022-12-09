package com.github.mrbean355.aoc.day9

class Rope {
    private val head = Node()
    private val tail = head.copy()
    private val visited = mutableSetOf<Pair<Int, Int>>()

    fun moveHead(direction: Direction) {
        val previousHead = head.copy()

        when (direction) {
            Direction.Left -> --head.x
            Direction.Right -> ++head.x
            Direction.Up -> ++head.y
            Direction.Down -> --head.y
        }

        if (shouldMoveTail()) {
            tail.x = previousHead.x
            tail.y = previousHead.y
        }

        visited += tail.x to tail.y
    }

    fun countVisitedPoints(): Int {
        return visited.size
    }

    private fun shouldMoveTail(): Boolean {
        val validX = (head.x - 1)..(head.x + 1)
        val validY = (head.y - 1)..(head.y + 1)

        return tail.x !in validX || tail.y !in validY
    }

    data class Node(
        var x: Int = 0,
        var y: Int = 0,
    )
}

enum class Direction {
    Left, Right, Up, Down;

    companion object {

        fun from(input: String): Direction {
            return when (input) {
                "L" -> Left
                "R" -> Right
                "U" -> Up
                "D" -> Down
                else -> error("Unexpected direction: $input")
            }
        }
    }
}