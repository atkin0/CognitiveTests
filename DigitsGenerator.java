import java.util.Random;

public class DigitsGenerator implements SequenceGenerator {
    @Override
    public String[] generate(int n) {
        Random random = new Random();
        n += 2;
        String[] sequence = new String[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = String.valueOf((random.nextInt(10)));
        }
        return sequence;
    }
}
