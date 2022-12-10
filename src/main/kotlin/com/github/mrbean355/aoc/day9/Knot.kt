package com.github.mrbean355.aoc.day9

import kotlin.math.sign

class Knot(
    val next: Knot? = null,
    trackVisitedPoints: Boolean = false
) {

    var x: Int = 0
        private set
    var y: Int = 0
        private set

    private val history: MutableSet<Pair<Int, Int>>? = if (trackVisitedPoints) mutableSetOf(x to y) else null

    fun pointsVisited(): Int {
        return history?.size ?: error("Visited points not tracked")
    }

    fun move(toX: Int = x, toY: Int = y) {
        x = toX
        y = toY
        history?.add(x to y)
    }
}

fun Knot.move(direction: String) {
    when (direction) {
        "L" -> move(toX = x - 1)
        "R" -> move(toX = x + 1)
        "U" -> move(toY = y + 1)
        "D" -> move(toY = y - 1)
        else -> error("Unexpected direction: $direction")
    }

    var previous = this
    var next = previous.next

    while (next != null) {
        if (next.shouldMoveTowards(previous)) {
            next.moveTowards(previous)
        }
        previous = next
        next = next.next
    }
}

private fun Knot.shouldMoveTowards(other: Knot): Boolean {
    return x !in (other.x - 1)..(other.x + 1)
            || y !in (other.y - 1)..(other.y + 1)
}

private fun Knot.moveTowards(other: Knot) {
    move(
        toX = x + (other.x - x).sign,
        toY = y + (other.y - y).sign,
    )
}