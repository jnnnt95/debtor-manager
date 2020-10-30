package control;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
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
    private ModifyClientView view;
    private String sessionKey;
    
    public ModifyClientController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new ModifyClientView();
        view.nameField.requestFocus();
        
        initview();
    }
    
    public ModifyClientView getView() {
        return view;
    }
    
    public void setCurrentClient(Client client) {
        this.client = client;
    }
    
    private void initview() {
        verifySession();
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.seek(OperationCode.cancelModifyingAClient,
                            sessionKey);
                } catch (IOException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
        view.acceptButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveInfo();
                } catch (ParseException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ModifyClientController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
        
    }
    
    private void saveInfo() throws ParseException, ClassNotFoundException, SQLException, IOException, InterruptedException {
        if(areFieldsRight()) {
            String name;
            name = view.nameField.getText().trim();
            
            String nick;
            nick = view.nickField.getText().trim();
            
            String cpNumber;
            cpNumber = view.cpNumberField.getText().trim();
            
            String area;
            area = view.areaField.getText().trim();

            String printable;
            printable = "Confirmar cambios:\n"
                    + "   Nombre: " + name + "\n"
                    + "   Nick: " + nick + "\n"
                    + "   Teléfono: " + cpNumber + "\n"
                    + "   Área: " + area;
            
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
                    JOptionPane.showMessageDialog(null, "No se realizaron cambios");
                    MainController.seek(OperationCode.cancelModifyingAClient,
                            sessionKey);
                    break;
            }
        }
    }
    
    private boolean areFieldsRight() {
        if(!areFieldsFilledOut()) {
            return false;
        }
        else if(!areNameAndNickRight()) {
            return false;
        }
        else if(!isCPNumberRight()) {
            return false;
        }
        return true;
    }
    
    private boolean areNameAndNickRight() {
        if(view.nameField.getText().trim().length() > 30) {
            view.nameField.requestFocus();
            JOptionPane.showMessageDialog(null, "El nombre debe tener 30 caracteres o menos");
            return false;
        }
        if(view.nickField.getText().trim().length() > 30) {
            view.nickField.requestFocus();
            JOptionPane.showMessageDialog(null, "El nick debe tener 30 caracteres o menos");
            return false;
        }
        return true;
    }
    
    private boolean areFieldsFilledOut() {
        if(view.nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo del nombre no puede estar vacío");
            view.nameField.requestFocus();
            return false;
        }
        else if(view.nickField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo del nick no puede estar vacío");
            view.nickField.requestFocus();
            return false;
        }
        else if(view.areaField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo del área no puede estar vacío");
            view.areaField.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean isCPNumberRight() {
        //check number for length
        String cpNumber;
        cpNumber = view.cpNumberField.getText().trim();
        if(cpNumber.length() > 10) {
            JOptionPane.showMessageDialog(null, "Número telefónico no válido: debe tener máximo 10 dígitos");
            view.cpNumberField.selectAll();
            view.cpNumberField.requestFocus();
            return false;
        }
        
        //check number for suitability
        for(int i = 0; i < cpNumber.length(); i++) {
            if(!isNumberADigit(String.valueOf(cpNumber.charAt(i)))) {
                JOptionPane.showMessageDialog(null, "Número telefónico no válido: debe contener solamente números");
                view.cpNumberField.selectAll();
                view.cpNumberField.requestFocus();
                return false;
            }
        }
        
        // return true if any of above return false first
        return true;
    }
    
    private boolean isNumberADigit(String s) {
        //returns true if argument s is a number
        if(s.equals("0"))
            return true;
        if(s.equals("1"))
            return true;
        if(s.equals("2"))
            return true;
        if(s.equals("3"))
            return true;
        if(s.equals("4"))
            return true;
        if(s.equals("5"))
            return true;
        if(s.equals("6"))
            return true;
        if(s.equals("7"))
            return true;
        if(s.equals("8"))
            return true;
        if(s.equals("9"))
            return true;
        
        //returns false if argument s is not a number
        return false;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
