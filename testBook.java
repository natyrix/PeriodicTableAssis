package sample;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

class ImageImplement extends JPanel {
    private Image img;
    public ImageImplement(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    } public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
public class testBook extends JFrame {
//    static boolean vis=true;
    JButton button= new JButton("Next");
    int rept=0;
    int pno=1;
    JFrame frame= new JFrame();
    JPanel jPanel= new JPanel();
    JLabel label= new JLabel("Page Number:  ");
    JTextField textField= new JTextField(10);
    JButton button2= new JButton("Go");
    static ImageImplement imageImplement;
    public testBook(String path) throws IOException {
        jPanel.setLayout(new FlowLayout());
        jPanel.add(label);
        jPanel.add(textField);
        jPanel.add(button2);
        setLayout(new BorderLayout());
        PDDocument document= PDDocument.load(new File(path));
        document.getClass();
        PDFRenderer renderer = new PDFRenderer(document);
        int x= document.getNumberOfPages();
        BufferedImage [] images= new BufferedImage[x];
        ImageImplement [] panel=new ImageImplement[x];
        for (int i=0;i<x;i++){
            images[i]= renderer.renderImage(i);
            panel[i] = new ImageImplement(new ImageIcon(images[i]).getImage());
        }

        add( getImage(imageImplement,panel),BorderLayout.CENTER);
        add(button,BorderLayout.EAST);
        add(jPanel,BorderLayout.SOUTH);
        textField.setText(String.valueOf(pno));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pno<x){
                    imageImplement=panel[pno];
                    pno++;
                    textField.setText(String.valueOf(pno));
//                    pno++;
                    setVisible(false);
                    chngPage();
                }
                else {
                    chngPage(null);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int u=Integer.valueOf(textField.getText());
                if(u<x && u>0){
                    imageImplement = panel[u-1];
                    setVisible(false);
                    chngPage();
                    pno=u;
//                    pno++;
                }
            }
        });
        setVisible(true);
        setSize(1000,1000);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        document.close();
    }
    public ImageImplement getImage(ImageImplement imageimp,ImageImplement[] panels){
        if(imageimp==null){
            return panels[0];
        }
        return imageimp;
    }
    public void chngPage(){
        if(rept==0){
            frame.setVisible(true);
            rept++;
        }
        else {
            frame.setVisible(false);
            frame=null;
            frame= new JFrame();
            frame.setVisible(true);
        }
        jPanel.setLayout(new FlowLayout());
        jPanel.add(label);
        jPanel.add(textField);
        jPanel.add(button2);
        frame.setLayout(new BorderLayout());
        frame.add(jPanel,BorderLayout.SOUTH);
        frame.add(getImage(imageImplement,null),BorderLayout.CENTER);
        frame.add(button,BorderLayout.EAST);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void chngPage(String x){
        if(rept==0){
            frame.setVisible(true);
            rept++;
        }
        else {
            frame.setVisible(false);
            frame=null;
            frame= new JFrame();
            frame.setVisible(true);
        }
        jPanel.setLayout(new FlowLayout());
        jPanel.add(label);
        jPanel.add(textField);
        jPanel.add(button2);
        frame.setLayout(new BorderLayout());
        frame.add(jPanel,BorderLayout.SOUTH);
        frame.add(getImage(imageImplement,null),BorderLayout.CENTER);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if(x==null){
            imageImplement=null;
        }
    }
}

