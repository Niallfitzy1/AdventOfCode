package year2020.day2

import java.io.File
import java.nio.charset.Charset

fun main() {
    val passwordsWithRules: List<PasswordWithRule> =
        File("src/year2020/day2/input").readLines(Charset.defaultCharset()).map(::convertInputStringToPasswordWithRule)
    val solutionOne = passwordsWithRules.count {
        val occurrences =
            it.password.toCharArray().filter { character -> character == it.letter }.count()
        occurrences >= it.min && occurrences <= it.max
    }
    println(solutionOne)
    val solutionTwo = passwordsWithRules.count {
        if (it.password.length < it.max) false
        else {
            val charIsInFirstPosition = it.password[it.min - 1] == it.letter
            val charIsInSecondPosition = it.password[it.max - 1] == it.letter
            charIsInFirstPosition != charIsInSecondPosition
        }
    }
    println(solutionTwo)
}

data class PasswordWithRule(val min: Int, val max: Int, val letter: Char, val password: String )


fun convertInputStringToPasswordWithRule(rawLine: String): PasswordWithRule {
    val components = rawLine.split(' ')
    val bounds = components[0].split('-').map(Integer::valueOf)
    val charToCheck = components[1].toCharArray().first()
    return PasswordWithRule(bounds[0], bounds[1], charToCheck, components[2])
}