import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SQL {
    public static void createBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                System.out.println("Connection to Store DB succesfull!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection("jdbc:mysql://localhost/ffs", "user","password");
    }

    public static ArrayList<Day> upDate() {
        ArrayList<Day> days = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM saleorderstat");
                while (resultSet.next()) {
                    Day day = new Day(resultSet.getString("cdate"), resultSet.getInt("sumSale"), resultSet.getInt("sumOrder"), resultSet.getInt("sumSaleMoney"), resultSet.getString("popItem"));
                    days.add(day);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return days;
    }
}

