/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarybookborrowing;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Filters {
    ConnectDatabase db = new ConnectDatabase();
    private String dateTimePattern = "MMM-dd-yyyy HH:mm";
    
    boolean isConnected() {
        boolean isConnected = true;
        try {
            Connection conn = db.createConnection();
            
            conn.close();
        } catch (Exception e) {
            isConnected = false;
        }
        return isConnected;
    }
    
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    LocalDateTime convertStringToLocalDate(String strDateTimeIn) {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimePattern);
        try {
            ldt = LocalDateTime.parse(strDateTimeIn, dtf);
        } catch (Exception e) {
        }
        return ldt;
    }
    
    boolean isValidDateTime(String strDateTimeIn) {
        boolean isValidDateTime = true;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimePattern);
        try {
            LocalDateTime ldt = LocalDateTime.parse(strDateTimeIn, dtf);
        } catch (Exception e) {
            isValidDateTime = false;
        }
        return isValidDateTime;
    }
    
    String convertLocalDateTimeToPattern(LocalDateTime ldtIn) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimePattern);
        String patternDateTime = dtf.format(ldtIn);
        return patternDateTime;
    }
    
    void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 5; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public boolean canBorrowBooks(DefaultTableModel borrowTable) {
        Map<Integer, Integer> borrowCount = new HashMap<>();
        String unavailableBooks = "";
        boolean allAvailable = true;

        for (int row = 0; row < borrowTable.getRowCount(); row++) {
            int bookId = Integer.parseInt(borrowTable.getValueAt(row, 0).toString());
            borrowCount.put(bookId, borrowCount.getOrDefault(bookId, 0) + 1);
        }

        String sqlCheck = "SELECT fld_quantity FROM tbl_book WHERE fld_book_id = ?";

        try (Connection conn = db.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {

            for (Map.Entry<Integer, Integer> entry : borrowCount.entrySet()) {
                int bookId = entry.getKey();
                int borrowQty = entry.getValue();

                pstmt.setInt(1, bookId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int availableQty = rs.getInt("fld_quantity");
                    if (borrowQty > availableQty) {
                        allAvailable = false;
                        unavailableBooks += String.format("\nBook ID #%d requested %d but only %d available.", bookId, borrowQty, availableQty);
                    }
                } else {
                    allAvailable = false;
                    unavailableBooks += String.format("\nBook ID #%d not found in database.", bookId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error checking book availability: " + e.getMessage());
            return false;
        }
        if (!allAvailable) {
            JOptionPane.showMessageDialog(null, "Borrowing cancelled!" + unavailableBooks);
        }

        return allAvailable;
    }

    
    
    
}
