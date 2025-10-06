package librarybookborrowing;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

public class Login extends javax.swing.JFrame {
    

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Login.class.getName());

    private final ConnectDatabase db = new ConnectDatabase();
    private final Methods methods = new Methods();
    private final Filters filters = new Filters();

    // Borders for validation highlighting
    private Border normalBorder;
    private final Border redBorder = BorderFactory.createLineBorder(new Color(200, 0, 0), 2);

    public Login() {
        initComponents(); // keep your generated UI here

        // Save a normal border reference for the Register inputs (same look from your initComponents)
        normalBorder = TextFieldFirstName.getBorder();

        // ---- Hook up behaviors ----
        // 1) Login show/hide password
        filters.wireShowPassword(CheckBoxShowPasswordLogin, PasswordFieldLogin);

        // 2) Register show/hide password
        filters.wireShowPassword(CheckBoxShowPasswordRegister, PasswordFieldRegister);

        // 3) Register: real-time field validation with red highlight
        addRegisterLiveValidation();

        // 4) Try database connection in background to set status panel/label
        pingDatabaseAndUpdateStatus();
    }

    /* ---------------------- UI wiring helpers ---------------------- */

    private void addRegisterLiveValidation() {
        // Names: allow letters, space, hyphen, apostrophe (no numbers)
        attachLiveValidator(TextFieldFirstName,  s -> filters.isValidName(s));
        attachLiveValidator(TextFieldMiddleName,s -> s.isBlank() || filters.isValidName(s)); // middle can be blank
        attachLiveValidator(TextFieldLastNameRegister, s -> filters.isValidName(s));

        // Email
        attachLiveValidator(TextFieldEmail, filters::isValidEmail);

        // Username (basic: 3-50, letters, numbers, underscore/dot)
        attachLiveValidator(TextFieldUsernameRegister, filters::isValidUsername);

        // Password: 8+ characters
        attachLiveValidator(PasswordFieldRegister, s -> s.length() >= 8);
    }

    private void attachLiveValidator(javax.swing.JComponent comp, java.util.function.Predicate<String> ok) {
        if (comp instanceof javax.swing.JTextField) {
            final javax.swing.JTextField tf = (javax.swing.JTextField) comp;
            tf.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
                @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
                @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
                private void update() {
                    String v = tf.getText().trim();
                    tf.setBorder(ok.test(v) ? normalBorder : redBorder);
                }
            });
        } else if (comp instanceof javax.swing.JPasswordField) {
            final javax.swing.JPasswordField pf = (javax.swing.JPasswordField) comp;
            pf.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
                @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
                @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
                private void update() {
                    String v = new String(pf.getPassword());
                    pf.setBorder(ok.test(v) ? normalBorder : redBorder);
                }
            });
        }
    }

    private void pingDatabaseAndUpdateStatus() {
        // Start as gray "Waiting Connection..."
        PanelDBConnectionStatus.setBackground(new Color(102, 102, 102));
        LabelConnectionStatus.setText("Waiting Connection...");

        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                try (Connection c = db.createConnection()) {
                    return true;
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, "DB test connection failed", ex);
                    return false;
                }
            }

            @Override
            protected void done() {
                boolean ok = false;
                try { ok = get(); } catch (Exception ignored) {}
                final boolean finalOk = ok;
                SwingUtilities.invokeLater(() -> {
                    if (finalOk) {
                        PanelDBConnectionStatus.setBackground(new Color(0, 153, 0)); // green
                        LabelConnectionStatus.setText("Successful");
                    } else {
                        PanelDBConnectionStatus.setBackground(new Color(200, 0, 0)); // red
                        LabelConnectionStatus.setText("Connection Error");
                    }
                });
            }
        }.execute();
    }

    private void clearRegisterFields() {
        TextFieldFirstName.setText("");
        TextFieldMiddleName.setText("");
        TextFieldLastNameRegister.setText("");
        TextFieldEmail.setText("");
        TextFieldUsernameRegister.setText("");
        PasswordFieldRegister.setText("");

        TextFieldFirstName.setBorder(normalBorder);
        TextFieldMiddleName.setBorder(normalBorder);
        TextFieldLastNameRegister.setBorder(normalBorder);
        TextFieldEmail.setBorder(normalBorder);
        TextFieldUsernameRegister.setBorder(normalBorder);
        PasswordFieldRegister.setBorder(normalBorder);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMain = new javax.swing.JPanel();
        PanelHeader = new javax.swing.JPanel();
        LogoJoysis = new javax.swing.JLabel();
        LogoTesda = new javax.swing.JLabel();
        LabelTitle = new javax.swing.JLabel();
        PanelBody = new javax.swing.JPanel();
        TabbedPanel = new javax.swing.JTabbedPane();
        TabbedPanelLogin = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TextFieldUsernameLogin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PasswordFieldLogin = new javax.swing.JPasswordField();
        ButtonLogin = new util.PrimaryButton();
        CheckBoxShowPasswordLogin = new javax.swing.JCheckBox();
        TabbedPanelRegister = new javax.swing.JPanel();
        ButtonRegister = new util.PrimaryButton();
        TextFieldFirstName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextFieldUsernameRegister = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldLastNameRegister = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFieldMiddleName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextFieldEmail = new javax.swing.JTextField();
        PasswordFieldRegister = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        ComboBoxRole = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CheckBoxShowPasswordRegister = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        PanelDBConnectionStatus = new javax.swing.JPanel();
        JLabel11 = new javax.swing.JLabel();
        LabelConnectionStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Book Borrowing System");

        PanelMain.setBackground(new java.awt.Color(204, 204, 204));

        PanelHeader.setBackground(new java.awt.Color(0, 102, 0));

        LogoJoysis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogoJoysis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo-joysis.png"))); // NOI18N
        LogoJoysis.setToolTipText("");

        LogoTesda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogoTesda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo-tesda.png"))); // NOI18N
        LogoTesda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LabelTitle.setBackground(new java.awt.Color(204, 204, 204));
        LabelTitle.setFont(new java.awt.Font("Inter", 1, 28)); // NOI18N
        LabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitle.setText("LIBRARY BOOK BORROWING SYSTEM");
        LabelTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelHeaderLayout = new javax.swing.GroupLayout(PanelHeader);
        PanelHeader.setLayout(PanelHeaderLayout);
        PanelHeaderLayout.setHorizontalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addComponent(LogoJoysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogoTesda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelHeaderLayout.setVerticalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addComponent(LogoJoysis, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(LabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LogoTesda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelBody.setBackground(new java.awt.Color(204, 204, 204));
        PanelBody.setFont(PanelBody.getFont().deriveFont(PanelBody.getFont().getSize()+2f));

        TabbedPanel.setBackground(new java.awt.Color(250, 250, 250));
        TabbedPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TabbedPanel.setForeground(new java.awt.Color(51, 51, 51));
        TabbedPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabbedPanel.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        TabbedPanelLogin.setBackground(new java.awt.Color(250, 250, 250));
        TabbedPanelLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        TabbedPanelLogin.setToolTipText("");
        TabbedPanelLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabbedPanelLogin.setFont(TabbedPanelLogin.getFont().deriveFont(TabbedPanelLogin.getFont().getSize()+2f));
        TabbedPanelLogin.setInheritsPopupMenu(true);

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Username:");

        TextFieldUsernameLogin.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldUsernameLogin.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        TextFieldUsernameLogin.setForeground(new java.awt.Color(51, 51, 51));
        TextFieldUsernameLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TextFieldUsernameLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUsernameLoginActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Password:");

        PasswordFieldLogin.setBackground(new java.awt.Color(204, 204, 204));
        PasswordFieldLogin.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        PasswordFieldLogin.setForeground(new java.awt.Color(51, 51, 51));
        PasswordFieldLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PasswordFieldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldLoginActionPerformed(evt);
            }
        });

        ButtonLogin.setText("LOGIN");
        ButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoginActionPerformed(evt);
            }
        });

        CheckBoxShowPasswordLogin.setBackground(new java.awt.Color(250, 250, 250));
        CheckBoxShowPasswordLogin.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        CheckBoxShowPasswordLogin.setForeground(new java.awt.Color(51, 51, 51));
        CheckBoxShowPasswordLogin.setText("Show Password");
        CheckBoxShowPasswordLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        CheckBoxShowPasswordLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxShowPasswordLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TabbedPanelLoginLayout = new javax.swing.GroupLayout(TabbedPanelLogin);
        TabbedPanelLogin.setLayout(TabbedPanelLoginLayout);
        TabbedPanelLoginLayout.setHorizontalGroup(
            TabbedPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabbedPanelLoginLayout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addGroup(TabbedPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextFieldUsernameLogin)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PasswordFieldLogin)
                    .addComponent(CheckBoxShowPasswordLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        TabbedPanelLoginLayout.setVerticalGroup(
            TabbedPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabbedPanelLoginLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldUsernameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckBoxShowPasswordLogin)
                .addGap(35, 35, 35)
                .addComponent(ButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        TabbedPanel.addTab("Login", TabbedPanelLogin);
        TabbedPanelLogin.getAccessibleContext().setAccessibleName("");

        TabbedPanelRegister.setBackground(new java.awt.Color(250, 250, 250));
        TabbedPanelRegister.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        TabbedPanelRegister.setFont(TabbedPanelRegister.getFont().deriveFont(TabbedPanelRegister.getFont().getSize()+2f));

        ButtonRegister.setText("REGISTER");
        ButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRegisterActionPerformed(evt);
            }
        });

        TextFieldFirstName.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldFirstName.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        TextFieldFirstName.setForeground(new java.awt.Color(51, 51, 51));
        TextFieldFirstName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldFirstNameActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("First Name:");

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Password:");

        TextFieldUsernameRegister.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldUsernameRegister.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        TextFieldUsernameRegister.setForeground(new java.awt.Color(51, 51, 51));
        TextFieldUsernameRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TextFieldUsernameRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUsernameRegisterActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Last Name:");

        TextFieldLastNameRegister.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldLastNameRegister.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        TextFieldLastNameRegister.setForeground(new java.awt.Color(51, 51, 51));
        TextFieldLastNameRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TextFieldLastNameRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldLastNameRegisterActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Middle Name:");

        TextFieldMiddleName.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldMiddleName.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        TextFieldMiddleName.setForeground(new java.awt.Color(51, 51, 51));
        TextFieldMiddleName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TextFieldMiddleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldMiddleNameActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Email:");

        TextFieldEmail.setBackground(new java.awt.Color(204, 204, 204));
        TextFieldEmail.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        TextFieldEmail.setForeground(new java.awt.Color(51, 51, 51));
        TextFieldEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldEmailActionPerformed(evt);
            }
        });

        PasswordFieldRegister.setBackground(new java.awt.Color(204, 204, 204));
        PasswordFieldRegister.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        PasswordFieldRegister.setForeground(new java.awt.Color(51, 51, 51));
        PasswordFieldRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PasswordFieldRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldRegisterActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Username:");

        ComboBoxRole.setBackground(new java.awt.Color(204, 204, 204));
        ComboBoxRole.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        ComboBoxRole.setForeground(new java.awt.Color(51, 51, 51));
        ComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Member", "Librarian", "Admin" }));
        ComboBoxRole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ComboBoxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxRoleActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Role:");

        CheckBoxShowPasswordRegister.setBackground(new java.awt.Color(250, 250, 250));
        CheckBoxShowPasswordRegister.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        CheckBoxShowPasswordRegister.setForeground(new java.awt.Color(51, 51, 51));
        CheckBoxShowPasswordRegister.setText("Show Password");
        CheckBoxShowPasswordRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        CheckBoxShowPasswordRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxShowPasswordRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TabbedPanelRegisterLayout = new javax.swing.GroupLayout(TabbedPanelRegister);
        TabbedPanelRegister.setLayout(TabbedPanelRegisterLayout);
        TabbedPanelRegisterLayout.setHorizontalGroup(
            TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabbedPanelRegisterLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckBoxShowPasswordRegister)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordFieldRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TabbedPanelRegisterLayout.createSequentialGroup()
                        .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(TextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldMiddleName, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldUsernameRegister)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(ButtonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxRole, 0, 220, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextFieldLastNameRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        TabbedPanelRegisterLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CheckBoxShowPasswordRegister, ComboBoxRole, PasswordFieldRegister, TextFieldEmail, TextFieldFirstName, TextFieldLastNameRegister, TextFieldMiddleName, TextFieldUsernameRegister, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        TabbedPanelRegisterLayout.setVerticalGroup(
            TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabbedPanelRegisterLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TabbedPanelRegisterLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PasswordFieldRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CheckBoxShowPasswordRegister))
                    .addGroup(TabbedPanelRegisterLayout.createSequentialGroup()
                        .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabbedPanelRegisterLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabbedPanelRegisterLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldLastNameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabbedPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldUsernameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addComponent(ButtonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        TabbedPanelRegisterLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CheckBoxShowPasswordRegister, ComboBoxRole, PasswordFieldRegister, TextFieldEmail, TextFieldFirstName, TextFieldLastNameRegister, TextFieldMiddleName, TextFieldUsernameRegister, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        TabbedPanel.addTab("Register", TabbedPanelRegister);
        TabbedPanelRegister.getAccessibleContext().setAccessibleName("");

        jLabel10.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("© 2025 Joysis Java Batch 10 — Group 1: Amamangpang, Bolina, Borre, Lorente, Masmela, Nocon.");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        PanelDBConnectionStatus.setBackground(new java.awt.Color(102, 102, 102));
        PanelDBConnectionStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout PanelDBConnectionStatusLayout = new javax.swing.GroupLayout(PanelDBConnectionStatus);
        PanelDBConnectionStatus.setLayout(PanelDBConnectionStatusLayout);
        PanelDBConnectionStatusLayout.setHorizontalGroup(
            PanelDBConnectionStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        PanelDBConnectionStatusLayout.setVerticalGroup(
            PanelDBConnectionStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        JLabel11.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        JLabel11.setForeground(new java.awt.Color(102, 102, 102));
        JLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLabel11.setText("Connection Status: ");
        JLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LabelConnectionStatus.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        LabelConnectionStatus.setForeground(new java.awt.Color(102, 102, 102));
        LabelConnectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelConnectionStatus.setText("Waiting Connection...");
        LabelConnectionStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelBodyLayout = new javax.swing.GroupLayout(PanelBody);
        PanelBody.setLayout(PanelBodyLayout);
        PanelBodyLayout.setHorizontalGroup(
            PanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBodyLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBodyLayout.createSequentialGroup()
                .addGroup(PanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelBodyLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelConnectionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelDBConnectionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelBodyLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(TabbedPanel)))
                .addGap(119, 119, 119))
        );
        PanelBodyLayout.setVerticalGroup(
            PanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBodyLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(PanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelDBConnectionStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLabel11)
                        .addComponent(LabelConnectionStatus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedPanel)
                .addGap(51, 51, 51)
                .addComponent(jLabel10)
                .addGap(14, 14, 14))
        );

        TabbedPanel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addComponent(PanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PanelMain, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxRoleActionPerformed
        // If Admin is selected, block and revert to Member
        Object sel = ComboBoxRole.getSelectedItem();
        if (sel != null && "Admin".equalsIgnoreCase(sel.toString())) {
            JOptionPane.showMessageDialog(this,
                "Admin role selection is prohibited and not allowed.",
                "Not allowed",
                JOptionPane.WARNING_MESSAGE
            );
            ComboBoxRole.setSelectedItem("Member");
        }
    }//GEN-LAST:event_ComboBoxRoleActionPerformed

    private void PasswordFieldRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldRegisterActionPerformed

    private void PasswordFieldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldLoginActionPerformed

    private void ButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoginActionPerformed
        final String userOrEmail = TextFieldUsernameLogin.getText().trim();
        final String rawPassword = new String(PasswordFieldLogin.getPassword());

        if (userOrEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username or email.");
            return;
        }
        if (rawPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your password.");
            return;
        }

        try {
            Methods.AccountLookup acc = methods.findAccountByUsernameOrEmail(userOrEmail);
            if (acc == null) {
                JOptionPane.showMessageDialog(this, "Account does not exist.");
                return;
            }

            String hashed = PasswordHash.hashPassword(rawPassword);
            if (!hashed.equalsIgnoreCase(acc.hashedPassword)) {
                JOptionPane.showMessageDialog(this, "Password is incorrect.");
                return;
            }

            // Success → open Dashboard
            this.setVisible(false);
            new Dashboard().setVisible(true);
            this.dispose();

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Login error", ex);
            JOptionPane.showMessageDialog(this,
                "We couldn’t complete the login due to a system issue.\nDetails: " + ex.getMessage());
        }
    }//GEN-LAST:event_ButtonLoginActionPerformed

    private void TextFieldUsernameLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUsernameLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldUsernameLoginActionPerformed

    private void CheckBoxShowPasswordLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxShowPasswordLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxShowPasswordLoginActionPerformed

    private void TextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldFirstNameActionPerformed

    private void TextFieldMiddleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldMiddleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldMiddleNameActionPerformed

    private void TextFieldLastNameRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldLastNameRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldLastNameRegisterActionPerformed

    private void TextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldEmailActionPerformed

    private void TextFieldUsernameRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUsernameRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldUsernameRegisterActionPerformed

    private void CheckBoxShowPasswordRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxShowPasswordRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxShowPasswordRegisterActionPerformed

    private void ButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRegisterActionPerformed
        // Collect + validate; accumulate errors to show in a single dialog
        final String first = TextFieldFirstName.getText().trim();
        final String middle = TextFieldMiddleName.getText().trim();     // optional
        final String last = TextFieldLastNameRegister.getText().trim();
        final String email = TextFieldEmail.getText().trim();
        final String username = TextFieldUsernameRegister.getText().trim();
        final String role = (String) ComboBoxRole.getSelectedItem();
        final String passRaw = new String(PasswordFieldRegister.getPassword());

        List<String> errors = new ArrayList<>();

        // REQUIRED vs INVALID messages
        if (first.isEmpty()) {
            errors.add("• First name is required.");
        } else if (!filters.isValidName(first)) {
            errors.add("• First name format is invalid.");
        }

        if (!middle.isEmpty() && !filters.isValidName(middle)) {
            errors.add("• Middle name format is invalid.");
        }

        if (last.isEmpty()) {
            errors.add("• Last name is required.");
        } else if (!filters.isValidName(last)) {
            errors.add("• Last name format is invalid.");
        }

        if (email.isEmpty()) {
            errors.add("• Email is required.");
        } else if (!filters.isValidEmail(email)) {
            errors.add("• Email format is invalid.");
        }

        if (username.isEmpty()) {
            errors.add("• Username is required.");
        } else if (!filters.isValidUsername(username)) {
            errors.add("• Username must be 3–50 chars (letters, numbers, . or _).");
        }

        if (passRaw.isEmpty()) {
            errors.add("• Password is required.");
        } else if (passRaw.length() < 8) {
            errors.add("• Password must be at least 8 characters.");
        }

        if (role == null || role.isBlank()) {
            errors.add("• Role is required.");
        }

        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please fix the following:\n\n" + String.join("\n", errors),
                "Invalid input",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Confirm
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure with your details?",
            "Confirm details",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            // Save based on role and always create an account
            methods.registerPersonAndAccount(
                first, middle, last, /*phone*/ null, email,
                username, role, PasswordHash.hashPassword(passRaw)
            );

            JOptionPane.showMessageDialog(this,
                    "Your account has been created successfully.\nRedirecting back to Login panel.");
            // Switch tab to Login
            TabbedPanel.setSelectedComponent(TabbedPanelLogin);

            // Clear Register fields & borders
            clearRegisterFields();

        } catch (SQLException dup) {
            JOptionPane.showMessageDialog(this,
                "Could not create the account.\nDetails: " + dup.getMessage());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Register error", ex);
            JOptionPane.showMessageDialog(this,
                "We couldn’t complete the registration due to a system issue.\nDetails: " + ex.getMessage());
        }
    }//GEN-LAST:event_ButtonRegisterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private util.PrimaryButton ButtonLogin;
    private util.PrimaryButton ButtonRegister;
    private javax.swing.JCheckBox CheckBoxShowPasswordLogin;
    private javax.swing.JCheckBox CheckBoxShowPasswordRegister;
    private javax.swing.JComboBox<String> ComboBoxRole;
    private javax.swing.JLabel JLabel11;
    private javax.swing.JLabel LabelConnectionStatus;
    private javax.swing.JLabel LabelTitle;
    private javax.swing.JLabel LogoJoysis;
    private javax.swing.JLabel LogoTesda;
    private javax.swing.JPanel PanelBody;
    private javax.swing.JPanel PanelDBConnectionStatus;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPasswordField PasswordFieldLogin;
    private javax.swing.JPasswordField PasswordFieldRegister;
    private javax.swing.JTabbedPane TabbedPanel;
    private javax.swing.JPanel TabbedPanelLogin;
    private javax.swing.JPanel TabbedPanelRegister;
    private javax.swing.JTextField TextFieldEmail;
    private javax.swing.JTextField TextFieldFirstName;
    private javax.swing.JTextField TextFieldLastNameRegister;
    private javax.swing.JTextField TextFieldMiddleName;
    private javax.swing.JTextField TextFieldUsernameLogin;
    private javax.swing.JTextField TextFieldUsernameRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
