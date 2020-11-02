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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.util.List;
import model.IO.Reader;
import model.IO.Writer;
import view.pop_up_view.AddDebtView;
/**
 *
 * @author admin
 */
public class AddDebtController {
    private final AddDebtView view;
    private Client client;
    private List<Integer> debts;
    private double mean;
    private double standardDeviation;
    private DecimalFormat amountFormater;
    private String sessionKey;
    private SimpleDateFormat dateFormater;
    private Calendar calendar;
    private final Date today;
    
    public AddDebtController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new AddDebtView();
        amountFormater = new DecimalFormat("###,###.##");
        dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();
        today = new Date();
        
        initView();
    }
    
    public void setViewData(Client currentClient) {
        verifySession();
        this.client = currentClient;
        this.debts = getDebts();
        mean = client.getMean();
        standardDeviation = client.getStandardDeviation();
        
        setToday();
        
        view.clientLabel.setText(client.getName() + ", " + client.getNick());
        view.totalNotPaidBalanceLabel.setText("$" + amountFormater.format(client.getTotalNotPaidBalance()));
        
        view.warningLabel.setVisible(client.isDefaulter());
    }
    
    private void addOneDayToDate() throws ParseException {
        calendar.setTime(dateFormater.parse(view.dateField.getText().trim()));
        calendar.add(Calendar.DATE, 1);
        view.dateField.setText(dateFormater.format(calendar.getTime()));
    }
    
    private void removeOneDayToDate() throws ParseException {
        calendar.setTime(dateFormater.parse(view.dateField.getText().trim()));
        calendar.add(Calendar.DATE, -1);
        view.dateField.setText(dateFormater.format(calendar.getTime()));
    }
    
    private void initView() {
        verifySession();
        view.oneMoreDayButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addOneDayToDate();
                } catch (ParseException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
        view.oneLessDayButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeOneDayToDate();
                } catch (ParseException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
        view.amountField.setText("");
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.changeToClientInfoMode(client,
                            sessionKey);
                } catch (ParseException ex) {
                    Logger.getLogger(AddDebtController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
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
        view.amountField.addKeyListener(new KeyAdapter() {
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
    }
    
    private void addDebt() throws IOException, ParseException, ClassNotFoundException, SQLException {
        if(noFieldsEmpty()) {
            try {
                int newDebtAmount;
                String date;
                Debt newDebt;
                DateFormat dateFormat;
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                newDebtAmount = Integer.parseInt(view.amountField.getText().trim());
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
                        date,
                        null,
                        MainController.getUser().getName(),
                        MainController.getUser().getId(),
                        null
                );
                Writer.addDebt(newDebt, client);
                client.getDebts().add(newDebt);
                client.sortDebts();
                client.update();
                JOptionPane.showMessageDialog(null, "Nuevo saldo para " + client.getName() + ", " + client.getNick() + ":\n\n     $" + amountFormater.format(client.getTotalNotPaidBalance()));
                MainController.changeToClientInfoMode(client,
                        sessionKey);
            }
            catch(NumberFormatException e) {
                
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Hay campos sin completar");
        }
    }
    
    private boolean noFieldsEmpty() {
        if(view.amountField.getText().equals("")) {
            return false;
        }
        if(view.dateField.getText().equals("")) {
            return false;
        }
        return true;
    }
    
    private void setToday() {
	view.dateField.setText(dateFormater.format(today));
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
            if((Integer.parseInt(debt.getCreationDate().substring(3, 5)) == thisMonth) && (!debt.isPaid())) {
                thisMonthBalance += debt.getBalance();
            }
        }
        
        return thisMonthBalance;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
