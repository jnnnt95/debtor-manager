package control;
import model.Debt;
import model.Client;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.util.List;
import model.Reader;
import model.Writer;
import view.AddDebtView;
/**
 *
 * @author admin
 */
public class AddDebtController {
    private final AddDebtView view;
    private ClientInfoController parent;
    private Client client;
    private List<Integer> debts;
    private double mean;
    private double standardDeviation;
    private DecimalFormat amountFormater;
    private String sessionKey;
    private LogInController parentLogIn;
    
    public AddDebtController(LogInController parentLogIn, String sessionKey) {
        this.parentLogIn = parentLogIn;
        this.sessionKey = sessionKey;
        view = new AddDebtView();
        amountFormater = new DecimalFormat("###,###.##");
        
        initView();
    }
    
    public void setViewData(ClientInfoController parent,
            Client currentClient) {
        verifySession();
        this.parent = parent;
        this.client = currentClient;
        this.debts = getDebts();
        mean = client.getMean();
        standardDeviation = client.getStandardDeviation();
        
        setToday();
        
        view.clientLabel.setText(client.getName() + ", " + client.getNick());
        view.totalNotPaidBalanceLabel.setText("$" + amountFormater.format(client.getTotalNotPaidBalance()));
    }
    
    private void initView() {
        verifySession();
        view.exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Esta nueva deuda no será agregada para este cliente");
                System.exit(0);
            }
        });
        
        view.addDebtButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    addDebt();
                } catch (IOException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        addDebt();
                    } catch (IOException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.newDebtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        addDebt();
                    } catch (IOException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.cancelButon.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    goBack();
                } catch (ParseException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void goBack() throws ParseException {
        parent.setInfoData();
        tools.swapWindow(parent.getView(), view);
    }
    
    private void addDebt() throws IOException, ParseException, ClassNotFoundException, SQLException {
        if(noFieldsEmpty()) {
            try {
                int newDebtAmount;
                String date;
                Debt newDebt;
                DateFormat dateFormat;
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                newDebtAmount = Integer.parseInt(view.newDebtField.getText().trim());
                if(newDebtAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida");
                    throw new NumberFormatException();
                }
                date = dateFormat.format(dateFormat.parse(view.dateField.getText().trim()));

                if ((newDebtAmount + getThisMonthBalance()) > (mean + standardDeviation)) {
                    JOptionPane.showMessageDialog(null, "El saldo del mes para este cliente sobrepasó el límite recomendado...", "Aviso: sobrepasando saldo recomendado", JOptionPane.WARNING_MESSAGE, null);
                }
                newDebt = new Debt(
                        Reader.getNewDebtId(),
                        client.getId(),
                        newDebtAmount,
                        0,
                        date
                );
                Writer.addDebt(newDebt, client);
                client.getDebts().add(newDebt);
                client.sortDebts();
                client.update();
                JOptionPane.showMessageDialog(null, "Nuevo saldo para " + client.getName() + ", " + client.getNick() + ":\n\n     $" + amountFormater.format(client.getTotalNotPaidBalance()));
                parent.setInfoData();
                tools.swapWindow(parent.getView(), view);
            }
            catch(NumberFormatException e) {
                
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Hay campos sin completar");
        }
    }
    
    private boolean noFieldsEmpty() {
        if(view.newDebtField.getText().equals("")) {
            return false;
        }
        if(view.dateField.getText().equals("")) {
            return false;
        }
        return true;
    }
    
    private void setToday() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	view.dateField.setText(dateFormat.format(date));
    }
    
    public AddDebtView getView() {
        return view;
    }

    private List<Integer> getDebts() {
        List<Integer> debts;
        debts = new ArrayList<>();
        for(Debt debt: client.getDebts()) {
            debts.add(debt.getBalance());
        }
        return debts;
    }

    private int getThisMonthBalance() {
        int thisMonthBalance;
        thisMonthBalance = 0;
        
        SimpleDateFormat formater;
        formater = new SimpleDateFormat("MM");
        
        int thisMonth;
        thisMonth = Integer.parseInt(formater.format(new Date()));
        
        for(Debt debt: client.getDebts()) {
            if((Integer.parseInt(debt.getDate().substring(3, 5)) == thisMonth) && (!debt.isPaid())) {
                thisMonthBalance += debt.getBalance();
            }
        }
        
        return thisMonthBalance;
    }
    
    private void verifySession() {
        parentLogIn.verifySession(sessionKey);
    }
}
