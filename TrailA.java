import java.awt.*;
import java.util.LinkedList;

/**
 * @author Atkin Rong
 */

public class TrailA implements TrailVersion {
    public void drawTrail(Graphics g, LinkedList<Shape> shapes) {

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);

        for (int i = 0; i < shapes.size(); i++) {
            Shape s = shapes.get(i);
            drawRectangle(g, s);
            g.setColor(Color.white);
            g.drawString(String.valueOf(i + 1), s.point.x + 20, s.point.y + 20);
        }

    }

    public void drawRectangle(Graphics g, Shape s) {
        g.setColor(s.color);
        g.fillRect(s.point.x, s.point.y, 60, 60);
    }
}
