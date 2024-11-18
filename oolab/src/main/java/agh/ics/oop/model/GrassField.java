package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap {
    private final HashMap<Vector2d, Grass> grasses;
    private Vector2d upperVisualisationBoundary;
    private Vector2d lowerVisualisationBoundary;

    public GrassField(int grassCount) {
        super.lowerBoundary = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        super.upperBoundary = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        grasses = new HashMap<>();
        var upperBound = (int) Math.sqrt(grassCount * 10);
        lowerVisualisationBoundary = new Vector2d(upperBound, upperBound);
        upperVisualisationBoundary = new Vector2d(0, 0);
//obsolete code - will be removed after pr 5, kept as fallback in case Random position generator is broken
//        for (var i = 0; i < grassCount; ++i) {
//
//            var possiblePosition = new Vector2d((int) (Math.random() * upperBound), (int) (Math.random() * upperBound));
//
//            while (grass.get(possiblePosition) != null) {
//                possiblePosition = new Vector2d((int) (Math.random() * upperBound), (int) (Math.random() * upperBound));
//            }
//
//            upperVisualisationBoundary = upperVisualisationBoundary.upperRight(possiblePosition);
//            lowerVisualisationBoundary = lowerVisualisationBoundary.lowerLeft(possiblePosition);
//
//            grass.put(possiblePosition, new Grass(possiblePosition));
//        }
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperBound, upperBound, grassCount);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
            upperVisualisationBoundary = upperVisualisationBoundary.upperRight(grassPosition);
            lowerVisualisationBoundary = lowerVisualisationBoundary.lowerLeft(grassPosition);
        }

        animals = new HashMap<>();
    }


    @Override
    public WorldElement objectAt(Vector2d position) {
        var animalAt = super.objectAt(position);
        if (animalAt != null) {
            return animalAt;
        }
        return grasses.get(position);
    }


    @Override
    public String toString() {
        var drawingLowerBoundary = lowerVisualisationBoundary;
        var drawingUpperBoundary = upperVisualisationBoundary;
        for (var animal : animals.values()) {
            drawingLowerBoundary = drawingLowerBoundary.lowerLeft(animal.getPosition());
            drawingUpperBoundary = drawingUpperBoundary.upperRight(animal.getPosition());
        }
        return visualizer.draw(lowerVisualisationBoundary, upperVisualisationBoundary);
    }

    @Override
    public List<WorldElement> getElements() {
        var temporaryAnimals = super.getElements();

        temporaryAnimals.addAll(grasses.values());

        return temporaryAnimals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && (position.follows(lowerBoundary) && position.precedes(upperBoundary));
    }

    //Methods used for testing
    protected Vector2d getUpperVisualisationBoundary() {
        return upperVisualisationBoundary;
    }

    protected Vector2d getLowerVisualisationBoundary() {
        return lowerVisualisationBoundary;
    }
}
