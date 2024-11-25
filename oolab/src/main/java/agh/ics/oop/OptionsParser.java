package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;


public class OptionsParser {
    public static List<MoveDirection> parseOptions(String[] options) {
//        var validMoves = 0;
//        for (var option : options) {
//            if (option.equals("f") || option.equals("b") || option.equals("l")|| option.equals("r")) {
//                validMoves++;
//            }
//        }
//        var moves = new MoveDirection [validMoves];
//        var current_index = 0;
//        for (String option : options) {
//            if (!(option.equals("f") || option.equals("b") || option.equals("l") || option.equals("r"))) {
//                continue;
//            }
//            moves[current_index] = switch (option) {
//                case "f" -> MoveDirection.FORWARD;
//                case "b" -> MoveDirection.BACKWARD;
//                case "l" -> MoveDirection.LEFT;
//                case "r" -> MoveDirection.RIGHT;
//                default -> null;
//            };
//            current_index++;
//        }
        List<MoveDirection> moves = new ArrayList<>();
        for (String option : options) {
            if (!(option.equals("f") || option.equals("b") || option.equals("l") || option.equals("r"))) {
                throw new IllegalArgumentException(option + " is not legal move specification");
            }
            moves.add(switch (option) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            });
        }
        return moves;
    }
}
