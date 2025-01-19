package org.example

enum class MapDirection(val vector2d: Vector2d) {
    NORTH(Vector2d(0, 1)),
    EAST(Vector2d(1, 0)),
    SOUTH(Vector2d(0, -1)),
    WEST(Vector2d(-1, 0)), ;

    override fun toString(): String {
        return when(this) {
            NORTH -> "Północ";
            EAST -> "Wschód";
            SOUTH -> "Południe";
            WEST -> "Zachód";
        }
    }
    fun next(): MapDirection {
        return entries[this.ordinal + 1% entries.size];
    }

    fun previous(): MapDirection {
        return entries[(this.ordinal - 1 + entries.size)% entries.size];
    }



}