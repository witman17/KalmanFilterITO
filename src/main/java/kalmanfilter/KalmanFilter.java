package kalmanfilter;

import model.CarState;
import org.ejml.simple.SimpleMatrix;

/**
 * Created by Witold on 23.11.2016.
 */
public class KalmanFilter {
    // a priori state
    protected SimpleMatrix xPriori, xPosteriori;
    protected SimpleMatrix PPriori, PPosterioti;
    protected SimpleMatrix A, Q;
    protected SimpleMatrix K, H, R;

    public KalmanFilter(SimpleMatrix A, SimpleMatrix Q, SimpleMatrix H, SimpleMatrix R, SimpleMatrix x0) {
        xPriori = new SimpleMatrix(x0.numRows(), x0.numCols());
        xPosteriori = x0;
        PPriori = new SimpleMatrix(Q.numRows(), Q.numCols());
        PPosterioti = new SimpleMatrix(Q);
        this.A = A;
        this.Q = Q;
        this.H = H;
        this.R = R;
        this.K = new SimpleMatrix(x0.numRows(), R.numCols());
    }

    public void initialize(SimpleMatrix x0, SimpleMatrix P0) {
        xPosteriori = x0;
        PPosterioti = P0;
    }

    public void predict(SimpleMatrix B, SimpleMatrix u) {
        xPriori = A.mult(xPosteriori).plus(B.mult(u));
        PPriori = A.mult(PPosterioti).mult(A.transpose()).plus(Q);
    }

    public void predict() {
        xPriori = A.mult(xPosteriori);
        PPriori = A.mult(PPosterioti).mult(A.transpose()).plus(Q);
    }

    public void filter(SimpleMatrix z) {
        K = PPriori.mult(H.transpose()).mult(H.mult(PPriori).mult(H.transpose()).plus(R).invert());
        xPosteriori = xPriori.plus(K.mult(z.minus(H.mult(xPriori))));
        PPosterioti = PPriori.minus(H.mult(K).mult(PPriori));
    }

    public CarState getFilterEstimate() {
        return new CarState(xPriori.get(0, 0), xPriori.get(1, 0), xPriori.get(2, 0),
                xPriori.get(3, 0));
    }

    public CarState getFilterCovariance() {
        return new CarState(PPriori.get(0, 0), PPriori.get(1, 1), PPriori.get(2, 2),
                PPriori.get(3, 3));
    }

    public SimpleMatrix getxPriori() {
        return xPriori;
    }

    public void setxPriori(SimpleMatrix xPriori) {
        this.xPriori = xPriori;
    }

    public SimpleMatrix getxPosteriori() {
        return xPosteriori;
    }

    public void setxPosteriori(SimpleMatrix xPosteriori) {
        this.xPosteriori = xPosteriori;
    }

    public SimpleMatrix getPPriori() {
        return PPriori;
    }

    public void setPPriori(SimpleMatrix PPriori) {
        this.PPriori = PPriori;
    }

    public SimpleMatrix getPPosterioti() {
        return PPosterioti;
    }

    public void setPPosterioti(SimpleMatrix PPosterioti) {
        this.PPosterioti = PPosterioti;
    }

    public SimpleMatrix getA() {
        return A;
    }

    public void setA(SimpleMatrix a) {
        A = a;
    }

    public SimpleMatrix getQ() {
        return Q;
    }

    public void setQ(SimpleMatrix q) {
        Q = q;
    }


    public SimpleMatrix getK() {
        return K;
    }

    public void setK(SimpleMatrix k) {
        K = k;
    }

    public SimpleMatrix getH() {
        return H;
    }

    public void setH(SimpleMatrix h) {
        H = h;
    }

    public SimpleMatrix getR() {
        return R;
    }

    public void setR(SimpleMatrix r) {
        R = r;
    }
}
