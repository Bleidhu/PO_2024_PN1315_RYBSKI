import agh.ics.oop.OptionParser;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OptionParserTest {
    @Test
    void translateSingular() {
        String[] testArguments = {"f"};
        MoveDirection[] expected = {MoveDirection.FORWARD};
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));

        testArguments[0] = "b";
        expected[0] = MoveDirection.BACKWARD;
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));

        testArguments[0] = "l";
        expected[0] = MoveDirection.LEFT;
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));

        testArguments[0] = "r";
        expected[0] = MoveDirection.RIGHT;
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));

        testArguments[0] = "x";
        MoveDirection[] expected_empty = {};
        assertArrayEquals(expected_empty, OptionParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleValid() {
        String[] testArguments = {"f", "r", "l", "b"};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD};
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleInvalid() {
        String[] testArguments = {"x", "y", "z", "m"};
        MoveDirection[] expected = {};
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleInvalidCombinationsOfValid() {
        String[] testArguments = {"frl", "bl", "lx", "lf", "fl", "rl", "lr"};
        MoveDirection[] expected = {};
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleValidWithInvalidInside() {
        String[] testArguments = {"f", "r", "l", "b", "xyz", "assimov", "Yugo", "l", "b"};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.BACKWARD};
        assertArrayEquals(expected, OptionParser.parseOptions(testArguments));
    }

}
