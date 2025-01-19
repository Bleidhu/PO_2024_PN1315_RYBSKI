package org.example

enum class MapDirection() {
    NORTH,
    EAST,
    SOUTH,
    WEST, ;

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