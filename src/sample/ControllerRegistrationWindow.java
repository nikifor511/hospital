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


public class ControllerRegistrationWindow {

    private DB_Adapter myDB = new DB_Adapter();

    @FXML
    private  TextField login_text_field;

    @FXML
    private PasswordField pass_field1, pass_field2;

    @FXML
    public void onCancelButtonMethod() {
        Stage stage = (Stage) login_text_field.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onRegistrationButtonMethod() throws SQLException, IOException {
        if (login_text_field.getLength() == 0 || pass_field1.getLength() == 0  || pass_field2.getLength() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hospital");
            alert.setHeaderText(null);
            alert.setContentText("Fill in all the fields!");
            alert.showAndWait();
            return;
        }
        if (!pass_field1.getText().equals(pass_field2.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hospital");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
            return;
        }
        String sql_str = "select register_user('";
        sql_str = sql_str + login_text_field.getText() + "', '" + pass_field1.getText() + "')";
        ResultSet res = myDB.query(sql_str);
        res.next();
        if (res.getInt("register_user") == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("User " + login_text_field.getText() + " has registered!");
            alert.showAndWait();

            Stage stage = (Stage) login_text_field.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Reistration");
            alert.setHeaderText(null);
            alert.setContentText("Registration error: user " + login_text_field.getText() + " already exists");
            alert.showAndWait();
        }
        System.out.println("RERERG");
        return;
    }
}
