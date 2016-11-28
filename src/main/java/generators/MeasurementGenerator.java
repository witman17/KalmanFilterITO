package generators;

import model.CarMeasurement;
import model.CarState;

import java.util.Random;

/**
 * Created by Witold on 26.11.2016.
 */
public class MeasurementGenerator {

    public static CarMeasurement generateCarMeasurement(CarState state, double roadAngle, double[] noiseStantardDev) {
        Random random = new Random();
        CarMeasurement measurement = new CarMeasurement();
        measurement.setX(state.getX() + random.nextGaussian() * noiseStantardDev[0]);
        measurement.setSpeed(Math.sqrt(Math.pow(state.getVx(), 2.0) + Math.pow(state.getVy(), 2.0)) +
                random.nextGaussian() * noiseStantardDev[1]);
        measurement.setAlfa(state.getAlfa() + random.nextGaussian() * noiseStantardDev[2]);
        measurement.setBeta(roadAngle + random.nextGaussian() * noiseStantardDev[3]);
        return measurement;
    }
}
