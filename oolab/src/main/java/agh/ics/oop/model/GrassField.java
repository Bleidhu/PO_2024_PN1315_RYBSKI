package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap {
    private final HashMap<Vector2d, Grass> grasses;
    private Vector2d upperVisualisationBoundary = new Vector2d(0, 0);
    private Vector2d lowerVisualisationBoundary = new Vector2d(0, 0);

    public GrassField(int grassCount) {
        super();
        grasses = new HashMap<>();
        var upperBound = (int) Math.sqrt(grassCount * 10);
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

    //Explanatory comment - to be removed after lab5 PR
    //This part is more complicated, because we are updating lowerVisualisationBoundary and upperVisualisationBoundary in place and move operations, but this approach
    // is not very demanding in terms of time complexity - we are going from O(n), to O(3*n) - I am abusing notation here, but it is to demonstrate, that slightly larger constant here
    // saves time when drawing visualisation. Other approach revolves around building list of all elements and calculating the visualisation boundaries by iterating over every item in the list
    // While this method shaves off the constant, it adds huge workload to every call to toString method, that is not really necessary, for example if we have animal centered in the
    //visible section, and it moves only one position left in first approach we only do one additional calculation in O(1) time, and are ready to visualise the step. In latter implementation
    // This one move requires us to do O(n) operations before visualisation, having huge impact on visualising the movement of animals step-by-step. (I think it's better ux to take more time to generate map,
    // rather than to display it)
    @Override
    public boolean place(Animal animal) {
        var animalProposedLocalisation = animal.getLocalizationOnMap();
        if (super.place(animal)) {
            upperVisualisationBoundary = upperVisualisationBoundary.upperRight(animalProposedLocalisation);
            lowerVisualisationBoundary = lowerVisualisationBoundary.lowerLeft(animalProposedLocalisation);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        var oldPosition = animal.getLocalizationOnMap();
        super.move(animal, direction);
        var newPosition = animal.getLocalizationOnMap();
        if (oldPosition != newPosition) {
            upperVisualisationBoundary = upperVisualisationBoundary.upperRight(newPosition);
            lowerVisualisationBoundary = lowerVisualisationBoundary.lowerLeft(newPosition);
        }
    }


    @Override
    public WorldElement objectAt(Vector2d position) {
        var tmp = super.objectAt(position);
        if (tmp != null) {
            return tmp;
        }
        return grasses.get(position);
    }


    @Override
    public String toString() {
        return visualizer.draw(lowerVisualisationBoundary, upperVisualisationBoundary);
    }

    @Override
    public List<WorldElement> getElements() {
        var temporaryAnimals = super.getElements();

        for (var key : grasses.entrySet()) {
            temporaryAnimals.add(grasses.get(key));
        }

        return temporaryAnimals;
    }


    //Methods used for testing
    protected Vector2d getUpperVisualisationBoundary() {
        return upperVisualisationBoundary;
    }

    protected Vector2d getLowerVisualisationBoundary() {
        return lowerVisualisationBoundary;
    }
}
