package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.TextMap;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parseOptions(args);
        List<String> inhabitants = new ArrayList<>(List.of("Ala", "ma", "sowoniedźwiedzia"));
        TextMap map = new TextMap();

        Simulation<String, Integer> simulation = new Simulation<String, Integer>(inhabitants, directions, map);
        simulation.run();
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
