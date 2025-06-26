import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Corsi Block-Tapping Test
 *
 * @author Atkin Rong
 */

public class BlockTapTest extends ShapesPanel implements MouseListener {

    private int level;
    private int currUserBlock;
    private long startTime;
    private double timeTaken;
    private boolean showingSequence;

    public BlockTapTest() {
        addMouseListener(this);
        for (int i = 0; i < 9; i++) {
            Point newPoint = randomUnoccupiedPoint();
            shapes.add(new Shape(newPoint, Color.black));
        }
    }

    public void start() {
        if (level >= 8) {
            SummaryPanel.showSummary(-1, -1, level + 1, timeTaken, -1);
        } else {
            Timer timer = new Timer(500, e -> {
                viewSequence();
            });
            timer.setRepeats(false);
            Supervisor.getInstance().setTimer(timer);
            timer.start();
            currUserBlock = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            drawRectangle(g, s);
        }
    }

    private void viewSequence() {
        Timer timer = new Timer(800, new ActionListener() {
            int nBlock = 0;

            public void actionPerformed(ActionEvent e) {
                if (nBlock > level) {
                    if (nBlock - 1 >= 0) shapes.get(nBlock - 1).color = Color.black;
                    repaint();
                    ((Timer) e.getSource()).stop();
                    startTime = System.currentTimeMillis();
                    showingSequence = false;
                } else {
                    if (nBlock - 1 >= 0) shapes.get(nBlock - 1).color = Color.black;
                    shapes.get(nBlock).color = Color.yellow;
                    repaint();
                    nBlock++;
                }
            }
        });
        showingSequence = true;
        Supervisor.getInstance().setTimer(timer);
        timer.start();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (showingSequence) {
            return;
        }

        Point currPoint = shapes.get(currUserBlock).point;
        if (currPoint.x <= e.getX() && e.getX() <= currPoint.x + 60 && currPoint.y <= e.getY() && e.getY() <= currPoint.y + 60) {
            Shape s = shapes.get(currUserBlock);
            s.color = Color.green;
            repaint();
            Timer timer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    s.color = Color.black;
                    repaint();
                }
            });
            timer.setRepeats(false);
            Supervisor.getInstance().setTimer(timer);
            timer.start();
            currUserBlock++;
        } else {
            timeTaken += (System.currentTimeMillis() - startTime) / 1000.0;
            SummaryTemplate.showSummary(-1, -1, level + 1, timeTaken, -1);
        }

        if (currUserBlock == level + 1) {
            timeTaken += (System.currentTimeMillis() - startTime) / 1000.0;
            level++;
            start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
