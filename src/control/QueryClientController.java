package control;

import model.Client;
import model.ClientDataWithTotalizedDebts;
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
        clients = new ArrayList<>();
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

    private void initView() throws IOException, ParseException, ClassNotFoundException, SQLException {
        update();
    }


    public void setSearchTable() {
        List<ClientDataWithTotalizedDebts> matches = new ArrayList<>();
        List<ClientDataWithTotalizedDebts> clientsData;

        try {
            clientsData = Reader.getClientDataWithTotalizedDebts();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (ClientDataWithTotalizedDebts clientData : clientsData) {
            if (removeDiacriticalMarks(clientData.getName()).
                    toUpperCase().
                    contains(removeDiacriticalMarks(view.getSearchFieldText().
                            toUpperCase()))
                    || removeDiacriticalMarks(clientData.getNick().
                    toUpperCase()).
                    contains(removeDiacriticalMarks(view.
                            getSearchFieldText().
                            toUpperCase()))
                    || removeDiacriticalMarks(clientData.getArea().
                    toUpperCase()).
                    contains(removeDiacriticalMarks(view.
                            getSearchFieldText().
                            toUpperCase()))) {
                matches.add(clientData);
            }
        }

        setClientsAsResult(matches);
    }



    public void setSearchTable(List<ClientDataWithTotalizedDebts> clientsData) {
        List<ClientDataWithTotalizedDebts> matches = new ArrayList<>();

        for (ClientDataWithTotalizedDebts clientData : clientsData) {
            if (removeDiacriticalMarks(clientData.getName()).
                    toUpperCase().
                    contains(removeDiacriticalMarks(view.getSearchFieldText().
                            toUpperCase()))
                    || removeDiacriticalMarks(clientData.getNick().
                            toUpperCase()).
                            contains(removeDiacriticalMarks(view.
                                    getSearchFieldText().
                                    toUpperCase()))
                    || removeDiacriticalMarks(clientData.getArea().
                            toUpperCase()).
                            contains(removeDiacriticalMarks(view.
                                    getSearchFieldText().
                                    toUpperCase()))) {
                matches.add(clientData);
            }
        }

        setClientsAsResult(matches);
    }

    public void showDefaulters() {
        List<ClientDataWithTotalizedDebts> all;
        List<ClientDataWithTotalizedDebts> matches = new ArrayList<>();

        try {
            all = Reader.getClientDataWithTotalizedDebts();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (ClientDataWithTotalizedDebts clientData : all) {
            if (clientData.isDefaulter()) {
                matches.add(clientData);
            }
        }

        setClientsAsResult(matches);
    }

    private void setClientsAsResult(List<ClientDataWithTotalizedDebts> matches) {
        view.setNewModel(matches);
    }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Form.NFD).
                replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public void update() {

        List<ClientDataWithTotalizedDebts> clientsData;

        try {
            clientsData = Reader.getClientDataWithTotalizedDebts();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        setSearchTable(clientsData);
        setDebtorInfoLabels(clientsData);

    }

    public void softUpdate() {

        List<ClientDataWithTotalizedDebts> clientsData;

        try {
            clientsData = Reader.getClientDataWithTotalizedDebts();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        setSearchTable(clientsData);
        setDebtorInfoLabels(clientsData);
    }

    public void setDebtorInfoLabels(List<ClientDataWithTotalizedDebts> clientsData) {
        int defaulterCounter;
        defaulterCounter = 0;

        int debtorCounter;
        debtorCounter = 0;

        for (ClientDataWithTotalizedDebts clientData : clientsData) {
            if (clientData.isDefaulter()) {
                defaulterCounter++;
            }
            if (clientData.getTotalizedDebts()
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
        Object[][] clientsPrintableData;
        List<ClientDataWithTotalizedDebts> clientsData;

        try {
            clientsData = Reader.getClientDataWithTotalizedDebts();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        clientsPrintableData = new String[clientsData.size()][6];

        for (int i = 0; i < clientsPrintableData.length; i++) {
            clientsPrintableData[i][0] = clientsData.get(i).getNick();
            clientsPrintableData[i][1] = clientsData.get(i).getName();
            clientsPrintableData[i][2] = clientsData.get(i).getCpnumber();
            clientsPrintableData[i][3] = clientsData.get(i).getArea();
            clientsPrintableData[i][4] = String.valueOf(clientsData.get(i).getTotalizedDebts());
            if (clientsData.get(i).isDefaulter()) {
                clientsPrintableData[i][5] = "*";
            } else {
                clientsPrintableData[i][5] = "";
            }
        }

        return clientsPrintableData;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public void updateActiveClient(int clientId) {
        clients.remove(null);
        activeClient = null;

        for (int i = 0; i < clients.size(); i++) {
            if (clientId == clients.get(i).getId()) {
                activeClient = clients.get(i);
            }
        }

        if(activeClient == null) {
            try {
                activeClient = Reader.getClientById(clientId);
                clients.add(activeClient);
            } catch (IOException | ParseException | ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            MainController.changeToClientInfoMode(activeClient, sessionKey);
        } catch (ParseException ex) {
            Logger.getLogger(QueryClientController.class.
                    getName()).
                    log(Level.SEVERE,
                            null,
                            ex);
        }
    }
}
