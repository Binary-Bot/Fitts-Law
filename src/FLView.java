import javax.swing.*;
import java.awt.*;

public class FLView {
    private JPanel mainPanel;
    private JPanel controlPanel;

    private TestPanel testPanel;

    public FLView() {
        testPanel = new TestPanel();
        mainPanel.add(testPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fitt's Law");
        frame.setContentPane(new FLView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
