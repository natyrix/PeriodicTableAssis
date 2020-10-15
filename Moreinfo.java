package sample;

import com.adobe.acrobat.Viewer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Moreinfo extends Model implements Initializable{
    @FXML
    private WebView more_info;
    @FXML
    private JFXProgressBar pbar;
    @FXML
    private MediaView mediaview;
    @FXML
    private WebView viddd;
    @FXML
    private WebView booklist;
    @FXML
    private JFXListView<String> playlist;
    @FXML
    private Label vidTitle;
    @FXML
    private Slider volconnt;

    @FXML
    private Label volum;

    private Media media;
    private MediaPlayer mediaPlayer;
    private Viewer viewer;


    String idddddd;
//    Connection connection= SqliteConnection.Connector();

    public void backk(ActionEvent event) throws IOException {
//        mediaPlayer.stop();
        ((Node)event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader();
        Stage primaryStage =new Stage();
        Pane root = loader.load(getClass().getResource("sample.fxml").openStream());
        primaryStage.setTitle("Periodic table assistant");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public String getIdddddd(){
        return idddddd;
    }
    public void fillinfo2(String id){
        idddddd=id;
//        String x=null;
//        x=getName(id);
//        int z=x.indexOf(x.charAt(0));
//        int y=x.indexOf('(');
//        String h=x.substring(z,y);
        WebEngine engine = more_info.getEngine();
        //String url = Main.class.getResource("map.html").toExternalForm();
        engine.load("https://en.wikipedia.org/wiki/"+id.toLowerCase());

    }
    public void fillInfo(String id) throws SQLException {
        idddddd=id;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        String x=null;
        x=getName(id);
        int z=x.indexOf(x.charAt(0));
        int y=x.indexOf('(');
        String h=x.substring(z,y);
        WebEngine engine = more_info.getEngine();
        //String url = Main.class.getResource("map.html").toExternalForm();
        engine.load("https://en.wikipedia.org/wiki/"+h.toLowerCase());

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

//        String query = "SELECT * FROM descriptions WHERE id = ?";
//
//        try{
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,id);
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                x = getName(id);
//                x+="\n\n\t\t\t****************INTRODUCTION:****************\n\n" + resultSet.getString(2);
//                x+="\n\n\t\t\t****************OCCURRENCE:****************\n\n" + resultSet.getString(3);
//                x+="\n\n\t\t\t****************PHYSICAL PROPERTIES:****************\n\n" + resultSet.getString(4);
//                x+="\n\n\t\t\t****************CHEMICAL PROPERTIES:****************\n\n" + resultSet.getString(5);
//                x+="\n\n\t\t\t****************PREPARATION AND USES:****************\n\n" + resultSet.getString(6);
//                x+="\n\n\t\t\t****************DISCOVERY:****************\n\n" + resultSet.getString(7);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            preparedStatement.close();
//            resultSet.close();
//        }
//        more_info.setText(x);
    }
    public void loadMedia2(String x){
        WebEngine engine = viddd.getEngine();
        //String url = Main.class.getResource("map.html").toExternalForm();
        engine.load("https://www.youtube.com/results?search_query="+x.toLowerCase());
//        pbar.progressProperty().bind(engine.getLoadWorker().progressProperty());
//        engine.getLoadWorker().stateProperty().addListener(
//                new ChangeListener<Worker.State>() {
//                    @Override
//                    public void changed(ObservableValue observable, Worker.State oldValue, Worker.State newValue) {
//                        if(newValue== Worker.State.SUCCEEDED){
//                            pbar.setVisible(false);
//                        }
//                    }
//                }
//        );

    }
    public void loadMedia(String x) throws SQLException {
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        String t=null;
        t=getName(x);
        int z=t.indexOf(t.charAt(0));
        int y=t.indexOf('(');
        String h=t.substring(z,y);
        WebEngine engine = viddd.getEngine();
        //String url = Main.class.getResource("map.html").toExternalForm();
        engine.load("https://www.youtube.com/results?search_query="+h.toLowerCase());
//        pbar.progressProperty().bind(engine.getLoadWorker().progressProperty());
//
//        engine.getLoadWorker().stateProperty().addListener(
//                new ChangeListener<Worker.State>() {
//                    @Override
//                    public void changed(ObservableValue observable, Worker.State oldValue, Worker.State newValue) {
//                        if(newValue== Worker.State.SUCCEEDED){
//                            pbar.setVisible(false);
//                        }
//                    }
//                }
//        );


        //        String[] listss=getPlaylist(x);
//        String[] links=getLinks(x);
//        if(listss!=null && links!=null){
//            for (int i=1;i<Integer.valueOf(listss[0]);i++){
//                playlist.getItems().add(listss[i]);
//            }
//            vidTitle.setText(listss[1]);
//            String path= new File(links[1]).getAbsolutePath();
//            media= new Media(new File(path).toURI().toString());
//            mediaPlayer= new MediaPlayer(media);
//            mediaview.setMediaPlayer(mediaPlayer);
//            DoubleProperty width=mediaview.fitWidthProperty();
//            DoubleProperty height=mediaview.fitHeightProperty();
//            width.bind(Bindings.selectDouble(mediaview.parentProperty(),"width"));
//            height.bind(Bindings.selectDouble(mediaview.parentProperty(),"height"));
//            volconnt.setValue(mediaPlayer.getVolume() * 100);
//            volum.setText(String.valueOf(volconnt.getValue()));
//            volconnt.valueProperty().addListener(new InvalidationListener() {
//                @Override
//                public void invalidated(Observable observable) {
//                    mediaPlayer.setVolume(volconnt.getValue()/100);
//                    volum.setText(String.valueOf((int)volconnt.getValue()));
//                }
//            });
//            playlist.setOnMouseClicked(e->{
//                mediaPlayer.stop();
//                int i=playlist.getSelectionModel().getSelectedIndex();
//                chgMedia(listss[i+1],links[i+1]);
//            });
//        }

    }
    public void chgMedia(String title,String path){
        vidTitle.setText(title);
        String path2= new File(path).getAbsolutePath();
        media= new Media(new File(path2).toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        mediaview.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }
    public void loadBook(String x) throws SQLException{
//        BookLoader bookLoader= new BookLoader();
//        String [] link= getBookLinks(x);
//        String [] list=getBookList(x);
//        for (int i=1;i<Integer.valueOf(list[0]);i++){
//            booklist.getItems().add(list[i]);
//        }
//        booklist.setOnMouseClicked(e->{
            try {
//                int i= booklist.getSelectionModel().getSelectedIndex();

//                ((Node)e.getSource()).getScene().getWindow().hide();
                FXMLLoader loader=new FXMLLoader();
                Stage primaryStage =new Stage();
                Pane root = loader.load(getClass().getResource("bookk.fxml").openStream());
                BookLoader bookLoader=(BookLoader) loader.getController();
                String xx=null;
                xx=getName(x);
                int z=xx.indexOf(xx.charAt(0));
                int y=xx.indexOf('(');
//                System.out.println(z + "\n"+y + "\n"+xx + "\n" + x);
                String h=xx.substring(z,y);

                WebEngine engine = booklist.getEngine();
                //String url = Main.class.getResource("map.html").toExternalForm();

                engine.load("https://www.google.com/search?q="+h.toLowerCase()+"pdf");
                pbar.progressProperty().bind(engine.getLoadWorker().progressProperty());
                engine.getLoadWorker().stateProperty().addListener(
                        new ChangeListener<Worker.State>() {
                            @Override
                            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                                if(newValue==Worker.State.SCHEDULED){
                                    System.out.println(engine.getLocation());

                                    String j= engine.getLocation();
                                    System.out.println(j.length());
                                    if (j.length()>50){
                                        bookLoader.load(j);
                                        primaryStage.setTitle(j);
                                        primaryStage.setScene(new Scene(root));
                                        primaryStage.initModality(Modality.APPLICATION_MODAL);
                                        primaryStage.show();
                                    }

                                }
                            }
                        }
                );
            } catch (IOException e1) {
                e1.printStackTrace();
            }

    }
    public void play(ActionEvent event ){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    public void pause(ActionEvent event){
        mediaPlayer.pause();
    }
    public void stop(ActionEvent event){
        mediaPlayer.stop();
    }
    public void rwd(ActionEvent event){
        // mediaPlayer.setStartTime(mediaPlayer.getCurrentTime().subtract(Duration.seconds(15)));
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(7)));
    }
   // double x=2;
    public void fwd(ActionEvent event){
//        mediaPlayer.setStartTime(mediaPlayer.getCurrentTime().add(Duration.seconds(15)));
//        mediaPlayer.setRate(x);
//        x++;
        //mediaPlayer.setRate(1);
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(7)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

}
