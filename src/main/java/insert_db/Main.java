package insert_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            //

            //statement.execute("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Mia', 15, 'pink')");

            statement.addBatch("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Lina', 22, 'black')");
            statement.addBatch("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Lola', 10, 'purple')");
            statement.addBatch("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Sia', 16, 'yellow')");
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
