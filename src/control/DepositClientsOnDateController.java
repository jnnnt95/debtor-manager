package control;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.IO.Reader;
import view.full_size_view.DepositClientsOnDateView;

/**
 *
 * @author admin
 */
public class DepositClientsOnDateController {

    private final DepositClientsOnDateView view;
    private final String sessionKey;
    private final SimpleDateFormat dateFormater;
    private final Calendar calendar;
    private final Date today;

    public DepositClientsOnDateController(String sessionKey)
            throws ParseException {
        this.sessionKey = sessionKey;
        view = new DepositClientsOnDateView(this, sessionKey);
        view.updateView();
        dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();
        today = new Date();
        view.setDate(dateFormater.format(today));
        verifySession();
    }

    public void setViewData()
            throws ParseException {
        verifySession();
        view.setInfoData(view.getDate());
    }

    public void modifyClient()
            throws ParseException {
    }

    public DepositClientsOnDateView getView() {
        return view;
    }

    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public void setToday() {
        view.setDate(dateFormater.format(today));
    }

    public void addOneDayToDate() throws ParseException {
        verifyDate(view.getDate());
        calendar.setTime(dateFormater.parse(view.getDate()));
        calendar.add(Calendar.DATE, 1);
        view.setDate(dateFormater.format(calendar.getTime()));
        view.setFocusOnDate();
    }

    public void removeOneDayToDate() throws ParseException{
        verifyDate(view.getDate());
        calendar.setTime(dateFormater.parse(view.getDate()));
        calendar.add(Calendar.DATE, -1);
        view.setDate(dateFormater.format(calendar.getTime()));
        view.setFocusOnDate();
    }
    
    public ArrayList<String[]> getDepositClients(String date) throws ClassNotFoundException, SQLException {
        return Reader.getDepositClients(date);
    }
    
    public void verifyDate(String date) {
        try {
            dateFormater.parse(date);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Fecha no válida, la operación se realizará sobre el día de hoy");
            setToday();
            view.setFocusOnDate();
        }
    }
}
