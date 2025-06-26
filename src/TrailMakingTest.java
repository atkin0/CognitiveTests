import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

/**
 * @author Atkin Rong
 */

public class TrailMakingTest extends ShapesPanel implements CognitiveTests, MouseListener, MouseMotionListener {

    private LinkedList<Line> lines;
    private boolean pressSuccessful;
    private Point pressedPoint, currPoint;
    private boolean drawing;
    private int currShape;
    private long startTime;
    private TrailVersion trail;


    public TrailMakingTest(TrailVersion trail) {
        this.trail = trail;

        lines = new LinkedList<>();
        pressSuccessful = false;

        addMouseListener(this);
        addMouseMotionListener(this);

        for (int i = 0; i < 10; i++) {
            Point newPoint = randomUnoccupiedPoint();
            shapes.add(new Shape(newPoint, Color.black));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        trail.drawTrail(g, shapes);
        for (Line l : lines) {
            drawLine(g, l);
        }
        if (drawing) {
            drawLine(g, new Line(pressedPoint, currPoint, Color.red));
        }
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
    }

    private void drawLine(Graphics g, Line l) {
        g.setColor(l.color);
        g.drawLine(l.p1.x, l.p1.y, l.p2.x, l.p2.y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point currShapePoint = shapes.get(currShape).point;
        if (currShapePoint.x <= e.getX() && e.getX() <= currShapePoint.x + 60 &&
                currShapePoint.y <= e.getY() && e.getY() <= currShapePoint.y + 60) {
            pressSuccessful = true;
            drawing = true;
            pressedPoint = new Point(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point currShapePoint = shapes.get(currShape + 1).point;
        if (pressSuccessful && currShapePoint.x <= e.getX() && e.getX() <= currShapePoint.x + 60 &&
                currShapePoint.y <= e.getY() && e.getY() <= currShapePoint.y + 60) {
            pressSuccessful = false;
            drawing = false;
            lines.add(new Line(pressedPoint, new Point(e.getX(), e.getY()), Color.black));
            currShape++;
            repaint();
        }

        if (currShape + 1 == shapes.size()) {
            double timeTaken = (System.currentTimeMillis() - startTime) / 1000.0;
            SummaryTemplate.showSummary(-1, -1, -1, timeTaken, -1);
        }
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
