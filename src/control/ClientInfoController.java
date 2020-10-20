package control;

import model.Debt;
import model.Client;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Writer;
import view.ClientInfoView;

/**
 *
 * @author admin
 */
public class ClientInfoController {
    private final ClientInfoView view;
    private Client client;
    private QueryClientController parent;
    private AddDebtController addDebtSon;
    private DetailedHistoryController chartHistorySon;
    private ModifyClientController modifyClientSon;
    private List<Debt> debts;
    private DecimalFormat amountFormater;
    private boolean showAllDebts;
    private String sessionKey;
    private LogInController parentLogIn;
    
    public ClientInfoController(LogInController parentLogIn, String sessionKey) throws ParseException {
        this.parentLogIn = parentLogIn;
        this.sessionKey = sessionKey;
        view = new ClientInfoView();
        amountFormater = new DecimalFormat("###,###.##");
        showAllDebts = false;
        addDebtSon = new AddDebtController(parentLogIn, sessionKey);
        chartHistorySon = new DetailedHistoryController(parentLogIn, sessionKey);
        modifyClientSon = new ModifyClientController(parentLogIn, sessionKey);
        
        initView();
    }
    
    public void setViewData(QueryClientController parent, Client client) throws ParseException {
        verifySession();
        this.parent = parent;
        this.client = client;
        this.debts = client.getDebts();
        
        setInfoData();
    }
    
    private void initView() throws ParseException {
        verifySession();
        view.modifyClientButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modifyClient();
                } catch (ParseException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        view.payButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    managePayment();
                } catch (IOException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.addDebtButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(client.isDefaulter()) {
                   JOptionPane.showMessageDialog(null, "Este cliente se encuentra en estado de mora", "Aviso: cliente en mora", JOptionPane.WARNING_MESSAGE, null); 
                }
                addDebt();
            }
        });
        view.viewDetailedHistoryButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    viewDetailedHistory();
                } catch (ParseException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.toggleListButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!showAllDebts) {
                    view.toggleListButton.setText("Ver deudas pendientes");
                    showAllDebts = true;
                    try {
                        setInfoData();
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    view.toggleListButton.setText("Ver todas las deudas");
                    showAllDebts = false;
                    try {
                        setInfoData();
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    parent.update();
                    tools.swapWindow(parent.getView(), view);
                } catch (IOException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    
    private void modifyClient() throws ParseException {
        modifyClientSon.setViewData(client, this);
        tools.swapWindow(modifyClientSon.getView(), view);
    }
    
    private void viewDetailedHistory() throws ParseException {
        chartHistorySon.setViewData(this, client);
        tools.swapWindow(chartHistorySon.getView(), view);
    }
    
    private void addDebt() {
        addDebtSon.setViewData(this, client);
        tools.swapWindow(addDebtSon.getView(), view);
    }
    
    private void managePayment() throws IOException, ClassNotFoundException, SQLException {
        if(client.getTotalNotPaidBalance() > 0) {
            Integer amount;
            try {
                amount = Integer.parseInt(JOptionPane.showInputDialog("Ingresar cantidad a pagar:"));
                if(amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida");
                    throw new NumberFormatException();
                }
                switch(JOptionPane.showConfirmDialog(null, "Se pagarán $" + amountFormater.format(amount) + "\n\n¿Continuar?")) {
                    case 0:
                        pay(amount);
                        JOptionPane.showMessageDialog(null, "Nuevo saldo para " + client.getName() + ":\n\n" + "     $" + amountFormater.format(client.getTotalNotPaidBalance()));
                        parent.update();
                        tools.swapWindow(parent.getView(), view);
                        break;
                    case 1:
                        managePayment();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Pago cancelado");
                        break;
                }
            }
            catch(NumberFormatException e) {
            } catch (ParseException ex) {
                Logger.getLogger(ClientInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, client.getName() + " está a paz y salvo: no hay nada que pagar");
        }
    }
    
    private void pay(int amount) throws IOException, ClassNotFoundException, SQLException {
        for(Debt debt: debts) {
            if(!debt.isPaid() && amount > 0) {
                int debitAssessment;
                debitAssessment = amount - debt.getTotalDebt();
                if(debitAssessment > 0) {
                    debt.updateDebt(debt.getTotalDebt());
                    amount = debitAssessment;
                }
                else {
                    debt.updateDebt(amount);
                    amount = 0;
                }
            }
            Writer.modifyDebt(debt);
        }
    }
    
    public void setInfoData() throws ParseException {
        modifyClientSon.getView().setVisible(false);
        modifyClientSon.getView().dispose();
        client.update();
        view.nameLabel.
                setText(client.getName());
        view.nickLabel.
                setText(client.getNick());
        if(!client.getCPNumber().equals("")) {
            view.cpNumberLabel.
                setText(client.getCPNumber());
        }
        else {
            view.cpNumberLabel.
                setText("<No registra>");
        }
        view.nonPaidBalanceLabel.
                setText("$" + amountFormater.format(client.getTotalNotPaidBalance()));
        if(client.isDefaulter()) {
            view.defaultAmountLabel.setVisible(true);
            view.defaultAmountTitleLabel.setVisible(true);
            view.defaultAmountLabel.
                setText("$" + amountFormater.format(client.getDefaultAmount()));
        }
        else {
            view.defaultAmountLabel.setVisible(false);
            view.defaultAmountTitleLabel.setVisible(false);
        }
        setHistoryTable(showAllDebts);
    }
    
    private void setHistoryTable(boolean showAllDebts) {
        Object[][] objectMatrix;
        
        if(showAllDebts) {
            objectMatrix = new Object[debts.size()][4];
            for (int i = 0; i < debts.size(); i++) {
                objectMatrix[i][0] = amountFormater.format(debts.get(i).getBalance());
                objectMatrix[i][1] = amountFormater.format(debts.get(i).getDeposit());
                objectMatrix[i][2] = debts.get(i).getDate();
                objectMatrix[i][3] = debts.get(i).isPaid();
            }
        }
        else {
            int totalRows;
            totalRows = 0;
            for(Debt debt: debts) { 
                if(!debt.isPaid()) {
                    totalRows++;
                }
            }
            List<Debt> notPaidDebts;
            notPaidDebts = new ArrayList();
            for (int i = 0; i < debts.size(); i++) {
                if(!debts.get(i).isPaid()) {
                    notPaidDebts.add(debts.get(i));
                }
            }
            objectMatrix = new Object[totalRows][4];
            for (int i = 0; i < totalRows; i++) {
                objectMatrix[i][0] = amountFormater.format(notPaidDebts.get(i).getBalance());
                objectMatrix[i][1] = amountFormater.format(notPaidDebts.get(i).getDeposit());
                objectMatrix[i][2] = notPaidDebts.get(i).getDate();
                objectMatrix[i][3] = notPaidDebts.get(i).isPaid();
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(
                objectMatrix,
                new String [] {
                "Deuda ($)", "Abono ($)", "Fecha", "Cancelada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
                        
            Class[] types = new Class [] {
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        }
                ;
        if (view.historyTable.getColumnModel().getColumnCount() > 0) {
            view.historyTable.getColumnModel().getColumn(0).setResizable(false);
            view.historyTable.getColumnModel().getColumn(1).setResizable(false);
            view.historyTable.getColumnModel().getColumn(2).setResizable(false);
            view.historyTable.getColumnModel().getColumn(3).setResizable(false);
        }
        view.historyTable.setModel(model);
    }

    public ClientInfoView getView() {
        return view;
    }
    
    private void verifySession() {
        parentLogIn.verifySession(sessionKey);
    }
}
