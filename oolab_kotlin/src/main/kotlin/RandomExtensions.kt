package org.example

import com.sun.jdi.IntegerType

fun Map<Vector2d, Any>.randomPosition(): Vector2d?{
    return this.keys.randomOrNull();
}

fun Map<Vector2d, Any>.randomFreePosition(mapSize: Vector2d): Vector2d?{
    //var possiblyFreePosition = Vector2d((0..<mapSize.x).random(),(0..<mapSize.y).random());
    for (i in 0..<mapSize.x)
        for (j in 0..(mapSize.y)){
            var potentialVector = Vector2d(i, j);
            if (!this.containsKey(potentialVector)) {
                return potentialVector;
            }
        }
    return null;
}