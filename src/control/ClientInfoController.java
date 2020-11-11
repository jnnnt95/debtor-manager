package control;

import model.Client;
import java.text.ParseException;
import view.full_size_view.ClientInfoView;

/**
 *
 * @author admin
 */
public class ClientInfoController {

    private final ClientInfoView view;
    private Client currentClient;
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
