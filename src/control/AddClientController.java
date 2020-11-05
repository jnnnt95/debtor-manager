package control;

import model.Debt;
import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.IO.Reader;
import model.IO.Writer;
import model.enums.OperationCode;
import view.pop_up_view.AddClientView;

/**
 *
 * @author admin
 */
public class AddClientController {

    private final String sessionKey;
    private final AddClientView view;

    public AddClientController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new AddClientView(this, sessionKey);
        view.updateView();
    }

    public void setReady()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        view.clearName();
        view.clearNick();
        view.clearCPNumber();
        view.clearArea();
        view.clearInitialBalance();
        view.setMainElementFocus();
    }

    public void cancelAddingAClient() {
        try {
            MainController.executeOperation(OperationCode.cancelAddingAClient,
                    sessionKey);
        } catch (IOException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }
    }

    private boolean isNewClientDataCorrect() {
        if (!isNewClientDataSet()) {
            return false;
        } else if (!isTextDataRight()) {
            return false;
        } else if (!isCPNumberRight()) {
            return false;
        } else if (!isAValidInitialBalance()) {
            return false;
        }
        return true;
    }

    private boolean isTextDataRight() {
        if (view.getNewClientName().
                length()
                > 30) {
            view.setFocusOnName();
            JOptionPane.showMessageDialog(null,
                    "El nombre debe tener 30 caracteres o menos");
            return false;
        }
        if (view.getNewClientNick().
                length()
                > 30) {
            view.setFocusOnNick();
            JOptionPane.showMessageDialog(null,
                    "El nick debe tener 30 caracteres o menos");
            return false;
        }
        if (view.getNewClientArea().
                length()
                > 30) {
            view.setFocusOnNick();
            JOptionPane.showMessageDialog(null,
                    "El área debe tener 30 caracteres o menos");
            return false;
        }
        return true;
    }

    private boolean isAValidInitialBalance() {
        int initialBalance;
        initialBalance = 0;
        if (view.getNewClientInitialBalance().length() > 0) {
            try {
                initialBalance
                        = Integer.parseInt(view.getNewClientInitialBalance());
                if (initialBalance
                        <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Saldo inicial no válido");
                view.setFocusOnInitialBalance();
                return false;
            }
        }
        return true;
    }

    private boolean isNewClientDataSet() {
        if (view.getNewClientName().
                length() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "El campo del nombre no puede estar vacío");
            view.setFocusOnName();
            return false;
        } else if (view.getNewClientNick().
                length() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "El campo del nick no puede estar vacío");
            view.setFocusOnNick();
            return false;
        } else if (view.getNewClientArea().length() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "El campo del área no puede estar vacío");
            view.setFocusOnArea();
            return false;
        }
        return true;
    }

    private boolean isCPNumberRight() {
        //check number for length
        String cpNumber;
        cpNumber = view.getNewClientCPNumber();
        
        if (cpNumber.length()
                > 10) {
            JOptionPane.showMessageDialog(null,
                    "Número telefónico no válido: debe tener máximo 10 dígitos");
            view.setFocusOnCPNumber();
            return false;
        }

        //check number for suitability
        for (int i = 0;
                i
                < cpNumber.length();
                i++) {
            if (!MainController.isNumberADigit(String.valueOf(cpNumber.charAt(i)))) {
                JOptionPane.showMessageDialog(null,
                        "Número telefónico no válido: debe contener solamente números");
                view.setFocusOnCPNumber();
                return false;
            }
        }

        // return true if any of above return false first
        return true;
    }

    public void addClient()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        try {
            int newId;
            newId = getNewId();
            
            if (isNewClientDataCorrect()) {
                String name;
                name = view.getNewClientName();
                
                String nick;
                nick = view.getNewClientNick();
                
                String cpNumber;
                cpNumber = view.getNewClientCPNumber();
                
                String area;
                area = view.getNewClientArea();
                
                int amount;
                if (view.getNewClientInitialBalance().length() <= 0) {
                    amount = 0;
                } else {
                    amount = Integer.parseInt(view.getNewClientInitialBalance());
                }
                
                Debt initialBalance;
                initialBalance = getInitialBalance(newId,
                        amount);
                
                List<Debt> debts;
                debts = new ArrayList<>();
                
                if (amount
                        > 0) {
                    debts.add(initialBalance);
                }
                
                Client client;
                client = new Client(
                        newId,
                        name,
                        nick,
                        cpNumber,
                        area,
                        debts,
                        MainController.getUser().
                                getName(),
                        MainController.getUser().
                                getId()
                );
                Writer.addClient(client);
                MainController.
                        executeOperation(OperationCode.completeAddinAClient,
                                sessionKey);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AddClientController.class.getName()).
                    log(Level.SEVERE,
                    null,
                    ex);
        }
    }

    private int getNewId()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        int newId;
        newId = 0;
        List<Client> clients;
        clients = Reader.getClients();
        for (Client client
                : clients) {
            if (client.getId()
                    > newId) {
                newId = client.getId();
            }
        }
        newId++;
        return newId;
    }

    public AddClientView getView() {
        return view;
    }

    private Debt getInitialBalance(int newClientId,
            int amount)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        Debt initialBalance;

        initialBalance = new Debt(
                Reader.getNewDebtId(),
                newClientId,
                amount,
                0,
                new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                null,
                MainController.getUser().
                        getName(),
                MainController.getUser().
                        getId(),
                null
        );

        return initialBalance;
    }
}
