package sample;

import java.sql.*;

public class DB_Adapter {

    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://ec2-54-75-235-28.eu-west-1.compute.amazonaws.com:5432/d3l35hvgdquvnm";
    static final String USER = "ezfhdiiyfyfnul";
    static final String PASS = "0ebccaa759c4eb556fca9dd7fc7573e8a07d24989872d376fcc32e98e85b33e7";

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

        //String query_str = "select login_user('login1', 'pass1')";

        result1 = statement.executeQuery(query_str);

//        System.out.println("output statement");
//        result1.next();
        return  result1;


    }

}
