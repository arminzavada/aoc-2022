package io.armin.aoc

import java.io.File
import java.util.PriorityQueue

class FixedSizePriorityQueue<T : Comparable<T>>(
    val maxSize: Int
) : PriorityQueue<T>(maxSize) {
    override fun add(element: T): Boolean {
        if (count() >= maxSize) {
            if (element < peek()) {
                return false
            } else {
                poll()
            }
        }

        return super.add(element)
    }
}

private fun <T : Comparable<T>> Iterable<T>.max(amount: Int): Collection<T> {
    val queue = FixedSizePriorityQueue<T>(3)

    this.forEach {
        queue.add(it)
    }

    return queue
}

fun main(args: Array<String>) {
    val path = if (args.size > 1) args[1] else "src/main/resources/DayOne.txt"

    val calories = readFile(path)
    val max = calories.max(3)

    println(max.max())
    println(max.sum())
}

private fun readFile(path: String): List<Int> {
    val file = File(path)
    val collectedCalories = mutableListOf<Int>()
    var tempCounter = 0
    file.forEachLine {
        if (it.isEmpty()) {
            collectedCalories.add(tempCounter)
            tempCounter = 0
        } else {
            tempCounter += it.toInt()
        }
    }
    collectedCalories.add(tempCounter)

    return collectedCalories
}