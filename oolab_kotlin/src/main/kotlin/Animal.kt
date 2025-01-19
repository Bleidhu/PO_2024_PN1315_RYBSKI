package org.example

import java.util.*

class Animal( var localizationOnMap: Vector2d = Vector2d(2, 2), val id:Int){
    var facingDirection: MapDirection = MapDirection.NORTH;

    fun move(direction: MoveDirection, validator: MoveValidator){
        when(direction) {
            MoveDirection.LEFT -> facingDirection = facingDirection.previous();
            MoveDirection.RIGHT -> facingDirection = facingDirection.next();
            MoveDirection.FORWARD -> {
                var newLoc = localizationOnMap + facingDirection.vector2d;
                if(validator.canMoveTo(newLoc)){
                    localizationOnMap = newLoc;
                }
            }
            MoveDirection.BACKWARD ->  {
                var newLoc = localizationOnMap - facingDirection.vector2d;
                if(validator.canMoveTo(newLoc)){
                    localizationOnMap = newLoc;
                }
            }
        }
    }

    override fun toString(): String {
        return return String.format(
            when (facingDirection) {
                MapDirection.NORTH -> "^"
                MapDirection.EAST -> ">"
                MapDirection.WEST -> "<"
                MapDirection.SOUTH -> "v"
            }
        )
    }

    fun isAt(position: Vector2d): Boolean {
        return localizationOnMap == position;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Animal

        if (localizationOnMap != other.localizationOnMap) return false
        if (facingDirection != other.facingDirection) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(localizationOnMap, facingDirection, id);
    }


}