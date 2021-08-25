package LoginPage;
import java.util.*;
import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;//static object of class
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "Aslan200310";

    private DatabaseConnection() throws SQLException {//connects with database
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("Something is wrong with the DB connection String : " + ex.getMessage());
        }
    }
    public Connection getConnection() {//returns connection
        return connection;
    }
    public static DatabaseConnection getInstance() throws SQLException {//returns the connected object of class
        if (instance == null) {//If  database not connected yet, it will create connection
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs){//closes the connection with database
        try {
            if(!(rs==null))rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
