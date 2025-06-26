import java.util.Random;

public class LetterNumberGenerator implements SequenceGenerator {

    @Override
    public String[] generate(int n) {
        Random random = new Random();
        n *= 2;
        n += 2;
        String[] sequence = new String[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) sequence[i] = String.valueOf((char) ('a' + (random.nextInt(26))));
            else sequence[i] = String.valueOf((random.nextInt(10)));
        }
        return sequence;
    }
}
