package model;

import generators.MeasurementGenerator;
import generators.StateGenerator;

/**
 * Created by Witold on 26.11.2016.
 */
public class Car {
    protected CarState currentState;
    protected CarMeasurement measurement;
    protected double[] measurementNoiseStDev;
    protected double roadCurve;

    public Car(CarState currentState, double[] measurementNoiseStDev, double roadCurve) {
        this.currentState = currentState;
        this.measurementNoiseStDev = measurementNoiseStDev;
        this.roadCurve = roadCurve;
    }

    public void runForward(double dt) {
        currentState = StateGenerator.generateCarRunForward(currentState);
        measurement = MeasurementGenerator.generateCarMeasurement(currentState, roadCurve, measurementNoiseStDev);

    }

    public void runAccelerating(double acceleration, double dt) {
        currentState = StateGenerator.generateCarAccelerating(currentState, acceleration, dt);
        measurement = MeasurementGenerator.generateCarMeasurement(currentState, roadCurve, measurementNoiseStDev);
    }


    public void runTurning(double turnAngle, double dt) {
        currentState = StateGenerator.generateteCarTurning(currentState, turnAngle, dt);
        measurement = MeasurementGenerator.generateCarMeasurement(currentState, roadCurve, measurementNoiseStDev);
    }

    public CarState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CarState currentState) {
        this.currentState = currentState;
    }

    public CarMeasurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(CarMeasurement measurement) {
        this.measurement = measurement;
    }

    public double[] getMeasurementNoiseStDev() {
        return measurementNoiseStDev;
    }

    public void setMeasurementNoiseStDev(double[] measurementNoiseStDev) {
        this.measurementNoiseStDev = measurementNoiseStDev;
    }

    public double getRoadCurve() {
        return roadCurve;
    }

    public void setRoadCurve(double roadCurve) {
        this.roadCurve = roadCurve;
    }
}
