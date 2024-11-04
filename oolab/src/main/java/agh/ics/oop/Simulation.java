package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moves) {
        for (var position : startingPositions) {
            animals.add(new Animal(position));
        }
        this.moves = moves;
    }

    public int getAnimalsAmount() {
        return animals.size();
    }

    public Vector2d getAnimalLocalisation(int i) {
        return animals.get(i).getLocalizationOnMap();
    }

    public MapDirection getAnimalFacingDirection(int i) {
        return animals.get(i).getFacingDirection();
    }

    public void run() {
        int currentAnimalIndex = 0;

        for (var move : moves) {
            var tmpAnimal = animals.get(currentAnimalIndex);
            tmpAnimal.move(move);
            System.out.println(String.format("Zwierzę %d : %s", currentAnimalIndex, tmpAnimal.toString().split(" ")[1]));
            currentAnimalIndex += 1;
            currentAnimalIndex %= animals.size();
        }

    }
}

