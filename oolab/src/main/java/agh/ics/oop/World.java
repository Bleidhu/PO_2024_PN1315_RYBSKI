package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    public static void main(String[] args) {
//        var animal = new Animal();
//        List<MoveDirection> directions = OptionsParser.parseOptions(args);
//        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
//        WorldMap map = new RectangularMap(4, 5);
//        Simulation simulation = new Simulation(positions, directions, map);
//        simulation.run();
        //f b r l f f r r f f f f f f f f
        try {
            List<MoveDirection> moves = OptionsParser.parseOptions("f b r l f f r r f f f f f f f f".split(" "));
            List<Vector2d> positions = List.of(new Vector2d(1, 1));
            var grassField = new GrassField(10);
            var observer = new ConsoleMapDisplay();
            grassField.addObserver(observer);
            var simulation = new Simulation(positions, moves, grassField);
            simulation.run();
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
