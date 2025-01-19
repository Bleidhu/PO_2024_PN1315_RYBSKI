package org.example

import jdk.internal.foreign.abi.Binding

interface WorldMap : MoveValidator  {
    fun place(animal: Animal): Boolean;

    fun move(animal: Animal, direction: MoveDirection);

    fun isOccupied(position: Vector2d) : Boolean;

    fun objectAt(position: Vector2d): Animal?;
}