package model;

import org.ejml.simple.SimpleMatrix;

/**
 * Created by Witold on 26.11.2016.
 */
public class CarMeasurement {
    protected double x;
    protected double speed;
    // steering wheel angle
    // wychylenie kierownicy
    protected double alfa;
    // curvature of road
    // krzywizna drogi
    protected double beta;

    public CarMeasurement(double x, double speed, double alfa, double beta) {
        this.alfa = alfa;
        this.speed = speed;
        this.x = x;
        this.beta = beta;
    }

    public CarMeasurement() {
    }

    public SimpleMatrix getMeasurementVector() {
        SimpleMatrix matrix = new SimpleMatrix(4, 1);
        matrix.set(0, 0, x);
        matrix.set(1, 0, speed * Math.sin(Math.toRadians(alfa)));
        matrix.set(2, 0, speed * Math.cos(Math.toRadians(alfa)));
        matrix.set(3, 0, alfa);
        return matrix;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}
