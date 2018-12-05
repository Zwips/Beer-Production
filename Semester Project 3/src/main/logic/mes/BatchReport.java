package logic.mes;

import acquantiance.IBatch;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.util.*;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import java.awt.image.BufferedImage;
import org.jfree.data.xy.XYDataset;

public class BatchReport implements Serializable{

    public BatchReport(int batchID, String factoryID) throws IOException {

        IBatch batch = MESOutFacade.getInstance().getBatch(batchID, factoryID);
        File file = new File("Batch_report" + Integer.toString(batch.getBatchID()) +".pdf");

        PDDocument d = new PDDocument();
        PDPage page = new PDPage();
        PDPage page1 = new PDPage();
        d.addPage(page);
        d.addPage(page1);

        d.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(d, page);
        contentStream.beginText();

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 30);
        contentStream.setLeading(14.5F);
        contentStream.newLineAtOffset(30, 750);

        String header = "Batch report";
        String time = "Report generated: " + new Date().toString();

        String text = "Batch ID: " + Integer.toString(batch.getBatchID());
        String text1 ="Factory: " + factoryID;
        String text2 = "Total produced: " + Integer.toString(batch.getTotalProduced());
        String text3 = "Total losses: " + Integer.toString(batch.getTotalDiscarded());
        String text4 = "Type: " + batch.getProductType().getType();



        contentStream.showText(header);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
        contentStream.showText(time);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(text);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(text1);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(text2);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(text3);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(text4);

        contentStream.endText();
        contentStream.close();

        d.getPage(1);
        contentStream = new PDPageContentStream(d, page1);

        BufferedImage chartImageVibration = createVibrationTimeChart(createVibrationTimeSeriesDataset(batch.getBatchID(),factoryID)).createBufferedImage(500,200);
        PDImageXObject pdfChartImageVibration = JPEGFactory.createFromImage(d, chartImageVibration, 1f);
        contentStream.drawImage(pdfChartImageVibration, 50, 500);

        BufferedImage chartImageHumidity = createHumidityTimeChart(createHumidityTimeSeriesDataset(batch.getBatchID(),factoryID)).createBufferedImage(500,200);
        PDImageXObject pdfChartImageHumidity = JPEGFactory.createFromImage(d, chartImageHumidity, 1f);
        contentStream.drawImage(pdfChartImageHumidity, 50, 280);

        BufferedImage chartImageTemperature = createTemperatureTimeChart(createTemperatureTimeSeriesDataset(batch.getBatchID(),factoryID)).createBufferedImage(500,200);
        PDImageXObject pdfChartImageTemperature = JPEGFactory.createFromImage(d, chartImageTemperature, 1f);
        contentStream.drawImage(pdfChartImageTemperature, 50, 60);

        //System.out.println("Content added");

        contentStream.close();
        d.save(file);
        d.close();
        MESOutFacade.getInstance().saveBatchReport(batchID, factoryID, file);
    }

    public XYDataset createVibrationTimeSeriesDataset(int batchID, String factoryID){
        IBatch batch = MESOutFacade.getInstance().getBatch(batchID, factoryID);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries ts = new TimeSeries("PBS");


        for (Map.Entry<Date, Float> dateFloatEntry : batch.getVibrations().entrySet()) {
            Date myDate = dateFloatEntry.getKey();
            ts.add(new Second(myDate), dateFloatEntry.getValue());
        }
        dataset.addSeries(ts);
        return dataset;
    }

    public XYDataset createHumidityTimeSeriesDataset(int batchID, String factoryID){
        IBatch batch = MESOutFacade.getInstance().getBatch(batchID, factoryID);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries ts = new TimeSeries("Humidity in percent");


        for (Map.Entry<Date, Float> dateFloatEntry : batch.getHumidity().entrySet()) {
            Date myDate = dateFloatEntry.getKey();
            ts.add(new Second(myDate), dateFloatEntry.getValue());
        }
        dataset.addSeries(ts);
        return dataset;
    }

    public XYDataset createTemperatureTimeSeriesDataset(int batchID, String factoryID){
        IBatch batch = MESOutFacade.getInstance().getBatch(batchID, factoryID);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries ts = new TimeSeries("Celcius");


        for (Map.Entry<Date, Float> dateFloatEntry : batch.getTemperatures().entrySet()) {
            Date myDate = dateFloatEntry.getKey();
            ts.add(new Second(myDate), dateFloatEntry.getValue());
        }
        dataset.addSeries(ts);
        return dataset;
    }

    public JFreeChart createVibrationTimeChart (XYDataset dataset)throws NumberFormatException{
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Vibrations",
                "Time",
                "PBS",
                dataset
        );
        return chart;
    }

    public JFreeChart createTemperatureTimeChart (XYDataset dataset)throws NumberFormatException{
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Temperature",
                "Time",
                "Celcius",
                dataset
        );
        return chart;
    }

    public JFreeChart createHumidityTimeChart (XYDataset dataset)throws NumberFormatException{
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Humidity",
                "Time",
                "%",
                dataset
        );
        return chart;
    }
}
