package model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimulationTest {
    @Test
    void animalsFacingNorthOnCreation() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions);

        var animals = simulation.getAnimals();

        for (Animal animal : animals) {
            Assertions.assertEquals(MapDirection.NORTH, animal.getFacingDirection());
        }
    }

    @Test
    void animalRotateLeft() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));

        directions.add(MoveDirection.LEFT);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        var animals = simulation.getAnimals();

        Assertions.assertEquals(MapDirection.WEST, animals.get(0).getFacingDirection());
    }

    @Test
    void animalRotateRight() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));

        directions.add(MoveDirection.RIGHT);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        var animals = simulation.getAnimals();

        Assertions.assertEquals(MapDirection.EAST, animals.get(0).getFacingDirection());
    }

    @Test
    void animalsCreatedOnCoordinates() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));


        Simulation simulation = new Simulation(positions, directions);
        var animals = simulation.getAnimals();

        for (int i = 0; i < 2; ++i) {
            Assertions.assertEquals(positions.get(i), animals.get(i).getLocalizationOnMap());
        }
    }

    @Test
    void animalMovesForward() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        var finalPosition = new Vector2d(2, 3);
        directions.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        var animals = simulation.getAnimals();

        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());
    }

    @Test
    void animalMovesBackward() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        var finalPosition = new Vector2d(2, 1);
        directions.add(MoveDirection.BACKWARD);
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        var animals = simulation.getAnimals();

        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());
    }

    @Test
    void animalMovesAfterRotation() {
        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(2, 2));
        var finalPosition = new Vector2d(1, 2);
        directions.add(MoveDirection.LEFT);
        directions.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        var animals = simulation.getAnimals();

        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());
    }

    @Test
    void animalWontGoOutOfTheMap() {

        List<MoveDirection> directions = new ArrayList<>();
        List<Vector2d> positions = List.of(new Vector2d(4, 4));
        directions.add(MoveDirection.FORWARD);
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        var animals = simulation.getAnimals();

        //upper and right boundary
        var finalPosition = new Vector2d(4, 4);

        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        directions = List.of(MoveDirection.RIGHT, MoveDirection.FORWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        directions = List.of(MoveDirection.LEFT, MoveDirection.BACKWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        directions = List.of(MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        //bottom and left boundary
        positions = List.of(new Vector2d(0, 0));
        finalPosition = new Vector2d(0, 0);
        directions = List.of(MoveDirection.BACKWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        directions = List.of(MoveDirection.RIGHT, MoveDirection.BACKWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        directions = List.of(MoveDirection.LEFT, MoveDirection.FORWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());

        directions = List.of(MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.FORWARD);
        simulation = new Simulation(positions, directions);
        simulation.run();

        animals = simulation.getAnimals();
        Assertions.assertEquals(finalPosition, animals.get(0).getLocalizationOnMap());
    }

    @Test
    void simulationRunsWithWalidDirections() {
        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        var animals = simulation.getAnimals();

        Assertions.assertEquals(new Vector2d(3, 3), animals.get(0).getLocalizationOnMap());
        Assertions.assertEquals(new Vector2d(4, 4), animals.get(1).getLocalizationOnMap());
        Assertions.assertEquals(MapDirection.WEST, animals.get(0).getFacingDirection());
        Assertions.assertEquals(MapDirection.WEST, animals.get(1).getFacingDirection());
    }

    @Test
    void simulationRunsWithInwalidDirections() {
        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        var animals = simulation.getAnimals();

        Assertions.assertEquals(new Vector2d(4, 4), animals.get(0).getLocalizationOnMap());
        Assertions.assertEquals(new Vector2d(4, 4), animals.get(1).getLocalizationOnMap());
        Assertions.assertEquals(MapDirection.EAST, animals.get(0).getFacingDirection());
        Assertions.assertEquals(MapDirection.EAST, animals.get(1).getFacingDirection());
    }

    @Test
    void inputStringWithWalidMovesIsProperlySimulated() {
        String[] testArgs = {"f", "f", "l", "l", "b", "b"};
        List<MoveDirection> directions = OptionsParser.parseOptions(testArgs);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        var animals = simulation.getAnimals();

        Assertions.assertEquals(new Vector2d(3, 3), animals.get(0).getLocalizationOnMap());
        Assertions.assertEquals(new Vector2d(4, 4), animals.get(1).getLocalizationOnMap());
        Assertions.assertEquals(MapDirection.WEST, animals.get(0).getFacingDirection());
        Assertions.assertEquals(MapDirection.WEST, animals.get(1).getFacingDirection());
    }

    @Test
    void inputStringWithInwalidMovesIsProperlySimulated() {
        String[] testArgs = {"f", "x", "example", "f", "rubbish", "l", "lbr", "l", "b", "b"};
        List<MoveDirection> directions = OptionsParser.parseOptions(testArgs);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        var animals = simulation.getAnimals();

        Assertions.assertEquals(new Vector2d(3, 3), animals.get(0).getLocalizationOnMap());
        Assertions.assertEquals(new Vector2d(4, 4), animals.get(1).getLocalizationOnMap());
        Assertions.assertEquals(MapDirection.WEST, animals.get(0).getFacingDirection());
        Assertions.assertEquals(MapDirection.WEST, animals.get(1).getFacingDirection());
    }

}
