package task2;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Task2 {

    private static final String URL = "jdbc:mysql://localhost:3306/homeworkdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static final String GET_ALL = "SELECT * FROM homeworktable";


    public static void main(String[] args) {

        File f = new File("text.txt");

        registerDriver();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(GET_ALL);

            ResultSet resultSet = statement.executeQuery();

            FileWriter fr = null;
            try {
                fr = new FileWriter(f);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                try {

                    fr.write(id + " " + name + '\n');

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner sc = null;
            try {
                sc = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc.hasNext()){
                System.out.println(sc.nextLine());
            }

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
