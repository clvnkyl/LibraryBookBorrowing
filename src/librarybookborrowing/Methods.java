package librarybookborrowing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void refreshDashboard(JTable tblDestination) {
        callFilters.resizeColumnWidth(tblDestination);
        getDashboard(tblDestination);

        Timer timer = new Timer(5000, e -> {
            callFilters.resizeColumnWidth(tblDestination);
            getDashboard(tblDestination);
        });
        timer.start();

        // Smooth refresh niceties
        DefaultTableModel model = (DefaultTableModel) tblDestination.getModel();
        model.fireTableDataChanged();
        tblDestination.setFillsViewportHeight(true);
        if (model.getRowCount() > 0) {
            tblDestination.setRowSelectionInterval(0, 0);
            tblDestination.scrollRectToVisible(tblDestination.getCellRect(0, 0, true));
        }
    }

    void getBooks(JTable tblName) {
        DefaultTableModel dtm = (DefaultTableModel) tblName.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published FROM tbl_book";
        try (Connection conn = db.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] newRow = {
                    rs.getString("fld_book_id"),
                    rs.getString("fld_title"),
                    rs.getString("fld_author"),
                    rs.getString("fld_publisher"),
                    rs.getInt("fld_year_published")
                };
                dtm.addRow(newRow);
            }
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }

    public void searchMemberName(JTable tblDestination, String inSearchVal) {
        String searchLikeVal = "%" + inSearchVal + "%";
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_member_id, "
                        + "CONCAT(fld_first_name, ' ', fld_middle_name, ' ', fld_last_name) AS full_name "
                        + "FROM tbl_member "
                        + "WHERE CONCAT(fld_first_name, ' ', fld_middle_name, ' ', fld_last_name) LIKE ? ";
        try (Connection conn = db.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, searchLikeVal);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] newRow = { rs.getInt("fld_member_id"), rs.getString("full_name") };
                    dtm.addRow(newRow);
                }
            }
        } catch (Exception e) {
            String[] errorMsg = {"error:", e.getMessage()};
            dtm.addRow(errorMsg);
        }
    }

    public void searchStaffName(JTable tblDestination, String inSearchVal) {
        String searchLikeVal = "%" + inSearchVal + "%";
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_staff_id, "
                        + "CONCAT(fld_first_name, ' ', fld_middle_name, ' ', fld_last_name) AS full_name "
                        + "FROM tbl_staff "
                        + "WHERE CONCAT(fld_first_name, ' ', fld_middle_name, ' ', fld_last_name) LIKE ?";
        try (Connection conn = db.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, searchLikeVal);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] newRow = { rs.getInt("fld_staff_id"), rs.getString("full_name") };
                    dtm.addRow(newRow);
                }
            }
        } catch (Exception e) {
            String[] errorMsg = {"error:", e.getMessage()};
            dtm.addRow(errorMsg);
        }
    }

    public void searchBookTitle(JTable tblDestination, String inSearchVal) {
        String searchLikeVal = "%" + inSearchVal + "%";
        DefaultTableModel dtm = (DefaultTableModel) tblDestination.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT fld_book_id, fld_title, fld_author, fld_publisher, fld_year_published "
                        + "FROM tbl_book WHERE fld_title LIKE ? ";
        try (Connection conn = db.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, searchLikeVal);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] newRow = {
                        rs.getString("fld_book_id"),
                        rs.getString("fld_title"),
                        rs.getString("fld_author"),
                        rs.getString("fld_publisher"),
                        rs.getInt("fld_year_published")
                    };
                    dtm.addRow(newRow);
                }
            }
        } catch (Exception e) {
            Object[] errRow = {"error", e.getMessage()};
            dtm.addRow(errRow);
        }
    }

    void getID(JTable tblDestination, JTextField txtPersonName) {
        int selectedRow = tblDestination.getSelectedRow();
        if (selectedRow != -1) {
            Object name = tblDestination.getValueAt(selectedRow, 1);
            txtPersonName.setText(name != null ? name.toString() : "");
        }
    }

    void getBookID(JTable tblDestination, JTextField txtBookTitle) {
        int selectedRow = tblDestination.getSelectedRow();
        if (selectedRow != -1) {
            Object title = tblDestination.getValueAt(selectedRow, 1);
            txtBookTitle.setText(title != null ? title.toString() : "");
        }
    }

    public int getFirstCell(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Object value = table.getValueAt(selectedRow, 0);
            if (value != null) {
                String strValue = value.toString();
                if (Filters.isNumeric(strValue)) return Integer.parseInt(strValue);
            }
        }
        return -1;
    }

    public boolean memberHavePending(int memberId) {
        boolean canBorrow = false;
        try (Connection conn = db.createConnection()) {
            String sql = "SELECT COUNT(*) AS borrowed_count "
                       + "FROM tbl_transaction "
                       + "WHERE fld_member_id = ? AND fld_status = 'Borrowed'";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, memberId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int borrowedCount = rs.getInt("borrowed_count");
                        canBorrow = (borrowedCount == 0);
                    }
                }
            }
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

        String sqlSelect = "SELECT t.fld_reference_id, "
            + "CONCAT_WS(' ', m.fld_first_name, m.fld_middle_name, m.fld_last_name) AS member_name, "
            + "CONCAT_WS(' ', s1.fld_first_name, s1.fld_middle_name, s1.fld_last_name) AS issued_by, "
            + "CONCAT_WS(' ', s2.fld_first_name, s2.fld_middle_name, s2.fld_last_name) AS received_by, "
            + "b.fld_title AS book_title, "
            + "t.fld_borrow_date, t.fld_due_date, "
            + "t.fld_return_date, t.fld_status "
            + "FROM tbl_transaction t "
            + "JOIN tbl_member m ON t.fld_member_id = m.fld_member_id "
            + "JOIN tbl_staff s1 ON t.fld_issuer_staff_id = s1.fld_staff_id "
            + "LEFT JOIN tbl_staff s2 ON t.fld_receiver_staff_id = s2.fld_staff_id "
            + "JOIN tbl_book b ON t.fld_book_id = b.fld_book_id "
            + "ORDER BY t.fld_borrow_date DESC";

        try (Connection conn = db.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Timestamp returnDate = rs.getTimestamp("fld_return_date");
                String returnDateVal = (returnDate != null)
                        ? callFilters.convertLocalDateTimeToPattern(returnDate.toLocalDateTime())
                        : "Not Returned";

                String receiverName = (rs.getString("received_by") != null)
                        ? rs.getString("received_by")
                        : "Not Processed";

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /** Record returned by account lookup. */
    public static class AccountLookup {
        public final int accountId;
        public final String username;
        public final String hashedPassword;
        public final String role;
        public final int staffId;
        public AccountLookup(int accountId, String username, String hashedPassword, String role, int staffId) {
            this.accountId = accountId;
            this.username = username;
            this.hashedPassword = hashedPassword;
            this.role = role;
            this.staffId = staffId;
        }
    }

    /** Find account by username OR staff email. */
    public AccountLookup findAccountByUsernameOrEmail(String userOrEmail) throws Exception {
        String sql =
            "SELECT a.fld_account_id, a.fld_username, a.fld_password, a.fld_role, a.fld_staff_id "
          + "FROM tbl_accounts a "
          + "LEFT JOIN tbl_staff s ON s.fld_staff_id = a.fld_staff_id "
          + "WHERE a.fld_username = ? OR s.fld_email = ? "
          + "LIMIT 1";

        try (Connection conn = db.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userOrEmail);
            ps.setString(2, userOrEmail);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AccountLookup(
                        rs.getInt("fld_account_id"),
                        rs.getString("fld_username"),
                        rs.getString("fld_password"),
                        rs.getString("fld_role"),
                        rs.getInt("fld_staff_id")
                    );
                }
                return null;
            }
        }
    }

    /**
     * Register flow depending on role:
     * - Member: insert into tbl_member (phone may be null), then tbl_accounts with NULL fld_staff_id
     * - Librarian/Admin: insert into tbl_staff, then tbl_accounts with new staff id
     */
    public void registerPersonAndAccount(
        String first, String middle, String last, String phoneOrNull, String email,
        String username, String role, String hashedPassword
    ) throws Exception {

        final boolean isMember = "Member".equalsIgnoreCase(role);
        final boolean isStaff  = "Librarian".equalsIgnoreCase(role) || "Admin".equalsIgnoreCase(role);

        if (!isMember && !isStaff) {
            throw new SQLException("Unsupported role: " + role);
        }

        final String insMember =
            "INSERT INTO tbl_member (fld_first_name, fld_middle_name, fld_last_name, fld_phone_number, fld_email) "
          + "VALUES (?, ?, ?, ?, ?)";

        final String insStaff =
            "INSERT INTO tbl_staff (fld_first_name, fld_middle_name, fld_last_name, fld_email) "
          + "VALUES (?, ?, ?, ?)";

        final String insAccountWithStaff =
            "INSERT INTO tbl_accounts (fld_username, fld_password, fld_role, fld_staff_id) "
          + "VALUES (?, ?, ?, ?)";

        final String insAccountNoStaff =
            "INSERT INTO tbl_accounts (fld_username, fld_password, fld_role, fld_staff_id) "
          + "VALUES (?, ?, ?, NULL)";

        try (Connection conn = db.createConnection()) {
            conn.setAutoCommit(false);

            try {
                if (isMember) {
                    // 1) Insert member
                    try (PreparedStatement ps = conn.prepareStatement(insMember)) {
                        ps.setString(1, first);
                        ps.setString(2, (middle == null || middle.isBlank()) ? null : middle);
                        ps.setString(3, last);
                        ps.setString(4, (phoneOrNull == null || phoneOrNull.isBlank()) ? null : phoneOrNull);
                        ps.setString(5, email);
                        ps.executeUpdate();
                    }
                    // 2) Account with NULL staff_id
                    try (PreparedStatement ps = conn.prepareStatement(insAccountNoStaff)) {
                        ps.setString(1, username);
                        ps.setString(2, hashedPassword);
                        ps.setString(3, role);
                        ps.executeUpdate();
                    }
                } else {
                    // Staff (Admin/Librarian)
                    int newStaffId;
                    try (PreparedStatement ps1 = conn.prepareStatement(
                            insStaff, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                        ps1.setString(1, first);
                        ps1.setString(2, (middle == null || middle.isBlank()) ? null : middle);
                        ps1.setString(3, last);
                        ps1.setString(4, email);
                        ps1.executeUpdate();
                        try (ResultSet keys = ps1.getGeneratedKeys()) {
                            if (!keys.next()) {
                                conn.rollback();
                                throw new SQLException("Failed to get new staff id.");
                            }
                            newStaffId = keys.getInt(1);
                        }
                    }
                    try (PreparedStatement ps2 = conn.prepareStatement(insAccountWithStaff)) {
                        ps2.setString(1, username);
                        ps2.setString(2, hashedPassword);
                        ps2.setString(3, role);
                        ps2.setInt(4, newStaffId);
                        ps2.executeUpdate();
                    }
                }

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
