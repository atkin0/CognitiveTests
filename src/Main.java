import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cognitive test program built using Java Swing.
 *
 * @author Atkin Rong
 */

public class Main extends JFrame implements ActionListener {

    private SelectionHandler selectionHandler;

    public static void main(String[] args) {
        Main app = new Main();
        app.setSize(1400, 800);
        app.setTitle("Cognitive Tests");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setResizable(false);
    }

    public Main() {
        Supervisor.getInstance().setMain(this);
        JMenuBar menuBar = new JMenuBar();

        JMenu tests = new JMenu("tests");
        JMenu scores = new JMenu("scores");
        JMenuItem about = new JMenuItem("about");

        about.addActionListener(this);

        menuBar.add(tests);
        menuBar.add(scores);
        menuBar.add(about);

        JMenuItem digitSpan = new JMenuItem("digitSpan");
        JMenuItem bwDigitSpan = new JMenuItem("bwDigitSpan");
        JMenuItem sequencing = new JMenuItem("sequencing");
        JMenuItem blockTapping = new JMenuItem("blockTapping");
        JMenuItem stroop = new JMenuItem("stroop");
        JMenuItem trailMakingA = new JMenuItem("trailMakingA");
        JMenuItem trailMakingB = new JMenuItem("trailMakingB");
        JMenuItem tower = new JMenuItem("tower");

        digitSpan.addActionListener(this);
        bwDigitSpan.addActionListener(this);
        sequencing.addActionListener(this);
        blockTapping.addActionListener(this);
        stroop.addActionListener(this);
        trailMakingA.addActionListener(this);
        trailMakingB.addActionListener(this);
        tower.addActionListener(this);

        tests.add(digitSpan);
        tests.add(bwDigitSpan);
        tests.add(sequencing);
        tests.add(blockTapping);
        tests.add(stroop);
        tests.add(trailMakingA);
        tests.add(trailMakingB);
        tests.add(tower);

        JMenuItem digitSpanScores = new JMenuItem("digitSpanScores");
        JMenuItem bwDigitSpanScores = new JMenuItem("bwDigitSpanScores");
        JMenuItem sequencingScores = new JMenuItem("sequencingScores");
        JMenuItem blockTappingScores = new JMenuItem("blockTappingScores");
        JMenuItem stroopScores = new JMenuItem("stroopScores");
        JMenuItem trailMakingAScores = new JMenuItem("trailMakingAScores");
        JMenuItem trailMakingBScores = new JMenuItem("trailMakingBScores");
        JMenuItem towerScores = new JMenuItem("towerScores");

        digitSpanScores.addActionListener(this);
        bwDigitSpanScores.addActionListener(this);
        sequencingScores.addActionListener(this);
        blockTappingScores.addActionListener(this);
        stroopScores.addActionListener(this);
        trailMakingAScores.addActionListener(this);
        trailMakingBScores.addActionListener(this);
        towerScores.addActionListener(this);

        scores.add(digitSpanScores);
        scores.add(bwDigitSpanScores);
        scores.add(sequencingScores);
        scores.add(blockTappingScores);
        scores.add(stroopScores);
        scores.add(trailMakingAScores);
        scores.add(trailMakingBScores);
        scores.add(towerScores);

        setJMenuBar(menuBar);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        String userInput = JOptionPane.showInputDialog(this, "What is ur name?: ");
        ScoreManager.getInstance().setUser(userInput);

        SelectionHandler testSelection = new TestHandler();
        SelectionHandler scoreSelection = new ScoreHandler();
        testSelection.setNextHandler(scoreSelection);
        selectionHandler = testSelection;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        String action = source.getText();

        if (action.equals("about")) {
            JOptionPane.showMessageDialog(null, "Hello, this is a program for CognitiveTests");
        }

        selectionHandler.handleSelection(action);
    }
}
