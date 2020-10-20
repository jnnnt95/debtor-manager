
package view;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author admin
 */
public class ClientInfoView extends javax.swing.JFrame {

    /**
     * Creates new form ClientInfo
     */
    public ClientInfoView() {
        setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 180));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
            }
        });
        initComponents();
        
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLocationRelativeTo(null);
        payButton.requestFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        background = new javax.swing.JDesktopPane();
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
        exitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        nameLabelTitle.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nameLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        nameLabelTitle.setText("Nombre:");

        nameLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("+++");

        nickLabelTitle.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nickLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        nickLabelTitle.setText("Nick:");

        nickLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        nickLabel.setForeground(new java.awt.Color(255, 255, 255));
        nickLabel.setText("+++");

        balanceLabelTitle.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        balanceLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        balanceLabelTitle.setText("Saldo no pagado:");

        nonPaidBalanceLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nonPaidBalanceLabel.setForeground(new java.awt.Color(255, 153, 153));
        nonPaidBalanceLabel.setText("+++");

        payButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        payButton.setForeground(new java.awt.Color(0, 153, 0));
        payButton.setText("Pagar");

        addDebtButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        addDebtButton.setText("Agregar deuda");

        toggleListButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        toggleListButton.setText("Ver todas las deudas");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Historia:");

        historyTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Deuda", "Abono", "Fecha", "Cancelada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
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

        defaultAmountTitleLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        defaultAmountTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        defaultAmountTitleLabel.setText("Saldo en mora:");

        defaultAmountLabel.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        defaultAmountLabel.setForeground(new java.awt.Color(255, 102, 102));
        defaultAmountLabel.setText("+++");

        modifyClientButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        modifyClientButton.setText("Modificar cliente");

        viewDetailedHistoryButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        viewDetailedHistoryButton.setForeground(new java.awt.Color(67, 106, 137));
        viewDetailedHistoryButton.setText("Ver historia detallada");

        nickLabelTitle1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        nickLabelTitle1.setForeground(new java.awt.Color(255, 255, 255));
        nickLabelTitle1.setText("Teléfono:");

        cpNumberLabel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cpNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        cpNumberLabel.setText("+++");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nickLabelTitle)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nickLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nickLabelTitle1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cpNumberLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(balanceLabelTitle)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nonPaidBalanceLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addDebtButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(nameLabelTitle)
                                .addGap(10, 10, 10)
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(modifyClientButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toggleListButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewDetailedHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(defaultAmountTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defaultAmountLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addDebtButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nickLabelTitle)
                                    .addComponent(nickLabel))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nickLabelTitle1)
                            .addComponent(cpNumberLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(balanceLabelTitle)
                            .addComponent(nonPaidBalanceLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultAmountTitleLabel)
                    .addComponent(defaultAmountLabel))
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(viewDetailedHistoryButton)
                        .addComponent(toggleListButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exitButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        exitButton.setText("Salir");

        cancelButton.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cancelButton.setText("Volver");

        background.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        background.setLayer(exitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        background.setLayer(cancelButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addGap(19, 19, 19))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(cancelButton))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addDebtButton;
    public javax.swing.JDesktopPane background;
    private javax.swing.JLabel balanceLabelTitle;
    public javax.swing.JButton cancelButton;
    public javax.swing.JLabel cpNumberLabel;
    public javax.swing.JLabel defaultAmountLabel;
    public javax.swing.JLabel defaultAmountTitleLabel;
    public javax.swing.JButton exitButton;
    public javax.swing.JTable historyTable;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JButton modifyClientButton;
    public javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabelTitle;
    public javax.swing.JLabel nickLabel;
    private javax.swing.JLabel nickLabelTitle;
    private javax.swing.JLabel nickLabelTitle1;
    public javax.swing.JLabel nonPaidBalanceLabel;
    public javax.swing.JButton payButton;
    public javax.swing.JButton toggleListButton;
    public javax.swing.JButton viewDetailedHistoryButton;
    // End of variables declaration//GEN-END:variables
}
