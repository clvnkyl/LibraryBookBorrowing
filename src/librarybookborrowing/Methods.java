/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarybookborrowing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Methods {
    ConnectDatabase db = new ConnectDatabase();
    Filters callFilters = new Filters();
    String messageResult = "";
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static String generateReference(int randomLength) {
        
        String datePart = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());

        Random random = new Random();
        StringBuilder randomPart = new StringBuilder();

        for (int i = 0; i < randomLength; i++) {
            int index = random.nextInt(ALPHABETS.length());
            randomPart.append(ALPHABETS.charAt(index));
        }
        
        return datePart + randomPart.toString();
    }
    
    public void refreshDashboard(JTable tblDestination){
        callFilters.resizeColumnWidth(tblDestination);
        getDashboard(tblDestination);
        Timer timer = new Timer(5000, e -> {
            callFilters.resizeColumnWidth(tblDestination);
            getDashboard(tblDestination);
        });
        timer.start();
    }
    
    void getBooks(JTable tblName){
        DefaultTableModel dtm = (DefaultTableModel) tblName.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published FROM tbl_book";
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getString("fld_book_id")
                    , rs.getString("fld_title")
                    , rs.getString("fld_author")
                    , rs.getString("fld_publisher")
                    , rs.getInt("fld_year_published")
                };
                dtm.addRow(newRow);
            }
            
            conn.close();
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }
    
    void searchMemberByLastName(JTable tblDestination, String inSearchVal){
        String searchField, searchLikeVal;
        
        searchLikeVal = "%" + inSearchVal + "%";
        
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_member_id," +
                "CONCAT(fld_first_name, ' ', fld_middle_name, ' ', fld_last_name) AS full_name " +
                "FROM tbl_member " +
                "WHERE fld_last_name LIKE ? ";
        
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
            pstmt.setString(1, searchLikeVal);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Object[] newRow = {
                    rs.getInt("fld_member_id"),
                    rs.getString("full_name")
                };
            
                dtm.addRow(newRow);
            }
            
            conn.close();
        } catch (Exception e) {
            String[] errorMsg = {"error:" , e.getMessage()};
            dtm.addRow(errorMsg);
        }
    }
   
    void searchStaffByLastName(JTable tblDestination, String inSearchVal){
        String searchField, searchLikeVal;
        
        searchLikeVal = "%" + inSearchVal + "%";
        
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_staff_id," +
                "CONCAT(fld_first_name, ' ', fld_middle_name, ' ', fld_last_name) AS full_name " +
                "FROM tbl_staff " +
                "WHERE fld_last_name LIKE ?";
        
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
            pstmt.setString(1, searchLikeVal);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Object[] newRow = {
                    rs.getInt("fld_staff_id"),
                    rs.getString("full_name")
                };
                
                dtm.addRow(newRow);
            }
            
            conn.close();
        } catch (Exception e) {
            String[] errorMsg = {"error:" , e.getMessage()};
            dtm.addRow(errorMsg);
        }
    }
    
    public void searchBookTitle(JTable tblDestination, String inSearchVal){
        String searchLikeVal;
        
        searchLikeVal = "%" + inSearchVal + "%";
        
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published " +
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
                };
                dtm.addRow(newRow);
            }
            
            
            conn.close();
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }
    
    void getID(JTable tblDestination, JTextField txtPersonName){
        int ID;
        int selectedBook = tblDestination.getSelectedRow();

            if (selectedBook != -1) {  
                Object[] value = {
                    ID = (int) tblDestination.getValueAt(selectedBook, 0),
                    tblDestination.getValueAt(selectedBook, 1)
                };
                txtPersonName.setText(value[1].toString());
        }
    }
    
    void getBookID(JTable tblDestination, JTextField txtBookTitle){
        int selectedRow = tblDestination.getSelectedRow();

        
        if (selectedRow != -1) {  
            Object[] value = {
                tblDestination.getValueAt(selectedRow, 0),
                tblDestination.getValueAt(selectedRow, 1),
                tblDestination.getValueAt(selectedRow, 2),
                tblDestination.getValueAt(selectedRow, 3),
                tblDestination.getValueAt(selectedRow, 4)
            };
            txtBookTitle.setText(value[1].toString());
        }
    }
    
    public int getFirstCell(JTable table) {    
        int selectedRow = table.getSelectedRow();
    
        if (selectedRow != -1) {
            Object value = table.getValueAt(selectedRow, 0);

            if (value != null) {
                String strValue = value.toString();

                if (callFilters.isNumeric(strValue)) {
                    return Integer.parseInt(strValue); 
                }
            }
        }

        return -1;
    }
    
    public boolean memberHavePending(int memberId){
        boolean canBorrow = false;

        try {
            Connection conn = db.createConnection();
            String sql = "SELECT COUNT(*) AS borrowed_count " +
                         "FROM tbl_transaction " +
                         "WHERE fld_member_id = ? AND fld_status = 'Borrowed'";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int borrowedCount = rs.getInt("borrowed_count");
                canBorrow = (borrowedCount == 0);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return canBorrow;
    }
    
    public void getDashboard(JTable tblDestination) {
        DefaultTableModel dashboard = new DefaultTableModel(
            new String[]{
                "Transaction ID", "Member Name", "Issuer", "Book Title",
                "Borrow Date", "Due Date", "Return Date", "Receiver", "Status"
            }, 0
        );

        String sqlSelect = "SELECT t.fld_reference_id, " +
            "CONCAT_WS(' ', m.fld_first_name, m.fld_middle_name, m.fld_last_name) AS member_name, " +
            "CONCAT_WS(' ', s1.fld_first_name, s1.fld_middle_name, s1.fld_last_name) AS issued_by, " +
            "CONCAT_WS(' ', s2.fld_first_name, s2.fld_middle_name, s2.fld_last_name) AS received_by, " +
            "b.fld_title AS book_title, " +
            "t.fld_borrow_date, t.fld_due_date, " +
            "t.fld_return_date, t.fld_status " +
            "FROM tbl_transaction t " +
            "JOIN tbl_member m ON t.fld_member_id = m.fld_member_id " +
            "JOIN tbl_staff s1 ON t.fld_issuer_staff_id = s1.fld_staff_id " +
            "LEFT JOIN tbl_staff s2 ON t.fld_reveiver_staff_id = s2.fld_staff_id " +  // <-- fixed typo
            "JOIN tbl_book b ON t.fld_book_id = b.fld_book_id " +
            "ORDER BY t.fld_borrow_date DESC";


        
        try {
            Connection conn = db.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {  
                Timestamp returnDate = rs.getTimestamp("fld_return_date");
                String returnDateVal;
                if (returnDate != null) {
                    returnDateVal = callFilters.convertLocalDateTimeToPattern(returnDate.toLocalDateTime());
                } else {
                    returnDateVal = "Not Returned";
                }

                String receiverName;
                if (rs.getString("received_by") != null) {
                    receiverName = rs.getString("received_by");
                } else {
                    receiverName = "Not Processed";
                }

                Object[] newRow = {
                    rs.getString("fld_reference_id"),   
                    rs.getString("member_name"),       
                    rs.getString("issued_by"),
                    rs.getString("book_title"),
                    callFilters.convertLocalDateTimeToPattern(
                        rs.getTimestamp("fld_borrow_date").toLocalDateTime()
                    ),                
                    callFilters.convertLocalDateTimeToPattern(
                        rs.getTimestamp("fld_due_date").toLocalDateTime()
                    ),                             
                    returnDateVal,
                    receiverName,
                    rs.getString("fld_status") 
                };


                dashboard.addRow(newRow);
            }

            
              tblDestination.setModel(dashboard);
            
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
