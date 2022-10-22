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

    private TestPanel testPanel;

    public FLView() {
        testPanel = new TestPanel();
        mainPanel.add(testPanel, BorderLayout.CENTER);
        radiusSpinner.setValue(10);
        spinner1.setValue(100);
        radiusSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
                int value = (int)spinner.getValue();
                testPanel.changeTargetButtonRadius(value);
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testPanel.startTest((Integer) spinner1.getValue());
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
