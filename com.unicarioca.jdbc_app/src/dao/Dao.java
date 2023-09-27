package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    private static Connection con;

    public static Connection getConnection() throws SQLException{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola", "root", "Andreluis87");
        } catch (Exception e) {
            System.out.println("ERRO : " + e.getMessage());
        }
        return con;
    }
}
