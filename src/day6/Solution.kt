package day6

import java.io.File

fun main() {
    val groups: MutableList<Group> = mutableListOf()
    val forms: MutableList<Form> = mutableListOf()
    File("src/day6/input").forEachLine { line ->
        if (line.isNotEmpty()) forms.add(Form(line.toSet()))
        else {
            groups.add(
                Group(forms.toList())
            )
            forms.clear()
        }
    }
    groups.add(
        Group(forms.toList())
    )
    println(groups)
    println(groups.sumBy { it.uniqueQuestions.size })
    println(groups.sumBy { it.answeredByAll.size })
}

data class Group(
    val fields: List<Form>
) {
    val uniqueQuestions: Set<Char> = fields.flatMap { form -> form.fields }.toSet()
    private val getAllAnswered: () -> Set<Char> = {
        val retained: MutableSet<Char> = fields[0].fields.toMutableSet()
        for (i in 1..fields.lastIndex) {
            retained.retainAll(fields[i].fields)
        }
        retained.toSet()
    }
    val answeredByAll = getAllAnswered.invoke()
}

data class Form(
    val fields: Set<Char>
) {
}