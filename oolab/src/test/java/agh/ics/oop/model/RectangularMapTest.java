package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    void animalIsPlacedOnValidCoordinates() {
        var testMap = new RectangularMap(4, 5);
        var properPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(properPosition);

        var result = testMap.place(testAnimal);

        Assertions.assertTrue(result);
        Assertions.assertEquals(testAnimal, testMap.objectAt(properPosition));
    }

    @Test
    void animalIsNotPlacedOutsideMap() {
        var testMap = new RectangularMap(4, 5);
        var properPosition = new Vector2d(10, 10);
        var testAnimal = new Animal(properPosition);

        var result = testMap.place(testAnimal);

        Assertions.assertFalse(result);
    }

    @Test
    void animalIsNotPlacedOnOtherAnimal() {
        var testMap = new RectangularMap(4, 5);
        var properPosition = new Vector2d(1, 1);
        var exampleAnimal = new Animal(properPosition);
        var testAnimal = new Animal(properPosition);

        testMap.place(exampleAnimal);
        var result = testMap.place(testAnimal);

        Assertions.assertFalse(result);
        Assertions.assertEquals(exampleAnimal, testMap.objectAt(properPosition));
    }

    @Test
    void animalMovesIfPositionValid() {
        var testMap = new RectangularMap(4, 5);
        var testPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(testPosition);

        testMap.place(testAnimal);
        testMap.move(testAnimal, MoveDirection.FORWARD);

        var expectedPosition = new Vector2d(1, 2);

        Assertions.assertFalse(testMap.isOccupied(testPosition));
        Assertions.assertEquals(testAnimal, testMap.objectAt(expectedPosition));
    }

    @Test
    void animalWontMoveIfPositionInvalid() {
        var testMap = new RectangularMap(4, 5);
        var testPosition = new Vector2d(0, 0);
        var testAnimal = new Animal(testPosition);

        testMap.place(testAnimal);
        testMap.move(testAnimal, MoveDirection.BACKWARD);

        Assertions.assertTrue(testMap.isOccupied(testPosition));
        Assertions.assertEquals(testAnimal, testMap.objectAt(testPosition));
    }

    @Test
    void occupiedPlaceIsOccupied() {
        var testMap = new RectangularMap(4, 5);
        var testPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(testPosition);

        testMap.place(testAnimal);

        Assertions.assertTrue(testMap.isOccupied(testPosition));
    }

    @Test
    void unoccupiedPlaceIsNotOccupied() {
        var testMap = new RectangularMap(4, 5);
        var occupiedPosition = new Vector2d(1, 1);
        var unoccupiedPosition = new Vector2d(2, 2);
        var testAnimal = new Animal(occupiedPosition);

        testMap.place(testAnimal);

        Assertions.assertFalse(testMap.isOccupied(unoccupiedPosition));
    }

    @Test
    void objectThatIsOnPositionIsReturned() {
        var testMap = new RectangularMap(4, 5);
        var occupiedPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(occupiedPosition);

        testMap.place(testAnimal);

        Assertions.assertEquals(testAnimal, testMap.objectAt(occupiedPosition));
    }

    @Test
    void canMoveToValidPosition() {
        var testMap = new RectangularMap(4, 5);
        var testPosition = new Vector2d(1, 1);


        Assertions.assertTrue(testMap.canMoveTo(testPosition));
    }

    @Test
    void cantMoveToPositionOutsideMap() {
        var testMap = new RectangularMap(4, 5);
        var testPosition = new Vector2d(10, 10);


        Assertions.assertFalse(testMap.canMoveTo(testPosition));
    }

    @Test
    void cantMoveToOccupiedPosition() {
        var testMap = new RectangularMap(4, 5);
        var occupiedPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(occupiedPosition);

        testMap.place(testAnimal);

        Assertions.assertFalse(testMap.canMoveTo(occupiedPosition));
    }


}
