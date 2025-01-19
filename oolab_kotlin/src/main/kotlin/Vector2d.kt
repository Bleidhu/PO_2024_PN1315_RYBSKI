package org.example

import org.example.MapDirection.*
import kotlin.math.max
import kotlin.math.min

data class Vector2d(val x: Int, val y: Int){
    fun precedes(other: Vector2d): Boolean{
        return x <= other.x && y <= other.y;
    }
    fun follows(other: Vector2d): Boolean{
        return x >= other.x && y >= other.y;
    }

    operator fun plus(other: Vector2d): Vector2d{
        return Vector2d(x + other.x, y + other.y);
    }
    operator fun minus(other: Vector2d): Vector2d{
        return Vector2d(x - other.x, y - other.y);
    }
    fun upperRight(other: Vector2d): Vector2d{
        return Vector2d(max(x, other.x), max(y, other.y));
    }
    fun lowerLeft(other: Vector2d): Vector2d{
        return Vector2d(min(x, other.x), min(y, other.y));
    }
    fun opposite(): Vector2d{
        return  Vector2d(-1*x, -1*y);
    }
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.WEST -> Vector2d(-1, 0)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.EAST -> Vector2d(1, 0)
    }
}