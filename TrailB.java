import java.awt.*;
import java.util.LinkedList;

/**
 * @author Atkin Rong
 */

public class TrailB implements TrailVersion {

    public void drawTrail(Graphics g, LinkedList<Shape> shapes) {
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);

        for (int i = 0; i < shapes.size(); i++) {
            Shape s = shapes.get(i);
            drawCircle(g, s);
            g.setColor(Color.white);
            if (i % 2 == 0) g.drawString(String.valueOf(i / 2 + 1), s.point.x + 20, s.point.y + 20);
            else g.drawString(String.valueOf((char) ('a' + i / 2)), s.point.x + 20, s.point.y + 20);
        }
    }

    public void drawCircle(Graphics g, Shape s) {
        g.setColor(s.color);
        g.fillOval(s.point.x, s.point.y, 60, 60);
    }
}
