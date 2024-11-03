import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {
    @Test
    void translateSingular() {
        String[] testArguments = {"f"};
        List<MoveDirection> expected = Arrays.asList(MoveDirection.FORWARD);
        assertEquals(expected, OptionsParser.parseOptions(testArguments));

        testArguments[0] = "b";
        expected.set(0, MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parseOptions(testArguments));

        testArguments[0] = "l";
        expected.set(0, MoveDirection.LEFT);
        assertEquals(expected, OptionsParser.parseOptions(testArguments));

        testArguments[0] = "r";
        expected.set(0, MoveDirection.RIGHT);
        assertEquals(expected, OptionsParser.parseOptions(testArguments));

        testArguments[0] = "x";
        List<MoveDirection> expected_empty = new ArrayList<>();
        assertEquals(expected_empty, OptionsParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleValid() {
        String[] testArguments = {"f", "r", "l", "b"};
        List<MoveDirection> expected = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleInvalid() {
        String[] testArguments = {"x", "y", "z", "m"};
        List<MoveDirection> expected = new ArrayList<>();
        assertEquals(expected, OptionsParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleInvalidCombinationsOfValid() {
        String[] testArguments = {"frl", "bl", "lx", "lf", "fl", "rl", "lr"};
        List<MoveDirection> expected = new ArrayList<>();
        assertEquals(expected, OptionsParser.parseOptions(testArguments));
    }

    @Test
    void translateMultipleValidWithInvalidInside() {
        String[] testArguments = {"f", "r", "l", "b", "xyz", "assimov", "Yugo", "l", "b"};
        List<MoveDirection> expected = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parseOptions(testArguments));
    }

}
