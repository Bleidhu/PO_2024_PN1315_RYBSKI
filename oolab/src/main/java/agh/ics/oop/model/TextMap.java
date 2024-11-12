package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String, Integer> {
    private static int leftBoundary = 0;
    private int rightBoundary = 0;
    private List<String> map = new ArrayList<>();


    @Override
    public boolean place(String inhabitant) {
        map.add(inhabitant);
        rightBoundary += 1;
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        int currentPosition = 0;
        while (currentPosition < rightBoundary && !object.equals(map.get(currentPosition))) {
        }
        Integer desiredPosition = currentPosition + switch (direction) {
            case MoveDirection.LEFT, MoveDirection.BACKWARD -> -1;
            case MoveDirection.RIGHT, MoveDirection.FORWARD -> 1;
        };

        if (canMoveTo(desiredPosition)) {
            Collections.swap(map, desiredPosition, currentPosition);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return map.get(position) != null;
    }

    @Override
    public String objectAt(Integer position) {
        return map.get(position);
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return leftBoundary <= position && position < rightBoundary;
    }

    @Override
    public String toString() {
        return "TextMap{" +
                "map=" + map +
                '}';
    }
}
