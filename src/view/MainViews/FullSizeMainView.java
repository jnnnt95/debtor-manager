package view.MainViews;

import control.MainController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import model.IO.Reader;
import view.full_size_view.ClientInfoView;
import view.full_size_view.DepositClientsOnDateView;
import view.full_size_view.DetailedHistoryView;
import view.full_size_view.QueryClientView;

public final class FullSizeMainView
        extends javax.swing.JFrame {

    private boolean updated;
    private final String sessionKey;
    private PopUpMainView popUpSizeViewport;

    public FullSizeMainView(String sessionKey) {
        updated = false;
        this.sessionKey = sessionKey;

        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Debtor Manager");
        ImageIcon img = new ImageIcon("Images/icon.png");
        setIconImage(img.getImage());
    }

    public void setInstantiationUserType(String userType,
            String user) {
        MainController.authenticate(sessionKey);
        setTitle("Debtor Manager | "
                + userType
                + ": "
                + user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        queryClientsMenuItem = new javax.swing.JMenuItem();
        createClientMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        todaysPaymentsMenuItem = new javax.swing.JMenuItem();
        depositClientsMenuItem = new javax.swing.JMenuItem();
        businessMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        userInfoMenuItem = new javax.swing.JMenuItem();
        usersCredentialsMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Default content placeholder");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel1)
                .addContainerGap(587, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel1)
                .addContainerGap(467, Short.MAX_VALUE))
        );

        FileMenu.setText("Opciones");

        queryClientsMenuItem.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        queryClientsMenuItem.setText("Consultar clientes");
        queryClientsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryClientsMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(queryClientsMenuItem);

        createClientMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        createClientMenuItem.setText("Crear cliente");
        FileMenu.add(createClientMenuItem);
        FileMenu.add(jSeparator1);

        aboutMenuItem.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        aboutMenuItem.setText("About...");
        FileMenu.add(aboutMenuItem);

        exitMenuItem.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        exitMenuItem.setText("Salir");
        FileMenu.add(exitMenuItem);

        MenuBar.add(FileMenu);

        jMenu1.setText("Información");

        todaysPaymentsMenuItem.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        todaysPaymentsMenuItem.setText("Caja por cobros del día");
        jMenu1.add(todaysPaymentsMenuItem);

        depositClientsMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        depositClientsMenuItem.setText("Clientes abonados");
        jMenu1.add(depositClientsMenuItem);

        businessMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        businessMenuItem.setText("Resumen de negocio");
        jMenu1.add(businessMenuItem);
        jMenu1.add(jSeparator2);

        userInfoMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        userInfoMenuItem.setText("Información de usuario");
        jMenu1.add(userInfoMenuItem);

        usersCredentialsMenuItem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        usersCredentialsMenuItem.setText("Credenciales de usuarios");
        jMenu1.add(usersCredentialsMenuItem);

        MenuBar.add(jMenu1);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void queryClientsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryClientsMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_queryClientsMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu FileMenu;
    public javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem businessMenuItem;
    public javax.swing.JPanel container;
    private javax.swing.JMenuItem createClientMenuItem;
    private javax.swing.JMenuItem depositClientsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem queryClientsMenuItem;
    private javax.swing.JMenuItem todaysPaymentsMenuItem;
    private javax.swing.JMenuItem userInfoMenuItem;
    public javax.swing.JMenuItem usersCredentialsMenuItem;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            queryClientsMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainController.changeToQueryClientMode(sessionKey);
                    } catch (InterruptedException |
                            IOException |
                            ParseException |
                            ClassNotFoundException |
                            SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }

            });
            exitMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int option;
                    option = JOptionPane.showConfirmDialog(null,
                            "¿Salir de Debtor Manager?");
                    switch (option) {
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                }
            });
            todaysPaymentsMenuItem.addActionListener(
                    new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JOptionPane.showMessageDialog(null,
                                Reader.getTodaysPaymentsBalance());
                    } catch (ClassNotFoundException |
                            SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });
            
            updated = true;
        }
        createClientMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.changeToCreateClientMode(sessionKey);
            }
        });
        aboutMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAbout();
            }
        });
        userInfoMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getUserInfo(MainController.getUser().getId()));
                } catch (SQLException |
                        ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
    }

    public void prepareViewForAdministrator() {
        MainController.authenticate(sessionKey);
        setInstantiationUserType("Administrador",
                MainController.getUser().
                        getName());
        businessMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getBusinessBriefing());
                } catch (SQLException |
                        ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        usersCredentialsMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getUsersCredentials());
                } catch (SQLException |
                        ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        depositClientsMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.changeToDepositClientsOnDateMode(sessionKey);
                } catch (ParseException ex) {
                    Logger.getLogger(FullSizeMainView.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void prepareViewForNormalUser() {
        MainController.authenticate(sessionKey);
        setInstantiationUserType("Usuario",
                MainController.getUser().
                        getName());
        businessMenuItem.setVisible(false);
        usersCredentialsMenuItem.setVisible(false);
    }
    
    private void setContainerContent(JDesktopPane content) {
        MainController.authenticate(sessionKey);
        if(!content.getSize().equals(container.getSize())) {
            content.setSize(MainController.getFullSizeDimension());
        }
        container.getComponent(0).
                setVisible(false);
        container.removeAll();
        container.add(content);
        container.getComponent(0).
                setVisible(true);
    }
    
    private void backToFullSize() {
        MainController.authenticate(sessionKey);
        popUpSizeViewport.setVisible(false);
        setEnabled(true);
        requestFocus();
    }

    private static void showAbout() {
        String printable = "<html><strong>® Debtor Manager<br><br>"
                + "© Jonathan Torres</strong><br>"
                + "   Se prohibe la distribución no autorizada de este software.<br>"
                + "   Para más información:<br>"
                + "      <strong>email</strong>: jnthntrm@gmail.com<br>"
                + "      <strong>teléfono celular</strong>: 305 925 40 24<br><br>"
                + "<strong>Arte</strong>: https://iconos8.es/</html>";
        JOptionPane.showMessageDialog(null,
                printable);
    }
    
    public void setPopUpSizeViewport(PopUpMainView popUpSizeViewport) {
        MainController.authenticate(sessionKey);
        this.popUpSizeViewport = popUpSizeViewport;
    }
    
    // -------------------- Change-to-methods

    public void changeToQueryClientMode(QueryClientView queryClientView) {
        MainController.authenticate(sessionKey);
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        if(!depositClientsMenuItem.isVisible()) {
            depositClientsMenuItem.setVisible(true);
        }
        setContainerContent(queryClientView.mainContainer);
        queryClientView.setSearchFieldText("");
        queryClientView.setMainElementFocus();
        queryClientsMenuItem.setVisible(false);
        backToFullSize();
    }
    
    public void changeToClientInfoMode(ClientInfoView clientInfoView) {
        MainController.authenticate(sessionKey);
        if (!queryClientsMenuItem.isVisible()) {
            queryClientsMenuItem.setVisible(true);
        }
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        if(!depositClientsMenuItem.isVisible()) {
            depositClientsMenuItem.setVisible(true);
        }
        setContainerContent(clientInfoView.mainContainer);
        backToFullSize();
        clientInfoView.setMainElementFocus();
    }

    public void changeToDetailedHistoryMode(DetailedHistoryView detailedHistoryView) {
        MainController.authenticate(sessionKey);
        if (!queryClientsMenuItem.isVisible()) {
            queryClientsMenuItem.setVisible(true);
        }
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        if(!depositClientsMenuItem.isVisible()) {
            depositClientsMenuItem.setVisible(true);
        }
        MainController.authenticate(sessionKey);
        setContainerContent(detailedHistoryView.mainContainer);
        backToFullSize();
        detailedHistoryView.setMainElementFocus();
    }

    public void changeToDepositClientsOnDateMode(DepositClientsOnDateView depositClientsOnDateView) {
        MainController.authenticate(sessionKey);
        if (!queryClientsMenuItem.isVisible()) {
            queryClientsMenuItem.setVisible(true);
        }
        if (!createClientMenuItem.isVisible()) {
            createClientMenuItem.setVisible(true);
        }
        depositClientsOnDateView.setToday();
        setContainerContent(depositClientsOnDateView.mainContainer);
        backToFullSize();
        depositClientsOnDateView.setMainElementFocus();
        depositClientsMenuItem.setVisible(false);
    }
}
