package control;

import model.Client;
import java.awt.Color;
import java.awt.Component;
import model.Reader;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import jxl.write.WriteException;
import model.Writer;
import view.QueryClientView;

public class QueryClientController {
    private List<Client> clients;
    private QueryClientView view;
    private MenuController parent;
    private ClientInfoController son;
    private Client activeClient;
    private DecimalFormat amountFormater;
    private String sessionKey;
    private LogInController parentLogIn;
        
    
    public QueryClientController(LogInController parentLogIn, String sessionKey) throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.parentLogIn = parentLogIn;
        this.sessionKey = sessionKey;
        view = new QueryClientView();
        amountFormater = new DecimalFormat("###,###.##");
        activeClient = null;
        son = new ClientInfoController(parentLogIn, sessionKey);
        initView();
    }
    
    public QueryClientView getView() {
        verifySession();
        return view;
    }
    
    public void setViewData(MenuController parent) throws IOException, ParseException, ClassNotFoundException, SQLException {
        verifySession();
        this.parent = parent;
        update();
    }
    
    public void setClients() throws IOException, ParseException, ClassNotFoundException, SQLException {
        clients = Reader.getClients();
    }
    
    private void initView() throws IOException, ParseException, ClassNotFoundException, SQLException {
        update();
        view.showDefaultersButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDefaulters();
            }
        });
        view.resetButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.searchTextField.setText("");
                setSearchTable();
            }
        });
        view.exportToExcelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    exportToExcel();
                    openExportedData();
                } catch (IOException ex) {
                    Logger.getLogger(QueryClientController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriteException ex) {
                    Logger.getLogger(QueryClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        view.resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount() == 2){
                    int clientId;
                    clientId = 
                            (Integer) 
                            view.resultTable.getValueAt(view.resultTable.getSelectedRow(), 0);
                
                    for(int i = 0; i < clients.size(); i++) {
                        if(clientId == clients.get(i).getId()) {
                            activeClient = clients.get(i);
                        }
                    }
                
                    clients.remove(null);
                
                    try {
                        showClientInfo(activeClient);
                    } catch (ParseException ex) {
                        Logger.getLogger(QueryClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        view.searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                    setSearchTable();
                }
                if(event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    view.searchTextField.setText("");
                    setSearchTable();
                }
            }
        });
        view.cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tools.swapWindow(parent.getView(), view);
            }
        });
    }
    
    private void showClientInfo(Client activeClient) throws ParseException {
        son.setViewData(this, activeClient);
        tools.swapWindow(son.getView(), view);
    }
    
    public void setSearchTable() {
        List<Client> matches;
        matches = new ArrayList<>();
        
        for(Client client: clients) {
            if(removeDiacriticalMarks(client.getName()).toUpperCase().
                    contains(removeDiacriticalMarks(view.searchTextField.
                            getText().trim().toUpperCase()))
                    ||
                removeDiacriticalMarks(client.getNick().toUpperCase()).
                    contains(removeDiacriticalMarks(view.searchTextField.
                            getText().trim().toUpperCase()))
                    ||
                removeDiacriticalMarks(client.getArea().toUpperCase()).
                    contains(removeDiacriticalMarks(view.searchTextField.
                            getText().trim().toUpperCase()))
                    ) {
                matches.add(client);
            }
        }
        
        setClientsAsResult(matches);
    }
    
    private void showDefaulters() {
        List<Client> matches;
        matches = new ArrayList<>();
        
        for(Client client: clients) {
            if(client.isDefaulter()) {
                matches.add(client);
            }
        }
        
        setClientsAsResult(matches);
    }
    
    private void setClientsAsResult(List<Client> matches) {
        Object[][] objectMatrix;
        objectMatrix = new Object[matches.size()][6];
        
        for(int i = 0; i < matches.size(); i++) {
            objectMatrix[i][0] = matches.get(i).getId();
            objectMatrix[i][1] = matches.get(i).getNick();
            objectMatrix[i][2] = matches.get(i).getName();
            objectMatrix[i][3] = matches.get(i).getCPNumber();
            objectMatrix[i][4] = matches.get(i).getArea();
            objectMatrix[i][5] = "$ " + amountFormater.format(matches.get(i).getTotalNotPaidBalance());
        }
        
        DefaultTableModel model = new DefaultTableModel(
                objectMatrix,
                new String [] {
                "Id", "Nick", "Nombre", "Teléfono", "Área", "Total deuda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            
            Class[] types = new Class [] {
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        }
                ;
        view.resultTable.setModel(model);
        if (view.resultTable.getColumnModel().getColumnCount() > 0) {
            view.resultTable.getColumnModel().getColumn(0).setResizable(false);
            view.resultTable.getColumnModel().getColumn(1).setResizable(false);
            view.resultTable.getColumnModel().getColumn(2).setResizable(false);
            view.resultTable.getColumnModel().getColumn(3).setResizable(false);
            view.resultTable.getColumnModel().getColumn(4).setResizable(false);
            view.resultTable.getColumnModel().getColumn(5).setResizable(false);
        }
        
        view.resultTable.getTableHeader().setResizingAllowed(false);
        view.resultTable.getTableHeader().setReorderingAllowed(false);
        view.resultTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        view.resultTable.getColumnModel().getColumn(5).setPreferredWidth(15);
        
        TableColumnModel columns;
        columns = view.resultTable.getColumnModel();
        
        for(int i = 0; i < columns.getColumnCount(); i++) {
            view.resultTable.setDefaultRenderer(view.resultTable.getColumnClass(i), new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    
                    if (matches.get(row).isDefaulter()) {
                        setForeground(new Color(255, 102, 102));
                    }
                    else {
                        setForeground(Color.BLACK);
                    }

                    return super.getTableCellRendererComponent(table, value, isSelected,
                            hasFocus, row, column);
                }
            });
        }
    }
    
    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public void update() throws IOException, ParseException, ClassNotFoundException, SQLException {
        setClients();
        setSearchTable();
        setDebtorInfoLabels();
    }
    
    public void setDebtorInfoLabels() {
        int defaulterCounter;
        defaulterCounter = 0;
        
        int debtorCounter;
        debtorCounter = 0;
        
        for(Client client: clients) {
            if(client.isDefaulter()) {
                defaulterCounter++;
            }
            if(client.getTotalNotPaidBalance() > 0) {
                debtorCounter++;
            }
        }
        
        view.defaulterCounterLabel.setText(amountFormater.format(defaulterCounter));
        view.debtorCounterLabel.setText(amountFormater.format(debtorCounter));
    }
    
    public void exportToExcel() throws IOException, WriteException {
        Object[][] clientsData;
        clientsData = getClientsData();
        Writer.exportToExcel(clientsData);
    }
    
    private Object[][] getClientsData() {
        Object[][] clientsData;
        clientsData = new String[clients.size()][6];
        
        for(int i = 0; i < clientsData.length; i++) {
            clientsData[i][0] = clients.get(i).getNick();
            clientsData[i][1] = clients.get(i).getName();
            clientsData[i][2] = clients.get(i).getCPNumber();
            clientsData[i][3] = clients.get(i).getArea();
            clientsData[i][4] = String.valueOf(clients.get(i).getTotalNotPaidBalance());
            if(clients.get(i).isDefaulter()) {
                clientsData[i][5] = "*";
            }
            else {
                clientsData[i][5] = "";
            }
        }
        
        return clientsData;
    }
    
    private void openExportedData() throws IOException {
        Reader.openExportedData();
    }
    
    private void verifySession() {
        parentLogIn.verifySession(sessionKey);
    }
}