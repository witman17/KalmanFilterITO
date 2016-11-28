package generators;

import model.CarState;

/**
 * Created by Witold on 26.11.2016.
 */
public class StateGenerator {

    public static CarState generateCarRunForward(CarState prevState) {
        return new CarState(prevState);
    }

    public static CarState generateCarAccelerating(CarState prevState, double a, double dt) {
        CarState newState = new CarState(prevState);
        newState.setVx(prevState.getVx() + a * dt * Math.sin(Math.toRadians(prevState.getAlfa())));
        newState.setVy(prevState.getVy() + a * dt * Math.cos(Math.toRadians(prevState.getAlfa())));
        newState.setX(prevState.getX() + newState.getVx() * dt);
        if (a < 0 && newState.getVy() <= 0)
            newState.setVy(0);
        return newState;
    }


    public static CarState generateteCarTurning(CarState prevState, double angleChange, double dt) {
        double alfa = prevState.getAlfa() + angleChange * dt;
        double V = Math.sqrt(Math.pow(prevState.getVx(), 2.0) + Math.pow(prevState.getVy(), 2.0));
        double vx = V * Math.sin(Math.toRadians(alfa));
        double vy = V * Math.cos(Math.toRadians(alfa));
        double x = prevState.getX() + vx * dt;
        return new CarState(x, vx, vy, alfa);
    }

}
