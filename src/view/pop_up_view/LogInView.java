package view.pop_up_view;

import control.LogInController;
import control.MainController;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;

/**
 *
 * @author admin
 */
public class LogInView extends javax.swing.JFrame {

    Container contentPane;
    private boolean updated;
    private final LogInController controller;
    private final String sessionKey;

    /**
     * Creates new form LogInView
     * @param controller
     * @param sessionKey
     */
    public LogInView(LogInController controller, String sessionKey) {
        updated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
        userTextField.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JDesktopPane();
        jPanel6 = new javax.swing.JPanel();
        logInButton = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();
        userTextField = new javax.swing.JTextField();
        nameLabelTitle5 = new javax.swing.JLabel();
        nickLabelTitle5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setOpaque(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Iniciar sesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setOpaque(false);

        logInButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        logInButton.setText("Iniciar sesión");

        nameLabelTitle5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nameLabelTitle5.setForeground(new java.awt.Color(255, 255, 255));
        nameLabelTitle5.setText("Usuario:");

        nickLabelTitle5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nickLabelTitle5.setForeground(new java.awt.Color(255, 255, 255));
        nickLabelTitle5.setText("Contraseña:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nickLabelTitle5)
                            .addComponent(nameLabelTitle5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTextField)
                            .addComponent(userTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 331, Short.MAX_VALUE)
                        .addComponent(logInButton)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabelTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickLabelTitle5)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(logInButton)
                .addContainerGap())
        );

        mainContainer.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainContainer)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton logInButton;
    public javax.swing.JDesktopPane mainContainer;
    private javax.swing.JLabel nameLabelTitle5;
    private javax.swing.JLabel nickLabelTitle5;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            userTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.logIn();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                            Logger.getLogger(LogInController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            passwordTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.logIn();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                            Logger.getLogger(LogInController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            logInButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        controller.logIn();
                    } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            });
            logInButton.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        try {
                            controller.logIn();
                        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
                            Logger.getLogger(LogInController.class.getName()).
                                    log(Level.SEVERE,
                                            null,
                                            ex);
                        }
                    }
                }
            });
            updated = true;
        }
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        userTextField.requestFocus();
    }

    public String getUsername() {
        MainController.authenticate(sessionKey);
        return userTextField.getText().
                trim();
    }

    public String getPassword() {
        MainController.authenticate(sessionKey);
        return passwordTextField.getText().
                trim();
    }

    public void clear() {
        MainController.authenticate(sessionKey);
        clearUsername();
        clearPassword();
    }

    private void clearUsername() {
        MainController.authenticate(sessionKey);
        userTextField.setText("");
    }

    private void clearPassword() {
        MainController.authenticate(sessionKey);
        userTextField.setText("");
    }

}
