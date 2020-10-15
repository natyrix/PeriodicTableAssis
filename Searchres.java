package sample;

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
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Searchres implements Initializable{
    @FXML
    private WebView webresult;
    @FXML
    private JFXProgressBar pbar;
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
    public void load(String x){
        WebEngine engine = webresult.getEngine();
        engine.load("https://en.wikipedia.org/wiki/"+x);
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
