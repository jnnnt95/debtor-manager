package control;

import model.Client;
import model.IO.Reader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.full_size_view.QueryClientView;

public class QueryClientController {

    private List<Client> clients;
    private QueryClientView view;
    private ClientInfoController son;
    private Client activeClient;
    private String sessionKey;

    public QueryClientController(String sessionKey) throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.sessionKey = sessionKey;
        view = new QueryClientView(this, sessionKey);
        view.updateView();
        activeClient = null;
        son = new ClientInfoController(sessionKey);
        initView();
    }

    public QueryClientView getView() {
        verifySession();
        return view;
    }
    
    public void loginUpdate() {
        view.loginUpdate();
    }
    
    public void setViewData() throws IOException, ParseException, ClassNotFoundException, SQLException {
        verifySession();
        update();
    }

    public void setClients() throws IOException, ParseException, ClassNotFoundException, SQLException {
        clients = Reader.getClients();
    }

    private void initView() throws IOException, ParseException, ClassNotFoundException, SQLException {
        update();
    }

    public void setSearchTable() {
        List<Client> matches;
        matches = new ArrayList<>();

        for (Client client : clients) {
            if (removeDiacriticalMarks(client.getName()).
                    toUpperCase().
                    contains(removeDiacriticalMarks(view.getSearchFieldText().
                            toUpperCase()))
                    || removeDiacriticalMarks(client.getNick().
                            toUpperCase()).
                            contains(removeDiacriticalMarks(view.
                                    getSearchFieldText().
                                    toUpperCase()))
                    || removeDiacriticalMarks(client.getArea().
                            toUpperCase()).
                            contains(removeDiacriticalMarks(view.
                                    getSearchFieldText().
                                    toUpperCase()))) {
                matches.add(client);
            }
        }

        setClientsAsResult(matches);
    }

    public void showDefaulters() {
        List<Client> matches;
        matches = new ArrayList<>();

        for (Client client : clients) {
            if (client.isDefaulter()) {
                matches.add(client);
            }
        }

        setClientsAsResult(matches);
    }

    private void setClientsAsResult(List<Client> matches) {
        view.setNewModel(matches);
    }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Form.NFD).
                replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public void update() throws IOException, ParseException, ClassNotFoundException, SQLException {
        setClients();
        setSearchTable();
        setDebtorInfoLabels();
    }

    public void softUpdate() {
        //setClients();
        setSearchTable();
        setDebtorInfoLabels();
    }

    private static void updateClient(Client client) {

    }

    public void setDebtorInfoLabels() {
        int defaulterCounter;
        defaulterCounter = 0;

        int debtorCounter;
        debtorCounter = 0;

        for (Client client : clients) {
            if (client.isDefaulter()) {
                defaulterCounter++;
            }
            if (client.getTotalNotPaidBalance()
                    > 0) {
                debtorCounter++;
            }
        }

        view.defaulterCounterLabel.setText(MainController.
                formatAmount(defaulterCounter));
        view.debtorCounterLabel.setText(MainController.
                formatAmount(debtorCounter));
    }

    public Object[][] getClientsData() {
        Object[][] clientsData;
        clientsData = new String[clients.size()][6];

        for (int i = 0; i
                < clientsData.length; i++) {
            clientsData[i][0] = clients.get(i).
                    getNick();
            clientsData[i][1] = clients.get(i).
                    getName();
            clientsData[i][2] = clients.get(i).
                    getCPNumber();
            clientsData[i][3] = clients.get(i).
                    getArea();
            clientsData[i][4] = String.valueOf(clients.get(i).
                    getTotalNotPaidBalance());
            if (clients.get(i).
                    isDefaulter()) {
                clientsData[i][5] = "*";
            } else {
                clientsData[i][5] = "";
            }
        }

        return clientsData;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public void updateActiveClient(int clientId) {
        for (int i = 0;
                i
                < clients.size();
                i++) {
            if (clientId
                    == clients.get(i).
                            getId()) {
                activeClient = clients.get(i);
            }
        }

        clients.remove(null);

        try {
            MainController.changeToClientInfoMode(activeClient,
                    sessionKey);
        } catch (ParseException ex) {
            Logger.getLogger(QueryClientController.class.
                    getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }
    }
}
