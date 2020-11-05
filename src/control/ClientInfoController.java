package control;

import model.Debt;
import model.Client;
import java.text.ParseException;
import java.util.List;
import view.full_size_view.ClientInfoView;

/**
 *
 * @author admin
 */
public class ClientInfoController {

    private final ClientInfoView view;
    private Client currentClient;
    private List<Debt> debts;
    private final String sessionKey;

    public ClientInfoController(String sessionKey)
            throws ParseException {
        this.sessionKey = sessionKey;
        view = new ClientInfoView(this, sessionKey);
        view.updateView();
        verifySession();
    }

    public void setViewData(Client client)
            throws ParseException {
        verifySession();
        client.sortDebts();
        this.currentClient = client;
        this.debts = client.getDebts();

        view.setInfoData();
    }

    public void modifyClient()
            throws ParseException {
    }

    public ClientInfoView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public Client getCurrentClient() {
        return currentClient;
    }
}
