package control;

import model.IO.Reader;
import model.User;
import model.enums.OperationCode;
import view.pop_up_view.LogInView;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author admin
 */
public class LogInController {

    private final LogInView view;
    private final String sessionKey;
    private User user;

    public LogInController(String sessionKey)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        this.sessionKey = sessionKey;
        view = new LogInView(this, sessionKey);
        view.updateView();
    }

    public LogInView getView() {
        return view;
    }

    public void logIn()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException,
            InterruptedException {
        
        String username = view.getUsername();
        String password = view.getPassword();
        
        user = Reader.getUser(username, password);
        
        if (user != null) {
            MainController.executeOperation(OperationCode.launchApplication, sessionKey);
            username = null;
            password = null;
            view.clear();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Datos inválidos");
        }
    }
    
    public User getUser() {
        return user;
    }
}
