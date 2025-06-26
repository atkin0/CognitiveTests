import javax.swing.*;

/**
 * @author Atkin Rong
 */

public abstract class SummaryTemplate extends JPanel {

    abstract void scoreSummary(int correct, int incorrect, int level, double timeTaken, int moves);

    abstract void scoreboard(String filename);

    public static void showSummary(int correct, int incorrect, int level, double timeTaken, int moves) {
        SummaryPanel summaryPanel = new SummaryPanel();
        summaryPanel.scoreSummary(correct, incorrect, level, timeTaken, moves);
        Supervisor.getInstance().switchPanel(summaryPanel);
    }

    public static void showScores(String filename) {
        SummaryPanel summaryPanel = new SummaryPanel();
        summaryPanel.scoreboard(filename);
        Supervisor.getInstance().switchPanel(summaryPanel);
    }

}
