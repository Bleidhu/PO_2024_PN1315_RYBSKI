package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String args[]){
        System.out.println("System wystartował");
        var moves = OptionParser.parseOptions(args);
        run(moves);
        System.out.println("System zakończył działanie");
    }
    public static void run(MoveDirection args[])
    {
        for(int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case MoveDirection.FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case MoveDirection.BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case MoveDirection.RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case MoveDirection.LEFT-> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
