package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer visualizer;
    protected Vector2d upperBoundary = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d lowerBoundary = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected HashMap<Vector2d, Animal> animals;

    public AbstractWorldMap() {

        visualizer = new MapVisualizer(this);
        animals = new HashMap<>();

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
    public boolean place(Animal animal) {
        var animalProposedLocalisation = animal.getLocalizationOnMap();
        if (canMoveTo(animalProposedLocalisation)) {
            animals.put(animalProposedLocalisation, animal);
            return true;
        }
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    //I don't know if we can assume that, grass is occupying position, if so the can move to can be simplified, but for now I am leaving it as is
    //Update - we NEED to assume so, because otherwise map visualiser breaks
    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (animals.get(position) == null) && (position.follows(lowerBoundary) && position.precedes(upperBoundary));
    }


    //this getter is safe, because we are returning new list - modification of it won't directly impact object state

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> objectsToReturn = new ArrayList<>();

        for (var key : animals.entrySet()) {
            objectsToReturn.add(animals.get(key));
        }
        return objectsToReturn;
    }
}
