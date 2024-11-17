package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    void grassFieldIsGeneratedWithProperAmountOfGrass() {
        int grassCount = 10;
        var testGrassField = new GrassField(grassCount);

        Assertions.assertEquals(grassCount, testGrassField.getElements().size());
    }
    
    @Test
    void animalIsPlacedOnValidCoordinates() {
        var testMap = new GrassField(10);
        var properPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(properPosition);

        var result = testMap.place(testAnimal);

        Assertions.assertTrue(result);
        Assertions.assertEquals(testAnimal, testMap.objectAt(properPosition));
    }


    @Test
    void animalIsNotPlacedOnOtherAnimal() {
        var testMap = new GrassField(10);
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
        var testMap = new GrassField(10);
        var testPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(testPosition);

        testMap.place(testAnimal);
        testMap.move(testAnimal, MoveDirection.FORWARD);

        var expectedPosition = new Vector2d(1, 2);

        Assertions.assertEquals(testAnimal, testMap.objectAt(expectedPosition));
    }

    @Test
    void animalWontMoveIfPositionInvalid() {
        var testMap = new GrassField(10);
        var testPosition = new Vector2d(0, 0);
        var futurePosition = new Vector2d(0, -1);
        var testAnimal = new Animal(testPosition);
        var blockingAnimal = new Animal(futurePosition);

        testMap.place(testAnimal);
        testMap.place(blockingAnimal);
        testMap.move(testAnimal, MoveDirection.BACKWARD);
        Assertions.assertEquals(testAnimal, testMap.objectAt(testPosition));
    }

    @Test
    void occupiedPlaceIsOccupied() {
        var testMap = new GrassField(10);
        var testPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(testPosition);

        testMap.place(testAnimal);

        Assertions.assertTrue(testMap.isOccupied(testPosition));
    }

    @Test
    void unoccupiedPlaceIsUnoccupied() {
        var testMap = new GrassField(10);
        var testPosition = new Vector2d(1000, 1000);

        Assertions.assertFalse(testMap.isOccupied(testPosition));
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
        var testMap = new GrassField(10);
        var testPosition = new Vector2d(1, 1);


        Assertions.assertTrue(testMap.canMoveTo(testPosition));
    }

    @Test
    void cantMoveToOccupiedPosition() {
        var testMap = new GrassField(10);
        var occupiedPosition = new Vector2d(1, 1);
        var testAnimal = new Animal(occupiedPosition);

        testMap.place(testAnimal);

        Assertions.assertFalse(testMap.canMoveTo(occupiedPosition));
    }

    @Test
    void upperMapVisualisationBoundaryIsProperlySet() {
        var testMap = new GrassField(10);
        var topRightPosition = new Vector2d(100, 100);
        var testAnimal = new Animal(topRightPosition);

        testMap.place(testAnimal);

        Assertions.assertEquals(topRightPosition, testMap.getUpperVisualisationBoundary());

        testMap.move(testAnimal, MoveDirection.FORWARD);

        topRightPosition = new Vector2d(100, 101);

        Assertions.assertEquals(topRightPosition, testMap.getUpperVisualisationBoundary());
    }

    @Test
    void lowerMapVisualisationBoundaryIsProperlySet() {
        var testMap = new GrassField(10);
        var lowerLeftPosition = new Vector2d(-100, -100);
        var testAnimal = new Animal(lowerLeftPosition);

        testMap.place(testAnimal);

        Assertions.assertEquals(lowerLeftPosition, testMap.getLowerVisualisationBoundary());

        testMap.move(testAnimal, MoveDirection.BACKWARD);

        lowerLeftPosition = new Vector2d(-100, -101);

        Assertions.assertEquals(lowerLeftPosition, testMap.getLowerVisualisationBoundary());
    }

    @Test
    void grassFieldReturnsProperAmountOfElements() {
        GrassField defaultMap = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(0, 0));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        defaultMap.place(animal1);
        defaultMap.place(animal2);
        Assertions.assertEquals(12, defaultMap.getElements().size());
    }
}
