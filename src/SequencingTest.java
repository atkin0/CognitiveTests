import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Atkin Rong
 */

public class SequencingTest extends JPanel implements CognitiveTests {

    private int correct;
    private int incorrect;
    private int errors;
    private int level = 0;
    private long startTime;
    private double timeTaken;
    private String[] sequence;
    private final JLabel labelDisplay;
    private final JTextField inputBox;
    private Grader grader;
    private SequenceGenerator generator;

    public SequencingTest(Grader grader, SequenceGenerator generator) {
        this.grader = grader;
        this.generator = generator;

        labelDisplay = new JLabel();
        labelDisplay.setForeground(Color.black);
        add(labelDisplay);

        inputBox = new JTextField(20);
        inputBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradeAnswer();
            }
        });
        inputBox.setVisible(false);
        add(inputBox);
    }

    public void start() {
        if (errors >= 3) {
            Supervisor.getInstance().getTimer().stop();
            SummaryTemplate.showSummary(correct, incorrect, level, timeTaken, -1);
        } else {
            viewLevel();
        }
    }

    private void gradeAnswer() {
        Supervisor.getInstance().getTimer().stop();
        String[] actual = inputBox.getText().split("");
        if (grader.grade(sequence, actual)) {
            level++;
            correct++;
            errors = 0;
        } else {
            incorrect++;
            errors++;
        }
        timeTaken += (System.currentTimeMillis() - startTime) / 1000.0;
        inputBox.setVisible(false);
        start();
    }

    private void getAnswer() {
        inputBox.setText("");
        labelDisplay.setVisible(false);
        inputBox.setVisible(true);
        Timer timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gradeAnswer();
            }
        });
        startTime = System.currentTimeMillis();
        Supervisor.getInstance().setTimer(timer);
        timer.start();
    }

    private void viewSequence() {
        sequence = generator.generate(level);
        Timer timer = new Timer(1000, new ActionListener() {
            int i = 0;

            public void actionPerformed(ActionEvent e) {
                if (i >= sequence.length) {
                    ((Timer) e.getSource()).stop();
                    getAnswer();
                } else {
                    labelDisplay.setText(sequence[i]);
                    i++;
                }
            }
        });
        Supervisor.getInstance().setTimer(timer);
        timer.start();
    }

    private void viewLevel() {
        labelDisplay.setText("Level: " + level);
        labelDisplay.setVisible(true);
        Timer timer = new Timer(1500, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                viewSequence();
            }
        });
        Supervisor.getInstance().setTimer(timer);
        timer.start();
    }

}
