package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller extends Model implements Initializable {
    @FXML
    private Label info_label;

    @FXML
    private Label db_chk;
    @FXML
    private Label info0;
    @FXML
    private Label info2;

    @FXML
    private Label info1;

    @FXML
    private Label info3;

    @FXML
    private Label info4;

    @FXML
    private Label info5;

    @FXML
    private Label info6;

    @FXML
    private Label errsearch;
    @FXML
    private JFXTextField seratxt;

    @FXML
    private Label info7;

    private Object btn;
    private String more = null;
    modelinterface mi;
    interfaceA ia;

    public void info(ActionEvent event) throws IOException, SQLException, NotBoundException {
        btn = event.getSource();
        String string =btn.toString();

        String y =ia.fetchId(string);

//        int a=0,b=0,c=0;
//        char[] x = string.toCharArray();
//        for(int i=0;i<x.length ;i++ ){
//            if(x[i]=='=' || x[i]==',' ){
//              if(x[i]=='='){
//                  a=i;
//                  c++;
//              }
//              else if(x[i]==','){
//                  b=i;
//                  c++;
//              }
//            }
//            else if(c==2) {
//                break;
//            }
//        }
//        if(a!=0 && b!=0){
//            y=string.substring(a+1,b);

            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            Stage primaryStage =new Stage();
            Pane root = loader.load(getClass().getResource("moreinfo.fxml").openStream());
           Moreinfo moreinfo=(Moreinfo) loader.getController();
           moreinfo.idddddd=y;
           moreinfo.loadMedia(y);
            moreinfo.fillInfo(y);
            moreinfo.loadBook(y);
            primaryStage.setTitle(getName(y));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
//        }
//        else {
//           info_label.setText(String.valueOf(a) + " " + String.valueOf(b));
//        }

    }
    public void info2(MouseEvent mouseEvent) throws IOException, SQLException {
        btn = mouseEvent.getSource();
        String string =btn.toString();
        String y = null;
//        int a=0,b=0,c=0;
//        char[] x = string.toCharArray();
//        for(int j=0;j<x.length ;j++ ){
//            if(x[j]=='=' || x[j]==',' ){
//                if(x[j]=='='){
//                    a=j;
//                    c++;
//                }
//                else if(x[j]==','){
//                    b=j;
//                    c++;
//                }
//            }
//            else if(c==2) {
//                break;
//            }
//        }
//        if(a!=0 && b!=0){
            y=ia.fetchId(string);
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            Stage primaryStage =new Stage();
            Pane root = loader.load(getClass().getResource("moreinfo.fxml").openStream());
            Moreinfo moreinfo=(Moreinfo) loader.getController();
            moreinfo.idddddd=y;
            moreinfo.loadMedia2(y);
            moreinfo.fillinfo2(y);
//          moreinfo.loadBook(y);
            primaryStage.setTitle(y);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
//        }
    }
    public void info3(MouseEvent mouseEvent) throws SQLException,RemoteException {
        btn = mouseEvent.getSource();
        String string =btn.toString();
        String y = null;
//        int a=0,b=0,c=0;
//        char[] x = string.toCharArray();
//        for(int j=0;j<x.length ;j++ ){
//            if(x[j]=='=' || x[j]==',' ){
//                if(x[j]=='='){
//                    a=j;
//                    c++;
//                }
//                else if(x[j]==','){
//                    b=j;
//                    c++;
//                }
//            }
//            else if(c==2) {
//                break;
//            }
//        }
//        if(a!=0 && b!=0){
            String [] infos =new String[9];
            y =ia.fetchId(string);
            infos = getInfo(y);
            info0.setText("Element name: " + infos[2]);
            info1.setText("Atomic number: " + infos[1]);
            info2.setText("Atomic weight: " + infos[3]);
            info3.setText("Group: " + infos[4]);
            info4.setText("Electron config: " + infos[5]);
            info5.setText("Date discovered: " + infos[6]);
            info6.setText("Discovered by: " + infos[7]);
            info7.setText("Origin of name: " + infos[8]);
//            more = y;
//        }
    }
    public void info4(MouseEvent mouseEvent) throws SQLException,RemoteException {
        btn = mouseEvent.getSource();
        String string =btn.toString();
        String y = null;
//        int a=0,b=0,c=0;
//        char[] x = string.toCharArray();
//        for(int j=0;j<x.length ;j++ ){
//            if(x[j]=='=' || x[j]==',' ){
//                if(x[j]=='='){
//                    a=j;
//                    c++;
//                }
//                else if(x[j]==','){
//                    b=j;
//                    c++;
//                }
//            }
//            else if(c==2) {
//                break;
//            }
//        }
//        if(a!=0 && b!=0){
            String [] info=new String[2];
            y=ia.fetchId(string);
            info=getinfoGroup(y);

            if(info!=null){
                info0.setText("Group name  " + info[0]);
                info1.setText("Description :");
                info2.setText(info[1]);
                info3.setText(null);
                info4.setText(null);
                info5.setText(null);
                info6.setText(null);
                info7.setText(null);
            }

//        }
    }
    public void searchh(ActionEvent event) throws IOException {
        if(!seratxt.getText().isEmpty()){
            errsearch.setText(null);
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            Stage primaryStage =new Stage();
            Pane root = loader.load(getClass().getResource("searches.fxml").openStream());
            primaryStage.setTitle("Search results");
            Searchres searchres =(Searchres) loader.getController();
            searchres.load(seratxt.getText());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        else {
            errsearch.setText("Search Field can not be empty");
        }

    }
    public void simulaterxn(MouseEvent mouseEvent) throws IOException {
        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader();
        Stage primaryStage =new Stage();
        Pane root = loader.load(getClass().getResource("reaction.fxml").openStream());
        primaryStage.setTitle("Simulate a Reaction");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DbModel dbModel =new DbModel();
        if(dbModel.isDbconnected()){
            db_chk.setText("********");
            db_chk.setTextFill(Color.GREEN);
        }
        else {
            db_chk.setText("********");
            db_chk.setTextFill(Color.RED);
        }
//        modee.setItems(FXCollections.observableArrayList("Online","Offline"));

        seratxt.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER){
                if(!seratxt.getText().isEmpty()){
                    errsearch.setText(null);
                    ((Node)e.getSource()).getScene().getWindow().hide();
                    FXMLLoader loader=new FXMLLoader();
                    Stage primaryStage =new Stage();
                    Pane root = null;
                    try {
                        root = loader.load(getClass().getResource("searches.fxml").openStream());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    primaryStage.setTitle("Search results");
                    Searchres searchres =(Searchres) loader.getController();
                    searchres.load(seratxt.getText());
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                }
                else {
                    errsearch.setText("Search Field can not be empty");
                }
            }
        });
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost",2312);
            ia=(interfaceA) registry.lookup("test1");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

//        Registry registry1 = null;
//        try {
//            registry1 = LocateRegistry.getRegistry("10.4.53.153",2354);
//            mi=(modelinterface) registry1.lookup("mod");
////            System.out.println(mi.getName("H"));
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (NotBoundException e) {
//            e.printStackTrace();
//        }
    }
}
