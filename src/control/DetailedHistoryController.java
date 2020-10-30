package control;

import model.Client;
import view.full_size_view.DetailedHistoryView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author admin
 */
public class DetailedHistoryController {
    private DetailedHistoryView view;
    private Client client;
    private List<Integer> monthlyAmount;
    private List<String> monthlyDates;
    private double standardDeviation;
    private double mean;
    private DecimalFormat amountFormater;
    private String sessionKey;
    
    
    public DetailedHistoryController(String sessionKey) throws ParseException {
        this.sessionKey = sessionKey;
        view = new DetailedHistoryView();
        amountFormater = new DecimalFormat("###,###.##");
        initView();
    }
    
    public void setViewData(Client client, TableModel tableModel) throws ParseException {
        verifySession();
        this.client = client;
        monthlyDates = client.getMonthlyDates();
        monthlyAmount = client.getMonthlyAmount();
        mean = client.getMean();
        standardDeviation = client.getStandardDeviation();
        
        view.meanLabel.setText("$" + amountFormater.format((int) Math.ceil(mean/100) * 100));
        view.stdDevUpLimLabel.setText("$" + amountFormater.format((int) Math.ceil((mean + standardDeviation)/100) * 100));
        view.historyPanel.setOpaque(false);
        view.nameLabel.setText(client.getName());
        view.nickLabel.setText(client.getNick());
        if(!client.getCPNumber().equals("")) {
            view.cpNumberLabel.setText(client.getCPNumber());
        }
        else {
            view.cpNumberLabel.setText("<No registra>");
        }
        view.notPaidBalanceLabel.setText("$" + amountFormater.format(client.getTotalNotPaidBalance()));
        setMonthlyHistoryTableModel();
        view.totalHistoryTable.setModel(tableModel);
        try {
            displayChart();
        } catch (ParseException ex) {
            Logger.getLogger(DetailedHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initView() {
        verifySession();
        view.goBackButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainController.changeToClientInfoMode(client,
                            sessionKey);
                } catch (ParseException ex) {
                    Logger.getLogger(DetailedHistoryController.class.getName()).
                            log(Level.SEVERE,
                            null,
                            ex);
                }
            }
        });
    }
    
    public DetailedHistoryView getView() {
        return view;
    }
    
    private void displayChart() throws ParseException {
        client.sortDebts();
        view.chartPanel.removeAll();
        
        List<Integer> chartableMonthlyAmounts;
        chartableMonthlyAmounts = setChartableMonthlyAmounts();
        
        List<String> chartableMonthlyDates;
        chartableMonthlyDates =  setChartableMonthlyDates();
        
        SetChartableListsSize(chartableMonthlyAmounts, chartableMonthlyDates);
        
        List<String> controlDates;
        controlDates = setControlDates();
        
        List<Integer> finalAmounts;
        finalAmounts = setFinalAmounts(
                controlDates, 
                chartableMonthlyDates, 
                chartableMonthlyAmounts);
        
        
        DefaultCategoryDataset dcd;
        dcd = new DefaultCategoryDataset();
        
        for(int i = finalAmounts.size() -1; i >= 0; i--) {
            if(finalAmounts.get(i) != null) {
                dcd.setValue(
                        finalAmounts.get(i),
                        "Máximo crédito (pesos) por mes",
                        getMonthName(
                                controlDates.get(i).
                                        substring(0, 2)) + 
                                ", " + 
                                controlDates.get(i).
                                        substring(3, 7)
                );
            }
            else {
                dcd.setValue(
                        0,
                        "Máximo crédito (pesos) por mes",
                        getMonthName(
                                controlDates.get(i).
                                        substring(0, 2)) + 
                                ", " + 
                                controlDates.get(i).
                                        substring(3, 7)
                );
            }
            dcd.setValue(
                    mean,
                    "Promedio",
                    getMonthName(
                            controlDates.get(i).
                                    substring(0, 2))
                    + ", "
                    + controlDates.get(i).
                            substring(3, 7)
            );
            dcd.setValue(
                    mean + standardDeviation,
                    "(Lím. Sup.) Desviación estándar",
                    getMonthName(
                            controlDates.get(i).
                                    substring(0, 2))
                    + ", "
                    + controlDates.get(i).
                            substring(3, 7)
            );
            
        }
        
        JFreeChart chart;
        chart = ChartFactory.createLineChart(
                "Deudas históricas: " + client.getName() + ", " +client.getNick(),
                "Tiempo (último semestre)",
                "Máximo crédito registrado ($)",
                dcd,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        chart.getCategoryPlot().setRangeGridlinePaint(Color.LIGHT_GRAY);
        chart.getCategoryPlot().setBackgroundPaint(new Color(53, 85, 108));
        chart.getCategoryPlot().setDomainGridlinesVisible(true);
        
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.WHITE);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(1, Color.YELLOW);
        chart.getCategoryPlot().getRenderer().setSeriesPaint(2, Color.RED);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        
        view.chartPanel.add(chartPanel);
        
        view.chartPanel.updateUI();
    }
    
    private String getMonthName(String month) {
        if(month.equals("01")) return "Ene";
        if(month.equals("02")) return "Feb";
        if(month.equals("03")) return "Mar";
        if(month.equals("04")) return "Abr";
        if(month.equals("05")) return "May";
        if(month.equals("06")) return "Jun";
        if(month.equals("07")) return "Jul";
        if(month.equals("08")) return "Ago";
        if(month.equals("09")) return "Sep";
        if(month.equals("10")) return "Oct";
        if(month.equals("11")) return "Nov";
        if(month.equals("12")) return "Dic";
        return null;
    }

    private void setMonthlyHistoryTableModel() {
        Object[][] objectMatrix;
        objectMatrix = new Object[monthlyAmount.size()][2];
        
        for(int i = 0; i < monthlyAmount.size(); i++) {
            objectMatrix[i][0] = amountFormater.format(monthlyAmount.get(i));
            objectMatrix[i][1] = getMonthName(monthlyDates.get(i).substring(0, 2)) +
                    ", " +
                    monthlyDates.get(i).substring(3);
        }
        
        DefaultTableModel model = new DefaultTableModel(
                objectMatrix,
                new String [] {
                "Deuda ($)", "Fecha"
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
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        }
                ;
        if (view.MonthlyHistoryTable.getColumnModel().getColumnCount() > 0) {
            view.MonthlyHistoryTable.getColumnModel().getColumn(0).setResizable(false);
            view.MonthlyHistoryTable.getColumnModel().getColumn(1).setResizable(false);
        }
        view.MonthlyHistoryTable.setModel(model);
    }

    private List<Integer> setChartableMonthlyAmounts() {
        List<Integer> chartableMonthlyAmounts;
        chartableMonthlyAmounts =  new ArrayList<>();
        for(int amount: monthlyAmount) {
            chartableMonthlyAmounts.add(amount);
        }
        return chartableMonthlyAmounts;
    }

    private List<String> setChartableMonthlyDates() {
        List<String> chartableMonthlyDates;
        chartableMonthlyDates = new ArrayList<>();
        for(String date: monthlyDates) {
            chartableMonthlyDates.add(date);
        }
        return chartableMonthlyDates;
    }

    private List<String> setControlDates() throws ParseException {
        SimpleDateFormat formater;
        formater = new SimpleDateFormat("MM/yyyy");
        
        Date thisMonth;
        thisMonth = 
                formater.
                        parse(formater.format(new Date()));
        
        List<String> controlDates;
        controlDates = new ArrayList<>();
        
        for(int i = 0; i < 6; i++) {
            controlDates.add(null);
        }
        for(int i = controlDates.size() - 1; i >= 0 ; i--) {
            controlDates.set(
                    i,
                    formater.format(
                            formater.parse(
                                    (Integer.parseInt(formater.format(thisMonth).substring(0, 2)) - i) + 
                                            formater.format(thisMonth).substring(2, 7))
                    )
            );
        }
        return controlDates;
    }

    private void SetChartableListsSize(List<Integer> chartableMonthlyAmounts, List<String> chartableMonthlyDates) {
        if(chartableMonthlyAmounts.size() < 6) {
            for(int i = 0; i < (6 - chartableMonthlyAmounts.size()); i++) {
                chartableMonthlyAmounts.add(0, null);
                chartableMonthlyDates.add(0, null);
            }
        }
        if(chartableMonthlyAmounts.size() > 6) {
            chartableMonthlyAmounts = chartableMonthlyAmounts.subList(
                    chartableMonthlyAmounts.size() - 6,
                    chartableMonthlyAmounts.size()
            );
            chartableMonthlyDates = chartableMonthlyDates.subList(
                    chartableMonthlyDates.size() - 6,
                    chartableMonthlyDates.size()
            );
        }
    }

    private List<Integer> setFinalAmounts(
                List<String> controlDates, 
                List<String> chartableMonthlyDates, 
                List<Integer> chartableMonthlyAmounts) {
        List<Integer> finalAmounts;
        finalAmounts = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            finalAmounts.add(null);
        }
        
        for(int i = controlDates.size() -1; i >= 0; i--) {
            for(int j = chartableMonthlyDates.size() -1; j >= 0; j--) {
                if(controlDates.get(i).equals(chartableMonthlyDates.get(j))) {
                    finalAmounts.set(i, chartableMonthlyAmounts.get(j));
                }
            }
        }
        return finalAmounts;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}