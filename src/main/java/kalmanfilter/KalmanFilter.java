package kalmanfilter;

import org.ejml.data.DenseMatrix64F;

/**
 * Created by Witold on 23.11.2016.
 */
public class KalmanFilter {
    // a priori state
    protected DenseMatrix64F xPriori, xPosteriori;
    protected DenseMatrix64F PPriori, PPosterioti;
    protected DenseMatrix64F A, B, Q;
    protected DenseMatrix64F u;
    protected DenseMatrix64F K, R;

    public KalmanFilter() {
    }

    public void predict(){

    }

    public void filter(DenseMatrix64F z){

    }
}
