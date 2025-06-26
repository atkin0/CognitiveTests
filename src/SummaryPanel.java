import javax.swing.*;
import java.util.LinkedList;

/**
 * Score Displaying
 *
 * @author Atkin Rong
 */

public class SummaryPanel extends SummaryTemplate {

    //use -1 if no such variable to display
    public void scoreSummary(int correct, int incorrect, int level, double timeTaken, int moves) {
        if (correct != -1) add(new JLabel("Correct: " + correct));
        if (incorrect != -1) add(new JLabel("Incorrect: " + incorrect));
        if (level != -1) add(new JLabel("Level: " + level));
        if (timeTaken != -1) add(new JLabel("Time Taken: " + timeTaken));
        if (moves != -1) add(new JLabel("Moves: " + moves));
        repaint();

        ScoreManager.getInstance().writeData(correct, incorrect, level, timeTaken);
    }

    public void scoreboard(String filename) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        LinkedList<String> scoreList = ScoreManager.getInstance().readData(filename);
        for (int i = 0; i < Math.min(20, scoreList.size()); i++) {
            add(new JLabel(scoreList.get(i)));
        }
    }

}
