package view.pop_up_view;

import control.AddClientController;
import control.AddDebtController;
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
public class AddClientView extends javax.swing.JFrame {

    private boolean updated;
    private AddClientController controller;

    /**
     * Creates new form ClientInfo
     */
    public AddClientView(AddClientController controller) {
        updated = false;
        this.controller = controller;

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
        nameTextField.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        addClientButton = new javax.swing.JButton();
        nickTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        areaField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        initialBalanceField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cpNumberField = new javax.swing.JTextField();
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        addClientButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        addClientButton.setText("Agregar cliente");

        nickTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nick:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");

        nameTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Área:");

        areaField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Saldo inicial:");

        initialBalanceField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        initialBalanceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initialBalanceFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Teléfono:");

        cpNumberField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        cancelButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 102, 102));
        cancelButton.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(areaField)
                            .addComponent(cpNumberField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nickTextField)
                            .addComponent(nameTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 128, Short.MAX_VALUE)
                                .addComponent(addClientButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton))
                            .addComponent(initialBalanceField))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cpNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(areaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initialBalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(addClientButton))
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
            .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initialBalanceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initialBalanceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_initialBalanceFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClientButton;
    private javax.swing.JTextField areaField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField cpNumberField;
    private javax.swing.JTextField initialBalanceField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JDesktopPane mainContainer;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField nickTextField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        if (!updated) {
            nameTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            nickTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            addClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.addClient();
                    } catch (IOException ex) {
                        Logger.getLogger(AddClientController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddClientController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddClientController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddClientController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });
            addClientButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.addClient();
                        } catch (IOException ex) {
                            Logger.getLogger(AddDebtController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ParseException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        } catch (SQLException ex) {
                            Logger.
                                    getLogger(AddClientController.class.
                                            getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelAddingAClient();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelAddingAClient();
                    }
                }
            });

            updated = true;
        }
    }

    public void setMainElementFocus() {
        nameTextField.requestFocus();
    }

    private void setFocus(JTextField field) {
        field.requestFocus();
        field.selectAll();
    }

    public void clearName() {
        nameTextField.setText("");
    }

    public void clearNick() {
        nickTextField.setText("");
    }

    public void clearCPNumber() {
        cpNumberField.setText("");
    }

    public void clearArea() {
        areaField.setText("");
    }

    public void clearInitialBalance() {
        initialBalanceField.setText("");
    }

    public String getNewClientName() {
        return nameTextField.getText().
                trim();
    }

    public void setFocusOnName() {
        setFocus(nameTextField);
    }

    public String getNewClientNick() {
        return nickTextField.getText().
                trim();
    }

    public void setFocusOnNick() {
        setFocus(nickTextField);
    }

    public String getNewClientCPNumber() {
        return cpNumberField.getText().
                trim();
    }

    public void setFocusOnCPNumber() {
        setFocus(cpNumberField);
    }

    public String getNewClientArea() {
        return areaField.getText().
                trim();
    }

    public void setFocusOnArea() {
        setFocus(areaField);
    }

    public String getNewClientInitialBalance() {
        return initialBalanceField.getText().
                trim();
    }

    public void setFocusOnInitialBalance() {
        setFocus(initialBalanceField);
    }

    public void setNullNewBalance() {
        initialBalanceField.setText("");
    }
}
