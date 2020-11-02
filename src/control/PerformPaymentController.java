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
import model.IO.Writer;
import model.enums.OperationCode;
import view.pop_up_view.PerformPaymentView;
/**
 *
 * @author admin
 */
public class PerformPaymentController {
    private final PerformPaymentView view;
    private Client client;
    private List<Integer> debts;
    private double mean;
    private double standardDeviation;
    private DecimalFormat amountFormater;
    private String sessionKey;
    
    public PerformPaymentController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new PerformPaymentView();
        amountFormater = new DecimalFormat("###,###.##");
        
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
    
    private void initView() {
        view.paymentDateField.setEnabled(false);
        verifySession();
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.seek(OperationCode.cancelPaymentPerforming,
                            sessionKey);
                } catch (IOException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
        view.performPaymentButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    performPayment();
                } catch (IOException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.paymentDateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        performPayment();
                    } catch (IOException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.amountField.setText("");
        view.amountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        performPayment();
                    } catch (IOException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PerformPaymentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    private void performPayment() throws IOException, ParseException, ClassNotFoundException, SQLException {
        if(noFieldsEmpty()) {
            if(client.getTotalNotPaidBalance() > 0) {
            Integer amount;
            try {
                amount = Integer.parseInt(view.amountField.getText().trim());
                if(amount <= 0) {
                    throw new NumberFormatException();
                }
                view.setVisible(false);
                switch(JOptionPane.showConfirmDialog(null, "Se pagarán $" + amountFormater.format(amount) + "\n\n¿Continuar?")) {
                    case 0:
                        pay(amount);
                        JOptionPane.showMessageDialog(null, "Nuevo saldo para " + client.getName() + ":\n\n" + "     $" + amountFormater.format(client.getTotalNotPaidBalance()));
                        MainController.changeToClientInfoMode(client,
                                sessionKey);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "Ingrese un monto válido");
                        resetFields();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Pago cancelado");
                        MainController.changeToClientInfoMode(client,
                                sessionKey);
                        resetFields();
                        break;
                }
            }
            catch(NumberFormatException e) {
                view.setVisible(false);
                JOptionPane.showMessageDialog(null, "El valor ingresado no es válido, intentar de nuevo");
                resetFields();
                view.setVisible(true);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, client.getName() + " está a paz y salvo: no hay nada que pagar");
        }
        }
        else {
            JOptionPane.showMessageDialog(null, "Hay campos sin completar");
        }
    }
    
    private void resetFields() {
        view.amountField.setText("");
        view.amountField.requestFocus();
    }
    
    private void pay(int amount) throws IOException, ClassNotFoundException, SQLException {
        String date;
        date = view.paymentDateField.getText().trim();
        
        
        
        for(Debt debt: client.getDebts()) {
            if(!debt.isPaid() && amount > 0) {
                int debitAssessment;
                debitAssessment = amount - debt.getTotalDebt();
                if(debitAssessment > 0) {
                    debt.updateDebt(debt.getTotalDebt(), date);
                    amount = debitAssessment;
                    
                }
                else {
                    debt.updateDebt(amount, date);
                    amount = 0;
                }
                Writer.modifyDebt(debt);
            }
        }
    }
    
    private boolean noFieldsEmpty() {
        if(view.amountField.getText().equals("")) {
            return false;
        }
        if(view.paymentDateField.getText().equals("")) {
            return false;
        }
        return true;
    }
    
    private void setToday() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	view.paymentDateField.setText(dateFormat.format(date));
    }
    
    public PerformPaymentView getView() {
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
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
