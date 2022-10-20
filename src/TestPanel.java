import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TestPanel extends JPanel {
    private Random random;
    private ArrayList<Dot> dots;
    private int radius;
    private static final int width = 500;
    private static final int height = 500;

    public TestPanel() {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(width, height));
        random = new Random();
        dots = new ArrayList<Dot>();
        radius = 100;
        generateTargetButtons();
    }

    private void generateTargetButtons() {
        int xCenter = width / 2;
        int yCenter = height / 2;
        int newX, newY;
        Dot dot;

        for (int i = 0; i < 360; i+=30) {
            newX = (int) (xCenter + (radius * Math.cos(Math.toRadians(i))));
            newY = (int) (yCenter + (radius * Math.sin(Math.toRadians(i))));
            dot = new Dot(new Point(newX, newY), 10, Color.RED);
            dots.add(dot);
        }

    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Dot d: dots) {
            d.paint(g);
        }
        repaint();
    }

    public void changeTargetButtonRadius(int newRadius) {
        for (Dot d: dots) {
            d.setRadius(newRadius);
        }
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}
