package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Reaction extends Model implements Initializable {
    @FXML
    private ChoiceBox<String> elements;


    String [] subscripts={"\u2081","\u2082","\u2083","\u2084","\u2085","\u2086","\u2087"};
    String selected,first,reactantts;
    String lastResult="",errr="",lastrestowiki;
    int qw=0;
    @FXML
    private Label reactions;
    @FXML
    private JFXProgressBar pbar;

    @FXML
    private JFXButton calcres;

    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton moreinfo;

    @FXML
    private Label results;

    @FXML
    private Label errrdisc;

    @FXML
    private WebView webmore;
    @FXML
    void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader();
        Stage primaryStage =new Stage();
        Pane root = loader.load(getClass().getResource("sample.fxml").openStream());
        primaryStage.setTitle("Periodic table assistant");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @FXML
    void selectedElements(ActionEvent event) throws InterruptedException {
        Thread getGetSelected=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    selected= getId(elements.getValue());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        getGetSelected.start();
        getGetSelected.join();

        if(qw==0){
            reactantts=selected;
            reactions.setText(selected);
            qw++;
        }
        else {
            qw++;
            reactantts="";
            reactantts +=reactions.getText() + "+"+selected;
            reactions.setText(reactantts);
        }

        if(qw==2){
            addbtn.setDisable(true);
            calcres.setDisable(false);
        }
        elements.getItems().clear();

        Thread fillPossibleReactants = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String [] x= getElements();
                    for (int i=0;i<x.length;i++){
                        elements.getItems().add(x[i]);
                    }
                    first=x[0];
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        fillPossibleReactants.start();
        fillPossibleReactants.join();
        elements.setValue(first);
//      addIons();

    }
    @FXML
    void seeResults(ActionEvent event) {
        String x=reactions.getText();

        Thread resultts= new Thread(new Runnable() {
            @Override
            public void run() {
               String u=x.replace('+',':');
                String[] h=u.split(":");

                try {
                    if(h[0].equals("He")||h[1].equals("He")){
                        errr=h[0] +" Can not be Reactant";
                    }
                    if(h[0].equals(h[1])){
                        if(checkDiatomic(h[0])){
                            errr ="";
                            lastResult=h[0]+subscripts[1];
                            lastrestowiki=h[0]+"2";
                        }
                        else {
                            lastResult="";
                            errr=h[0] +" Can not be Diatomic";
                        }
                    }
                    else {
                        lastResult="";
                        int c=getIonn(h[0]);
                        int d= getIonn(h[1]);
                        if (c>4 && d >4){
                            c=8-c;
                            d=8-d;
                            if(d>c){
                                int temp=d;
                                d=c;
                                c=temp;
                                String temp1=h[0];
                                h[0]=h[1];
                                h[1]=temp1;
                            }
                            if(c!=0&&d!=0){
                                lastResult=h[0] + subscripts[d-1]+h[1]+subscripts[c-1];
                                if(d==1||c==1){
                                    if(d==1){
                                        lastrestowiki=h[0]+h[1]+c;
                                    }
                                    else {
                                        lastrestowiki=h[0]+d+h[1];
                                    }
                                }else {
                                    lastrestowiki=h[0] +d+h[1]+c;
                                }
                            }
                            else {
                                errr=h[0]+" And "+h[1] +" Can not React";
                            }
                        }
                        else if(c<4 && d<4){
                            lastResult="";
                            errr="";
                            if(c==1&&d==1){
                                if(h[0].equals("H")||h[1].equals("H")){
                                    if(h[0].equals("H")){
                                        String temp1=h[0];
                                        h[0]=h[1];
                                        h[1]=temp1;
                                    }
                                    lastResult=h[0]+h[1];
                                    lastrestowiki=h[0]+h[1];
                                }
                                else {
                                    errr=h[0]+" And "+h[1] +" Can not React";
                                }
                            }
                        }
                        else if(c>4 && d<4){
                            lastResult="";
                            errr="";
                            int temp=d;
                            d=c;
                            c=temp;
                            d=8-d;
                            String temp1=h[0];
                            h[0]=h[1];
                            h[1]=temp1;
                            if(d!=0){
                                lastResult=h[0]+subscripts[d-1]+h[1]+subscripts[c-1];
                                if(d==1||c==1){
                                    if(d==1){
                                        lastrestowiki=h[0]+h[1]+c;
                                    }
                                    else {
                                        lastrestowiki=h[0]+d+h[1];
                                    }
                                }else {
                                    lastrestowiki=h[0] +d+h[1]+c;
                                }
                            }
                            else {
                                errr=h[0]+" And "+h[1] +" Can not React";
                            }
                        }
                        else if(c<4 && d>4){
                            lastResult="";
                            d=8-d;
                            if(d!=0){
                                lastResult=h[0]+subscripts[d-1]+h[1]+subscripts[c-1];
                                if(d==1||c==1){
                                    if(d==1){
                                        lastrestowiki=h[0]+h[1]+c;
                                    }
                                    else {
                                        lastrestowiki=h[0]+d+h[1];
                                    }
                                }else {
                                    lastrestowiki=h[0] +d+h[1]+c;
                                }
                            }
                            else {
                                errr=h[0]+" And "+h[1] +" Can not React";
                            }
                        }
                        else if(c==4 || d==4){
                            lastResult="";
                            if(c==4 && d==4){
                                d=1;
                                lastResult=h[0]+subscripts[d-1]+h[1]+subscripts[c-1];
                                lastrestowiki=h[0] +h[1]+c;
                            }
                            else{
                                if(c>4 || d>4){
                                    if(c>d){
                                        int temp=d;
                                        d=c;
                                        c=temp;
                                        d=8-d;
                                        String temp1=h[0];
                                        h[0]=h[1];
                                        h[1]=temp1;
                                    }
                                    else {
                                        d=8-d;
                                    }
                                    if(c%d==0){
                                        d=d/2;
                                        c=c/2;
                                    }
                                    if(d!=0){
                                        lastResult+=h[0]+subscripts[d-1]+h[1]+subscripts[c-1];
                                        if(d==1||c==1){
                                            if(d==1){
                                                lastrestowiki=h[0]+h[1]+c;
                                            }
                                            else {
                                                lastrestowiki=h[0]+d+h[1];
                                            }
                                        }else {
                                            lastrestowiki=h[0] +d+h[1]+c;
                                        }
                                    }
                                    else {
                                        errr=h[0]+" And "+h[1] +" Can not React";
                                    }
                                }
                                else {
                                    if(c<d){
                                        int temp=d;
                                        d=c;
                                        c=temp;
                                        String temp1=h[0];
                                        h[0]=h[1];
                                        h[1]=temp1;
                                    }
//                                    if(c%d==0){
//                                        d=d/2;
//                                        c=c/2;
//                                    }
                                    lastResult+=h[0]+subscripts[d-1]+h[1]+subscripts[c-1];
                                    if(d==1||c==1){
                                        if(d==1){
                                            lastrestowiki=h[0]+h[1]+c;
                                        }
                                        else {
                                            lastrestowiki=h[0]+d+h[1];
                                        }
                                    }else {
                                        lastrestowiki=h[0] +d+h[1]+c;
                                    }
                                }
                            }
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        resultts.start();
        try {
            resultts.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        errrdisc.setText(errr);
        results.setText(lastResult);
        moreinfo.setDisable(false);
    }
    public void setMoreinfo(ActionEvent event){
        pbar.setVisible(true);
        WebEngine engine = webmore.getEngine();
        engine.load("https://en.wikipedia.org/wiki/"+lastrestowiki);
        pbar.progressProperty().bind(engine.getLoadWorker().progressProperty());

        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue observable, Worker.State oldValue, Worker.State newValue) {
                        if(newValue== Worker.State.SUCCEEDED){
                            pbar.setVisible(false);
                        }
                    }
                }
        );
    }
    public void clear(ActionEvent event){

        try {
            qw=0;
            moreinfo.setDisable(true);
            errrdisc.setText(null);
            addbtn.setDisable(false);
            calcres.setDisable(true);
            elements.getItems().clear();
            reactions.setText(null);
            results.setText(null);
            String x[]=getElements();
            elements.setValue(x[0]);
            elements.getItems().addAll(x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String x[]=getElements();
            elements.setValue(x[0]);
            elements.getItems().addAll(x);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
