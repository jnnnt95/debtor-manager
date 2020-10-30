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
import model.IO.Writer;
import model.User;
import model.enums.OperationCode;
import view.full_size_view.ClientInfoView;

/**
 *
 * @author admin
 */
public class ClientInfoController {

    private final ClientInfoView view;
    private Client client;
    private AddDebtController addDebtSon;
    private PerformPaymentController performPaymentSon;
    private DetailedHistoryController chartHistorySon;
    private List<Debt> debts;
    private DecimalFormat amountFormater;
    private boolean showAllDebts;
    private String sessionKey;

    public ClientInfoController(String sessionKey)
            throws ParseException {
        this.sessionKey = sessionKey;
        view = new ClientInfoView();
        amountFormater = new DecimalFormat("###,###.##");
        showAllDebts = false;
        addDebtSon = new AddDebtController(sessionKey);
        performPaymentSon = new PerformPaymentController(sessionKey);
        chartHistorySon = new DetailedHistoryController(sessionKey);

        initView();
    }

    public void setViewData(Client client)
            throws ParseException {
        verifySession();
        this.client = client;
        this.debts = client.getDebts();

        setInfoData();
    }
    
    public void updateUser(User user) {
        switch (user.getType()) {
            case administrator:
                view.disableClientButton.addActionListener(
                        new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Writer.disableClient(client);
                            MainController.changeToQueryClientMode(sessionKey);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ClientInfoController.class.getName()).
                                    log(Level.SEVERE,
                                    null,
                                    ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientInfoController.class.getName()).
                                    log(Level.SEVERE,
                                    null,
                                    ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ClientInfoController.class.getName()).
                                    log(Level.SEVERE,
                                    null,
                                    ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientInfoController.class.getName()).
                                    log(Level.SEVERE,
                                    null,
                                    ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(ClientInfoController.class.getName()).
                                    log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                });
                break;
            case normal:
                view.disableClientButton.setVisible(false);
                break;
        }
    }

    private void initView()
            throws ParseException {
        verifySession();
        view.modifyClientButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modifyClient();
                } catch (ParseException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        view.payButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (client.getTotalNotPaidBalance()
                            > 0) {
                        performPayment();
                        MainController.seek(OperationCode.updateQueryClientData,
                                sessionKey);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "El cliente se encuentra a paz y salvo");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        view.addDebtButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (client.isDefaulter()) {
                    JOptionPane.showMessageDialog(null,
                            "Este cliente se encuentra en estado de mora",
                            "Aviso: cliente en mora",
                            JOptionPane.WARNING_MESSAGE,
                            null);
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
                    Logger.getLogger(ClientInfoController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        view.toggleListButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!showAllDebts) {
                    view.toggleListButton.setText("Ver deudas pendientes");
                    showAllDebts = true;
                    try {
                        setInfoData();
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientInfoController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                } else {
                    view.toggleListButton.setText("Ver todas las deudas");
                    showAllDebts = false;
                    try {
                        setInfoData();
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientInfoController.class.getName()).
                                log(Level.SEVERE,
                                        null,
                                        ex);
                    }
                }
            }
        });
    }

    private void modifyClient()
            throws ParseException {
        MainController.changeToModifyClientMode(sessionKey,
                client);
    }

    private void viewDetailedHistory()
            throws ParseException {
        MainController.changeToDetailedHistoryMode(client,
                view.historyTable.getModel(),
                sessionKey);
    }

    private void performPayment() {
        MainController.changeToPerformPaymentMode(client,
                sessionKey);
    }

    private void addDebt() {
        MainController.changeToAddDebtMode(client,
                sessionKey);
    }

    public void setInfoData()
            throws ParseException {
        client.update();
        view.nameLabel.
                setText(client.getName());
        view.nickLabel.
                setText(client.getNick());
        if (!client.getCPNumber().
                equals("")) {
            view.cpNumberLabel.
                    setText(client.getCPNumber());
        } else {
            view.cpNumberLabel.
                    setText("<No registra>");
        }
        view.nonPaidBalanceLabel.
                setText("$"
                        + amountFormater.format(client.getTotalNotPaidBalance()));
        if (client.isDefaulter()) {
            view.defaultAmountLabel.setVisible(true);
            view.defaultAmountTitleLabel.setVisible(true);
            view.defaultAmountLabel.
                    setText("$"
                            + amountFormater.format(client.getDefaultAmount()));
        } else {
            view.defaultAmountLabel.setVisible(false);
            view.defaultAmountTitleLabel.setVisible(false);
        }
        setHistoryTable(showAllDebts);
    }

    private void setHistoryTable(boolean showAllDebts) {
        Object[][] objectMatrix;

        if (showAllDebts) {
            objectMatrix = new Object[debts.size()][5];
            for (int i = 0;
                    i
                    < debts.size();
                    i++) {
                objectMatrix[i][0] = amountFormater.format(debts.get(i).
                        getBalance());
                objectMatrix[i][1] = amountFormater.format(debts.get(i).
                        getDeposit());
                objectMatrix[i][2] = debts.get(i).
                        getCreationDate();
                objectMatrix[i][3] = debts.get(i).
                        isPaid();
                if (!debts.get(i).
                        isPaid()) {
                    objectMatrix[i][3] = "No pagada";
                } else if (debts.get(i).
                        isPaid()
                        && (debts.get(i).
                                getPaidDate()
                        == null)) {
                    objectMatrix[i][3] = "Pagada v.a. (no fecha)";
                } else {
                    objectMatrix[i][3] = debts.get(i).
                            getPaidDate();
                }
                objectMatrix[i][4] = debts.get(i).
                        getCreatedBy();
            }
        } else {
            int totalRows;
            totalRows = 0;
            for (Debt debt
                    : debts) {
                if (!debt.isPaid()) {
                    totalRows++;
                }
            }
            List<Debt> notPaidDebts;
            notPaidDebts = new ArrayList();
            for (int i = 0;
                    i
                    < debts.size();
                    i++) {
                if (!debts.get(i).
                        isPaid()) {
                    notPaidDebts.add(debts.get(i));
                }
            }
            objectMatrix = new Object[totalRows][5];
            for (int i = 0;
                    i
                    < totalRows;
                    i++) {
                objectMatrix[i][0] = amountFormater.format(notPaidDebts.get(i).
                        getBalance());
                objectMatrix[i][1] = amountFormater.format(notPaidDebts.get(i).
                        getDeposit());
                objectMatrix[i][2] = notPaidDebts.get(i).
                        getCreationDate();
                if (!notPaidDebts.get(i).
                        isPaid()) {
                    objectMatrix[i][3] = "No pagada";
                } else if (notPaidDebts.get(i).
                        isPaid()
                        && (notPaidDebts.get(i).
                                getPaidDate()
                        == null)) {
                    objectMatrix[i][3] = "Pagada v.a.";
                } else {
                    objectMatrix[i][3] = notPaidDebts.get(i).
                            getPaidDate();
                }
                objectMatrix[i][4] = notPaidDebts.get(i).
                        getCreatedBy();
            }
        }

        DefaultTableModel model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "Deuda ($)",
                    "Abono ($)",
                    "Creada (d/m/a)",
                    "Pagada (d/m/a)",
                    "Registrada por"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false,
                false,
                false,
                false,
                false
            };

            public boolean isCellEditable(int rowIndex,
                    int columnIndex) {
                return canEdit[columnIndex];
            }

            Class[] types = new Class[]{
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        view.historyTable.getTableHeader().
                setResizingAllowed(false);
        view.historyTable.getTableHeader().
                setReorderingAllowed(false);
        view.historyTable.setModel(model);

        view.historyTable.getColumnModel().
                getColumn(0).
                setPreferredWidth(15);
        view.historyTable.getColumnModel().
                getColumn(1).
                setPreferredWidth(15);

        if (view.historyTable.getColumnModel().
                getColumnCount()
                > 0) {
            view.historyTable.getColumnModel().
                    getColumn(0).
                    setResizable(false);
            view.historyTable.getColumnModel().
                    getColumn(1).
                    setResizable(false);
            view.historyTable.getColumnModel().
                    getColumn(2).
                    setResizable(false);
            view.historyTable.getColumnModel().
                    getColumn(3).
                    setResizable(false);
            view.historyTable.getColumnModel().
                    getColumn(4).
                    setResizable(false);
        }
    }

    public ClientInfoView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
