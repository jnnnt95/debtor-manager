package control;

import model.Debt;
import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.IO.Writer;
import view.pop_up_view.PerformPaymentView;

/**
 *
 * @author admin
 */
public class PerformPaymentController {

    private final PerformPaymentView view;
    private Client currentClient;
    private final String sessionKey;

    public PerformPaymentController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new PerformPaymentView(this, sessionKey);
        view.updateView();
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setViewData(Client currentClient) {
        verifySession();
        this.currentClient = currentClient;

        view.clear();
        setToday();
        view.setClientIdentification();
        view.setClientNotPaidBalance();
        view.setWarningLabel();
    }

    public void performPayment() throws IOException, ParseException, ClassNotFoundException, SQLException {
        MainController.authenticate(sessionKey);
        if (noFieldsEmpty()) {
            if (currentClient.getTotalNotPaidBalance() > 0) {
                int amount;
                try {
                    amount = Integer.parseInt(view.getNewDebtAmount());
                    if (amount <= 0) {
                        throw new NumberFormatException("debe ser mayor a cero");
                    }
                    if (amount > currentClient.getTotalNotPaidBalance()) {
                        throw new NumberFormatException("es mayor a la deuda del cliente");
                    }
                    if ((amount % 50) != 0) {
                        throw new NumberFormatException("revisar monto");
                    }
                    view.setVisible(false);
                    switch (JOptionPane.
                            showConfirmDialog(null, "Se cobrarán $" + MainController.
                                    formatAmount(amount) + "\n\n¿Continuar?")) {
                        case 0:
                            pay(amount);
                            JOptionPane.
                                    showMessageDialog(null, "Nuevo saldo para " + currentClient.
                                            getName() + ":\n\n" + "     $" + MainController.
                                                    formatAmount(currentClient.
                                                            getTotalNotPaidBalance()));
                            MainController.changeToClientInfoMode(currentClient,
                                    sessionKey);
                            break;
                        case 1:
                            JOptionPane.
                                    showMessageDialog(null, "Ingrese un monto válido");
                            break;
                        case 2:
                            JOptionPane.
                                    showMessageDialog(null, "Cobro cancelado");
                            MainController.changeToClientInfoMode(currentClient,
                                    sessionKey);
                            break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.
                            showMessageDialog(null, "El valor ingresado no es válido, " + e.getMessage());
                    view.setFocusOnAmount();
                }
            } else {
                JOptionPane.
                        showMessageDialog(null, currentClient.getName() + " está a paz y salvo: no hay nada que cobrar");
            }
        }
    }

    private void pay(int amount) throws IOException, ClassNotFoundException, SQLException {
        MainController.authenticate(sessionKey);
        String date;
        date = view.getNewDebtDate();
        
        Writer.recordPayment(currentClient.getId(), amount, date);

        for (Debt debt : currentClient.getDebts()) {
            if (!debt.isPaid() && amount > 0) {
                int debitAssessment;
                debitAssessment = amount - debt.getTotalDebt();
                if (debitAssessment > 0) {
                    debt.updateDebt(debt.getTotalDebt(), date);
                    amount = debitAssessment;

                } else {
                    debt.updateDebt(amount, date);
                    amount = 0;
                }
                Writer.modifyDebt(debt);
            }
        }
    }

    private boolean noFieldsEmpty() {
        if (view.getNewDebtAmount().
                length() <= 0) {
            JOptionPane.
                    showMessageDialog(null, "El campo de monto no puede estar vacío");
            view.setFocusOnAmount();
            return false;
        }
        if (view.getNewDebtDate().
                length() <= 0) {
            JOptionPane.
                    showMessageDialog(null, "El campo de fecha no puede estar vacío");
            view.setFocusOnDate();
            return false;
        }
        return true;
    }

    private void setToday() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        view.setDate(dateFormat.format(date));
    }

    public PerformPaymentView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
