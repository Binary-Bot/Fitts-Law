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
    private static final int width = 500;
    private static final int height = 500;
    private final Color bgColor;
    private Dot visibleDot;
    private int start;
    private int trial;
    private long sTime;
    private long totalTime;
    private JLabel label;

    public TestPanel() {
        bgColor = Color.DARK_GRAY;
        setBackground(bgColor);
        setPreferredSize(new Dimension(width, height));
        random = new Random();
        dots = new ArrayList<Dot>();
        radius = 200;
        trial = -1;
        targetRadius = 10;
        totalTime = 0;
        generateTargetButtons();
        start = random.nextInt(dots.size());
        visibleDot = dots.get(start);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                sTime = System.currentTimeMillis();
                showNextDot(new Point(e.getX(), e.getY()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                totalTime += System.currentTimeMillis() - sTime;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void startTest(int radius) {
        this.radius = radius;
        generateTargetButtons();
        start = random.nextInt(dots.size());
        visibleDot = dots.get(start);
        trial = 0;
        totalTime = 0;
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
        if (trial <= 24 && trial >=0) {
            visibleDot.setColor(Color.RED);
            visibleDot.paint(g);
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
            visibleDot = dots.get(start);
            trial++;
        }
        if (trial == 24){
            JLabel label1 = new JLabel();
            System.out.println("showNextDot");
            float iod = (2 *targetRadius)/radius;
            float avgMotionTime = (totalTime/24);
            System.out.println(iod);
            String s = String.format("Index of Difficulty: %f \n Average Motion Time: %f", iod, avgMotionTime);
            label1.setText(s);
            label1.setFont(new Font("Fira Code", Font.BOLD, 36));
            label1.setForeground(Color.GRAY);
            this.add(label1);
        }
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
