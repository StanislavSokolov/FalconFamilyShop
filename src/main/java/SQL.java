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

    public static ArrayList<Day> upDate(String shop) {
        ArrayList<Day> days = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;
                if (shop.equals("wb")) resultSet = statement.executeQuery("SELECT * FROM saleorderstat");
                if (shop.equals("ozon")) resultSet = statement.executeQuery("SELECT * FROM salestat");
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

    //
    public static ArrayList<Product1> getData(String db, int day) {
        ArrayList<Product1> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;
                if (db.equals("wborders")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromwb WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                if (db.equals("wbsales")) resultSet = statement.executeQuery("SELECT * FROM statofeverysalefromwb WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                if (db.equals("ozon")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromozon WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                while (resultSet.next()) {
                    Product1 product1 = new Product1(resultSet.getString("cdate"), resultSet.getString("csubject"), resultSet.getString("supplierArticle"), resultSet.getInt("nmId"), resultSet.getInt("finishedPrice"), resultSet.getInt("forPay"), resultSet.getString("oblastOkrugName"), resultSet.getString("odid"));
                    products.add(product1);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
                return products;
    }

    public static ArrayList<Product1> getData(String db, String data, String product, int day) {
        ArrayList<Product1> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;
                if (db.equals("wborders")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromwb WHERE cdate = '" + URLRequestResponse.getData(day) + "' AND " + data + " = '" + product + "';");
                if (db.equals("wbsales")) resultSet = statement.executeQuery("SELECT * FROM statofeverysalefromwb WHERE cdate = '" + URLRequestResponse.getData(day) + "' AND " + data + " = '" + product + "';");
//                if (db.equals("ozon")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromozon WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                while (resultSet.next()) {
                    Product1 product1 = new Product1(resultSet.getString("cdate"), resultSet.getString("csubject"), resultSet.getString("supplierArticle"), resultSet.getInt("nmId"), resultSet.getInt("finishedPrice"), resultSet.getInt("forPay"), resultSet.getString("oblastOkrugName"), resultSet.getString("odid"));
                    products.add(product1);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return products;
    }

    public static Product getData(String shop, String product) {
        Product p = null;
        ArrayList<Product> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;
                if (shop.equals("wb")) resultSet = statement.executeQuery("SELECT * FROM itemcostpricewb WHERE supplierArticle = '" + product + "';");
//                if (db.equals("ozon")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromozon WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                while (resultSet.next()) {
                    p = new Product(resultSet.getString("subject"), resultSet.getString("supplierArticle"), resultSet.getInt("costprice"), resultSet.getInt("nmId"), resultSet.getInt("shippingСost"), resultSet.getInt("quantity"), resultSet.getInt("quantityFull"), resultSet.getInt("SaintPetersburg"), resultSet.getInt("SaintPetersburg2"), resultSet.getInt("Koledino"), resultSet.getInt("Electrostal"), resultSet.getInt("Kazan"), resultSet.getInt("Other"), resultSet.getInt("price"), resultSet.getInt("discount"), resultSet.getInt("promoCode"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return p;
    }

    public static ArrayList<Product> getData(String db) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;
                if (db.equals("wb")) resultSet = statement.executeQuery("SELECT * FROM itemcostpricewb;");
//                if (db.equals("ozon")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromozon WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getString("subject"), resultSet.getString("supplierArticle"), resultSet.getInt("costprice"), resultSet.getInt("nmId"), resultSet.getInt("shippingСost"), resultSet.getInt("quantity"), resultSet.getInt("quantityFull"), resultSet.getInt("price"), resultSet.getInt("discount"), resultSet.getInt("promoCode"));
                    products.add(product);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return products;
    }

    public static ArrayList<Product> getDataALP(String db, String data) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;
                if (db.equals("wb")) resultSet = statement.executeQuery("SELECT * FROM itemcostpricewb WHERE subject = '" + data + "';");
//                if (db.equals("ozon")) resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromozon WHERE cdate = '" + URLRequestResponse.getData(day) + "';");
                while (resultSet.next()) {
                    Product product = new Product(resultSet.getString("subject"), resultSet.getString("supplierArticle"), resultSet.getInt("costprice"), resultSet.getInt("nmId"), resultSet.getInt("shippingСost"), resultSet.getInt("quantity"), resultSet.getInt("quantityFull"), resultSet.getInt("SaintPetersburg"), resultSet.getInt("SaintPetersburg2"), resultSet.getInt("Koledino"), resultSet.getInt("Electrostal"), resultSet.getInt("Kazan"), resultSet.getInt("Other"), resultSet.getInt("price"), resultSet.getInt("discount"), resultSet.getInt("promoCode"));
                    products.add(product);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return products;
    }
}

