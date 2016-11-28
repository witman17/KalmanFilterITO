package simulation;

import model.CarState;

import java.util.ArrayList;

/**
 * Created by Witold on 26.11.2016.
 */
public class SimulationResults {
    protected ArrayList<CarState> realStates;
    protected ArrayList<CarState> estimatedStates;
    protected ArrayList<CarState> estimateCovariance;

    public SimulationResults(int NumberOfSteps) {
        realStates = new ArrayList<CarState>(NumberOfSteps);
        estimatedStates = new ArrayList<CarState>(NumberOfSteps);
        estimateCovariance = new ArrayList<CarState>(NumberOfSteps);
    }

    public SimulationResults() {
        realStates = new ArrayList<CarState>();
        estimatedStates = new ArrayList<CarState>();
        estimateCovariance = new ArrayList<CarState>();
    }

    public void addResult(CarState real, CarState estimate, CarState estimateCov) {
        realStates.add(real);
        estimatedStates.add(estimate);
        estimateCovariance.add(estimateCov);
    }

    public double[] getXRealResults() {
        double[] results = new double[realStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = realStates.get(i).getX();
        }
        return results;
    }

    public double[] getVxRealResults() {
        double[] results = new double[realStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = realStates.get(i).getVx();
        }
        return results;
    }

    public double[] getVyRealResults() {
        double[] results = new double[realStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = realStates.get(i).getVy();
        }
        return results;
    }

    public double[] getAlfaRealResults() {
        double[] results = new double[realStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = realStates.get(i).getAlfa();
        }
        return results;
    }

    public double[] getXEstimateResults() {
        double[] results = new double[estimatedStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimatedStates.get(i).getX();
        }
        return results;
    }

    public double[] getVxEstimateResults() {
        double[] results = new double[estimatedStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimatedStates.get(i).getVx();
        }
        return results;
    }

    public double[] getVyEstimateResults() {
        double[] results = new double[estimatedStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimatedStates.get(i).getVy();
        }
        return results;
    }

    public double[] getAlfaEstimateResults() {
        double[] results = new double[estimatedStates.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimatedStates.get(i).getAlfa();
        }
        return results;
    }

    public double[] getXCovarianceResults() {
        double[] results = new double[estimateCovariance.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimateCovariance.get(i).getX();
        }
        return results;
    }

    public double[] getVxCovarianceResults() {
        double[] results = new double[estimateCovariance.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimateCovariance.get(i).getVx();
        }
        return results;
    }

    public double[] getVyCovarianceResults() {
        double[] results = new double[estimateCovariance.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimateCovariance.get(i).getVy();
        }
        return results;
    }

    public double[] getAlfaCovarianceResults() {
        double[] results = new double[estimateCovariance.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = estimateCovariance.get(i).getAlfa();
        }
        return results;
    }


    public ArrayList<CarState> getRealStates() {
        return realStates;
    }

    public void setRealStates(ArrayList<CarState> realStates) {
        this.realStates = realStates;
    }

    public ArrayList<CarState> getEstimatedStates() {
        return estimatedStates;
    }

    public void setEstimatedStates(ArrayList<CarState> estimatedStates) {
        this.estimatedStates = estimatedStates;
    }

    public ArrayList<CarState> getEstimateCovariance() {
        return estimateCovariance;
    }

    public void setEstimateCovariance(ArrayList<CarState> estimateCovariance) {
        this.estimateCovariance = estimateCovariance;
    }
}
