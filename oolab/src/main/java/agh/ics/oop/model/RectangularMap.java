package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d upperBoundary;
    private final Vector2d lowerBoundary;
    Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        upperBoundary = new Vector2d(width - 1, height - 1);
        lowerBoundary = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && (position.follows(lowerBoundary) && position.precedes(upperBoundary));
    }


    @Override
    public String toString() {
        return visualizer.draw(lowerBoundary, upperBoundary);
    }
}
