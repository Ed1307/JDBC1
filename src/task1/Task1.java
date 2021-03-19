package task1;


import java.sql.*;

public class Task1 {

    private static final String URL = "jdbc:mysql://localhost:3306/homeworkdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String INSERT_NEW = "INSERT INTO homeworktable (name) "
            + " VALUES(?)";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(INSERT_NEW);

            statement.setString(1, "Ed");

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
