package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import model.IO.Reader;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.LogInView;

/**
 *
 * @author admin
 */
public class LogInController {

    private LogInView view;
    private String sessionKey;
    private User user;

    public LogInController(String sessionKey)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        this.sessionKey = sessionKey;
        view = new LogInView();
        initView();
        view.userTextField.requestFocus();
    }

    public LogInView getView() {
        return view;
    }

    private void initView() {
        view.userTextField.requestFocus();
        view.logInButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    logIn();
                } catch (IOException ex) {
                    Logger.getLogger(LogInController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ParseException ex) {
                    Logger.getLogger(LogInController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LogInController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LogInController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LogInController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        view.logInButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode()
                        == KeyEvent.VK_ENTER) {
                    try {
                        logIn();
                    } catch (IOException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            }
        });
        view.userTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode()
                        == KeyEvent.VK_ENTER) {
                    try {
                        logIn();
                    } catch (IOException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            }
        });
        view.passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode()
                        == KeyEvent.VK_ENTER) {
                    try {
                        logIn();
                    } catch (IOException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LogInController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            }
        });

    }

    public void logIn()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException,
            InterruptedException {
        
        String username = view.userTextField.getText().trim();
        String password = view.passwordTextField.getText().trim();
        
        user = Reader.getUser(username, password);
        
        if (user != null) {
            MainController.seek(OperationCode.launchApplication,
                    sessionKey);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos inválidos");
        }
    }
    
    public User getUser() {
        return user;
    }
}
