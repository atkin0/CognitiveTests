import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author Atkin Rong
 */

public class StroopTest extends JPanel implements CognitiveTests, ActionListener {


    private final int ROUNDS_PER_CATEGORY = 7;
    private final int NUMBER_OF_COLORS = 5;
    private final String[] WORDS = {"Mouse", "Keyboard", "Monitor", "Laptop", "PC"};
    private final Color[] COLORS = {Color.yellow, Color.blue, Color.red, Color.green, Color.pink};
    private final String[] COLOR_STRINGS = {"yellow", "blue", "red", "green", "pink"};
    private final Point[] POSITIONS = {new Point(120, 500), new Point(240, 500), new Point(360, 500), new Point(480, 500), new Point(600, 500)};
    private final JButton[] COLOR_BUTTONS = {new JButton(), new JButton(), new JButton(), new JButton(), new JButton()};
    private final JLabel text = new JLabel();
    private final Random random = new Random();
    private int currentColor;
    private int round;
    private boolean answeredCorrect;
    private long startTime;
    private int incorrect;
    private int level;

    public StroopTest() {

        setLayout(null);

        text.setSize(500, 200);
        text.setLocation(200, 200);
        Font font = new Font("Arial", Font.PLAIN, 50);
        text.setFont(font);
        add(text);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            COLOR_BUTTONS[i].setSize(100, 100);
            COLOR_BUTTONS[i].setLocation(POSITIONS[i]);
            COLOR_BUTTONS[i].setBackground(COLORS[i]);
            COLOR_BUTTONS[i].addActionListener(this);
            COLOR_BUTTONS[i].setActionCommand("" + i);
            add(COLOR_BUTTONS[i]);
        }
    }


    @Override
    public void start() {
        level = 1;
        round = 1;
        answeredCorrect = false;
        showRandomWord();

        Timer timer = new Timer(100, e -> {
            if (level > 3) {
                Supervisor.getInstance().getTimer().stop();
                end();
            } else {
                if (round < ROUNDS_PER_CATEGORY) {
                    if (answeredCorrect) {
                        answeredCorrect = false;
                        round++;
                        if (level == 1) showRandomWord();
                        if (level == 2) showCorrectColor();
                        if (level == 3) showWrongColor();
                    }
                } else {
                    level++;
                    round = 0;
                }
            }

        });
        Supervisor.getInstance().setTimer(timer);
        timer.start();
        startTime = System.currentTimeMillis();
    }


    private void end() {
        double timeTaken = (System.currentTimeMillis() - startTime) / 1000.0;
        SummaryTemplate.showSummary(-1, incorrect, -1, timeTaken, -1);
    }


    private void showCorrectColor() {
        int index = random.nextInt(0, 5);
        currentColor = index;

        text.setForeground(COLORS[index]);
        text.setText(COLOR_STRINGS[index]);
    }

    private void showWrongColor() {
        int colorIndex = random.nextInt(0, 5);
        currentColor = colorIndex;

        int colorStringIndex;

        do {
            colorStringIndex = random.nextInt(0, 5);
        }
        while (colorStringIndex == colorIndex);

        text.setForeground(COLORS[colorIndex]);
        text.setText(COLOR_STRINGS[colorStringIndex]);
    }

    private void showRandomWord() {
        int colorIndex = random.nextInt(0, 5);
        currentColor = colorIndex;

        int wordIndex = random.nextInt(0, 5);

        text.setForeground(COLORS[colorIndex]);
        text.setText(WORDS[wordIndex]);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int colorPressed = Integer.parseInt(e.getActionCommand());
        if (colorPressed == currentColor) answeredCorrect = true;
        else incorrect++;
    }
}
