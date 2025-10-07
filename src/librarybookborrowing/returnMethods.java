
package librarybookborrowing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class returnMethods {
    ConnectDatabase db = new ConnectDatabase();
    Filters callFilters = new Filters();
    
        public static void loadBorrowedBook(JTable table) {
           
            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"transId", "Member Name", "Book Title", "Borrow Date","Due Date", "Status"}, 0);
                table.setModel(model);
                        
                String sql = "SELECT t.fld_transaction_id, CONCAT(m.fld_first_name, ' ', m.fld_last_name) AS MemberName, " 
                        + "b.fld_title, t.fld_borrow_date, t.fld_due_date, t.fld_status " 
                        + "FROM tbl_transaction t " 
                        + "JOIN tbl_member m ON t.fld_member_id = m.fld_member_id "
                        + "JOIN tbl_book b ON t.fld_book_id = b.fld_book_id " 
                        + "WHERE t.fld_status = 'Borrowed'" 
                        + "ORDER BY t.fld_borrow_date DESC";

            try  {
                ConnectDatabase db = new ConnectDatabase();
                Connection conn = db.createConnection(); 
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery(sql);
                
                
                
                while (rs.next()) {
                    int transId = rs.getInt("fld_transaction_id");
                    String membername = rs.getString("MemberName");
                    String bookTitle = rs.getString("fld_title");
                    Timestamp tsBorrow = rs.getTimestamp("fld_borrow_date");
                    LocalDateTime borrowDate = tsBorrow.toLocalDateTime();
                    Timestamp tsDue = rs.getTimestamp("fld_due_date");
                    LocalDateTime dueDate = tsDue.toLocalDateTime();
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy   HH:mm");
                    String formattedBorrow = borrowDate.format(formatter);
                    String formattedDue = dueDate.format(formatter);
                    String status = rs.getString("fld_status");

                   model.addRow(new Object[]{ transId, membername, bookTitle, formattedBorrow, formattedDue, status
                    }); 
                } 
                int transIdColIndex = 0;
                table.getColumnModel().getColumn(transIdColIndex).setMinWidth(0);
                table.getColumnModel().getColumn(transIdColIndex).setMaxWidth(0);
                table.getColumnModel().getColumn(transIdColIndex).setWidth(0);
                table.getColumnModel().getColumn(transIdColIndex).setPreferredWidth(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading table: " + e.getMessage());
            }
         
    }
         
    public static void loadReturnedBook(JTable table) {
           
            DefaultTableModel model = (DefaultTableModel)table.getModel(); 
            model.setRowCount(0);

            String sql = "SELECT CONCAT(m.fld_first_name, ' ', m.fld_last_name) AS MemberName, " 
                        + "b.fld_title, t.fld_borrow_date, t.fld_due_date,t.fld_return_date, " 
                        + "CONCAT(s.fld_first_name, ' ', s.fld_last_name) AS ReceiverName, t.fld_status " 
                        + "FROM tbl_transaction t " 
                        + "JOIN tbl_member m ON t.fld_member_id = m.fld_member_id "
                        + "JOIN tbl_book b ON t.fld_book_id = b.fld_book_id " 
                        + "LEFT JOIN tbl_staff s ON t.fld_receiver_staff_id = s.fld_staff_id " 
                        + "WHERE t.fld_status = 'Returned'" 
                        + "ORDER BY t.fld_borrow_date DESC";

            try  {
                ConnectDatabase db = new ConnectDatabase();
                Connection conn = db.createConnection(); 
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery(sql);
                
                
                
                while (rs.next()) {
                    String membername = rs.getString("MemberName");
                    String bookTitle = rs.getString("fld_title");
                    Timestamp tsBorrow = rs.getTimestamp("fld_borrow_date");
                    LocalDateTime borrowDate = tsBorrow.toLocalDateTime();
                    Timestamp tsDue = rs.getTimestamp("fld_due_date");
                    LocalDateTime dueDate = tsDue.toLocalDateTime();
                    Timestamp tsReturn = rs.getTimestamp("fld_return_date");
                    LocalDateTime returnDate = tsReturn.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy   HH:mm");
                    String formattedBorrow = borrowDate.format(formatter);
                    String formattedDue = dueDate.format(formatter);
                    String formattedReturn = returnDate.format(formatter);  
                    String receiver =rs.getString("ReceiverName");
                    String status = rs.getString("fld_status");

                   model.addRow(new Object[]{
                        membername, bookTitle, formattedBorrow, formattedDue, formattedReturn, receiver, status
                    }); 
                } conn.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading table: " + e.getMessage());
            }    
    } 
    

    public void searchName(JTable table, JTextField inSearchVal) {
        String searchVal = "%" + inSearchVal.getText().trim() + "%";

        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"transId", "Member Name", "Book Title", "Borrow Date", "Due Date", "Status"}, 0
        );
        table.setModel(model);

        String sql = "SELECT t.fld_transaction_id, " +
                     "CONCAT(m.fld_first_name, ' ', m.fld_last_name) AS MemberName, " +
                     "b.fld_title, t.fld_borrow_date, t.fld_due_date, t.fld_status " +
                     "FROM tbl_transaction t " +
                     "JOIN tbl_member m ON t.fld_member_id = m.fld_member_id " +
                     "JOIN tbl_book b ON t.fld_book_id = b.fld_book_id " +
                     "WHERE t.fld_status = 'Borrowed' " +
                     "AND CONCAT(m.fld_first_name, ' ', m.fld_last_name) LIKE ? " +
                     "ORDER BY t.fld_borrow_date DESC";

        try {
            ConnectDatabase db = new ConnectDatabase();
            Connection conn = db.createConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, searchVal); // ✅ correct parameter index
            ResultSet rs = st.executeQuery(); // ✅ no SQL string here

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy   HH:mm");

            while (rs.next()) {
                int transId = rs.getInt("fld_transaction_id");
                String memberName = rs.getString("MemberName");
                String bookTitle = rs.getString("fld_title");

                LocalDateTime borrowDate = rs.getTimestamp("fld_borrow_date").toLocalDateTime();
                LocalDateTime dueDate = rs.getTimestamp("fld_due_date").toLocalDateTime();

                String formattedBorrow = borrowDate.format(formatter);
                String formattedDue = dueDate.format(formatter);
                String status = rs.getString("fld_status");

                model.addRow(new Object[]{transId, memberName, bookTitle, formattedBorrow, formattedDue, status});
            }

            // ✅ hide transaction ID column
            int transIdColIndex = 0;
            table.getColumnModel().getColumn(transIdColIndex).setMinWidth(0);
            table.getColumnModel().getColumn(transIdColIndex).setMaxWidth(0);
            table.getColumnModel().getColumn(transIdColIndex).setWidth(0);
            table.getColumnModel().getColumn(transIdColIndex).setPreferredWidth(0);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading table: " + e.getMessage());
            e.printStackTrace();
        }
    }






}
