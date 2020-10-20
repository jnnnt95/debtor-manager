package control;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class tools {

    private static String sessionKey;
    private static boolean authenticated = false;

    public tools() {

    }
    
    public static String getSessionKey() {
        return sessionKey;
    }
    
    public static void swapWindow(JFrame son, JFrame parent) {
        son.setVisible(true);
        parent.setVisible(false);
    }

    public static int confirm(String s) {
        return JOptionPane.showConfirmDialog(null, s);
    }
}
