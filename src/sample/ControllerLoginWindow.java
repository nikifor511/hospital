package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLoginWindow {

    DB_Adapter myDB = new DB_Adapter();

    @FXML
    private TextField login_text_field;

    @FXML
    public void onCancelButtonMethod(){
        Stage stage = (Stage) login_text_field.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLoginButtonMethod() throws SQLException {
        ResultSet res = myDB.query("select login_user('login1', 'pass1')");
        System.out.println(res);

    }
}
