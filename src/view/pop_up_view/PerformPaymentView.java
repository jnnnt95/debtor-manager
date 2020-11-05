
package view.pop_up_view;

import control.AddDebtController;
import control.MainController;
import control.PerformPaymentController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class PerformPaymentView extends javax.swing.JFrame {
    private boolean updated;
    private final PerformPaymentController controller;
    private final String sessionKey;
    /**
     * Creates new form ClientInfo
     * @param controller
     * @param sessionKey
     */
    public PerformPaymentView(PerformPaymentController controller, String sessionKey) {
        
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;
        
        initComponents();
        dateField.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        clientLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        totalNotPaidBalanceLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dateField = new javax.swing.JTextField();
        performPaymentButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Realizar pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cliente:");

        clientLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        clientLabel.setForeground(new java.awt.Color(255, 255, 255));
        clientLabel.setText("+++");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Saldo no pagado:");

        totalNotPaidBalanceLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        totalNotPaidBalanceLabel.setForeground(new java.awt.Color(255, 102, 102));
        totalNotPaidBalanceLabel.setText("+++");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto ($):");

        amountField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        amountField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        amountField.setText("$");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha:");

        dateField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        dateField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dateField.setText("dd/MM/aaaa");

        performPaymentButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        performPaymentButton.setText("Realizar pago");
        performPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performPaymentButtonActionPerformed(evt);
            }
        });

        warningLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        warningLabel.setForeground(new java.awt.Color(255, 0, 51));
        warningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warningLabel.setText("!");

        cancelButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 102, 102));
        cancelButton.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalNotPaidBalanceLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                        .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amountField)
                            .addComponent(dateField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 232, Short.MAX_VALUE)
                        .addComponent(performPaymentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warningLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(clientLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(totalNotPaidBalanceLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(performPaymentButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        mainContainer.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void performPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performPaymentButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_performPaymentButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel clientLabel;
    private javax.swing.JTextField dateField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JDesktopPane mainContainer;
    private javax.swing.JButton performPaymentButton;
    private javax.swing.JLabel totalNotPaidBalanceLabel;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables

    
    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        amountField.requestFocus();
    }

    public void setWarningLabel() {
        MainController.authenticate(sessionKey);
        warningLabel.setVisible(controller.getCurrentClient().
                isDefaulter());
    }

    public void clear() {
        MainController.authenticate(sessionKey);
        clearAmount();
        clearDate();
    }

    public void clearAmount() {
        MainController.authenticate(sessionKey);
        amountField.setText("");
    }

    public void clearDate() {
        MainController.authenticate(sessionKey);
        dateField.setText("");
    }

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            amountField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            controller.performPayment();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            dateField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            controller.performPayment();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            performPaymentButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.performPayment();
                    } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        MainController.changeToClientInfoMode(controller.getCurrentClient(),
                                sessionKey);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });

            updated = true;
        }
    }

    public String getNewDebtDate() {
        MainController.authenticate(sessionKey);
        return dateField.getText().
                trim();
    }

    public String getNewDebtAmount() {
        MainController.authenticate(sessionKey);
        return amountField.getText().
                trim();
    }

    public void setDate(String date) {
        MainController.authenticate(sessionKey);
        dateField.setText(date);
    }

    public String getDate() {
        MainController.authenticate(sessionKey);
        return dateField.getText().
                trim();
    }
    
    public void setClientIdentification() {
        MainController.authenticate(sessionKey);
        clientLabel.setText("<html>" + controller.getCurrentClient().getName() + ",<br>" + controller.getCurrentClient().getNick() + "</html>");
    }
    
    public void setClientNotPaidBalance() {
        MainController.authenticate(sessionKey);
        totalNotPaidBalanceLabel.setText("$" + MainController.formatAmount(controller.getCurrentClient().getTotalNotPaidBalance()));
    }
    
    private void setFocus(JTextField field) {
        MainController.authenticate(sessionKey);
        field.selectAll();
        field.requestFocus();
    }
    
    public void setFocusOnAmount() {
        MainController.authenticate(sessionKey);
        setFocus(amountField);
    }
    
    public void setFocusOnDate() {
        MainController.authenticate(sessionKey);
        setFocus(dateField);
    }
}
