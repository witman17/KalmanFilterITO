package gui;

import gui.chart.Chart;
import simulation.CarRunningSimulator;
import simulation.SimulationResults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Witold on 23.11.2016.
 */
public class MainWindow extends JFrame {
    private JPanel panel1;
    private JComboBox simulationTypeBox;
    private JTextField startingSpeedField;
    private JTextField accelerationField;
    private JTextField stepsField;
    private JTextField deltaTimeField;
    private JTextField xProcCovField;
    private JTextField vxProcCovField;
    private JTextField vyProcCovField;
    private JTextField alfaProcCovField;
    private JTextField xMeasCovField;
    private JTextField vMeasCovField;
    private JTextField alfaMeasCovField;
    private JTextField betaMeasCovField;
    private JButton startButton;
    private JTextField roadAngleField;
    //--
    private int stepsNumber;
    private double dtPerStep;
    private double startSpeed;
    private double acceleration;
    private double roadAngle;
    private double[] processingCov;
    private double[] measurementCov;

    public MainWindow(String title) throws HeadlessException {
        super(title);
        this.initPoles();
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        simulationTypeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (simulationTypeBox.getSelectedIndex() == 1 || simulationTypeBox.getSelectedIndex() == 2)
                    accelerationField.setEnabled(true);
                else
                    accelerationField.setEnabled(false);
                if (simulationTypeBox.getSelectedIndex() == 3)
                    roadAngleField.setEnabled(true);
                else
                    roadAngleField.setEnabled(false);
            }
        });
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationResults results;
                collectFormData();
                CarRunningSimulator sim = new CarRunningSimulator(processingCov, measurementCov, dtPerStep);
                int option = simulationTypeBox.getSelectedIndex();
                switch (option) {
                    case 0:
                        results = sim.simulateRunForward(startSpeed, measurementCov, stepsNumber);
                        break;
                    case 1:
                        results = sim.simulateRunWithAcceleration(startSpeed, Math.abs(acceleration), measurementCov, stepsNumber);
                        break;
                    case 2:
                        results = sim.simulateRunWithAcceleration(startSpeed, -acceleration, measurementCov, stepsNumber);
                        break;
                    case 3:
                        results = sim.simulateRunOnCurve(startSpeed, roadAngle, measurementCov, stepsNumber);
                        break;
//                    case 4: break;
                    default:
                        results = new SimulationResults();
                }
                Chart x = new Chart("X", dtPerStep, results.getXRealResults(), results.getXEstimateResults(),
                        results.getXCovarianceResults());
                Chart vx = new Chart("Vx", dtPerStep, results.getVxRealResults(), results.getVxEstimateResults(),
                        results.getVxCovarianceResults());
                Chart vy = new Chart("Vy", dtPerStep, results.getVyRealResults(), results.getVyEstimateResults(),
                        results.getVyCovarianceResults());
                Chart alfa = new Chart("Alfa", dtPerStep, results.getAlfaRealResults(), results.getAlfaEstimateResults(),
                        results.getAlfaCovarianceResults());
                new TestResults("Wyniki", x.getChartPanel(), vx.getChartPanel(), vy.getChartPanel(), alfa.getChartPanel());
            }
        });
    }

    public static void main(String[] args) {
        new MainWindow("Kalman Filter ITO");

    }

    public void initPoles() {
        simulationTypeBox.addItem(new String("Jazda na wprost"));
        simulationTypeBox.addItem(new String("Jazda na wprost, przyspieszanie."));
        simulationTypeBox.addItem(new String("Jazda na wprost, hamowanie"));
        simulationTypeBox.addItem(new String("Jazda na po łuku"));
        simulationTypeBox.addItem(new String("Zmiana pasa"));
        accelerationField.setEnabled(false);
        roadAngleField.setEnabled(false);
        processingCov = new double[4];
        measurementCov = new double[4];
    }

    public void collectFormData() {
        try {
            stepsNumber = Integer.parseInt(stepsField.getText());
            dtPerStep = Double.parseDouble(deltaTimeField.getText());
            startSpeed = Double.parseDouble(startingSpeedField.getText());
            if (accelerationField.isEnabled())
                acceleration = Double.parseDouble(accelerationField.getText());
            else
                acceleration = 0.0;
            if (roadAngleField.isEnabled())
                roadAngle = Double.parseDouble(roadAngleField.getText());
            else
                roadAngle = 0.0;
            processingCov[0] = Double.parseDouble(xProcCovField.getText());
            processingCov[1] = Double.parseDouble(vxProcCovField.getText());
            processingCov[2] = Double.parseDouble(vyProcCovField.getText());
            processingCov[3] = Double.parseDouble(alfaProcCovField.getText());
            measurementCov[0] = Double.parseDouble(xMeasCovField.getText());
            measurementCov[1] = Double.parseDouble(vMeasCovField.getText());
            measurementCov[2] = Double.parseDouble(alfaMeasCovField.getText());
            measurementCov[3] = Double.parseDouble(betaMeasCovField.getText());
        } catch (Exception e) {
        }
    }

}
