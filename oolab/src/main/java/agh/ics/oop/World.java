package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.util.ConsoleMapDisplay;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> moves = OptionsParser.parseOptions("f b r l f f r r f f f f f f f f".split(" "));
            List<Vector2d> positions = List.of(new Vector2d(1, 1));
            var grassField = new GrassField(10, 0);
            var rectangularMap = new RectangularMap(10, 10, 1);
            var observer = new ConsoleMapDisplay();
            grassField.addObserver(observer);
            rectangularMap.addObserver(observer);
            var simulation = new Simulation(positions, moves, grassField);
            var simulation2 = new Simulation(positions, moves, rectangularMap);
            var simulationEngine = new SimulationEngine(List.of(simulation, simulation2));
            simulationEngine.runAsync();
            simulationEngine.awaitSimulationEnd();
            System.out.println("System zakończył działanie");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
            return;
        }

    }

    public static void run(MoveDirection[] args) {
        for (var move : args) {
            switch (move) {
                case MoveDirection.FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case MoveDirection.BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case MoveDirection.RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case MoveDirection.LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
