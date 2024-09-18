package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Client;
import model.IO.Writer;
import model.enums.OperationCode;
import view.pop_up_view.ModifyClientView;


/**
 *
 * @author admin
 */
public class ModifyClientController {
    private Client client;
    private final ModifyClientView view;
    private final String sessionKey;
    
    public ModifyClientController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new ModifyClientView(this, sessionKey);
        view.updateView();
        view.setMainElementFocus();
        
        verifySession();
    }
    
    public ModifyClientView getView() {
        return view;
    }
    
    public void setCurrentClient(Client client) {
        this.client = client;
    }
    
    public void modifyClient() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(isNewClientDataCorrect()) {
            String name;
            name = view.getNewName();
            
            String nick;
            nick = view.getNewNick();
            
            String cpNumber;
            cpNumber = view.getNewCPNumber();
            
            String area;
            area = view.getNewArea();

            String printable;
            printable = "<html><strong>Confirmar cambios</strong><br><br>"
                    + "   <strong>Nombre</strong>: " + name + "<br>"
                    + "   <strong>Nick</strong>: " + nick + "<br>"
                    + "   <strong>Teléfono</strong>: " + cpNumber + "<br>"
                    + "   <strong>Área</strong>: " + area + "</html>";
            
            switch(JOptionPane.showConfirmDialog(null, printable)) {
                case 0:
                    client.setName(name);
                    client.setNick(nick);
                    client.setCpNumber(cpNumber);
                    client.setArea(area);

                    Writer.modifyClient(client);
                    MainController.changeToClientInfoMode(client,
                            sessionKey);
                    break;
                case 1:
                    break;
                case 2:
                    cancelModifyingAClient();
                    break;
            }
        }
    }
    
    public void cancelModifyingAClient() {
        try {
            MainController.executeOperation(OperationCode.cancelModifyingAClient,
                    sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(ModifyClientController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isNewClientDataCorrect() {
        if(!isNewClientDataSet()) {
            return false;
        }
        else if(!isTextDataRight()) {
            return false;
        }
        else return isCPNumberRight();
    }
    
    private boolean isTextDataRight() {
        if(view.getNewName().length() > 30) {
            JOptionPane.showMessageDialog(null, "El nombre debe tener 30 caracteres o menos");
            view.setFocusOnName();
            return false;
        }
        if(view.getNewNick().length() > 30) {
            JOptionPane.showMessageDialog(null, "El nick debe tener 30 caracteres o menos");
            view.setFocusOnNick();
            return false;
        }
        if(view.getNewArea().length() > 30) {
            JOptionPane.showMessageDialog(null, "El área debe tener 30 caracteres o menos");
            view.setFocusOnArea();
            return false;
        }
        return true;
    }
    
    private boolean isNewClientDataSet() {
        if(view.getNewName().length() <= 0) {
            JOptionPane.showMessageDialog(null, "El campo del nombre no puede estar vacío");
            view.setFocusOnName();
            return false;
        }
        else if(view.getNewNick().length() <= 0) {
            JOptionPane.showMessageDialog(null, "El campo del nick no puede estar vacío");
            view.setFocusOnNick();
            return false;
        }
        else if(view.getNewArea().length() <= 0) {
            JOptionPane.showMessageDialog(null, "El campo del área no puede estar vacío");
            view.setFocusOnArea();
            return false;
        }
        return true;
    }
    
    private boolean isCPNumberRight() {
        //check number for length
        String cpNumber;
        cpNumber = view.getNewCPNumber();
        
        if(cpNumber.length() > 10) {
            JOptionPane.showMessageDialog(null, "Número telefónico no válido: debe tener máximo 10 dígitos");
            view.setFocusOnCPNumber();
            return false;
        }
        
        //check number for suitability
        for(int i = 0; i < cpNumber.length(); i++) {
            if(!MainController.isNumberADigit(String.valueOf(cpNumber.charAt(i)))) {
                JOptionPane.showMessageDialog(null, "Número telefónico no válido: debe contener solamente números");
                view.setFocusOnCPNumber();
                return false;
            }
        }
        
        // return true if any of above return false first
        return true;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
