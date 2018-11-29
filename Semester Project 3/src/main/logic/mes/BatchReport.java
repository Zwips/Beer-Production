package logic.mes;

import acquantiance.IBatch;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.io.File;
import java.io.IOException;

public class BatchReport {

    int Batch;


    public BatchReport(int batchID, String factoryID) throws IOException {

        IBatch batch = MESOutFacade.getInstance().getBatch(batchID, factoryID);

        File file = new File("C:\\test\\test.pdf");
        PDDocument document = PDDocument.load(file);

        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        contentStream.newLineAtOffset(25, 500);

        String text = Integer.toString(batch.getBatchID());

        contentStream.showText(text);

        contentStream.endText();

        System.out.println("Content added");

        contentStream.close();
        document.save(new File("C:\\test\\test.pdf"));
        document.close();


        batch.getBatchID();
        batch.getHumidity();
        batch.getProductType();
        batch.getTemperatures();
        batch.getTotalDiscarded();
        batch.getTotalProduced();
        batch.getVibrations();


    }

    public static void main(String[] args) throws IOException {

    }
}
