package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected final MapVisualizer visualizer;
    protected HashMap<Vector2d, Animal> animals;

    protected List<MapChangeListener> observers;

    public AbstractWorldMap() {

        visualizer = new MapVisualizer(this);
        animals = new HashMap<>();

        observers = new ArrayList();

    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }


    @Override
    public void move(Animal animal, MoveDirection direction) {
        var oldPosition = animal.getLocalizationOnMap();
        animal.move(direction, this);
        var newPosition = animal.getLocalizationOnMap();

        if (oldPosition != newPosition) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
            notifyObservers(String.format("Animal moved from %s to %s", oldPosition, newPosition));
        }
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        var animalProposedLocalisation = animal.getLocalizationOnMap();
        if (canMoveTo(animalProposedLocalisation)) {
            animals.put(animalProposedLocalisation, animal);
            notifyObservers(String.format("Animal was placed at %s", animalProposedLocalisation));
        } else {
            throw new IncorrectPositionException((animal.getPosition()));
        }
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

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary bounds = getCurrentBounds();
        return visualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }
}
