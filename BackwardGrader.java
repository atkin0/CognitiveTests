/**
 * Grader for SequencingTests
 *
 * @author Atkin Rong
 */

public class BackwardGrader implements Grader {
    @Override
    public boolean grade(String[] expected, String[] actual) {
        if (expected.length != actual.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (!expected[expected.length - 1 - i].equals(actual[i])) return false;
        }
        return true;
    }
}
