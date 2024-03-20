package model.IO;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Client;
import model.DataDBConnection;
import model.Debt;
import model.User;

/**
 *
 * @author admin
 */
public class Reader {

    private static final String ROOT_DIR = "Data";

    public static ArrayList<String[]> getDepositClients(String date) throws ClassNotFoundException, SQLException {

        ArrayList<String[]> depositClients;
        depositClients = new ArrayList<>();
        ResultSet rawDepositClients = getRawDepositClients(date);

        while (rawDepositClients.next()) {
            String[] data = new String[5];
            data[0] = rawDepositClients.getString(1);
            data[1] = rawDepositClients.getString(2);
            data[2] = rawDepositClients.getString(3);
            data[3] = rawDepositClients.getString(4);
            data[4] = rawDepositClients.getString(5);

            depositClients.add(data);
        }

        return depositClients;
    }

    private Reader() {

    }

    public static void openExportedData()
            throws IOException {
        Desktop.getDesktop().
                open(new File(ROOT_DIR
                        + "\\printable data.xls"));
    }

    public static List<Client> getClients()
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {
        ResultSet rawClients = getRawClients();
        List<Client> clients;
        clients = new ArrayList<>();

        while (rawClients.next()) {
            Client client;

            client = new Client(
                    rawClients.getInt(1),
                    rawClients.getString(2),
                    rawClients.getString(3),
                    rawClients.getString(4),
                    rawClients.getString(5),
                    getClientDebts(rawClients.getInt(1)),
                    rawClients.getString(6),
                    rawClients.getInt(7)
            );
            clients.add(client);
        }

        return clients;
    }

    private static ResultSet getRawClients()
            throws IOException,
            ClassNotFoundException,
            SQLException {
        String query;
        query = "SELECT "
                + "clients.id, "
                + "clients.name, "
                + "clients.nick, "
                + "clients.cpnumber, "
                + "clients.area, "
                + "users.name, "
                + "clients.created_by "
                + "FROM "
                + "clients, "
                + "users "
                + "WHERE "
                + "clients.created_by = users.id";

        ResultSet result = DataDBConnection.
                getConnection().
                createStatement().
                executeQuery(query);

        return result;
    }

    private static List<Debt> getClientDebts(int clientId)
            throws IOException,
            ClassNotFoundException,
            SQLException {
        ResultSet rawDebts = getRawDebts(clientId);
        List<Debt> debts;
        debts = new ArrayList<>();

        while (rawDebts.next()) {
            Debt debt;
            debt = new Debt(
                    //Id
                    rawDebts.getInt(1),
                    //Client id
                    clientId,
                    //Balance
                    rawDebts.getInt(2),
                    //Deposit
                    rawDebts.getInt(3),
                    //Debt date
                    rawDebts.getString(4),
                    //paid_date for new: null
                    rawDebts.getString(5),
                    //created by
                    rawDebts.getString(6),
                    //creator id
                    rawDebts.getInt(7)
            );
            debts.add(debt);
        }

        return debts;
    }

    public static int getNewClientId()
            throws SQLException,
            ClassNotFoundException {
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "SELECT MAX(id) from (SELECT MAX(id) id FROM clients union all SELECT MAX(id) id FROM disabled_clients)";

        PreparedStatement statement;
        statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();

        result.next();

        return result.getInt(1) + 1;
    }

    public static int getNewDebtId()
            throws ClassNotFoundException,
            SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "SELECT MAX(id) FROM debts";

        PreparedStatement statement;
        statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();

        result.next();

        return result.getInt(1)
                + 1;
    }

    private static ResultSet getRawDebts(int clientId)
            throws IOException,
            ClassNotFoundException,
            SQLException {
        String query;
        query = "SELECT "
                + "debts.id, "
                + "debts.balance, "
                + "debts.deposit, "
                + "debts.date, "
                + "debts.paid_date, "
                + "users.name, "
                + "debts.created_by "
                + "FROM "
                + "debts, "
                + "users "
                + "WHERE "
                + "debts.id_client = ? "
                + "AND "
                + "users.id = debts.created_by";

        PreparedStatement statement;
        statement = DataDBConnection.getConnection().
                prepareStatement(query);

        statement.setInt(1,
                clientId);

        ResultSet result = statement.executeQuery();

        return result;
    }

    private static ResultSet getRawUser(String username,
            String password)
            throws IOException,
            ClassNotFoundException,
            SQLException {
        String query;
        query = "SELECT * "
                + "FROM users "
                + "WHERE (username = '"
                + username
                + "' AND password = '"
                + password
                + "')";

        Statement statement;
        statement = DataDBConnection.getConnection().
                createStatement();

        ResultSet result = statement.executeQuery(query);

        return result;
    }

    public static User getUser(String username,
            String password)
            throws IOException,
            ClassNotFoundException,
            SQLException {
        ResultSet rawUser = getRawUser(username,
                password);
        User user;

        if (rawUser.next()) {
            user = new User(
                    //Id
                    rawUser.getInt(1),
                    //username
                    rawUser.getString(2),
                    //phone
                    rawUser.getString(4),
                    //date created
                    rawUser.getString(5),
                    //type
                    rawUser.getString(6),
                    //name
                    rawUser.getString(7)
            );
        } else {
            return null;
        }

        return user;
    }

    private static ResultSet getRawDepositClients(String date) throws ClassNotFoundException, SQLException {
        String query;
        query
                = "SELECT client_name, client_nick, sum(deposits.amount) AS 'total_deposit_made', total_debt, users.name AS 'user_registered' "
                + "FROM "
                +   "("
                +   "SELECT clients.id AS 'client_id', clients.name AS 'client_name', clients.nick AS 'client_nick', sum(debts.balance - debts.deposit) AS 'total_debt' "
                +   "FROM debts, clients "
                +   "WHERE clients.id = debts.id_client "
                +   "GROUP BY clients.id"
                +   "), "
                +   "deposits, users "
                + "WHERE (client_id = deposits.id_client) AND (date = '" + date + "') AND (users.id = deposits.received_by) GROUP BY client_id";

        ResultSet result = DataDBConnection.
                getConnection().
                createStatement().
                executeQuery(query);

        return result;
    }

    public static String getTodaysPaymentsBalance() throws ClassNotFoundException, SQLException {
        DecimalFormat amountFormater = new DecimalFormat("###,###.##");
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        String todayDate = formater.format(new Date());

        ResultSet rawBalance;
        rawBalance = getTodaysPaymentsBalanceRawData(todayDate);

        int valueBalance = rawBalance.getInt(1);

        String balance = "<html><strong>Dinero en caja por cobros <u>" + todayDate + "</u>:</strong><br><br>";
        balance += "    <i>$ " + amountFormater.format(valueBalance) + "</i>";
        balance += "</html>";
        return balance;
    }

    private static ResultSet getTodaysPaymentsBalanceRawData(String date) throws ClassNotFoundException, SQLException {
        String query;
        query = "SELECT SUM(amount) "
                + "FROM deposits WHERE deposits.date = '" + date.
                        trim() + "'";

        Statement statement;
        statement = DataDBConnection.getConnection().
                createStatement();

        ResultSet result = statement.executeQuery(query);

        return result;
    }

    public static String getBusinessBriefing() throws SQLException, ClassNotFoundException {
        ResultSet rawBriefing = getBusinessBriefingRawData();
        DecimalFormat amountFormater = new DecimalFormat("###,###.##");

        String briefing;
        briefing = "<html>"
                + "<strong><span style='color:green'>Total recaudado:</span> $ " + amountFormater.
                        format(rawBriefing.getInt(1)) + "</strong><br>"
                + "<strong><span style='color:red'>Total por cobrar:</span> $ " + amountFormater.
                        format(rawBriefing.getInt(2)) + "</strong><br>"
                + "</html>";

        return briefing;
    }

    private static ResultSet getBusinessBriefingRawData() throws ClassNotFoundException, SQLException {
        String query;
        query = "SELECT SUM(debts.deposit), SUM(debts.balance - debts.deposit) FROM debts";

        Statement statement;
        statement = DataDBConnection.getConnection().
                createStatement();

        ResultSet result = statement.executeQuery(query);

        return result;
    }

    public static String getUserInfo(int userId) throws SQLException, ClassNotFoundException {
        ResultSet rawUserInfo = getUserInfoRawData(userId);

        String userInfo;
        userInfo = "<html>"
                + "<strong><i>Información del usuario actual</i></strong><br><br>"
                + "<i><strong><span style='color:gray'>Nombre:</span></strong> " + rawUserInfo.
                        getString(1) + "<i><br>"
                + "<i><strong><span style='color:gray'>Nombre de usuario:</span></strong> " + rawUserInfo.
                        getString(2) + "<i><br>"
                + "<i><strong><span style='color:gray'>Teléfono:</span></strong> " + rawUserInfo.
                        getString(3) + "<i><br>"
                + "<i><strong><span style='color:gray'>Fecha de creación de usuario:</span></strong> " + rawUserInfo.
                        getString(4) + "<i><br>"
                + "<i><strong><span style='color:gray'>Tipo de usuario:</span></strong> " + rawUserInfo.
                        getString(5) + "<i><br>"
                + "</html>";

        return userInfo;
    }

    private static ResultSet getUserInfoRawData(int userId) throws ClassNotFoundException, SQLException {
        String query;
        query = "SELECT name, username, phone, date_created, type FROM users WHERE id = " + userId;

        Statement statement;
        statement = DataDBConnection.getConnection().
                createStatement();

        ResultSet result = statement.executeQuery(query);

        return result;
    }

    public static String getUsersCredentials() throws SQLException, ClassNotFoundException {
        ResultSet rawUsersCredentials = getUsersCredentialsRawData();

        String userInfoCredentials = "<html><strong>Credenciales de usuarios no administradores</strong>:<br><br>";

        int c = 0;
        while (rawUsersCredentials.next()) {
            c++;
            userInfoCredentials += "<i><strong><span style='color:gray'>" + c + ") </span></strong> "
                    + rawUsersCredentials.getString(1) + "<i>: |" + rawUsersCredentials.
                    getString(2) + "|" + rawUsersCredentials.getString(3) + "<br>";
        }

        return userInfoCredentials;
    }

    private static ResultSet getUsersCredentialsRawData() throws ClassNotFoundException, SQLException {
        String query;
        query = "SELECT name, username, password FROM users WHERE type != 'administrator'";

        Statement statement;
        statement = DataDBConnection.getConnection().
                createStatement();

        ResultSet result = statement.executeQuery(query);

        return result;
    }
}
