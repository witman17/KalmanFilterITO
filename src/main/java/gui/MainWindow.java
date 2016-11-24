package gui;

import javax.swing.*;

/**
 * Created by Witold on 23.11.2016.
 */
public class MainWindow {
    private JTextField textField1;
    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalman Filter ITO");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
