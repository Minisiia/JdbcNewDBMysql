package insert_db;

import java.sql.*;

/**
 * Создать базу данных в Workbench и подключить к IntelijIdea и создать тестовую таблицу.
 * Заполнить ее данными с помощью запросов MySQL в IntelijIdea. Используя JDBC написать пример выполнения всех запросов.
 */

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement st = null;
        PreparedStatement ps = null;

        // static Statement
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            st = connection.createStatement();

            st.execute("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Mia', 15, 'pink')");

            st.addBatch("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Lina', 22, 'black')");
            st.addBatch("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Lola', 10, 'purple')");
            st.addBatch("INSERT INTO testtable(name, age, favouriteColor) VALUES ('Sia', 16, 'yellow')");
            st.executeBatch();
            st.execute("DELETE FROM testtable WHERE name='Lola';");
            st.execute("UPDATE testtable SET age=17 WHERE name='Sia';");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Prepared Statement INSERT
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            ps = connection.prepareStatement("INSERT INTO testtable(name, age, favouriteColor) VALUES (?, ?, ?)");
            ps.setString(1,"Mimi");
            ps.setInt(2,9);
            ps.setString(3,"green");
            ps.executeUpdate();

            ps.setString(1,"Lili");
            ps.setInt(2,8);
            ps.setString(3,"grey");
            ps.executeUpdate();

            ps.setString(1,"Nana");
            ps.setInt(2,8);
            ps.setString(3,"blue");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Prepared Statement DELETE
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            ps = connection.prepareStatement("DELETE FROM testtable WHERE name=?;");
            ps.setString(1,"Nana");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Prepared Statement UPDATE
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            ps = connection.prepareStatement("UPDATE testtable SET age=? WHERE name=?;");
            ps.setInt(1,100);
            ps.setString(2,"Lili");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
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
