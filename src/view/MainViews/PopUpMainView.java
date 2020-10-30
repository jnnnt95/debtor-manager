package view.MainViews;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

public final class PopUpMainView extends javax.swing.JFrame {

    public PopUpMainView() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Default content placeholder");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(jLabel1)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(jLabel1)
                    .addContainerGap(118, Short.MAX_VALUE)))
        );

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel container;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
