package control;

import model.Debt;
import model.Client;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Reader;
import model.Writer;
import view.AddClientView;

/**
 *
 * @author admin
 */
public class AddClientController {
    private AddClientView view;
    private MenuController parent;
    
    public AddClientController() {
        view = new AddClientView();
        initView();
    }
    
    public void setViewData(MenuController parent) throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.parent = parent;
        view.nameTextField.setText("");
        view.nickTextField.setText("");
        view.cpNumberField.setText("");
        view.areaField.setText("");
        view.initialBalanceField.setText("");
        view.nameTextField.requestFocus();
    }

    private void initView() {
        view.exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        view.addClientButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    addClient();
                } catch (IOException ex) {
                    Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.addClientButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        addClient();
                    } catch (IOException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tools.swapWindow(parent.getView(), view);
            }
        });
        view.nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        addClient();
                    } catch (IOException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.nickTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        addClient();
                    } catch (IOException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "No se agregó el nuevo cliente");
                tools.swapWindow(parent.getView(), view);
            }
        });
        view.cancelButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    JOptionPane.showMessageDialog(null, "No se agregó el nuevo cliente");
                    tools.swapWindow(parent.getView(), view);
                }
            }
        });
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
        else if(!isAValidInitialBalance()) {
            return false;
        }
        return true;
    }
    
    private boolean areNameAndNickRight() {
        if(view.nameTextField.getText().trim().length() > 30) {
            view.nameTextField.requestFocus();
            JOptionPane.showMessageDialog(null, "El nombre debe tener 30 caracteres o menos");
            return false;
        }
        if(view.nickTextField.getText().trim().length() > 30) {
            view.nickTextField.requestFocus();
            JOptionPane.showMessageDialog(null, "El nick debe tener 30 caracteres o menos");
            return false;
        }
        return true;
    }
    
    private boolean isAValidInitialBalance() {
        int initialBalance;
        initialBalance = 0;
        if(!view.initialBalanceField.getText().trim().equals("")) {
            try {
                initialBalance
                        = Integer.parseInt(view.initialBalanceField.getText().trim());
                if(initialBalance < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Saldo inicial no válido");
                view.initialBalanceField.selectAll();
            }
            if(initialBalance == 0) {
                view.initialBalanceField.setText("");
            }
        }
        return true;
    }
    
    private boolean areFieldsFilledOut() {
        if(view.nameTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo del nombre no puede estar vacío");
            view.nameTextField.requestFocus();
            return false;
        }
        else if(view.nickTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo del nick no puede estar vacío");
            view.nickTextField.requestFocus();
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
    
    private void addClient() throws IOException, ParseException, ClassNotFoundException, SQLException {
        int newId;
        newId = getNewId();

        if (areFieldsRight()) {
            String name;
            name = view.nameTextField.getText();

            String nick;
            nick = view.nickTextField.getText();

            String cpNumber;
            cpNumber = view.cpNumberField.getText();

            String area;
            area = view.areaField.getText();

            int amount;
            if(view.initialBalanceField.getText().trim().equals("")) {
                amount = 0;
            }
            else {
                amount = Integer.parseInt(view.initialBalanceField.getText().trim());
            }

            Debt initialBalance;
            initialBalance = getInitialBalance(newId, amount);

            List<Debt> debts;
            debts = new ArrayList<>();

            if(amount > 0) {
                debts.add(initialBalance);
            }

            Client client;
            client = new Client(
                    newId,
                    name,
                    nick,
                    cpNumber,
                    area,
                    debts
            );
            Writer.addClient(client);
            tools.swapWindow(parent.getView(), view);
        }
    }
    
    private int getNewId() throws IOException, ParseException, ClassNotFoundException, SQLException {
        int newId;
        newId = 0;
        List<Client> clients;
        clients = Reader.getClients();
        for(Client client: clients) {
            if(client.getId() > newId) {
                newId = client.getId();
            }
        }
        newId++;
        return newId;
    }
    
    public JFrame getView() {
        return view;
    }

    private Debt getInitialBalance(int newClientId, int amount) throws IOException, ParseException, ClassNotFoundException, SQLException {
        Debt initialBalance;
        
        initialBalance = new Debt(
                Reader.getNewDebtId(),
                newClientId,
                amount,
                0,
                new SimpleDateFormat("dd/MM/yyyy").format(new Date())
        );
        
        return initialBalance;
    }
}
