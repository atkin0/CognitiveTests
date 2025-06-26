/**
 * @author Atkin Rong
 */

public class TestHandler extends SelectionHandler {
    @Override
    public void handleSelection(String action) {
        Supervisor supervisor = Supervisor.getInstance();

        if (action.equals("digitSpan")) {
            SequencingTest forwardDigitsTest = new SequencingTest(new ForwardGrader(), new DigitsGenerator());
            supervisor.switchPanel(forwardDigitsTest);
            forwardDigitsTest.start();
            ScoreManager.getInstance().setCurrFileName("digitSpanScores.txt");
        } else if (action.equals("bwDigitSpan")) {
            SequencingTest backwardDigitsTest = new SequencingTest(new BackwardGrader(), new DigitsGenerator());
            supervisor.switchPanel(backwardDigitsTest);
            backwardDigitsTest.start();
            ScoreManager.getInstance().setCurrFileName("bwDigitSpanScores.txt");
        } else if (action.equals("sequencing")) {
            SequencingTest lettersNumbersTest = new SequencingTest(new ForwardGrader(), new LetterNumberGenerator());
            supervisor.switchPanel(lettersNumbersTest);
            lettersNumbersTest.start();
            ScoreManager.getInstance().setCurrFileName("sequencingScores.txt");
        } else if (action.equals("blockTapping")) {
            BlockTapTest blockTapTest = new BlockTapTest();
            supervisor.switchPanel(blockTapTest);
            blockTapTest.start();
            ScoreManager.getInstance().setCurrFileName("blockTappingScores.txt");
        } else if (action.equals("stroop")) {
            StroopTest stroopTest = new StroopTest();
            supervisor.switchPanel(stroopTest);
            stroopTest.start();
            ScoreManager.getInstance().setCurrFileName("blockTappingScores.txt");
            ScoreManager.getInstance().setCurrFileName("stroopScores.txt");
        } else if (action.equals("trailMakingA")) {

            TrailMakingTest trailMakingTestA = new TrailMakingTest(new TrailA());
            supervisor.switchPanel(trailMakingTestA);
            trailMakingTestA.start();
            ScoreManager.getInstance().setCurrFileName("trailMakingAScores.txt");
        } else if (action.equals("trailMakingB")) {
            TrailMakingTest trailMakingTestB = new TrailMakingTest(new TrailB());
            supervisor.switchPanel(trailMakingTestB);
            trailMakingTestB.start();
            ScoreManager.getInstance().setCurrFileName("trailMakingBScores.txt");
        } else if (action.equals("tower")) {
            TowerTest towerTest = new TowerTest();
            supervisor.switchPanel(towerTest);
            ScoreManager.getInstance().setCurrFileName("towerScores.txt");
        } else if (nextHandler != null) nextHandler.handleSelection(action);

    }

}
