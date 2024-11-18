package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        super.upperBoundary = new Vector2d(width - 1, height - 1);
        super.lowerBoundary = new Vector2d(0, 0);
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
