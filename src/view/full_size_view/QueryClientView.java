package view.full_size_view;

import control.MainController;
import control.QueryClientController;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import jxl.write.WriteException;
import model.Client;
import model.ClientDataWithTotalizedDebts;
import model.IO.Reader;
import model.IO.Writer;
import model.enums.UserType;

/**
 *
 * @author admin
 */
public class QueryClientView
        extends javax.swing.JFrame {

    private boolean updated;
    private boolean loginUpdated;
    private final QueryClientController controller;
    private final String sessionKey;

    /**
     * Creates new form ClientInfo
     * @param controller
     * @param sessionKey
     */
    public QueryClientView(QueryClientController controller, String sessionKey) {
        updated = false;
        loginUpdated = false;
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        showDefaultersButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        exportToExcelButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        defaulterCounterLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        debtorCounterLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("<html><strong>Buscar</strong> <i>(ingresar nombre, nick o área)</i></html>:");

        searchTextField.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        searchTextField.setFocusCycleRoot(true);
        searchTextField.setFocusTraversalPolicyProvider(true);

        resultTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nick", "Nombre", "Teléfono", "Total deuda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(resultTable);
        if (resultTable.getColumnModel().getColumnCount() > 0) {
            resultTable.getColumnModel().getColumn(0).setResizable(false);
            resultTable.getColumnModel().getColumn(1).setResizable(false);
            resultTable.getColumnModel().getColumn(2).setResizable(false);
            resultTable.getColumnModel().getColumn(3).setResizable(false);
            resultTable.getColumnModel().getColumn(4).setResizable(false);
        }

        showDefaultersButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        showDefaultersButton.setForeground(new java.awt.Color(255, 153, 153));
        showDefaultersButton.setText("Mostrar morosos");
        showDefaultersButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        resetButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        resetButton.setText("Mostrar todos");
        resetButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        exportToExcelButton.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        exportToExcelButton.setForeground(new java.awt.Color(102, 204, 255));
        exportToExcelButton.setText("Exportar a excel");
        exportToExcelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Clientes en mora:");

        defaulterCounterLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        defaulterCounterLabel.setForeground(new java.awt.Color(255, 102, 102));
        defaulterCounterLabel.setText("+++");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Clientes con pago pendiente:");

        debtorCounterLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        debtorCounterLabel.setText("+++");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("<html><i>Enter = buscar, Esc = restablecer</i></html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(resetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showDefaultersButton))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(searchTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(defaulterCounterLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(debtorCounterLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 615, Short.MAX_VALUE)
                                .addComponent(exportToExcelButton)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(defaulterCounterLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(debtorCounterLabel)
                    .addComponent(exportToExcelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showDefaultersButton)
                    .addComponent(resetButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel debtorCounterLabel;
    public javax.swing.JLabel defaulterCounterLabel;
    private javax.swing.JButton exportToExcelButton;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JPanel mainContainer;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable resultTable;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton showDefaultersButton;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if (!updated) {
            searchTextField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getKeyCode()
                            == KeyEvent.VK_ENTER) {
                        controller.setSearchTable();
                    }
                    if (event.getKeyCode()
                            == KeyEvent.VK_ESCAPE) {
                        setSearchFieldText("");
                        controller.setSearchTable();
                    }
                }
            });
            resultTable.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount()
                            == 2) {
                        int activeClientId
                                = (Integer) resultTable.getValueAt(
                                        resultTable.getSelectedRow(),
                                        0);
                        controller.updateActiveClient(activeClientId);

                    }
                }
            });
            resetButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setSearchFieldText("");
                    controller.setSearchTable();
                }
            });
            showDefaultersButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.showDefaulters();
                }
            });
            updated = true;
        }
    }

    public void exportToExcel() throws IOException, WriteException {
        MainController.authenticate(sessionKey);
        Object[][] clientsData;
        clientsData = controller.getClientsData();
        Writer.exportToExcel(clientsData);
    }

    private void openExportedData() throws IOException {
        MainController.authenticate(sessionKey);
        Reader.openExportedData();
    }

    public JPanel getMainContainer() {
        MainController.authenticate(sessionKey);
        return mainContainer;
    }

    public void setSearchFieldText(String text) {
        MainController.authenticate(sessionKey);
        searchTextField.setText(text);
    }

    public String getSearchFieldText() {
        MainController.authenticate(sessionKey);
        return searchTextField.getText().
                trim();
    }

    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        searchTextField.requestFocus();
    }

    public void setNewModel(List<ClientDataWithTotalizedDebts> matches) {
        MainController.authenticate(sessionKey);
        resultTable.setModel(getNewResultTableModel(matches));
        setResultTableModelFormat(matches);
    }

    private void setResultTableModelFormat(List<ClientDataWithTotalizedDebts> matches) {
        MainController.authenticate(sessionKey);
        if (resultTable.getColumnModel().
                getColumnCount()
                > 0) {
            resultTable.getColumnModel().
                    getColumn(0).
                    setResizable(false);
            resultTable.getColumnModel().
                    getColumn(1).
                    setResizable(false);
            resultTable.getColumnModel().
                    getColumn(2).
                    setResizable(false);
            resultTable.getColumnModel().
                    getColumn(3).
                    setResizable(false);
            resultTable.getColumnModel().
                    getColumn(4).
                    setResizable(false);
            resultTable.getColumnModel().
                    getColumn(5).
                    setResizable(false);
        }

        resultTable.getTableHeader().
                setResizingAllowed(false);
        resultTable.getTableHeader().
                setReorderingAllowed(false);
        resultTable.getColumnModel().
                getColumn(0).
                setPreferredWidth(15);
        resultTable.getColumnModel().
                getColumn(3).
                setPreferredWidth(15);
        resultTable.getColumnModel().
                getColumn(6).
                setPreferredWidth(15);

        TableColumnModel columns;
        columns = resultTable.getColumnModel();

        for (int i = 0; i
                < columns.getColumnCount(); i++) {
            resultTable.setDefaultRenderer(resultTable.
                    getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {

                    if (matches.get(row).
                            isDefaulter()) {
                        setForeground(new Color(255, 102, 102));
                    } else {
                        setForeground(Color.BLACK);
                    }

                    return super.
                            getTableCellRendererComponent(table, value, isSelected,
                                    hasFocus, row, column);
                }
            });
        }
    }

    private DefaultTableModel getNewResultTableModel(List<ClientDataWithTotalizedDebts> matches) {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;
        objectMatrix = new Object[matches.size()][7];

        for (int i = 0; i < matches.size(); i++) {
            objectMatrix[i][0] = matches.get(i).
                    getId();
            objectMatrix[i][1] = matches.get(i).
                    getNick();
            objectMatrix[i][2] = matches.get(i).
                    getName();
            objectMatrix[i][3] = matches.get(i).
                    getCpnumber();
            objectMatrix[i][4] = matches.get(i).
                    getArea();
            objectMatrix[i][5] = matches.get(i).
                    getCreatedBy();
            objectMatrix[i][6] = "$ "
                    + MainController.formatAmount(matches.get(i).getTotalizedDebts());
        }

        return getDefaultTableModel(objectMatrix);
    }

    private static DefaultTableModel getDefaultTableModel(Object[][] objectMatrix) {
        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String[]{
                    "Id",
                    "Nick",
                    "Nombre",
                    "Teléfono",
                    "Área",
                    "Creado por",
                    "Total deuda"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            Class[] types = new Class[]{
                Integer.class,
                String.class,
                String.class,
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

    public void loginUpdate() {
        if(!loginUpdated) {
            if (MainController.getUser().
                    getType() == UserType.administrator) {
                exportToExcelButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            exportToExcel();
                            openExportedData();
                        } catch (IOException | WriteException ex) {
                            Logger.getLogger(QueryClientController.class.
                                    getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else {
                exportToExcelButton.setVisible(false);
            }
            loginUpdated = true;
        }
    }
}
