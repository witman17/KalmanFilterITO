package gui.chart;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;

/**
 * Created by Witold on 28.11.2016.
 */
public class Chart {
    private double[] xSeries;
    private double[] ySeriesReal;
    private double[] ySeriesEstimate;
    private double[] ySeriesCov;
    private XYChart chart;

    public Chart(String title, double dt, double[] ySeriesReal, double[] ySeriesEstimate, double[] ySeriesCov) {
        this.xSeries = xSeries;
        this.ySeriesReal = ySeriesReal;
        this.ySeriesEstimate = ySeriesEstimate;
        this.ySeriesCov = ySeriesCov;
        this.xSeries = new double[ySeriesReal.length];
        for (int i = 0; i < xSeries.length; i++) {
            xSeries[i] = i * dt;
        }
        chart = new XYChartBuilder().width(800).height(600).title(title).xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.addSeries("rzeczywiste ".concat(title), xSeries, ySeriesReal);
        chart.addSeries("estymowane ".concat(title), xSeries, ySeriesEstimate, ySeriesCov);
    }

    public JPanel getChartPanel() {
        return new XChartPanel<XYChart>(chart);
    }
}
