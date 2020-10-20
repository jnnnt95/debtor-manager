package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import view.LogInView;

/**
 *
 * @author admin
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, SQLException {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LogInView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new LogInController();
    }
    
    
}
