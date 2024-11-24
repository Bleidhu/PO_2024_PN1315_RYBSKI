package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moves, WorldMap map) {
        for (var position : startingPositions) {
            var animalToBeAdded = new Animal(position);
            try {
                map.place(animalToBeAdded);
                animals.add(animalToBeAdded);
            } catch (IncorrectPositionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        this.moves = moves;
        this.map = map;
    }

    int getAnimalsAmount() {
        return animals.size();
    }

    Vector2d getAnimalLocalisation(int i) {
        return animals.get(i).getLocalizationOnMap();
    }

    MapDirection getAnimalFacingDirection(int i) {
        return animals.get(i).getFacingDirection();
    }

    public void run() {
        int currentAnimalIndex = 0;

        for (var move : moves) {
            var tmpAnimal = animals.get(currentAnimalIndex);
            map.move(tmpAnimal, move);
            currentAnimalIndex += 1;
            currentAnimalIndex %= animals.size();
        }

    }
}

