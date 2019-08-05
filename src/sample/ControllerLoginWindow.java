package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLoginWindow {

    private DB_Adapter myDB = new DB_Adapter();

    @FXML
    private TextField login_text_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    public void onCancelButtonMethod(){
        Stage stage = (Stage) login_text_field.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLoginButtonMethod() throws SQLException, IOException {
        if (login_text_field.getLength() == 0 || pass_field.getLength() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Fill in all the fields!");
            alert.showAndWait();
            return;
        }
        String sql_str = "select login_user('";
        sql_str = sql_str + login_text_field.getText() + "', '" + pass_field.getText() + "')";
        ResultSet res = myDB.query(sql_str);
        res.next();

        switch (res.getInt("login_user")) {
            case (1):
                Parent root = FXMLLoader.load(getClass().getResource("registry_window.fxml"));
                Stage HospitalStage = new Stage();
                HospitalStage.setTitle("Registry");
                HospitalStage.initModality(Modality.APPLICATION_MODAL);
                HospitalStage.setScene(new Scene(root, 600, 400));
                HospitalStage.show();

                Stage stage = (Stage) login_text_field.getScene().getWindow();
                stage.close();
                break;

            case(2):
                break;

            case(3):
                break;

            default:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Check your login/password");
                alert.showAndWait();
                break;
        }
    }
}
