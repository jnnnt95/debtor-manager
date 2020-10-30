package view.MainViews;

import javax.swing.ImageIcon;
import model.enums.UserType;

public final class FullSizeMainView extends javax.swing.JFrame {

    public FullSizeMainView() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Debtor Manager");
        
        ImageIcon img = new ImageIcon("Images/icon.png");
        setIconImage(img.getImage());
    }
    
    public void setInstantiationUserType(String userType, String user) {
        setTitle("Debtor Manager | " + userType + ": " + user);
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
    public javax.swing.JMenuItem aboutMenuItem;
    public javax.swing.JMenuItem businessMenuItem;
    public javax.swing.JPanel container;
    public javax.swing.JMenuItem createClientMenuItem;
    public javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JMenuItem queryClientsMenuItem;
    public javax.swing.JMenuItem todaysPaymentsMenuItem;
    public javax.swing.JMenuItem userInfoMenuItem;
    public javax.swing.JMenuItem usersCredentialsMenuItem;
    // End of variables declaration//GEN-END:variables
}
