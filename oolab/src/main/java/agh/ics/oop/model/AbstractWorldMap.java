package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer visualizer;
    protected Vector2d upperBoundary;
    protected Vector2d lowerBoundary;
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

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    //move to be abstract
    public boolean canMoveTo(Vector2d position) {
        return (animals.get(position) == null);
    }


    //this getter is safe, because we are returning new list - modification of it won't directly impact object state

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return elements;
    }
}
