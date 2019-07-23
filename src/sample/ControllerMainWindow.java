package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ControllerMainWindow {

    @FXML
    private Label status_label;


    @FXML
    public void onLoginButtonMethod() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login_window.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Hospital");
        LoginStage.initModality(Modality.APPLICATION_MODAL);
        LoginStage.setScene(new Scene(root, 300, 120));
        LoginStage.show();
    }

    @FXML
    public void onQuitButtonMethod(){
        Stage stage = (Stage) status_label.getScene().getWindow();
        stage.close();
    }
}
