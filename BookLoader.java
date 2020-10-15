package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class BookLoader implements Initializable{

    @FXML
    private ImageView img;

    @FXML
    private TextField pageNo;

    Image im;
    BufferedImage[] images;
    int initPage,curPage,noOfPage;
    public void load(String path){
        PDDocument document= null;
        try {

//            document = PDDocument.load(new File("src/book/H/HydrogenProperties.pdf"));
            int u= path.indexOf('q');
            int k=path.indexOf('&');
            String uu=path.substring(u+2,k);
            URL url=new URL(uu);
            InputStream inputStream= url.openStream();
            Files.copy(inputStream, Paths.get("src/book/H/newPdf.pdf"), StandardCopyOption.REPLACE_EXISTING);
            String xx="src/book/H/newPdf.pdf";
            document = PDDocument.load(new File(xx));
            document.getClass();
            PDFRenderer renderer = new PDFRenderer(document);
            int x= document.getNumberOfPages();
            images= new BufferedImage[x];
            noOfPage=x;
            initPage=0;
            curPage=initPage;
            pageNo.setText(String.valueOf(curPage+1));
            for (int i=0;i<x;i++){
                images[i]=renderer.renderImage(i);
            }
            document.close();
            im= SwingFXUtils.toFXImage(images[0],null);
            img.setImage(im);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void nextPage(ActionEvent event){
        curPage++;
        if(curPage<noOfPage){
            im=SwingFXUtils.toFXImage(images[curPage],null);
//            curPage++;
            img.setImage(im);
            pageNo.setText(String.valueOf(curPage+1));
        }
    }
    public void prevPage(ActionEvent event){
        curPage--;
        if(curPage>0){
            im=SwingFXUtils.toFXImage(images[curPage],null);
            img.setImage(im);
            pageNo.setText(String.valueOf(curPage+1));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        load();
        pageNo.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER){
                int x= Integer.parseInt(pageNo.getText());
                if(x<noOfPage && x>0){
                    im=SwingFXUtils.toFXImage(images[x-1],null);
                    img.setImage(im);
                    curPage=x;
                }
            }
        });
    }
}
