package code_clone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

public class BoxPlotDataProcessor {
    public List<Double> processInputData(double[] dataArray) {
        List<Double> list = new ArrayList<>();
        for (double value : dataArray) {
            list.add(value);
        }
        return list;
    }
}
public class BoxPlotChartRenderer {
    public JPanel createChart(DefaultBoxAndWhiskerCategoryDataset dataset) {
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setUseOutlinePaintForWhiskers(true);
        renderer.setMedianVisible(true);
        renderer.setMeanVisible(false);

        CategoryAxis xAxis = new CategoryAxis("First_Project_Files");
        NumberAxis yAxis = new NumberAxis("Second_Project_Values");
        CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        JFreeChart chart = new JFreeChart(
                "Box-and-Whisker Plot",
                new Font("SansSerif", Font.BOLD, 20),
                plot,
                true
        );
        chart.setBackgroundPaint(Color.LIGHT_GRAY);

        return new ChartPanel(chart);
    }
}
public class BoxPlotUI {
    private final DefaultBoxAndWhiskerCategoryDataset dataset;

    public BoxPlotUI(DefaultBoxAndWhiskerCategoryDataset dataset) {
        this.dataset = dataset;
    }

    public void display() {
        JFrame frame = new JFrame("Clone_Check");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BoxPlotChartRenderer renderer = new BoxPlotChartRenderer();
        JPanel chartPanel = renderer.createChart(dataset);

        chartPanel.setPreferredSize(new Dimension(600, 600));
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
public class BoxAndWhiskerChart {
    public static void main(String[] args) {
        // Simulated data (replace with actual data retrieval logic)
        List<double[]> similarArray = CosineSimilarity.similarArray;
        List<String> fileNames = CloneCheck.ProjectFileName1;

        // Data processing
        BoxPlotDataProcessor dataProcessor = new BoxPlotDataProcessor();
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

        for (int i = 0; i < similarArray.size(); i++) {
            List<Double> processedData = dataProcessor.processInputData(similarArray.get(i));
            dataset.add(processedData, "First_Project vs Second_Project", fileNames.get(i));
        }

        // Display UI
        EventQueue.invokeLater(() -> new BoxPlotUI(dataset).display());
    }
}