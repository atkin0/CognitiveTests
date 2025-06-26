import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Atkin Rong
 */

public class ShapesPanel extends JPanel {
    protected LinkedList<Shape> shapes;

    public ShapesPanel() {
        shapes = new LinkedList<>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    protected void drawRectangle(Graphics g, Shape s) {
        g.setColor(s.color);
        g.fillRect(s.point.x, s.point.y, 60, 60);
    }


    protected Point randomUnoccupiedPoint() {
        Random random = new Random();
        Point new_point = new Point(random.nextInt(50, 1250), random.nextInt(50, 680));
        while (isOccupied(new_point)) {
            new_point = new Point(random.nextInt(50, 1250), random.nextInt(50, 680));
        }
        return new_point;
    }

    private boolean isOccupied(Point point) {
        for (Shape s : shapes) {
            if (Math.abs(s.point.x - point.x) < 65 && Math.abs(s.point.y - point.y) < 65) return true;
        }
        return false;
    }
}
