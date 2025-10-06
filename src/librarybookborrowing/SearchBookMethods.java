/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarybookborrowing;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchBookMethods {
    Filters callFilter = new Filters();
    ConnectDatabase db = new ConnectDatabase();
    
    public void searchBookTitle(JTable tblDestination, JTextField inSearchVal){
        String searchVal;
        
        searchVal = "%" + inSearchVal.getText() + "%";
        
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published, fld_quantity " +
                "FROM tbl_book " +
                "WHERE fld_title LIKE ?";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
           
            pstmt.setString(1, searchVal);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getString("fld_title")
                    , rs.getString("fld_author")
                    , rs.getString("fld_publisher")
                    , rs.getInt("fld_year_published")
                    , rs.getInt("fld_quantity")
                };
                dtm.addRow(newRow);
            }
            
            
            conn.close();
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }
    public void searchBookAuthor(JTable tblDestination, JTextField inSearchVal){
        String searchVal;
        
        searchVal = "%" + inSearchVal.getText() + "%";
        
            
        System.out.println("Searching author with: " + searchVal);
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published, fld_quantity " +
                "FROM tbl_book " +
                "WHERE fld_author LIKE ?";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
           
            pstmt.setString(1, searchVal);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getString("fld_title")
                    , rs.getString("fld_author")
                    , rs.getString("fld_publisher")
                    , rs.getInt("fld_year_published")
                    , rs.getInt("fld_quantity")
                };
                dtm.addRow(newRow);
            }

            conn.close();
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }
    public void searchBookYear(JTable tblDestination, JTextField inSearchVal){
        String searchStr;
        int searchVal = 0;
        
        searchStr = inSearchVal.getText();
        
        if (callFilter.isNumeric(searchStr)) {
            searchVal = Integer.parseInt(searchStr);
        } else{
            JOptionPane.showMessageDialog(null, "You did not enter a year");
        }
        
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published, fld_quantity " +
                "FROM tbl_book " +
                "WHERE fld_year_published = ?";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
           
            pstmt.setInt(1, searchVal);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getString("fld_title")
                    , rs.getString("fld_author")
                    , rs.getString("fld_publisher")
                    , rs.getInt("fld_year_published")
                    , rs.getInt("fld_quantity")
                };
                dtm.addRow(newRow);
            }
            
            
            conn.close();
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }
    
    public void clearSearchTxt(JTextField title, JTextField author, JTextField year ){
        title.setText("");
        author.setText("");
        year.setText("");
    }
}
