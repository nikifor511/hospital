package sample;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerHospitalWindow   {

    private DB_Adapter myDB = new DB_Adapter();

    @FXML
    private ChoiceBox doctor_choice_box;

    @FXML
    public  void initialize() throws SQLException {
        String sql_str = "select \"Surname\", \"Name\" from doctors";
            ResultSet res = myDB.query(sql_str);
            doctor_choice_box.getItems().add("Choose doctor..");
            doctor_choice_box.setValue("Choose doctor..");
            while (res.next()) {
                doctor_choice_box.getItems().add(res.getString("Surname") + " " + res.getString("Name"));
        }

    }
}
