package day1

import java.io.File
import java.nio.charset.Charset

fun main() {
    val allNumbers: List<Int> =
        File("src/day1/input").readLines(Charset.defaultCharset()).map(Integer::valueOf)

    for (i in allNumbers.indices) {
        val firstValue = allNumbers[i]
        for (j in i + 1 until allNumbers.size) {
            val secondValue = allNumbers[j]
            if (firstValue + secondValue == 2020) {
                println("$firstValue + $secondValue = ${firstValue * secondValue}")
            }
            for (k in j + 1 until allNumbers.size) {
                val thirdValue = allNumbers[k]
                if (firstValue + secondValue + thirdValue == 2020) {
                    println("$firstValue + $secondValue + $thirdValue = ${firstValue * secondValue * thirdValue}")
                }
            }
        }
    }
}