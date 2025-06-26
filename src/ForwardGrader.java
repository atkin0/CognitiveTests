/**
 * Grader for Sequencing Tests
 *
 * @author Atkin Rong
 */

public class ForwardGrader implements Grader {
    @Override
    public boolean grade(String[] expected, String[] actual) {
        if (expected.length != actual.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(actual[i])) return false;
        }
        return true;
    }
}
