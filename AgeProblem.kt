package com.stuff

import kotlin.math.*

object AgeProblem {
    @JvmStatic
    fun main(args: Array<String>) {
        val differences = flipFlopQuery(10, 200, 400, 8)
        for (difference in differences) {
            println(difference)
        }
    }

    private fun flipFlopQuery(base: Int, maximumDifference: Int, maximumAge: Int, query: Int): ArrayList<Int> {
        val differences = ArrayList<Int>()
        var i = 1
        while (i <= maximumDifference) {
            val integers = findFlipFlopAges(base, maximumAge, i)
            if (integers.size == query) {
                differences.add(i)
                println("Difference: $i")
                for (age in integers) println("Younger: " + age + ", Older: " + (age + i))
            }
            i += 1
        }
        return differences
    }

    private fun findFlipFlopAges(base: Int, max: Int, difference: Int): ArrayList<Int> {
        val ages = ArrayList<Int>()
        var age = 0
        while (age < max) {
            if (digitsAreFlipped(base, age, age + difference)) {
                ages.add(age)
            }
            age++
        }
        return ages
    }

    private fun digitsAreFlipped(base: Int, a: Int, b: Int): Boolean {
        val digitsA = 1 + floor(log10(a.toDouble()) / log10(base.toDouble()))
            .toInt()
        val digitsB = 1 + floor(log10(b.toDouble()) / log10(base.toDouble()))
            .toInt()
        val maxDigits : Int = max(digitsA, digitsB)
        var i: Int = 0
        while (i < maxDigits) {
            if ((a / base.toDouble().pow(i.toDouble()) % base).toInt() != (b / base.toDouble()
                    .pow(maxDigits - i - 1.toDouble()) % base).toInt()
            ) return false
            i++
        }
        return true
    }
}