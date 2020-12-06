package year2020.day5

import java.io.File

fun main() {
    val passes = File("src/year2020/day5/input").readLines()
    val seatIds = passes.map { boardingPass ->
        val asBinaryString = boardingPass
            .replace('F', '0')
            .replace('B', '1')
            .replace('L', '0')
            .replace('R', '1')
        Integer.parseInt(asBinaryString, 2)
    }.sorted()
    println(seatIds.max())
    for (seat in seatIds.indices) {
        val currentSeat = seatIds[seat]
        if (seatIds[seat + 1] != currentSeat + 1) {
            println(currentSeat + 1)
            break
        }
    }
}