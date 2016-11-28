package model;

import org.ejml.simple.SimpleMatrix;

/**
 * Created by Witold on 26.11.2016.
 */
public class CarState {
    protected double x;
    protected double Vx;
    protected double Vy;
    protected double alfa;


    public CarState(double x, double vx, double vy, double alfa) {
        this.x = x;
        this.alfa = alfa;
        Vx = vx;
        Vy = vy;
    }

    public CarState(CarState carState) {
        this.x = carState.x;
        this.alfa = carState.alfa;
        this.Vx = carState.Vx;
        this.Vy = carState.Vy;
    }

    public SimpleMatrix getStateVector() {
        SimpleMatrix matrix = new SimpleMatrix(4, 1);
        matrix.set(0, x);
        matrix.set(1, Vx);
        matrix.set(2, Vy);
        matrix.set(3, alfa);
        return matrix;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getVx() {
        return Vx;
    }

    public void setVx(double vx) {
        Vx = vx;
    }

    public double getVy() {
        return Vy;
    }

    public void setVy(double vy) {
        Vy = vy;
    }
}
