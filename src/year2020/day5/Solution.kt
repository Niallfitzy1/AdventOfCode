package year2020.day5

import java.io.File

fun main() {
    val totalRows = 127
    val totalColumns = 7
    val passes = File("src/year2020/day5/input").readLines().map { BoardingPass(it.take(7), it.takeLast(3)) }
    var largestId = 0
    val seatIds = passes.map {
        var rowRange = 0..totalRows
        var colRange = 0..totalColumns
        for (direction in it.col) {
            val seatsToExclude = colRange.count() / 2
            colRange = if (direction == 'L') {
                colRange.first..(colRange.last - seatsToExclude)
            } else {
                (colRange.first + seatsToExclude)..colRange.last
            }
        }
        for (direction in it.row) {
            val seatsToExclude = rowRange.count() / 2
            rowRange = if (direction == 'F') {
                rowRange.first..(rowRange.last - seatsToExclude)
            } else {
                (rowRange.first + seatsToExclude)..rowRange.last
            }
        }
        val seatId = (rowRange.last * 8) + colRange.last
        if (seatId > largestId) largestId = seatId
        println("${rowRange.last}, ${colRange.last} with ID $seatId")
        seatId
    }.sorted()
    println(largestId)

    for (seat in seatIds.indices) {
        val currentSeat = seatIds[seat]
        if (seat == seatIds.lastIndex) {
            break
        }
            val nextSeatId = seatIds[seat + 1]
            if (nextSeatId != currentSeat + 1) {
                println(currentSeat + 1)
            }
    }
}

data class BoardingPass(val row: String, val col: String)
