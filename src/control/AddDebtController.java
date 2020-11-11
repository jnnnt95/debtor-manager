package control;

import model.Debt;
import model.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.List;
import model.IO.Reader;
import model.IO.Writer;
import view.pop_up_view.AddDebtView;

/**
 *
 * @author admin
 */
public class AddDebtController {

    private final AddDebtView view;
    private Client currentClient;
    private double mean;
    private double standardDeviation;
    private final String sessionKey;
    private final SimpleDateFormat dateFormater;
    private final Calendar calendar;
    private final Date today;

    public AddDebtController(String sessionKey) {
        this.sessionKey = sessionKey;
        view = new AddDebtView(this, sessionKey);
        view.updateView();
        view.clear();
        dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();
        today = new Date();
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setViewData(Client currentClient) {
        verifySession();
        this.currentClient = currentClient;
        mean = this.currentClient.getMean();
        standardDeviation = this.currentClient.getStandardDeviation();

        view.clear();
        setToday();
        view.setClientIdentification();
        view.setClientNotPaidBalance();
        view.setWarningLabel();
    }

    public void addOneDayToDate() throws ParseException {
        calendar.setTime(dateFormater.parse(view.getDate()));
        calendar.add(Calendar.DATE, 1);
        view.setDate(dateFormater.format(calendar.getTime()));
    }

    public void removeOneDayToDate() throws ParseException {
        calendar.setTime(dateFormater.parse(view.getDate()));
        calendar.add(Calendar.DATE, -1);
        view.setDate(dateFormater.format(calendar.getTime()));
    }

    public void addDebt() throws IOException, ParseException, ClassNotFoundException, SQLException {
        if (noFieldsEmpty()) {
            try {
                int newDebtAmount;
                String date;
                Debt newDebt;
                DateFormat dateFormat;
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                newDebtAmount = Integer.parseInt(view.getNewDebtAmount());
                if (newDebtAmount <= 0) {
                    throw new NumberFormatException("el monto debe ser mayor que cero");
                }
                if ((newDebtAmount % 50) != 0) {
                    throw new NumberFormatException("revisar monto");
                }
                date = dateFormat.format(dateFormat.parse(view.getDate()));

                if ((newDebtAmount + getThisMonthBalance()) > (mean + standardDeviation)) {
                    JOptionPane.
                            showMessageDialog(null, "El saldo del mes para este cliente sobrepasó el límite recomendado...", "Aviso: sobrepasando saldo recomendado", JOptionPane.WARNING_MESSAGE, null);
                }
                newDebt = new Debt(
                        Reader.getNewDebtId(),
                        currentClient.getId(),
                        newDebtAmount,
                        0,
                        date,
                        null,
                        MainController.getUser().
                                getName(),
                        MainController.getUser().
                                getId()
                );
                Writer.addDebt(newDebt, currentClient);
                currentClient.getDebts().
                        add(newDebt);
                currentClient.sortDebts();
                currentClient.update();
                JOptionPane.
                        showMessageDialog(null, "Nuevo saldo para " + currentClient.
                                getName() + ", " + currentClient.getNick() + ":\n\n     $" + MainController.
                                formatAmount(currentClient.
                                        getTotalNotPaidBalance()));
                MainController.changeToClientInfoMode(currentClient,
                        sessionKey);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor no válido, " + e.getMessage());
                view.setFocusOnAmount();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Fecha no válida");
                view.setFocusOnDate();
            }
        }
    }

    private boolean noFieldsEmpty() {
        if (view.getNewDebtAmount().
                length() <= 0) {
            JOptionPane.showMessageDialog(null, "El campo de monto no puede estar vacío");
            view.setFocusOnAmount();
            return false;
        }
        if (view.getNewDebtDate().
                length() <= 0) {
            JOptionPane.showMessageDialog(null, "El campo de fecha no puede estar vacío");
            view.setFocusOnDate();
            return false;
        }
        return true;
    }

    private void setToday() {
        view.setDate(dateFormater.format(today));
    }

    public AddDebtView getView() {
        return view;
    }

    private int getThisMonthBalance() {
        int thisMonthBalance;
        thisMonthBalance = 0;

        SimpleDateFormat formater;
        formater = new SimpleDateFormat("MM");

        int thisMonth;
        thisMonth = Integer.parseInt(formater.format(new Date()));

        for (Debt debt : currentClient.getDebts()) {
            if ((Integer.parseInt(debt.getCreationDate().
                    substring(3, 5)) == thisMonth) && (!debt.isPaid())) {
                thisMonthBalance += debt.getBalance();
            }
        }

        return thisMonthBalance;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }
}
