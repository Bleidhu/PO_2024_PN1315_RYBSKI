package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var animal: Animal = Animal(Vector2d(1,1), 0);
    var animal2: Animal = Animal(Vector2d(1,1), 1);
    var bouncyMap: BouncyMap = BouncyMap(10, 10)

    println(bouncyMap.place(animal))
    println(bouncyMap.place(animal2))
    println(animal2.localizationOnMap)
}