package view.pop_up_view;

import control.ModifyClientController;
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
public class ModifyClientView extends javax.swing.JFrame {

    private boolean updated;
    private ModifyClientController controller;

    /**
     * Creates new form modifyClientView
     */
    public ModifyClientView(ModifyClientController controller) {
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

        mainContainer = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nickTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        modifyClientButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cpNumberField = new javax.swing.JTextField();
        areaField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");

        nameTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nick:");

        nickTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Área:");

        modifyClientButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        modifyClientButton.setText("Modificar cliente");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Teléfono");

        cpNumberField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        areaField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpNumberField)
                            .addComponent(nameTextField)
                            .addComponent(nickTextField)
                            .addComponent(areaField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 214, Short.MAX_VALUE)
                        .addComponent(modifyClientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(areaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyClientButton)
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
            .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField cpNumberField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JDesktopPane mainContainer;
    private javax.swing.JButton modifyClientButton;
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
                            controller.modifyClient();
                        } catch (ParseException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
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
                            controller.modifyClient();
                        } catch (ParseException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            modifyClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.modifyClient();
                    } catch (ParseException ex) {
                        Logger.getLogger(ModifyClientView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ModifyClientView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ModifyClientView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ModifyClientView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ModifyClientView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            });
            modifyClientButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.modifyClient();
                        } catch (ParseException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ModifyClientView.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    controller.cancelModifyingAClient();
                }
            });
            cancelButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.cancelModifyingAClient();
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
    
    public String getNewName() {
        return nameTextField.getText().
                trim();
    }

    public void setFocusOnName() {
        setFocus(nameTextField);
    }

    public String getNewNick() {
        return nickTextField.getText().
                trim();
    }

    public void setFocusOnNick() {
        setFocus(nickTextField);
    }

    public String getNewCPNumber() {
        return cpNumberField.getText().
                trim();
    }

    public void setFocusOnCPNumber() {
        setFocus(cpNumberField);
    }

    public String getNewArea() {
        return areaField.getText().
                trim();
    }

    public void setFocusOnArea() {
        setFocus(areaField);
    }
    
    public void setClientName(String name) {
        nameTextField.setText(name);
    }
    
    public void setClientNick(String nick) {
        nickTextField.setText(nick);
    }
    
    public void setClientCPNumber(String cPNumber) {
        cpNumberField.setText(cPNumber);
    }
    
    public void setClientArea(String area) {
        areaField.setText(area);
    }
}
