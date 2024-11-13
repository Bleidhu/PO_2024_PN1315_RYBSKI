package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextMapTest {
    @Test
    public void occupiedPositionIsOccupied() {
        TextMap testMap = new TextMap();
        testMap.place("tmp");

        Assertions.assertTrue(testMap.isOccupied(0));
    }

    @Test
    public void objectAtReturnsObjectAtPosition() {
        TextMap testMap = new TextMap();
        String testObject = "tmp";
        testMap.place(testObject);

        Assertions.assertEquals(testObject, testMap.objectAt(0));
    }

    @Test
    public void objectCantMoveOutsideOfTheMap() {
        TextMap testMap = new TextMap();
        String testObject = "tmp";
        testMap.place(testObject);
        testMap.move(testObject, MoveDirection.LEFT);
        Assertions.assertEquals(testObject, testMap.objectAt(0));
        testMap.move(testObject, MoveDirection.RIGHT);
        Assertions.assertEquals(testObject, testMap.objectAt(0));
    }

    @Test
    public void objectCantMoveRight() {
        TextMap testMap = new TextMap();
        String testObject1 = "tmp1";
        String testObject2 = "tmp2";
        testMap.place(testObject1);
        testMap.place(testObject2);
        testMap.move(testObject1, MoveDirection.RIGHT);
        Assertions.assertEquals(testObject1, testMap.objectAt(1));
        Assertions.assertEquals(testObject2, testMap.objectAt(0));
    }

    @Test
    public void objectCantMoveLeft() {
        TextMap testMap = new TextMap();
        String testObject1 = "tmp1";
        String testObject2 = "tmp2";
        testMap.place(testObject1);
        testMap.place(testObject2);
        testMap.move(testObject2, MoveDirection.LEFT);
        Assertions.assertEquals(testObject1, testMap.objectAt(1));
        Assertions.assertEquals(testObject2, testMap.objectAt(0));
    }

    @Test
    public void objectIsPlaced() {
        TextMap testMap = new TextMap();
        String testObject = "tmp";
        testMap.place(testObject);
        Assertions.assertEquals(testObject, testMap.objectAt(0));
    }


}
