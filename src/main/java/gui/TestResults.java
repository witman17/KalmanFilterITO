package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Witold on 28.11.2016.
 */
public class TestResults extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;

    public TestResults(String title, JPanel x, JPanel vx, JPanel vy, JPanel alfa) throws HeadlessException {
        super(title);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabbedPane1.add(x);
        tabbedPane1.add(vx);
        tabbedPane1.add(vy);
        tabbedPane1.add(alfa);
        pack();
        setVisible(true);
    }

}
