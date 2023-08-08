import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerApp {
    // Time flies like an arrow... and our counter keeps track of it.
    private static int count = 0;
    private static JLabel counterLabel;

    public static void main(String[] args) {
        // Adding the Nimbus Look and Feel.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Now, let's put a frame around it, like a nice picture.
        JFrame frame = new JFrame("Timer App");
        frame.setBackground(new Color(70, 70, 70));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridBagLayout());

        // Label me, baby!
        counterLabel = new JLabel("Seconds: 0");
        counterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        counterLabel.setForeground(new Color(255, 255, 255));

        // This little button makes things happen.
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setPreferredSize(new Dimension(100, 40));
        startButton.setBackground(new Color(52, 152, 219));
        startButton.setForeground(Color.WHITE);

        // Layout adjustments.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        frame.add(counterLabel, constraints);
        constraints.gridy = 1;
        frame.add(startButton, constraints);

        startButton.addActionListener(new ActionListener() {
            private Timer timer;

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Start".equals(e.getActionCommand())) {
                    startButton.setText("Stop");
                    count = 0;
                    counterLabel.setText("Seconds: " + count);
                    if (timer != null) {
                        timer.stop();
                    }
                    timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            count++;
                            counterLabel.setText("Seconds: " + count);
                        }
                    });
                    timer.start();
                } else {
                    startButton.setText("Start");
                    if (timer != null) {
                        timer.stop();
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
