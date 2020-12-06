package year2020.day4

import com.github.ajalt.mordant.TermColors
import java.io.File

fun main() {
    val passports: MutableList<Passport> = mutableListOf()
    val fields: MutableList<Field> = mutableListOf()
    File("src/year2020/day4/input").forEachLine { line ->
        if (line.isNotEmpty()) {
            line.split(' ').map { rawField ->
                val splitField = rawField.split(':')
                fields.add(Field(splitField[0], splitField[1]))
            }
        } else {
            passports.add(
                Passport(fields.sortedBy { field -> field.name }.toList())
            )
            fields.clear()
        }
    }
    with(TermColors()) {
        passports.forEach { passport ->
            print("${if (!passport.validatedFieldsPresent) (yellow) else (reset)}Passport $reset")
            passport.fields.forEach {
                print("${it.name}: ${if (it.isValidField) (green)(it.value) else (red)(it.value)} $reset")
            }
            print(
                " -> " +
                        (if (!passport.validatedFieldsPresent) "${(reset + yellow)}missing fields"
                        else if (!passport.validatedFieldsPassChecks) "${(red)}invalid field values"
                        else "${(green)}passed") +
                        "\n$reset"
            )
        }

        val partOne = passports.count { passport -> passport.validatedFieldsPresent }
        val partTwo = passports.count { passport -> passport.validatedFieldsPassChecks }
        println("${(brightMagenta)}Found $partOne passports with all fields & $partTwo with fields passing validation$reset")
    }
}

data class Passport(
    val fields: List<Field>
) {
    private val validatedFields: List<Field> = fields.filter { field -> field.name != "cid" }
    val validatedFieldsPresent = validatedFields.size == 7
    val validatedFieldsPassChecks =
        validatedFieldsPresent && validatedFields.all { passportField -> passportField.isValidField }
}

data class Field(val name: String, val value: String) {
    val isValidField =
        when (name) {
            "byr" -> value.toInt() in 1920..2002
            "iyr" -> value.toInt() in 2010..2020
            "eyr" -> value.toInt() in 2020..2030
            "hgt" ->
                when (value.takeLast(2)) {
                    "cm" -> value.dropLast(2).toInt() in 150..193
                    "in" -> value.dropLast(2).toInt() in 59..76
                    else -> false
                }
            "hcl" -> value.matches(Regex("#[0-9a-f]{6}\$"))
            "ecl" -> value.matches(Regex("(amb\$)|(blu\$)|(brn\$)|(gry\$)|(grn\$)|(hzl\$)|(oth\$)"))
            "pid" -> value.matches(Regex("[0-9]{9}$"))
            else -> true
        }
}