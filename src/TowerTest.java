import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * @author Atkin Rong
 */

public class TowerTest extends JPanel implements MouseListener, MouseMotionListener, CognitiveTests {

    private final Color colors[] = {Color.red, Color.blue, Color.yellow};
    private final int boxHeight = 150;
    private final int boxWidth = 200;
    private final Point actualBoxPoints[] = {new Point(100, 400), new Point(400, 400), new Point(700, 400)};
    private final int diskHeight = 20;
    private final int diskWidth = 100;
    Stack<Color> actual[] = new Stack[3];
    Stack<Color> expected[] = new Stack[3];
    boolean drawing = false;
    Point currPoint;
    Color currColor;
    int ogStack;
    long startTime;
    int moves;

    public TowerTest() {
        addMouseListener(this);
        addMouseMotionListener(this);
        for (int i = 0; i < 3; i++) {
            actual[i] = new Stack<>();
            expected[i] = new Stack<>();
        }

        LinkedList<Color> colors1 = new LinkedList<>(List.of(colors));
        Random random = new Random();
        int colorIndex;
        int stackIndex;

        for (int i = 0; i < 3; i++) {
            colorIndex = random.nextInt(0, colors1.size());
            stackIndex = random.nextInt(3);
            expected[stackIndex].add(colors1.get(colorIndex));
            colors1.remove(colorIndex);
        }

        colors1 = new LinkedList<>(List.of(colors));
        for (int i = 0; i < 3; i++) {
            colorIndex = random.nextInt(0, colors1.size());
            actual[0].add(colors1.get(colorIndex));
            colors1.remove(colorIndex);
        }
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        //expected
        g.fillRect(100, 100, 200, 150);
        g.fillRect(400, 100, 200, 150);
        g.fillRect(700, 100, 200, 150);

        //actual
        g.fillRect(100, 400, 200, 150);
        g.fillRect(400, 400, 200, 150);
        g.fillRect(700, 400, 200, 150);


        Stack<Color> currStack;
        int x;
        int y;

        for (int i = 0; i < 3; i++) {
            currStack = expected[i];
            x = 150 + 300 * i;
            y = 220;
            for (Color color : currStack) {
                g.setColor(color);
                g.fillRect(x, y, diskWidth, diskHeight);
                y -= 40;
            }
        }

        for (int i = 0; i < 3; i++) {
            currStack = actual[i];
            x = 150 + 300 * i;
            y = 520;
            for (Color color : currStack) {
                g.setColor(color);
                g.fillRect(x, y, diskWidth, diskHeight);
                y -= 40;
            }
        }

        if (drawing) {
            g.setColor(currColor);
            g.fillRect(currPoint.x, currPoint.y, diskWidth, diskHeight);
        }

    }


    private void compareStacks() {
        for (int i = 0; i < 3; i++) {
            if (!actual[i].equals(expected[i])) return;
        }

        end();
    }

    private void end() {
        double timeTaken = (System.currentTimeMillis() - startTime) / 1000.0;
        SummaryTemplate.showSummary(-1, -1, -1, timeTaken, moves);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int diskY1 = 520 - 40 * (actual[0].size() - 1);
        int diskY2 = 520 - 40 * (actual[1].size() - 1);
        int diskY3 = 520 - 40 * (actual[2].size() - 1);

        System.out.println(x + " " + y);

        if (x >= 150 && x <= 150 + diskWidth
                && y >= diskY1 && y <= diskY1 + diskHeight) {
            ogStack = 0;
            drawing = true;
            currColor = actual[0].pop();
        } else if (x >= 450 && x <= 450 + diskWidth
                && y >= diskY2 && y <= diskY2 + diskHeight) {
            ogStack = 1;
            drawing = true;
            currColor = actual[1].pop();
        } else if (x >= 750 && x <= 750 + diskWidth
                && y >= diskY3 && y <= diskY3 + diskHeight) {
            ogStack = 2;
            drawing = true;
            currColor = actual[2].pop();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawing == false) return;
        drawing = false;
        if (e.getX() >= actualBoxPoints[0].x && e.getX() <= actualBoxPoints[0].x + boxWidth
                && e.getY() >= actualBoxPoints[0].y && e.getY() <= actualBoxPoints[0].y + boxHeight) {
            actual[0].add(currColor);
        } else if (e.getX() >= actualBoxPoints[1].x && e.getX() <= actualBoxPoints[1].x + boxWidth
                && e.getY() >= actualBoxPoints[1].y && e.getY() <= actualBoxPoints[1].y + boxHeight) {
            actual[1].add(currColor);
        } else if (e.getX() >= actualBoxPoints[2].x && e.getX() <= actualBoxPoints[2].x + boxWidth
                && e.getY() >= actualBoxPoints[2].y && e.getY() <= actualBoxPoints[2].y + boxHeight) {
            actual[2].add(currColor);
        } else {
            actual[ogStack].add(currColor);
        }
        moves++;
        repaint();
        compareStacks();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawing) {
            currPoint = new Point(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
