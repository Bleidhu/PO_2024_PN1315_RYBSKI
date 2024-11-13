package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.TextMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimulationTest {

    @Test
    public void simulationIsCreated() {
        String animal1 = "tmp1";
        String animal2 = "tmp2";
        List<String> animals = new ArrayList<>(List.of(animal1, animal2));
        List<MoveDirection> moves = new ArrayList<>();
        TextMap map = new TextMap();
        Simulation<String, Integer> simulation = new Simulation<>(animals, moves, map);

        Assertions.assertEquals(animal1, simulation.getAnimal(0));
        Assertions.assertEquals(animal2, simulation.getAnimal(1));
    }

    @Test
    public void simulationRuns() {
        String animal1 = "tmp1";
        String animal2 = "tmp2";
        List<String> animals = new ArrayList<>(List.of(animal1, animal2));
        List<MoveDirection> moves = new ArrayList<>(List.of(MoveDirection.RIGHT));
        TextMap map = new TextMap();
        Simulation<String, Integer> simulation = new Simulation<>(animals, moves, map);

        simulation.run();

        Assertions.assertEquals(animal2, simulation.getAnimalAt(0));
        Assertions.assertEquals(animal1, simulation.getAnimalAt(1));
    }

}
