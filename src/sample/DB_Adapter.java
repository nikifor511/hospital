package sample;

import java.sql.*;

public class DB_Adapter {

    static final String DB_URL = "jdbc:postgresql://ec2-54-247-189-1.eu-west-1.compute.amazonaws.com:5432/dcpfkt2qs5euq2";
    static final String USER = "cmacmnazbqrjzn";
    static final String PASS = "e17043de401bf4e7a84d5c538641c68c37769d817c7337238d778feb754998ee";

    public ResultSet query(String query_str) throws SQLException  {
        ResultSet result1 = null;
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return result1;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return result1;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        Statement statement = null;
        statement = connection.createStatement();
        result1 = statement.executeQuery(query_str);
        connection.close();
        System.out.println("Connection close");
        return  result1;
    }

}
