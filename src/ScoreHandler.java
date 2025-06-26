/**
 * @author Atkin Rong
 */

public class ScoreHandler extends SelectionHandler {
    public void handleSelection(String action) {
        String lastSix;
        if (action.length() < 6) {
            lastSix = "notScores";
        } else {
            lastSix = action.substring(action.length() - 6);
        }

        if (lastSix.equals("Scores")) {
            SummaryTemplate.showScores(action + ".txt");
        } else if (nextHandler != null) {
            nextHandler.handleSelection(action);
        }
    }
}
