/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarybookborrowing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminMethods extends Methods {
    
    public void loadBooks(JTable tblDestination){
        DefaultTableModel dtm = new DefaultTableModel(
            new String[]{
                "ID", "Title", "Author", "Publisher",
                "Year Published", "Quantity"
            }, 0
        );        
        
        String sqlSelect = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published, fld_quantity FROM tbl_book";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getInt("fld_book_id")
                        , rs.getString("fld_title")
                        , rs.getString("fld_author")
                        , rs.getString("fld_publisher")
                        , rs.getInt("fld_year_published")
                        , rs.getInt("fld_quantity")
                };
                dtm.addRow(newRow);
            }
            
            tblDestination.setModel(dtm);
            
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    void getBookInfo(JTable tblDestination, JTextField txtBookID, JTextField txtTitle, JTextField txtAuthor, JTextField txtPublisher, JTextField txtYearPublished, JTextField txtBookQty){
        int selectedRow = tblDestination.getSelectedRow();
        
        if (selectedRow != -1) {  
            Object[] value = {
                tblDestination.getValueAt(selectedRow, 0),
                tblDestination.getValueAt(selectedRow, 1),
                tblDestination.getValueAt(selectedRow, 2),
                tblDestination.getValueAt(selectedRow, 3),
                tblDestination.getValueAt(selectedRow, 4),
                tblDestination.getValueAt(selectedRow, 5)
            };
            txtBookID.setText(value[0].toString());
            txtTitle.setText(value[1].toString());
            txtAuthor.setText(value[2].toString());
            txtPublisher.setText(value[3].toString());
            txtYearPublished.setText(value[4].toString());
            txtBookQty.setText(value[5].toString());
        }
    }
    
    public void clearTxt(JTable tblBooksList, JTextField txtBookID, JTextField txtTitle, JTextField txtAuthor, JTextField txtPublisher, JTextField txtYearPublished, JTextField txtBookQty){
        txtBookID.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtYearPublished.setText("");
        txtBookQty.setText("");
        tblBooksList.clearSelection();
    }
    
    public void searchUpdateBook(JTable tblDestination, String inSearchVal){
        String searchLikeVal;
        
        searchLikeVal = "%" + inSearchVal + "%";
        
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published, fld_quantity " +
                "FROM tbl_book " +
                "WHERE fld_title LIKE ? ";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
            pstmt.setString(1, searchLikeVal);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getString("fld_book_id")
                    , rs.getString("fld_title")
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
    
    public void cmboxToDo( JTable tblBooksList, JComboBox cmbName,JTextField txtBookID,JTextField txtTitle,JTextField txtAuthor,
            JTextField txtPublisher,JTextField txtYearPublished,JTextField txtBookQty,
            JButton btnAdd,JButton btnMinus,JButton btnAdminUpdateBook,JButton btnAdminAddBook,JButton btnAdminDeleteBook) {

            int selectedIndex = cmbName.getSelectedIndex();

            // Disable all buttons first
            btnAdminUpdateBook.setEnabled(false);
            btnAdminAddBook.setEnabled(false);
            btnAdminDeleteBook.setEnabled(false);
            btnAdd.setEnabled(false);
            btnMinus.setEnabled(false);

            // Default: all fields not editable
            txtBookID.setEditable(false);
            txtTitle.setEditable(false);
            txtAuthor.setEditable(false);
            txtPublisher.setEditable(false);
            txtYearPublished.setEditable(false);
            txtBookQty.setEditable(false);

            // Main logic
            if (selectedIndex == 0) {
                // Default or View mode
                clearTxt(tblBooksList, txtBookID, txtTitle, txtAuthor, txtPublisher, txtYearPublished, txtBookQty);
                tblBooksList.clearSelection();

            } else if (selectedIndex == 1) {
                // Update mode
                txtTitle.setEditable(true);
                txtAuthor.setEditable(true);
                txtPublisher.setEditable(true);
                txtYearPublished.setEditable(true);
                // Quantity not editable when updating
                txtBookQty.setEditable(false);

                btnAdminUpdateBook.setEnabled(true);

            } else if (selectedIndex == 2) {
                // Add mode
                clearTxt(tblBooksList, txtBookID, txtTitle, txtAuthor, txtPublisher, txtYearPublished, txtBookQty);
                tblBooksList.clearSelection();

                txtTitle.setEditable(true);
                txtAuthor.setEditable(true);
                txtPublisher.setEditable(true);
                txtYearPublished.setEditable(true);
                txtBookQty.setEditable(true);
                txtBookQty.setText("0");

                btnAdminAddBook.setEnabled(true);
                tblBooksList.clearSelection();

            } else if (selectedIndex == 3) {
                // Delete mode
                txtBookQty.setEditable(false);
                btnAdminDeleteBook.setEnabled(true);
            }
        }


    
    public void updateBook(JTextField txtBookId, JTextField title, JTextField author, JTextField publisher, JTextField txtYearPublished, JTextField txtQuantity ){
        String sqlUpdate = "UPDATE tbl_book SET fld_title = ?, fld_author = ?, fld_publisher = ?, fld_year_published = ?, fld_quantity = ? WHERE fld_book_id = ?";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            
            int yearPublished = Integer.parseInt(txtYearPublished.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            int bookId = Integer.parseInt(txtBookId.getText());
            
                
            pstmt.setString(1, title.getText());
            pstmt.setString(2, author.getText());
            pstmt.setString(3, publisher.getText());    
            pstmt.setInt(4, yearPublished);
            pstmt.setInt(5, quantity); 
            pstmt.setInt(6, bookId);
            
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book updated");
            } else {
                JOptionPane.showMessageDialog(null, "No record found with that ID.");
            }
            
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void insertBook(JTextField title, JTextField author, JTextField publisher, JTextField txtYearPublished, JTextField txtQuantity ){
        String sqlInsert = "INSERT INTO tbl_book(fld_title, fld_author, fld_publisher, fld_year_published, fld_quantity) VALUES (?,?,?,?,?)";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            
            int yearPublished = 0;
            int quantity = 0;
            
            if (callFilters.isNumeric(txtYearPublished.getText())) {
                yearPublished = Integer.parseInt(txtYearPublished.getText());
            }
            if (callFilters.isNumeric(txtQuantity.getText())) {
                quantity = Integer.parseInt(txtQuantity.getText());
            }
            
            pstmt.setString(1, title.getText());
            pstmt.setString(2, author.getText());
            pstmt.setString(3, publisher.getText());    
            pstmt.setInt(4, yearPublished);
            pstmt.setInt(5, quantity);
            
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book Added");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid book added");
            }
            
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void deleteBook(JTextField txtBookId){
        String sqlUpdate = "DELETE FROM tbl_book WHERE fld_book_id = ? ";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            
            int bookId = Integer.parseInt(txtBookId.getText());
            
            pstmt.setInt(1, bookId);
            
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Book deleted");
            } else {
                JOptionPane.showMessageDialog(null, "No record found with that ID.");
            }
            
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
