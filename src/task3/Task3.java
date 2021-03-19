package task3;

import java.sql.*;

public class Task3 {

    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String GET_ALL_1 = "SELECT nameandphone.phone, marriedandbirthandadress.adress" +
            " from nameandphone inner join marriedandbirthandadress on nameandphone.id = marriedandbirthandadress.id";
    private static final String GET_ALL_2 = "SELECT marriedandbirthandadress.birth, nameandphone.phone" +
            " from marriedandbirthandadress INNER join nameandphone " +
            "on marriedandbirthandadress.id = nameandphone.id " +
            "where marriedandbirthandadress.married < 1";
    private static final String GET_ALL_3 = "SELECT marriedandbirthandadress.birth, nameandphone.phone from " +
            "marriedandbirthandadress inner join nameandphone on marriedandbirthandadress.id = nameandphone.id" +
            " inner join paymentandposition on marriedandbirthandadress.id = paymentandposition.id " +
            "where POSITION = 'manager'";

    public static void main(String[] args) {

        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(GET_ALL_1);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String phone = resultSet.getString("phone");
                String adress = resultSet.getString("adress");

                System.out.println(phone + " " + adress);
            }


            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            statement = connection.prepareStatement(GET_ALL_2);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Date birth = resultSet.getDate("birth");
                String phone = resultSet.getString("phone");

                System.out.println(birth + " " + phone);
            }


            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            statement = connection.prepareStatement(GET_ALL_3);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Date birth = resultSet.getDate("birth");
                String phone = resultSet.getString("phone");

                System.out.println(birth + " " + phone);
            }



        } catch (SQLException  e) {
            e.printStackTrace();
        }  finally {
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
