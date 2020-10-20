package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import view.LogInView;

/**
 *
 * @author admin
 */
public class LogInController {
    private LogInView view;
    private MenuController son;
    private String user = "johannamg75@yahoo.com";
    private String password = "negocio*1975";
    private static boolean logInAuthenticated;
    private static String sessionKey;
    //mode 1: Log In, 2: verification
    private int mode;
    //authentication verification
    private boolean authenticated;
    
        
    public LogInController() throws IOException, ParseException, ClassNotFoundException, SQLException {
        mode = 1;
        authenticated = false;
        view = new LogInView();
        logInAuthenticated = false;
        initView();
        view.setVisible(true);
    }
    
    private void initView() {
        view.userTextField.requestFocus();
        view.exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        view.logInButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    logIn();
                } catch (IOException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.logInButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        logIn();
                    } catch (IOException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.userTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        logIn();
                    } catch (IOException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        logIn();
                    } catch (IOException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public boolean isLogged() {
        return logInAuthenticated;
    }
    
    public void logIn() throws IOException, ParseException, ClassNotFoundException, SQLException {
        switch(mode) {
            case 1://log in
                if (userChecked() && passwordChecked()) {
                    logInAuthenticated = true;
                    setSessionKey();
                    start();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos inválidos");
                }
                break;
            case 2://verification
                if (userChecked() && passwordChecked()) {
                    authenticated = true;
                    son.showBusiness(authenticated);
                    tools.swapWindow(son.getView(), view);
                } else {
                    JOptionPane.showMessageDialog(null, "Datos inválidos");
                    tools.swapWindow(son.getView(), view);
                }
                break;
                
        }
        
    }
    
    private boolean userChecked() {
        if(view.userTextField.getText().equals(user)) {
            return true;
        }
        return false;
    }
    
    private boolean passwordChecked() {
        if(view.passwordTextField.getText().equals(password)) {
            return true;
        }
        return false;
    }
    
    private void setSessionKey() {
        String sessionKey;
        sessionKey = "";

        Random randomizer;
        randomizer = new Random();

        char currentCharacter;

        for (int i = 0; i < 30; i++) {
            switch (randomizer.nextInt(3)) {
                case 0:
                    currentCharacter = (char) (65 + randomizer.nextInt(26));
                    sessionKey = sessionKey + String.valueOf(currentCharacter);
                    break;
                case 1:
                    currentCharacter = (char) (97 + randomizer.nextInt(26));
                    sessionKey = sessionKey + String.valueOf(currentCharacter);
                    break;
                case 2:
                    currentCharacter = (char) (33 + randomizer.nextInt(32));
                    sessionKey = sessionKey + String.valueOf(currentCharacter);
                    break;
            }
        }

        this.sessionKey = sessionKey;
    }
    
    public void start() throws IOException, ParseException, ClassNotFoundException, SQLException {
        son = new MenuController(this, sessionKey);
        tools.swapWindow(son.getView(), view);
    }
    
    

    public void verifySession(String receivedSessionKey) {
        if(!receivedSessionKey.equals(sessionKey)) {
            JOptionPane.showMessageDialog(null, "Error:\n\n   No se pudo autenticar sesión de inicio. El programa se cerrará.");
            System.exit(0);
        }
    }
    
    public void verifyAuthentication() {
        view.userTextField.requestFocus();
        authenticated = false;
        
        view.userTextField.setText("");
        view.passwordTextField.setText("");
        view.setVisible(true);
        this.mode = 2;
    }
}
