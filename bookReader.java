package sample;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
//import com.asprise.ocr.util.Utils;
public class bookReader  {
    public static String[] readContent(String fname){

        try {
            PDDocument document= PDDocument.load(new File(fname));
            document.getClass();
            if(!document.isEncrypted()){
                PDFTextStripperByArea stripper= new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper textStripper= new PDFTextStripper();

                String pdffile=textStripper.getText(document);
                String [] lines=pdffile.split("\\n");
                return lines;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
