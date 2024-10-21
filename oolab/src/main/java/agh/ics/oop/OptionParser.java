package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;


public class OptionParser {
    public static MoveDirection[] parseOptions(String[] options) {
        var validMoves = 0;
        for (var option : options) {
            if (option.equals("f") || option.equals("b") || option.equals("l")|| option.equals("r")) {
                validMoves++;
            }
        }
        var moves = new MoveDirection [validMoves];
        var current_index = 0;
        for (String option : options) {
            if (!(option.equals("f") || option.equals("b") || option.equals("l") || option.equals("r"))) {
                continue;
            }
            moves[current_index] = switch (option) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };
            current_index++;
        }
        return moves;
    }
}
