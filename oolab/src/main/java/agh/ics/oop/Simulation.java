package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> moves = new ArrayList<>();

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> movesFromFunction) {
        for (var position : startingPositions) {
            animals.add(new Animal(position));
        }
        this.moves = movesFromFunction;
    }

    public List<Animal> getAnimals() {
        return animals;
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

