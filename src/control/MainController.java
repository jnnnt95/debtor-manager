package control;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import model.Client;
import view.MainViews.FullSizeMainView;
import view.pop_up_view.OmachiView;
import view.MainViews.PopUpMainView;
import model.User;
import model.enums.OperationCode;

public class MainController {

    private static FullSizeMainView fullSizeViewport;
    private static PopUpMainView popUpSizeViewport;
    private static OmachiView loadingView;

    private static Dimension fullSizeDimension;
    private static Dimension popUpSizeDimension;

    private static String sessionKey;

    private static LogInController login;
    private static QueryClientController queryClient;
    private static ClientInfoController clientInfo;
    private static AddClientController addClient;
    private static ModifyClientController modifyClient;
    private static DetailedHistoryController detailedHistory;
    private static PerformPaymentController performPayment;
    private static AddDebtController addDebt;
    private static DepositClientsOnDateController depositClientsOnDate;

    private static User user;
    
    private static DecimalFormat amountFormater;

    public static void start()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        setSessionKey();

        fullSizeViewport = new FullSizeMainView(sessionKey);
        popUpSizeViewport = new PopUpMainView(sessionKey);

        fullSizeViewport.setPopUpSizeViewport(popUpSizeViewport);
        popUpSizeViewport.setFullSizeViewport(fullSizeViewport);

        loadingView = new OmachiView();

        fullSizeDimension = new Dimension(
                fullSizeViewport.container.getSize().width,
                fullSizeViewport.container.getSize().height);
        popUpSizeDimension = new Dimension(
                popUpSizeViewport.container.getSize().width,
                popUpSizeViewport.container.getSize().height);

        loadingView.mainContainer.setSize(popUpSizeDimension);
        startLoading(sessionKey);
        
        amountFormater = new DecimalFormat("###,###.##");

        login = new LogInController(sessionKey);
        login.getView().mainContainer.setSize(popUpSizeDimension);
        addClient = new AddClientController(sessionKey);
        modifyClient = new ModifyClientController(sessionKey);
        detailedHistory = new DetailedHistoryController(sessionKey);
        performPayment = new PerformPaymentController(sessionKey);
        addDebt = new AddDebtController(sessionKey);
        depositClientsOnDate = new DepositClientsOnDateController(sessionKey);

        addClient.getView().mainContainer.setSize(popUpSizeDimension);
        modifyClient.getView().mainContainer.setSize(popUpSizeDimension);
        detailedHistory.getView().mainContainer.setSize(fullSizeDimension);
        performPayment.getView().mainContainer.setSize(popUpSizeDimension);
        addDebt.getView().mainContainer.setSize(popUpSizeDimension);
        depositClientsOnDate.getView().mainContainer.setSize(fullSizeDimension);

        login();
    }

    private static void setSessionKey() {
        String sessionKeyPlaceholder;
        sessionKeyPlaceholder = "";

        Random randomizer;
        randomizer = new Random();

        char currentCharacter;

        for (int i = 0;
                i
                < 30;
                i++) {
            switch (randomizer.nextInt(3)) {
                case 0:
                    currentCharacter = (char) (65
                            + randomizer.nextInt(26));
                    sessionKeyPlaceholder = sessionKeyPlaceholder
                            + String.valueOf(currentCharacter);
                    break;
                case 1:
                    currentCharacter = (char) (97
                            + randomizer.nextInt(26));
                    sessionKeyPlaceholder = sessionKeyPlaceholder
                            + String.valueOf(currentCharacter);
                    break;
                case 2:
                    currentCharacter = (char) (33
                            + randomizer.nextInt(32));
                    sessionKeyPlaceholder = sessionKeyPlaceholder
                            + String.valueOf(currentCharacter);
                    break;
            }
        }

        sessionKey = sessionKeyPlaceholder;
    }

    public static void authenticate(String sessionKeyToVerify) {
        if (!sessionKeyToVerify.equals(sessionKey)) {
            JOptionPane.showMessageDialog(null,
                    "Error, el programa se cerrará");
            System.exit(0);
        }
    }

    public static void executeOperation(OperationCode code,
            String sessionKeyToVerify)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException,
            InterruptedException {
        authenticate(sessionKeyToVerify);
        switch (code) {
            //Launching application after successful login
            case launchApplication:
                launch();
                break;
            //Updating data after client modification
            case updateQueryClientData:
                queryClient.update();
                break;
            case cancelAddingAClient:
                cancelOperation();
                break;
            case cancelModifyingAClient:
                cancelOperation();
                break;
            case cancelPaymentPerforming:
                cancelOperation();
                break;
            case completeAddinAClient:
                completeOperation();
                break;
        }
    }

    private static void cancelOperation() {
        popUpSizeViewport.setEnabled(true);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setEnabled(true);
        fullSizeViewport.setVisible(true);
    }

    private static void completeOperation() {
        popUpSizeViewport.setEnabled(true);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setEnabled(true);
        fullSizeViewport.setVisible(true);
        try {
            queryClient.update();
        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }
    }

    private static void launch()
            throws InterruptedException {
        popUpSizeViewport.setDefaultCloseOperation(
                WindowConstants.DO_NOTHING_ON_CLOSE);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setVisible(true);

        user = login.getUser();

        fullSizeViewport.updateView();
        switch (user.getType()) {
            case administrator:
                fullSizeViewport.prepareViewForAdministrator();
                break;
            case normal:
            default:
                fullSizeViewport.prepareViewForNormalUser();
                break;
        }
        
        try {
            queryClient = new QueryClientController(sessionKey);
            clientInfo = new ClientInfoController(sessionKey);
        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
        queryClient.getView().mainContainer.setSize(fullSizeDimension);
        clientInfo.getView().mainContainer.setSize(fullSizeDimension);
        
        try {
            changeToQueryClientMode(sessionKey);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }

    }

    public static User getUser() {
        return user;
    }

    public static Dimension getFullSizeDimension() {
        return fullSizeDimension;
    }

    public static Dimension getPopUpSizeDimension() {
        return popUpSizeDimension;
    }

    // -------------------- Change-to-methods
    // -------------------- Full Size View
    public static void changeToQueryClientMode(String sessionKey)
            throws InterruptedException,
            IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        authenticate(sessionKey);
        queryClient.update();
        fullSizeViewport.changeToQueryClientMode(queryClient.getView());
    }

    public static void changeToClientInfoMode(Client currentClient,
            String sessionKey)
            throws ParseException {
        authenticate(sessionKey);
        clientInfo.setViewData(currentClient);
        fullSizeViewport.changeToClientInfoMode(clientInfo.getView());
    }

    public static void changeToDetailedHistoryMode(Client currentClient,
            TableModel tableModel,
            String sessionKey)
            throws ParseException {
        detailedHistory.setViewData(currentClient,
                tableModel);
        fullSizeViewport.changeToDetailedHistoryMode(detailedHistory.getView());
    }

    public static void changeToDepositClientsOnDateMode(String sessionKey)
            throws ParseException {
        authenticate(sessionKey);
        fullSizeViewport.changeToDepositClientsOnDateMode(depositClientsOnDate.getView());
        depositClientsOnDate.setViewData();
    }

    // -------------------- Pop Up Size View
    public static void changeToCreateClientMode(String sessionKey) {
        try {
            addClient.setReady();
        } catch (IOException | ParseException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        popUpSizeViewport.changeToCreateClientMode(addClient.getView());
    }

    public static void changeToModifyClientMode(String sessionKey,
            Client currentClient) {
        authenticate(sessionKey);
        modifyClient.setCurrentClient(currentClient);
        modifyClient.getView().setClientName(currentClient.getName());
        modifyClient.getView().setClientNick(currentClient.getNick());
        modifyClient.getView().setClientCPNumber(currentClient.getCPNumber());
        modifyClient.getView().setClientArea(currentClient.getArea());
        popUpSizeViewport.changeToModifyClientMode(modifyClient.getView());
    }

    public static void changeToPerformPaymentMode(Client currentClient,
            String sessionKey) {
        authenticate(sessionKey);
        performPayment.setViewData(currentClient);
        popUpSizeViewport.changeToPerformPaymentMode(performPayment.getView());
    }

    public static void changeToAddDebtMode(Client currentClient,
            String sessionKey) {
        authenticate(sessionKey);
        addDebt.setViewData(currentClient);
        popUpSizeViewport.changeToAddDebtMode(addDebt.getView());
    }

    public static void startLoading(String sessionKey) {
        authenticate(sessionKey);
        popUpSizeViewport.startLoading(loadingView);
    }

    private static void login() {
        popUpSizeViewport.login(login.getView());
    }
    
    public static String formatAmount(int amount) {
        return amountFormater.format(amount);
    }
    
    public static String getMonthName(String month) {
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
    
    

    public static boolean isNumberADigit(String s) {
        //returns true if argument s is a number
        if (s.equals("0")) {
            return true;
        }
        if (s.equals("1")) {
            return true;
        }
        if (s.equals("2")) {
            return true;
        }
        if (s.equals("3")) {
            return true;
        }
        if (s.equals("4")) {
            return true;
        }
        if (s.equals("5")) {
            return true;
        }
        if (s.equals("6")) {
            return true;
        }
        if (s.equals("7")) {
            return true;
        }
        if (s.equals("8")) {
            return true;
        }
        if (s.equals("9")) {
            return true;
        }

        //returns false if argument s is not a number
        return false;
    }
}
