package com.github.mrbean355.aoc.day11

class Monkey(
    startingItems: List<Int>,
    private val operation: Operation,
    private val test: Int,
    private val positive: Int,
    private val negative: Int,
) {

    private val items = startingItems.toMutableList()

    var inspections = 0
        private set

    fun inspectItems(throwItem: (target: Int, item: Int) -> Unit) {
        while (items.isNotEmpty()) {
            val item = items.removeAt(0)
            val newItem = operation.evaluate(item) / 3
            val target = if (newItem % test == 0) positive else negative
            throwItem(target, newItem)
            ++inspections
        }
    }

    fun catchItem(item: Int) {
        items += item
    }

    class Operation(
        lhs: String,
        private val op: Char,
        rhs: String,
    ) {
        private val lhs = lhs.toIntOrNull()
        private val rhs = rhs.toIntOrNull()

        fun evaluate(value: Int): Int {
            val l = lhs ?: value
            val r = rhs ?: value
            return when (op) {
                '+' -> l + r
                '*' -> l * r
                else -> error("Unexpected operation: $op")
            }
        }
    }

    companion object {

        fun from(lines: List<String>): Monkey {
            val starting = lines[1]
                .substringAfter(':')
                .replace(" ", "")
                .split(',')
                .map { it.toInt() }

            val operation = lines[2]
                .substringAfter("= ")
                .split(' ')
                .let { Operation(it[0], it[1].first(), it[2]) }

            val test = lines[3]
                .substringAfter(" by ")
                .toInt()

            val positive = lines[4]
                .substringAfter("monkey ")
                .toInt()

            val negative = lines[5]
                .substringAfter("monkey ")
                .toInt()

            return Monkey(starting, operation, test, positive, negative)
        }
    }
}