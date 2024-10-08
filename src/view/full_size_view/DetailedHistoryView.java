package view.full_size_view;

import control.DetailedHistoryController;
import control.MainController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class DetailedHistoryView extends javax.swing.JFrame {

    private final DetailedHistoryController controller;
    private boolean updated;
    private final String sessionKey;

    /**
     * Creates new form DetailedHistoryView
     * @param controller
     * @param sessionKey
     */
    public DetailedHistoryView(DetailedHistoryController controller, String sessionKey) {
        this.controller = controller;
        this.sessionKey = sessionKey;

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        notPaidBalanceLabel = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        chartPanel = new javax.swing.JPanel();
        historyPanel = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nickLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        totalHistoryTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        monthlyHistoryTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cpNumberLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        meanLabel = new javax.swing.JLabel();
        stdDevUpLimLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        goBackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 500, 400));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historia detallada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setText("Saldo no pagado:");

        notPaidBalanceLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        notPaidBalanceLabel.setForeground(new java.awt.Color(255, 153, 153));
        notPaidBalanceLabel.setText("+++");

        chartPanel.setBackground(new java.awt.Color(255, 255, 255));
        chartPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chartPanel.setLayout(new javax.swing.BoxLayout(chartPanel, javax.swing.BoxLayout.LINE_AXIS));
        tabs.addTab("Comportamiento", chartPanel);

        historyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel1.setText("Nombre:");

        nameLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabel.setText("+++");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel2.setText("Nick:");

        nickLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabel.setText("+++");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setText("Endeudamiento histórico:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("Endeudamiento por mes:");

        totalHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        totalHistoryTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(totalHistoryTable);
        totalHistoryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        monthlyHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(monthlyHistoryTable);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel6.setText("Teléfono:");

        cpNumberLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cpNumberLabel.setText("+++");

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(nameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(nickLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(cpNumberLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabel))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpNumberLabel))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nickLabel))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpNumberLabel)
                    .addComponent(jLabel6))
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        historyPanel.add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 420));

        tabs.addTab("Historia", historyPanel);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setText("Promedio:");

        meanLabel.setBackground(new java.awt.Color(0, 0, 0));
        meanLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        meanLabel.setText("+++");

        stdDevUpLimLabel.setBackground(new java.awt.Color(0, 0, 0));
        stdDevUpLimLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        stdDevUpLimLabel.setText("+++");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel9.setText("LSDE*:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(meanLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(stdDevUpLimLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(notPaidBalanceLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(meanLabel)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stdDevUpLimLabel)
                        .addComponent(jLabel3)
                        .addComponent(notPaidBalanceLabel))
                    .addComponent(jLabel9)))
        );

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("*LSDE = límite superior de la desviación estándar");

        goBackButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        goBackButton.setForeground(new java.awt.Color(0, 102, 153));
        goBackButton.setText("Volver");
        goBackButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        goBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 982, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainContainerLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(goBackButton)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 646, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
            .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(goBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void goBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goBackButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    public javax.swing.JLabel cpNumberLabel;
    private javax.swing.JButton goBackButton;
    public javax.swing.JPanel historyPanel;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JPanel mainContainer;
    public javax.swing.JLabel meanLabel;
    private javax.swing.JTable monthlyHistoryTable;
    public javax.swing.JLabel nameLabel;
    public javax.swing.JLabel nickLabel;
    public javax.swing.JLabel notPaidBalanceLabel;
    public javax.swing.JLabel stdDevUpLimLabel;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable totalHistoryTable;
    // End of variables declaration//GEN-END:variables

    public void updateView() {
        MainController.authenticate(sessionKey);
        if(!updated) {
        goBackButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.changeToClientInfoMode(controller.getCurrentClient(),
                            sessionKey);
                } catch (ParseException ex) {
                    Logger.getLogger(DetailedHistoryController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
            updated = true;
        }
    }
    
    public void displayChart() throws ParseException {
        MainController.authenticate(sessionKey);
        chartPanel.removeAll();

        List<Integer> chartableMonthlyAmounts;
        chartableMonthlyAmounts = controller.setChartableMonthlyAmounts();

        List<String> chartableMonthlyDates;
        chartableMonthlyDates = controller.setChartableMonthlyDates();

        controller.
                SetChartableListsSize(chartableMonthlyAmounts, chartableMonthlyDates);

        List<String> controlDates;
        controlDates = controller.setControlDates();

        List<Integer> finalAmounts;
        finalAmounts = controller.setFinalAmounts(
                controlDates,
                chartableMonthlyDates,
                chartableMonthlyAmounts);

        DefaultCategoryDataset dcd;
        dcd = new DefaultCategoryDataset();

        for (int i = finalAmounts.size() - 1; i >= 0; i--) {
            if (finalAmounts.get(i) != null) {
                dcd.setValue(
                        finalAmounts.get(i),
                        "Máximo crédito (pesos) por mes",
                        MainController.getMonthName(
                                controlDates.get(i).
                                        substring(0, 2))
                        + ", "
                        + controlDates.get(i).
                                substring(3, 7)
                );
            } else {
                dcd.setValue(
                        0,
                        "Máximo crédito (pesos) por mes",
                        MainController.getMonthName(
                                controlDates.get(i).
                                        substring(0, 2))
                        + ", "
                        + controlDates.get(i).
                                substring(3, 7)
                );
            }
            dcd.setValue(
                    controller.getMean(),
                    "Promedio",
                    MainController.getMonthName(
                            controlDates.get(i).
                                    substring(0, 2))
                    + ", "
                    + controlDates.get(i).
                            substring(3, 7)
            );
            dcd.setValue(
                    controller.getMean() + controller.getStandardDeviation(),
                    "(Lím. Sup.) Desviación estándar",
                    MainController.getMonthName(
                            controlDates.get(i).
                                    substring(0, 2))
                    + ", "
                    + controlDates.get(i).
                            substring(3, 7)
            );

        }

        JFreeChart chart;
        chart = ChartFactory.createLineChart(
                "Deudas históricas: " + controller.getCurrentClient().
                        getName() + ", " + controller.getCurrentClient().
                        getNick(),
                "Tiempo (último semestre)",
                "Máximo crédito registrado ($)",
                dcd,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.getCategoryPlot().
                setRangeGridlinePaint(Color.LIGHT_GRAY);
        chart.getCategoryPlot().
                setBackgroundPaint(new Color(53, 85, 108));
        chart.getCategoryPlot().
                setDomainGridlinesVisible(true);

        chart.getCategoryPlot().
                getRenderer().
                setSeriesPaint(0, Color.WHITE);
        chart.getCategoryPlot().
                getRenderer().
                setSeriesPaint(1, Color.YELLOW);
        chart.getCategoryPlot().
                getRenderer().
                setSeriesPaint(2, Color.RED);

        ChartPanel dataChartPanel = new ChartPanel(chart);

        chartPanel.add(dataChartPanel);

        chartPanel.updateUI();
    }

    public void setMonthlyHistoryTableModel() {
        MainController.authenticate(sessionKey);
        Object[][] objectMatrix;
        objectMatrix = new Object[controller.getMonthlyAmount().size()][2];
        
        for(int i = 0; i < controller.getMonthlyAmount().size(); i++) {
            objectMatrix[i][0] = MainController.formatAmount(controller.getMonthlyAmount().get(i));
            objectMatrix[i][1] = MainController.getMonthName(controller.getMonthlyDates().get(i).substring(0, 2)) +
                    ", " +
                    controller.getMonthlyDates().get(i).substring(3);
        }

        DefaultTableModel model = getDefaultTableModel(objectMatrix);
        if (monthlyHistoryTable.getColumnModel().
                getColumnCount() > 0) {
            monthlyHistoryTable.getColumnModel().
                    getColumn(0).
                    setResizable(false);
            monthlyHistoryTable.getColumnModel().
                    getColumn(1).
                    setResizable(false);
        }
        monthlyHistoryTable.setModel(model);
    }

    private static DefaultTableModel getDefaultTableModel(Object[][] objectMatrix) {
        DefaultTableModel model;
        model = new DefaultTableModel(
                objectMatrix,
                new String [] {
                    "Deuda ($)", "Fecha"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

            Class[] types = new Class [] {
                Integer.class,
                String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        return model;
    }

    public void setTotalHistoryTable(TableModel model) {
        MainController.authenticate(sessionKey);
        totalHistoryTable.setModel(model);
    }
    
    public void setMainElementFocus() {
        MainController.authenticate(sessionKey);
        goBackButton.requestFocus();
    }
}
