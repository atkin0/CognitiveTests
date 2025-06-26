import javax.swing.*;

/**
 * Manage interactions between Frame and Panel
 *
 * @author Atkin Rong
 */

public class Supervisor {
    private static Supervisor instance;
    private JFrame main;
    private Timer timer;

    public static Supervisor getInstance() {
        if (instance == null) {
            instance = new Supervisor();
        }
        return instance;
    }

    public void setMain(JFrame main) {
        this.main = main;
    }

    public void switchPanel(JPanel panel) {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        main.getContentPane().removeAll();
        main.add(panel);
        main.revalidate();
        main.repaint();
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer() {
        return timer;
    }
}
