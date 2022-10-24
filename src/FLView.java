import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FLView {
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JLabel radiusLabel;
    private JSpinner radiusSpinner;
    private JLabel testRadiusLabel;
    private JSpinner spinner1;
    private JButton start;
    private JSlider slider1;
    private JSlider slider2;

    private TestPanel testPanel;

    public FLView() {
        testPanel = new TestPanel();
        mainPanel.add(testPanel, BorderLayout.CENTER);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testPanel.startTest(slider1.getValue(), slider2.getValue());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fitt's Law");
        frame.setContentPane(new FLView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
