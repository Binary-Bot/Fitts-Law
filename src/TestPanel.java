import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


public class TestPanel extends JPanel {
    private Random random;
    private ArrayList<Dot> dots;
    private int radius;
    private int targetRadius;
    private static final int width = 700;
    private static final int height = 700;
    private final Color bgColor;
    private Dot visibleDot;
    private int start;
    private int trial;
    private long sTime;
    private long totalTime;
    private JLabel label;
    private JLabel label2;


    public TestPanel() {
        bgColor = Color.DARK_GRAY;
        setBackground(bgColor);
        setPreferredSize(new Dimension(width, height));
        random = new Random();
        dots = new ArrayList<Dot>();
        radius = 100;
        targetRadius = 10;
        trial = -1;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                showNextDot(new Point(e.getX(), e.getY()));
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
        });
    }

    public void startTest(int targetRadius, int radius) {
        this.removeAll();
        dots.clear();
        this.radius = radius;
        this.targetRadius = targetRadius;
        trial = 0;
        totalTime = 0;
        generateTargetButtons();
        start = random.nextInt(dots.size());
        visibleDot = dots.get(start);
    }

    private void generateTargetButtons() {
        int xCenter = width / 2;
        int yCenter = height / 2;
        int newX, newY;
        Dot dot;
        for (int i = 0; i < 360; i+=30) {
            newX = (int) (xCenter + (radius * Math.cos(Math.toRadians(i))));
            newY = (int) (yCenter + (radius * Math.sin(Math.toRadians(i))));
            dot = new Dot(new Point(newX, newY), targetRadius, bgColor);
            dots.add(dot);
        }

    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (trial == -1) {
            for (Dot dot : dots) {
                dot.paint(g);
            }
        }
        if (visibleDot != null) {
            if (trial <= 24 && trial >= 0) {
                visibleDot.setColor(Color.RED);
                visibleDot.paint(g);
            }
        }
        repaint();
    }

    private void showNextDot(Point p) {
        if (visibleDot.isInside(p)) {
            if (trial % 2 == 0) {
                start = 1 + (start - 1 + 6) % 12;
            } else {
                start = 1 + (start - 1 + 7) % 12;
            }
            if (start == 12) start = 0;
            if (trial == 0) {
                totalTime = 0;
            } else {
                totalTime += System.currentTimeMillis() - sTime;
            }
            visibleDot = dots.get(start);
            trial++;
            sTime = System.currentTimeMillis();
        }
        if (trial == 23){
            JPanel panel = new JPanel();
            label = new JLabel();
            float iod = (float) Math.log((2 *radius)/targetRadius);
            float avgMotionTime = (float) (totalTime/24);
            String s = String.format("Index of Difficulty: %f", iod, avgMotionTime);
            label.setText(s);
            label.setFont(new Font("Fira Code", Font.BOLD, 28));
            label.setForeground(Color.GRAY);
            panel.add(label, BorderLayout.NORTH);
            label2 = new JLabel();
            s = String.format("Average Motion Time: %f",avgMotionTime);
            label2.setText(s);
            label2.setFont(new Font("Fira Code", Font.BOLD, 28));
            label2.setForeground(Color.GRAY);
            panel.add(label2, BorderLayout.SOUTH);
            panel.setPreferredSize(new Dimension(500, 200));
            this.add(panel, BorderLayout.CENTER);
        }
    }

    public void changeTargetButtonRadius(int newRadius) {
        this.targetRadius = newRadius;
        for (Dot d: dots) {
            d.setRadius(newRadius);
        }
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}
