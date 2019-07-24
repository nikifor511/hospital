package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLoginWindow {

    DB_Adapter myDB = new DB_Adapter();

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
        String sql_str = "select login_user('";
        sql_str = sql_str + login_text_field.getText() + "', '" + pass_field
        ResultSet res = myDB.query("select login_user('login1', 'pass1')");
        res.next();
        if (res.getInt("login_user") == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("hospital_window.fxml"));
            Stage HospitalStage = new Stage();
            HospitalStage.setTitle("Hospital");
            HospitalStage.initModality(Modality.APPLICATION_MODAL);
            HospitalStage.setScene(new Scene(root, 600, 400));
            HospitalStage.show();

            Stage stage = (Stage) login_text_field.getScene().getWindow();
            stage.close();
        }
        System.out.println(res.getInt("login_user"));
    }
}
