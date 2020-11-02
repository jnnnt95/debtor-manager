package control;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import model.Client;
import model.IO.Reader;
import model.IO.Writer;
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

    private static User user;

    public static void start()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        Writer.setDBPreviousAmount();
        setSessionKey();

        fullSizeViewport = new FullSizeMainView();
        popUpSizeViewport = new PopUpMainView();
        loadingView = new OmachiView();

        fullSizeDimension = new Dimension(
                fullSizeViewport.container.getSize().width,
                fullSizeViewport.container.getSize().height);
        popUpSizeDimension = new Dimension(
                popUpSizeViewport.container.getSize().width,
                popUpSizeViewport.container.getSize().height);

        startLoading(sessionKey);

        login = new LogInController(sessionKey);
        login.getView().mainContainer.setSize(popUpSizeDimension);
        queryClient = new QueryClientController(sessionKey);
        clientInfo = new ClientInfoController(sessionKey);
        addClient = new AddClientController(sessionKey);
        modifyClient = new ModifyClientController(sessionKey);
        detailedHistory = new DetailedHistoryController(sessionKey);
        performPayment = new PerformPaymentController(sessionKey);
        addDebt = new AddDebtController(sessionKey);

        queryClient.getView().mainContainer.setSize(fullSizeDimension);
        clientInfo.getView().mainContainer.setSize(fullSizeDimension);
        addClient.getView().mainContainer.setSize(popUpSizeDimension);
        modifyClient.getView().mainContainer.setSize(popUpSizeDimension);
        detailedHistory.getView().mainContainer.setSize(fullSizeDimension);
        performPayment.getView().mainContainer.setSize(popUpSizeDimension);
        addDebt.getView().mainContainer.setSize(popUpSizeDimension);

        login();
    }

    private static void login() {
        popUpSizeViewport.setLocationRelativeTo(null);

        authenticate(sessionKey);

        fullSizeViewport.setEnabled(false);

        if (popUpSizeViewport.container.getComponentCount()
                > 0) {
            popUpSizeViewport.container.getComponent(0).
                    setVisible(false);
            popUpSizeViewport.container.removeAll();
        }

        login.getView().mainContainer.setSize(popUpSizeDimension);
        popUpSizeViewport.container.add(login.getView().mainContainer);
        popUpSizeViewport.container.getComponent(0).
                setVisible(true);
        popUpSizeViewport.setVisible(true);
        login.getView().userTextField.requestFocus();
    }

    public static void changeToQueryClientMode(String sessionKey)
            throws InterruptedException,
            IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        authenticate(sessionKey);
        if (!fullSizeViewport.queryClientsMenuItem.isVisible()) {
            fullSizeViewport.queryClientsMenuItem.setVisible(true);
        }
        if (!fullSizeViewport.createClientMenuItem.isVisible()) {
            fullSizeViewport.createClientMenuItem.setVisible(true);
        }

        try {
            queryClient.update();

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

        fullSizeViewport.setEnabled(true);

        fullSizeViewport.container.getComponent(0).
                setVisible(false);
        fullSizeViewport.container.removeAll();
        fullSizeViewport.container.add(queryClient.getView().mainContainer);
        //popUpSizeViewport.setVisible(false);
        fullSizeViewport.container.getComponent(0).
                setVisible(true);
        fullSizeViewport.queryClientsMenuItem.setVisible(false);
        queryClient.getView().searchTextField.setText("");
        queryClient.update();
        queryClient.getView().searchTextField.requestFocus();
    }

    public static void changeToClientInfoMode(Client currentClient,
            String sessionKey)
            throws ParseException {
        authenticate(sessionKey);
        popUpSizeViewport.setVisible(false);

        if (!fullSizeViewport.queryClientsMenuItem.isVisible()) {
            fullSizeViewport.queryClientsMenuItem.setVisible(true);
        }
        if (!fullSizeViewport.createClientMenuItem.isVisible()) {
            fullSizeViewport.createClientMenuItem.setVisible(true);
        }

        fullSizeViewport.setEnabled(true);

        fullSizeViewport.requestFocus();

        clientInfo.setViewData(currentClient);

        fullSizeViewport.container.getComponent(0).
                setVisible(false);
        fullSizeViewport.container.removeAll();
        fullSizeViewport.container.add(clientInfo.getView().mainContainer);
        fullSizeViewport.container.getComponent(0).
                setVisible(true);
    }

    public static void changeToCreateClientMode(String sessionKey) {
        popUpSizeViewport.setLocationRelativeTo(null);

        authenticate(sessionKey);
        fullSizeViewport.setEnabled(false);

        popUpSizeViewport.container.getComponent(0).
                setVisible(false);
        popUpSizeViewport.container.removeAll();
        popUpSizeViewport.container.add(addClient.getView().mainContainer);
        popUpSizeViewport.container.getComponent(0).
                setVisible(true);
        popUpSizeViewport.setVisible(true);
    }

    public static void changeToModifyClientMode(String sessionKey,
            Client currentClient) {
        popUpSizeViewport.setLocationRelativeTo(null);

        authenticate(sessionKey);
        fullSizeViewport.setEnabled(false);

        modifyClient.setCurrentClient(currentClient);
        modifyClient.getView().nameField.setText(currentClient.getName());
        modifyClient.getView().nickField.setText(currentClient.getNick());
        modifyClient.getView().cpNumberField.
                setText(currentClient.getCPNumber());
        modifyClient.getView().areaField.setText(currentClient.getArea());

        popUpSizeViewport.container.getComponent(0).
                setVisible(false);
        popUpSizeViewport.container.removeAll();
        popUpSizeViewport.container.add(modifyClient.getView().mainContainer);
        popUpSizeViewport.container.getComponent(0).
                setVisible(true);
        popUpSizeViewport.setVisible(true);
    }

    public static void changeToDetailedHistoryMode(Client currentClient,
            TableModel tableModel,
            String sessionKey)
            throws ParseException {
        authenticate(sessionKey);
        popUpSizeViewport.setVisible(false);

        if (!fullSizeViewport.queryClientsMenuItem.isVisible()) {
            fullSizeViewport.queryClientsMenuItem.setVisible(true);
        }
        if (!fullSizeViewport.createClientMenuItem.isVisible()) {
            fullSizeViewport.createClientMenuItem.setVisible(true);
        }

        fullSizeViewport.setEnabled(true);

        fullSizeViewport.requestFocus();

        detailedHistory.setViewData(currentClient,
                tableModel);

        fullSizeViewport.container.getComponent(0).
                setVisible(false);
        fullSizeViewport.container.removeAll();
        fullSizeViewport.container.add(detailedHistory.getView().mainContainer);
        fullSizeViewport.container.getComponent(0).
                setVisible(true);
    }

    public static void changeToPerformPaymentMode(Client currentClient,
            String sessionKey) {
        popUpSizeViewport.setLocationRelativeTo(null);

        authenticate(sessionKey);
        fullSizeViewport.setEnabled(false);

        popUpSizeViewport.container.getComponent(0).
                setVisible(false);
        popUpSizeViewport.container.removeAll();

        performPayment.setViewData(currentClient);

        popUpSizeViewport.container.add(performPayment.getView().mainContainer);
        popUpSizeViewport.setVisible(true);
        popUpSizeViewport.container.getComponent(0).
                setVisible(true);
        performPayment.getView().amountField.requestFocus();

        popUpSizeViewport.setVisible(true);
    }

    public static void changeToAddDebtMode(Client currentClient,
            String sessionKey) {
        popUpSizeViewport.setLocationRelativeTo(null);

        authenticate(sessionKey);
        fullSizeViewport.setEnabled(false);

        popUpSizeViewport.container.getComponent(0).
                setVisible(false);
        popUpSizeViewport.container.removeAll();

        addDebt.setViewData(currentClient);

        popUpSizeViewport.container.add(addDebt.getView().mainContainer);
        popUpSizeViewport.setVisible(true);
        popUpSizeViewport.container.getComponent(0).
                setVisible(true);
        addDebt.getView().amountField.requestFocus();

        popUpSizeViewport.setVisible(true);
    }

    public static void startLoading(String sessionKey) {
        authenticate(sessionKey);

        popUpSizeViewport.setLocationRelativeTo(null);
        fullSizeViewport.setEnabled(false);

        if (popUpSizeViewport.container.getComponentCount()
                > 0) {
            popUpSizeViewport.container.getComponent(0).
                    setVisible(false);
        }
        popUpSizeViewport.container.removeAll();

        loadingView.mainContainer.setSize(popUpSizeDimension);
        popUpSizeViewport.container.add(loadingView.mainContainer);
        popUpSizeViewport.setVisible(true);
        popUpSizeViewport.container.getComponent(0).
                setVisible(true);
        popUpSizeViewport.setVisible(true);
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
                    "Sesión desconocida, el programa se cerrará");
            System.exit(0);
        }
    }

    public static void seek(OperationCode code,
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

    private static void launch()
            throws InterruptedException {
        popUpSizeViewport.setDefaultCloseOperation(
                WindowConstants.DO_NOTHING_ON_CLOSE);
        popUpSizeViewport.setVisible(false);
        fullSizeViewport.setVisible(true);

        user = login.getUser();

        switch (user.getType()) {
            case administrator:
                prepareViewsForAdministrator();
                break;
            case normal:
                prepareViewsForNormalUser();
                break;
            default:
                prepareViewsForNormalUser();
                break;
        }
        viewGenericInitialization();
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
        fullSizeViewport.todaysPaymentsMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getTodaysPaymentsBalance());
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
        });
    }

    private static void prepareViewsForAdministrator() {
        fullSizeViewport.setInstantiationUserType("Administrador",
                user.getName());
        fullSizeViewport.businessMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getBusinessBriefing());
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        fullSizeViewport.usersCredentialsMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getUsersCredentials());
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
        
    }

    private static void prepareViewsForNormalUser() {
        fullSizeViewport.setInstantiationUserType("Usuario",
                user.getName());
        fullSizeViewport.businessMenuItem.setVisible(false);
        fullSizeViewport.usersCredentialsMenuItem.setVisible(false);
        clientInfo.getView().disableClientButton.setVisible(false);
        queryClient.getView().exportToExcelButton.setVisible(false);
    }

    private static void viewGenericInitialization() {
        clientInfo.updateUser(user);
        fullSizeViewport.queryClientsMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changeToQueryClientMode(sessionKey);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
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
        });
        fullSizeViewport.createClientMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToCreateClientMode(sessionKey);
            }
        });
        fullSizeViewport.exitMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option;
                option = JOptionPane.showConfirmDialog(null,
                        "¿Salir de Debtor Manager?");
                switch (option) {
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        });
        fullSizeViewport.aboutMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAbout();
            }
        });
        fullSizeViewport.userInfoMenuItem.addActionListener(
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            Reader.getUserInfo(user.getId()));
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).
                            log(Level.SEVERE,
                                    null,
                                    ex);
                }
            }
        });
    }

    private static void showAbout() {
        String printable = "<html><strong>® Debtor Manager<br><br>"
                + "© Jonathan Torres</strong><br>"
                + "   Se prohibe la distribución no autorizada de este software.<br>"
                + "   Para más información:<br>"
                + "      <strong>email</strong>: jnthntrm@gmail.com<br>"
                + "      <strong>teléfono celular</strong>: 305 925 40 24<br><br>"
                + "<strong>Arte</strong>: https://iconos8.es/</html>";
        JOptionPane.showMessageDialog(null,
                printable);
    }

    public static User getUser() {
        return user;
    }
}
