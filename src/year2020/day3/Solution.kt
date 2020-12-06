package year2020.day3

import java.io.File
import java.nio.charset.Charset

fun main() {
    val mapData: List<String> =
        File("src/year2020/day3/input").readLines(Charset.defaultCharset())

    val map = Map(mapData)
    val productOfSlopes = listOf<Long>(
        findNumberOfTreesForDirection(map, Vector(1, 1)),
        findNumberOfTreesForDirection(map, Vector(3, 1)),
        findNumberOfTreesForDirection(map, Vector(5, 1)),
        findNumberOfTreesForDirection(map, Vector(7, 1)),
        findNumberOfTreesForDirection(map, Vector(1, 2))
    ).reduce(Long::times)
    println(productOfSlopes)

}

fun findNumberOfTreesForDirection(map: Map, direction: Vector): Long {
    var currentLocation = Coordinate( direction.x,direction.y)
    var numberOfTrees = 0L
    do {
        val isTree = isTree(map, currentLocation)
        print("\rAt $currentLocation. It ${if (isTree) "is" else "is not"} a tree")
        if (isTree) numberOfTrees++
        currentLocation = traverse(direction, currentLocation)
    }while (inbounds(map, currentLocation))
    println("\rReached $currentLocation & escaped the trees. Encountered: $numberOfTrees trees on slope $direction")
    return numberOfTrees
}

fun traverse(direction: Vector, location: Coordinate): Coordinate {
    return Coordinate(location.x + direction.x, location.y + direction.y)
}

fun isTree(map: Map, newLocation: Coordinate): Boolean {
    val row = map.map[newLocation.y]
    val locationWithinRepeatedMap = newLocation.x % map.boundaryOnXAxis()
    return row[locationWithinRepeatedMap] == '#'
}

fun inbounds(map: Map, newLocation: Coordinate): Boolean {
    return map.boundaryOnYAxis() > newLocation.y
}

data class Map(val map: List<String>) {
    fun boundaryOnXAxis(): Int {
        return map.first().length
    }

    fun boundaryOnYAxis(): Int {
        return map.size
    }
}

data class Vector(val x: Int, val y: Int)

data class Coordinate(val x: Int, val y: Int)