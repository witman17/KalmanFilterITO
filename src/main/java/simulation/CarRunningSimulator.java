package simulation;

import kalmanfilter.KalmanFilter;
import model.Car;
import model.CarState;
import org.ejml.simple.SimpleMatrix;

/**
 * Created by Witold on 26.11.2016.
 */
public class CarRunningSimulator {
    protected KalmanFilter filter;
    protected double deltaTime;
    protected double[][] matrixA = {{1., 1., 0., 0.},
            {0., 1., 0., 0.},
            {0., 0., 1., 0.},
            {0., 0., 0., 1.}};
    protected double[][] matrixH = {{1., 0., 0., 0.},
            {0., 1., 0., 0.},
            {0., 0., 1., 0.},
            {0., 0., 0., 1.}};
    protected double[][] matrixB = {{0.},
            {0.},
            {0.,},
            {1.}};

    SimpleMatrix A, B, Q, R, H;

    public CarRunningSimulator(double[] qNoise, double[] rNoise, double deltaTime) {
        this.deltaTime = deltaTime;
        matrixA[0][1] = deltaTime;
        A = new SimpleMatrix(matrixA);
        B = new SimpleMatrix(matrixB);
        Q = new SimpleMatrix(getDiagonalArray(qNoise));
        R = new SimpleMatrix(getDiagonalArray(rNoise));
        H = new SimpleMatrix(matrixH);
    }


    public SimulationResults simulateRunForward(double Speed, double[] measurementNoise, int stepsNumber) {
        CarState carState = new CarState(0.0, 0.0, Speed, 0.0);
        Car car = new Car(carState, measurementNoise, 0.0);
        filter = new KalmanFilter(A, Q, H, R, carState.getStateVector());
        SimulationResults results = new SimulationResults(stepsNumber);
        for (int i = 0; i < stepsNumber; i++) {
            car.runForward(deltaTime);
            filter.predict();
            filter.filter(car.getMeasurement().getMeasurementVector());
            results.addResult(car.getCurrentState(), filter.getFilterEstimate(), filter.getFilterCovariance());
        }

        return results;
    }

    public SimulationResults simulateRunWithAcceleration(double Speed, double a, double[] measurementNoise, int stepsNumber) {
        CarState carState = new CarState(0.0, 0.0, Speed, 0.0);
        Car car = new Car(carState, measurementNoise, 0.0);
        filter = new KalmanFilter(A, Q, H, R, carState.getStateVector());
        SimulationResults results = new SimulationResults(stepsNumber);
        B.set(2, 0, deltaTime);
        for (int i = 0; i < stepsNumber; i++) {
            car.runAccelerating(a, deltaTime);
            filter.predict(B, new SimpleMatrix(new double[][]{{a}}));
            filter.filter(car.getMeasurement().getMeasurementVector());
            results.addResult(car.getCurrentState(), filter.getFilterEstimate(), filter.getFilterCovariance());
        }
        return results;
    }

    public SimulationResults simulateRunOnCurve(double Speed, double roadAngle, double[] measurementNoise, int stepsNumber) {
        CarState carState = new CarState(0.0, 0.0, Speed, 0.0);
        Car car = new Car(carState, measurementNoise, roadAngle);
        filter = new KalmanFilter(A, Q, H, R, carState.getStateVector());
        SimulationResults results = new SimulationResults(stepsNumber);
        B.set(3, 0, deltaTime);
        for (int i = 0; i < stepsNumber; i++) {
            double angleDiff = roadAngle - car.getCurrentState().getAlfa();
            car.runTurning(angleDiff, deltaTime);
            filter.predict(B, new SimpleMatrix(new double[][]{{angleDiff}}));
            filter.filter(car.getMeasurement().getMeasurementVector());
            results.addResult(car.getCurrentState(), filter.getFilterEstimate(), filter.getFilterCovariance());
        }
        return results;
    }


    protected double[][] getDiagonalArray(double[] values) {
        double[][] array = new double[values.length][values.length];
        for (int i = 0; i < values.length; i++) {
            array[i][i] = values[i];
        }
        return array;
    }


}
