package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<T> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap<T, P> map;

    public Simulation(List<T> animals, List<MoveDirection> moves, WorldMap<T, P> map) {
        this.map = map;
        this.moves = moves;

        for (var animalToBeAdded : animals) {
            if (map.place(animalToBeAdded))
                this.animals.add(animalToBeAdded);
        }


    }


    public void run() {
        int currentAnimalIndex = 0;

        for (var move : moves) {
            var tmpAnimal = animals.get(currentAnimalIndex);
            map.move(tmpAnimal, move);
            System.out.println(map);
            currentAnimalIndex += 1;
            currentAnimalIndex %= animals.size();
        }

    }
}

