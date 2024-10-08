package view.full_size_view;

import control.ClientInfoController;
import control.MainController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Client;
import model.Debt;
import model.IO.Reader;
import model.IO.Writer;
import model.enums.OperationCode;

/**
 *
 * @author admin
 */
public class ClientInfoView extends javax.swing.JFrame {

    private boolean updated;
    private boolean loginUpdated;
    private final ClientInfoController controller;
    private final String sessionKey;
    private boolean showAllDebts;

    /**
     * Creates new form ClientInfo
     *
     * @param controller
     * @param sessionKey
     */
    public ClientInfoView(ClientInfoController controller, String sessionKey) {
        updated = false;
        loginUpdated = false;
        showAllDebts = false;
        this.controller = controller;
        this.sessionKey = sessionKey;
        initComponents();
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        nameLabelTitle = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nickLabelTitle = new javax.swing.JLabel();
        nickLabel = new javax.swing.JLabel();
        balanceLabelTitle = new javax.swing.JLabel();
        nonPaidBalanceLabel = new javax.swing.JLabel();
        payButton = new javax.swing.JButton();
        addDebtButton = new javax.swing.JButton();
        toggleListButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        defaultAmountTitleLabel = new javax.swing.JLabel();
        defaultAmountLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        modifyClientButton = new javax.swing.JButton();
        viewDetailedHistoryButton = new javax.swing.JButton();
        nickLabelTitle1 = new javax.swing.JLabel();
        cpNumberLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        disableClientButton = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));
        mainContainer.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        nameLabelTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabelTitle.setText("Nombre:");

        nameLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabel.setText("+++");

        nickLabelTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle.setText("Nick:");

        nickLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabel.setText("+++");

        balanceLabelTitle.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        balanceLabelTitle.setText("Saldo no pagado:");

        nonPaidBalanceLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nonPaidBalanceLabel.setForeground(new java.awt.Color(255, 153, 153));
        nonPaidBalanceLabel.setText("+++");

        payButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        payButton.setForeground(new java.awt.Color(102, 204, 255));
        payButton.setText("Realizar cobro");
        payButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addDebtButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addDebtButton.setText("Agregar deuda");
        addDebtButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        toggleListButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        toggleListButton.setText("Ver todas las deudas");
        toggleListButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Deudas:");

        historyTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Deuda", "Abono", "Fecha de creación", "Cancelada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(historyTable);
        if (historyTable.getColumnModel().getColumnCount() > 0) {
            historyTable.getColumnModel().getColumn(0).setResizable(false);
            historyTable.getColumnModel().getColumn(1).setResizable(false);
            historyTable.getColumnModel().getColumn(2).setResizable(false);
            historyTable.getColumnModel().getColumn(3).setResizable(false);
        }

        defaultAmountTitleLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        defaultAmountTitleLabel.setText("Saldo en mora:");

        defaultAmountLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        defaultAmountLabel.setForeground(new java.awt.Color(255, 102, 102));
        defaultAmountLabel.setText("+++");

        modifyClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        modifyClientButton.setText("Modificar cliente");
        modifyClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        viewDetailedHistoryButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        viewDetailedHistoryButton.setForeground(new java.awt.Color(67, 106, 137));
        viewDetailedHistoryButton.setText("Ver estadísticas de cliente");
        viewDetailedHistoryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        nickLabelTitle1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabelTitle1.setText("Teléfono:");

        cpNumberLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cpNumberLabel.setText("+++");

        disableClientButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        disableClientButton.setForeground(new java.awt.Color(255, 153, 153));
        disableClientButton.setText("Deshabilitar cliente");
        disableClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(payButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addDebtButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nameLabelTitle)
                                        .addGap(10, 10, 10)
                                        .addComponent(nameLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nickLabelTitle1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cpNumberLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(defaultAmountTitleLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(defaultAmountLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(balanceLabelTitle)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nonPaidBalanceLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 485, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(modifyClientButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(viewDetailedHistoryButton))
                                    .addComponent(disableClientButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toggleListButton)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nickLabelTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nickLabel)))
                        .addGap(0, 795, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(disableClientButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickLabelTitle)
                    .addComponent(nickLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickLabelTitle1)
                    .addComponent(cpNumberLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabelTitle)
                    .addComponent(nonPaidBalanceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(viewDetailedHistoryButton)
                        .addComponent(modifyClientButton))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(defaultAmountTitleLabel)
                        .addComponent(defaultAmountLabel)))
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toggleListButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addDebtButton)
                    .addComponent(payButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDebtButton;
    private javax.swing.JLabel balanceLabelTitle;
    public javax.swing.JLabel cpNumberLabel;
    public javax.swing.JLabel defaultAmountLabel;
    public javax.swing.JLabel defaultAmountTitleLabel;
    private javax.swing.JButton disableClientButton;
    private javax.swing.JTable historyTable;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton modifyClientButton;
    public javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabelTitle;
    public javax.swing.JLabel nickLabel;
    private javax.swing.JLabel nickLabelTitle;
    private javax.swing.JLabel nickLabelTitle1;
    public javax.swing.JLabel nonPaidBalanceLabel;
    private javax.swing.JButton payButton;
    private javax.swing.JButton toggleListButton;
    private javax.swing.JButton viewDetailedHistoryButton;
    // End of variables declaration//GEN-END:variables
    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            modifyClientButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.changeToModifyClientMode(sessionKey,
                            controller.getCurrentClient());
                }
            });
            viewDetailedHistoryButton.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Client client = controller.getCurrentClient();
                            if(!client.hasAllDebts()) {
                                try {
                                    client.replaceDebtList(Reader.getClientAllDebts(client.getId()));
                                } catch (IOException | ClassNotFoundException | SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            try {
                                MainController.
                                        changeToDetailedHistoryMode(controller.
                                                getCurrentClient(),
                                                historyTable.getModel(),
                                                sessionKey);
                            } catch (ParseException ex) {
                                Logger.getLogger(ClientInfoController.class.
                                        getName()).
                                        log(Level.SEVERE,
                                                null,
                                                ex);
                            }
                        }
                    });
            
            toggleListButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleTable();
                }
            });
            payButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (controller.getCurrentClient().getTotalNotPaidBalance() > 0) {
                        MainController.
                                changeToPerformPaymentMode(controller.
                                                getCurrentClient(),
                                        sessionKey);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "El cliente se encuentra a paz y salvo");
                    }
                }
            });
            addDebtButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (controller.getCurrentClient().
                            isDefaulter()) {
                        JOptionPane.showMessageDialog(null,
                                "Este cliente se encuentra en estado de mora",
                                "Aviso: cliente en mora",
                                JOptionPane.WARNING_MESSAGE,
                                null);
                    }
                    MainController.changeToAddDebtMode(controller.
                            getCurrentClient(),
                            sessionKey);
                }
            });
            updated = true;
        }
    }

    public void toggleTable() {
        
        try {
                setInfoData(1);
            } catch (ParseException ex) {
                Logger.getLogger(ClientInfoController.class.
                        getName()).
                        log(Level.SEVERE,
                                null,
                                ex);
            }
    }
    
    private void setToggleButtonText() {
        if(showAllDebts) {
            toggleListButton.setText("Mostrar deudas activas");
        }
        else {
            toggleListButton.setText("Mostrar deudas históricas");
        }
    }
    
    private void setInfoData(int option) throws ParseException {
        MainController.authenticate(sessionKey);
        //change from external 0: change table info to only active debts
        //cange from internal 1 (or other): chane table info to previous selection
        if (option == 0) {
            showAllDebts = false;
        } else {
            showAllDebts = !showAllDebts;
        }
        
        setToggleButtonText();
        
        controller.getCurrentClient().update();
        nameLabel.setText(controller.getCurrentClient().getName());
        nickLabel.setText(controller.getCurrentClient().getNick());
        if (!controller.getCurrentClient().
                getCPNumber().isEmpty()) {
            cpNumberLabel.
                    setText(controller.getCurrentClient().
                            getCPNumber());
        } else {
            cpNumberLabel.
                    setText("<No registra>");
        }
        nonPaidBalanceLabel.
                setText("$"
                        + MainController.formatAmount(controller.
                                getCurrentClient().
                                getTotalNotPaidBalance()));
        if (controller.getCurrentClient().
                isDefaulter()) {
            defaultAmountLabel.setVisible(true);
            defaultAmountTitleLabel.setVisible(true);
            defaultAmountLabel.
                    setText("$"
                            + MainController.formatAmount(controller.
                                    getCurrentClient().
                                    getDefaultAmount()));
        } else {
            defaultAmountLabel.setVisible(false);
            defaultAmountTitleLabel.setVisible(false);
        }
        setHistoryTable(showAllDebts);
    }

    public void setInfoData()
            throws ParseException {
        setInfoData(0);
    }

    private void setHistoryTable(boolean showAllDebts) {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;
        List<Debt> debts;
        Client client = controller.getCurrentClient();
        if(!showAllDebts) {
            debts = client.getDebts();
        } else {
            if(!client.hasAllDebts()) {
                try {
                    debts = Reader.getClientAllDebts(client.getId());
                } catch (IOException | ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                client.replaceDebtList(debts);
                client.setHasAllDebts();
            } else {
                debts = client.getDebts();
            }
        }

        if (showAllDebts) {
            objectMatrix = new Object[debts.size()][5];
            for (int i = 0;
                    i
                    < debts.size();
                    i++) {
                objectMatrix[i][0] = MainController.formatAmount(debts.get(i).
                        getBalance());
                objectMatrix[i][1] = MainController.formatAmount(debts.get(i).
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
                objectMatrix[i][0] = MainController.formatAmount(notPaidDebts.
                        get(i).
                        getBalance());
                objectMatrix[i][1] = MainController.formatAmount(notPaidDebts.
                        get(i).
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

        DefaultTableModel model = getDefaultTableModel(objectMatrix);

        historyTable.getTableHeader().
                setResizingAllowed(false);
        historyTable.getTableHeader().
                setReorderingAllowed(false);
        historyTable.setModel(model);

        historyTable.getColumnModel().
                getColumn(0).
                setPreferredWidth(15);
        historyTable.getColumnModel().
                getColumn(1).
                setPreferredWidth(15);

        if (historyTable.getColumnModel().
                getColumnCount()
                > 0) {
            historyTable.getColumnModel().
                    getColumn(0).
                    setResizable(false);
            historyTable.getColumnModel().
                    getColumn(1).
                    setResizable(false);
            historyTable.getColumnModel().
                    getColumn(2).
                    setResizable(false);
            historyTable.getColumnModel().
                    getColumn(3).
                    setResizable(false);
            historyTable.getColumnModel().
                    getColumn(4).
                    setResizable(false);
        }
    }

    private static DefaultTableModel getDefaultTableModel(Object[][] objectMatrix) {
        DefaultTableModel model;
        model = new DefaultTableModel(
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

            @Override
            public boolean isCellEditable(int rowIndex,
                    int columnIndex) {
                return canEdit[columnIndex];
            }

            Class[] types = new Class[]{
                Integer.class,
                String.class,
                String.class,
                String.class,
                String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        return model;
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        payButton.requestFocus();
    }
    
    public void loginUpdate() {
        if(!loginUpdated) {
            switch (MainController.getUser().
                    getType()) {
                case administrator:
                    disableClientButton.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int selection;
                                selection = JOptionPane.
                                        showConfirmDialog(null, "¿Deshabilitar cliente?");
                                switch (selection) {
                                    case 0:
                                        Writer.disableClient(controller.
                                                getCurrentClient());
                                        JOptionPane.
                                                showMessageDialog(null, "Cliente deshabilitado exitosamente");
                                        MainController.
                                                changeToQueryClientModeWithHardUpdate(sessionKey);
                                        break;
                                    case 1:
                                    case 2:
                                        break;
                                }
                            } catch (ClassNotFoundException | SQLException | InterruptedException | IOException | ParseException ex) {
                                Logger.getLogger(ClientInfoController.class.
                                        getName()).
                                        log(Level.SEVERE,
                                                null,
                                                ex);
                            }
                        }
                    });
                    break;
                case normal:
                    disableClientButton.setVisible(false);
                    break;
            }
            loginUpdated = false;
        }
    }
}
