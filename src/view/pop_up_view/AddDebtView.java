package view.pop_up_view;

import control.AddDebtController;
import control.MainController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
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
public class AddDebtView extends javax.swing.JFrame {

    private boolean updated;
    private AddDebtController controller;
    private String sessionKey;

    /**
     * Creates new form ClientInfo
     */
    public AddDebtView(AddDebtController controller, String sessionKey) {
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 180));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
            }
        });
        initComponents();
        setLocationRelativeTo(null);
        amountField.requestFocus();
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
        addDebtButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();
        oneLessDayButton = new javax.swing.JButton();
        oneMoreDayButton = new javax.swing.JButton();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar deuda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
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
        amountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha:");

        dateField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        dateField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dateField.setText("dd/MM/aaaa");

        addDebtButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        addDebtButton.setText("Agregar deuda");
        addDebtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDebtButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 102, 102));
        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        warningLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        warningLabel.setForeground(new java.awt.Color(255, 0, 51));
        warningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warningLabel.setText("!");

        oneLessDayButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        oneLessDayButton.setText("<");
        oneLessDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneLessDayButtonActionPerformed(evt);
            }
        });

        oneMoreDayButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        oneMoreDayButton.setText(">");
        oneMoreDayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneMoreDayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalNotPaidBalanceLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amountField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dateField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oneLessDayButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oneMoreDayButton)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(236, Short.MAX_VALUE)
                .addComponent(addDebtButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(clientLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(totalNotPaidBalanceLabel)))
                    .addComponent(warningLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oneLessDayButton)
                    .addComponent(oneMoreDayButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(addDebtButton))
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

    private void addDebtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDebtButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addDebtButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void amountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountFieldActionPerformed

    private void oneLessDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneLessDayButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oneLessDayButtonActionPerformed

    private void oneMoreDayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneMoreDayButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oneMoreDayButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDebtButton;
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
    private javax.swing.JButton oneLessDayButton;
    private javax.swing.JButton oneMoreDayButton;
    private javax.swing.JLabel totalNotPaidBalanceLabel;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables

    public void setMainElementFocus() {
        amountField.requestFocus();
    }

    public void setWarningLabel() {
        warningLabel.setVisible(controller.getCurrentClient().
                isDefaulter());
    }

    public void clear() {
        clearAmount();
        clearDate();
    }

    public void clearAmount() {
        amountField.setText("");
    }

    public void clearDate() {
        dateField.setText("");
    }

    public void updateView() {
        if (!updated) {
            amountField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            controller.addDebt();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
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
                            controller.addDebt();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            oneLessDayButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.removeOneDayToDate();
                    } catch (ParseException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });
            oneMoreDayButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.addOneDayToDate();
                    } catch (ParseException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });
            addDebtButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.addDebt();
                    } catch (IOException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
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
        return dateField.getText().
                trim();
    }

    public String getNewDebtAmount() {
        return amountField.getText().
                trim();
    }

    public void setDate(String date) {
        dateField.setText(date);
    }

    public String getDate() {
        return dateField.getText().
                trim();
    }
    
    public void setClientIdentification() {
        clientLabel.setText("<html>" + controller.getCurrentClient().getName() + ",<br>" + controller.getCurrentClient().getNick() + "</html>");
    }
    
    public void setClientNotPaidBalance() {
        totalNotPaidBalanceLabel.setText("$" + MainController.formatAmount(controller.getCurrentClient().getTotalNotPaidBalance()));
    }
    
    private void setFocus(JTextField field) {
        field.selectAll();
        field.requestFocus();
    }
    
    public void setFocusOnAmount() {
        setFocus(amountField);
    }
    
    public void setFocusOnDate() {
        setFocus(dateField);
    }
}
