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
        } catch (Exception e) { }
        return ldt;
    }
    
    boolean isValidDateTime(String strDateTimeIn) {
        boolean isValidDateTime = true;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimePattern);
        try {
            LocalDateTime.parse(strDateTimeIn, dtf);
        } catch (Exception e) {
            isValidDateTime = false;
        }
        return isValidDateTime;
    }
    
    String convertLocalDateTimeToPattern(LocalDateTime ldtIn) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateTimePattern);
        return dtf.format(ldtIn);
    }
    
    void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 5; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) width = 300;
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
                        unavailableBooks += String.format(
                            "\nBook ID #%d requested %d but only %d available.",
                            bookId, borrowQty, availableQty
                        );
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
    
    /** Attach a checkbox to toggle a password field’s echo char. */
    public void wireShowPassword(javax.swing.JCheckBox chk, javax.swing.JPasswordField pf) {
        final char[] original = { pf.getEchoChar() == 0 ? '•' : pf.getEchoChar() };
        if (pf.getEchoChar() == 0) pf.setEchoChar('•');
        chk.addActionListener(e -> {
            if (chk.isSelected()) {
                pf.setEchoChar((char)0);
            } else {
                pf.setEchoChar(original[0]);
            }
        });
    }

    /** Letters, spaces, hyphen, apostrophe; 1–60 chars. */
    public boolean isValidName(String s) {
        return s != null && s.matches("^[A-Za-z'\\- ]{1,60}$");
    }

    /** RFC-light email check. */
    public boolean isValidEmail(String s) {
        if (s == null) return false;
        if (s.length() > 300) return false;
        return s.matches("^(?=[^@\\s]+@[^@\\s]+$)(?!.*\\.\\.)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,24}$");
    }

    /** Username 3–50, letters numbers underscore or dot. */
    public boolean isValidUsername(String s) {
        return s != null && s.matches("^[A-Za-z0-9._]{3,50}$");
    }
}