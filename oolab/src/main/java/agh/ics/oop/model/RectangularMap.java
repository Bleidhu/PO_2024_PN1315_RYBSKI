package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements agh.ics.oop.model.WorldMap {
    private final Vector2d upperBoundary;
    private final Vector2d lowerBoundary;
    private final MapVisualizer visualiser;
    Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        this.upperBoundary = new Vector2d(width - 1, height - 1);
        this.lowerBoundary = new Vector2d(0, 0);
        this.visualiser = new MapVisualizer(this);
    }

    @Override
    public boolean place(Animal animal) {
        var animalProposedLocalisation = animal.getLocalizationOnMap();
        if (canMoveTo(animalProposedLocalisation)) {
            animals.put(animalProposedLocalisation, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        var oldPosition = animal.getLocalizationOnMap();
        animal.move(direction, this);
        var newPosition = animal.getLocalizationOnMap();

        if (oldPosition != newPosition) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.get(position) != null;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(lowerBoundary) && position.precedes(upperBoundary)) && !isOccupied(position);
    }

    @Override
    public String toString() {
        return visualiser.draw(lowerBoundary, upperBoundary);
    }
}
