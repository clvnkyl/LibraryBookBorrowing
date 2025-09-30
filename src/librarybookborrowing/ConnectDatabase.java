/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarybookborrowing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class ConnectDatabase {
    
    private String dbAddress= "jdbc:mysql://localhost:3306/db_library_system";
    private String dbUsername= "root";
    private String dbPassword= "";

    Connection createConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
        return conn;
    }
    
    
    
    
}
