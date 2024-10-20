package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;

public class OptionParser {
    public static MoveDirection[] parseOptions(String[] options) {

        var validMoves = 0;
        for (int i = 0; i < options.length; i++) {
            if ("fbrl".contains(options[i])) {
                validMoves++;
            }
        }
        var moves = new MoveDirection [validMoves];
        for (int i = 0; i < options.length; i++) {
            if(!"fbrl".contains(options[i]))
            {
                continue;
            }
            moves [i] = switch(options[i]){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };
        }
        return moves;
    }
}
