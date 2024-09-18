package control;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.pop_up_view.LogInView;

/**
 *
 * @author admin
 */
public class Main {

    private static File instanceValidation;
    private static FileChannel channel;
    private static FileLock lock;

    public static void main(String[] args)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            instanceValidation = new File("Data/instanceValidation.dat");
            if (instanceValidation.exists()) {
                instanceValidation.delete();
            }
            channel = new RandomAccessFile(instanceValidation,
                    "rw").getChannel();
            lock = channel.tryLock();

            if (lock
                    == null) {
                channel.close();
                throw new RuntimeException(
                        "Debtor Manager ya se encuentra en ejecución");
            }
            
            Thread shutdown = new Thread(Main::unlock);
            
            Runtime.getRuntime().addShutdownHook(shutdown);
            
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LogInView.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage());
            System.exit(0);
        }
        
        MainController.kickstart();
    }

    private static void unlock() {
        try {
            if (lock
                    != null) {
                lock.release();
                channel.close();
                instanceValidation.delete();
            }
        } catch (IOException ex) {}
    }
}
