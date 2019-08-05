package sample;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ControllerRegistryWindow {

    @FXML
    private TextField search_patient_text_field;

    @FXML
    private TableView patients_table_view;

    private DB_Adapter myDB = new DB_Adapter();

    public void onLogoutButtonMethod() {
        System.out.println("logout");

    }

    public void onSearchPatientButtonMethod() throws SQLException, IOException {
        String fio_str = search_patient_text_field.getText().replaceAll("[\\s]{2,}", " ");
        fio_str = fio_str.trim();
        String[] FIO_str = fio_str.split(" ", 3);
        String sql_str;
        switch (FIO_str.length) {
            case(2):
                sql_str = "select * from patients where \"Surname\" = '" + FIO_str[0] + "' and \"Name\" = '" + FIO_str[1] + "'";
                break;
            case(3):
                sql_str = "select * from patients where \"Surname\" = '" + FIO_str[0] + "' and \"Name\" = '" + FIO_str[1] + "' and \"Patronymic\" = '" + FIO_str[2] + "'";
                break;
            default:
                sql_str = "select * from patients where \"Surname\" = '" + FIO_str[0] + "'";
                break;
        }
        System.out.println(sql_str);
        ResultSet res = myDB.query(sql_str);
        ResultSetMetaData resMetaData = res.getMetaData();
        int col_count = resMetaData.getColumnCount();
        TableColumn[] columns = new TableColumn[col_count];
        for (int i=0; i<col_count ; i++)
            columns[i] = new TableColumn(resMetaData.getColumnName(i+1));



        patients_table_view.getColumns().addAll(columns);
    }

}
