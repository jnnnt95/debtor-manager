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
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.*;
import utils.Action;

/**
 *
 * @author admin
 */
public class Reader {

    private Reader() {}

    public static void openExportedData()
            throws IOException {
        Desktop.getDesktop().
                open(new File("Data\\printable data.xls"));
    }

    // TODO ArrayList of String arrays should be replaced by actual DepositClient new Entity.
    public static ArrayList<String[]> getDepositClients(String date) throws ClassNotFoundException, SQLException {

        ArrayList<String[]> depositClients = new ArrayList<>();

        String query
                = "SELECT client_name, client_nick, sum(deposits.amount) AS 'total_deposit_made', total_debt, users.name AS 'user_registered' "
                + "FROM "
                +   "("
                +   "SELECT clients.id AS 'client_id', clients.name AS 'client_name', clients.nick AS 'client_nick', sum(debts.balance - debts.deposit) AS 'total_debt' "
                +   "FROM debts, clients "
                +   "WHERE clients.id = debts.id_client "
                +   "GROUP BY clients.id"
                +   ") client_aggregated_data, "
                +   "deposits, users "
                + "WHERE (client_id = deposits.id_client) AND (date = ?) AND (users.id = deposits.received_by) GROUP BY client_id";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setString(1, date);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        Action resultAction = o -> {
            ResultSet rawClients = (ResultSet) o;
            try {
                while (rawClients.next()) {
                    String[] data = new String[5];
                    data[0] = rawClients.getString(1);
                    data[1] = rawClients.getString(2);
                    data[2] = rawClients.getString(3);
                    data[3] = rawClients.getString(4);
                    data[4] = rawClients.getString(5);

                    depositClients.add(data);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();

        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        return depositClients;
    }

    public static Client getClientById(int id)
            throws IOException,
            ParseException,
            ClassNotFoundException,
            SQLException {

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();
        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        Action resultAction = o -> {
            ResultSet rawClients = (ResultSet) o;
            try {
                if (rawClients.next()) {
                    clientBuilder
                            .withId(rawClients.getInt(1))
                            .withName(rawClients.getString(2))
                            .withNick(rawClients.getString(3))
                            .withCpNumber(rawClients.getString(4))
                            .withArea(rawClients.getString(5))
                            .withDebts(getClientActiveDebts(rawClients.getInt(1)))
                            .withCreator(rawClients.getString(6))
                            .withCreatorId(rawClients.getInt(7))
                            .setDone();
                }
            } catch (SQLException | IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
        String query =
                "SELECT " +
                        "    clients.id, " +
                        "    clients.name, " +
                        "    nick, " +
                        "    cpnumber, " +
                        "    area, " +
                        "    users.name, " +
                        "    created_by " +
                        "from clients inner join users on clients.id = ? and clients.created_by = users.id";

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        return clientBuilder.build();
    }

    public static List<ClientDataWithTotalizedDebts> getClientDataWithTotalizedDebts()
            throws IOException,
            ClassNotFoundException,
            SQLException {

        List<ClientDataWithTotalizedDebts> clientsData = new ArrayList<>();
        String query = "select " +
                "    clients.id, " +
                "    nick, " +
                "    clients.name, " +
                "    cpnumber, " +
                "    area, " +
                "    users.name as created_by, " +
                "    coalesce(totalized_debts.debt, 0) totalized_debt, " +
                "    coalesce(totalized_default_debts.debt, 0) totalized_default_debt " +
                "from " +
                "    clients " +
                "        inner join users on clients.created_by = users.id " +
                "        left join ( " +
                "            select " +
                "                id_client, " +
                "                (sum(balance) - sum(deposit)) debt " +
                "            from debts " +
                "            where " +
                "                balance <> deposit " +
                "            group by id_client " +
                "        ) totalized_debts on clients.id = totalized_debts.id_client " +
                "        left join ( " +
                "            select " +
                "                id_client, " +
                "                (sum(balance) - sum(deposit)) debt " +
                "            from debts " +
                "            where " +
                "                balance <> deposit AND " +
                "                (substr(date,7)||'-'||substr(date,4,2)||'-'||substr(date,1,2)) < DATE('now', '-30 day') " +
                "            group by id_client " +
                "        ) totalized_default_debts on clients.id = totalized_default_debts.id_client " +
                "order by  clients.id";

        Action preparationAction = o -> {};

        Action resultAction = o -> {
            ResultSet rawClientsData = (ResultSet) o;
            try {
                while (rawClientsData.next()) {
                    ClientDataWithTotalizedDebts clientData = new ClientDataWithTotalizedDebts(
                            rawClientsData.getInt(1),
                            rawClientsData.getString(2),
                            rawClientsData.getString(3),
                            rawClientsData.getString(4),
                            rawClientsData.getString(5),
                            rawClientsData.getString(6),
                            rawClientsData.getInt(7),
                            rawClientsData.getInt(8)
                    );
                    clientsData.add(clientData);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        return clientsData;
    }

    public static List<Debt> getClientAllDebts(int clientId)
            throws IOException,
            ClassNotFoundException,
            SQLException {

        List<Debt> debts = new ArrayList<>();

        String query = "SELECT "
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

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, clientId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        Action resultAction = o -> {
            ResultSet rawDebts = (ResultSet) o;
            try {
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        return debts;
    }

    private static List<Debt> getClientActiveDebts(int clientId)
            throws IOException,
            ClassNotFoundException,
            SQLException {


        List<Debt> debts = new ArrayList<>();

        String query = "SELECT "
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
                + "balance <> deposit AND "
                + "users.id = debts.created_by";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, clientId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        Action resultAction = o -> {
            ResultSet rawDebts = (ResultSet) o;
            try {
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        return debts;
    }

    public static int getNewClientId()
            throws SQLException,
            ClassNotFoundException {

        StringBuilder indexSb = new StringBuilder();
        String query = "SELECT MAX(id) from (SELECT MAX(id) id FROM clients union all SELECT MAX(id) id FROM disabled_clients) as cidci";

        Action preparationAction = o -> {};

        Action resultAction = o -> {
            ResultSet result = (ResultSet) o;
            try {
                if(result.next()) {
                    indexSb.append(result.getInt(1) +1);
                } else {
                    throw new RuntimeException("No fue posible crear un id para el nuevo usuario.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        return Integer.parseInt(indexSb.toString());
    }

    public static int getNewDebtId()
            throws ClassNotFoundException,
            SQLException {

        StringBuilder indexSb = new StringBuilder();
        String query = "SELECT MAX(id) FROM debts";

        Action preparationAction = o -> {};

        Action resultAction = o -> {
            ResultSet result = (ResultSet) o;
            try {
                if(result.next()) {
                    indexSb.append(result.getInt(1) +1);
                } else {
                    throw new RuntimeException("No fue posible crear un id para el nuevo usuario.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        return Integer.parseInt(indexSb.toString());
    }

    public static User getUser(String username, String password) {

        User.UserBuilder userBuilder = new User.UserBuilder();
        String query = "SELECT * "
                + "FROM users "
                + "WHERE (username = ? AND password = ?)";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setString(1, username);
                stmt.setString(2, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        Action resultAction = o -> {
            ResultSet rawUser = (ResultSet) o;
            try {
                if (rawUser.next()) {
                    userBuilder
                            .withId(rawUser.getInt(1))
                            .withUsername(rawUser.getString(2))
                            .withPhone(rawUser.getString(4))
                            .withDateCreated(rawUser.getString(5))
                            .withType(rawUser.getString(6))
                            .withName(rawUser.getString(7))
                            .setDone();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        try {
            return userBuilder.build();
        } catch (RuntimeException e){
            return null;
        }
    }

    public static String getTodaysPaymentsBalance() throws ClassNotFoundException, SQLException {

        DecimalFormat amountFormater = new DecimalFormat("###,###.##");
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        String todayDate = formater.format(new Date());

        String query = "SELECT SUM(amount) "
                + "FROM deposits WHERE deposits.date = ?";

        StringBuilder amountSb = new StringBuilder();

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setString(1, todayDate.trim());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        Action resultAction = o -> {
            ResultSet result = (ResultSet) o;
            try {
                if(result.next()) {
                    amountSb.append(result.getInt(1));
                } else {
                    throw new RuntimeException("No fue posible determinar la cantidad recaudada del día.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };


        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();

        dbop.run();

        String balance = "<html><strong>Dinero en caja por cobros <u>" + todayDate + "</u>:</strong><br><br>";
        balance += "    <i>$ " + amountFormater.format(Integer.parseInt(amountSb.toString())) + "</i>";
        balance += "</html>";
        return balance;
    }

    public static String getBusinessBriefing() throws SQLException, ClassNotFoundException {

        StringBuilder totalCollected = new StringBuilder();
        StringBuilder totalToCollect = new StringBuilder();

        String query = "SELECT SUM(debts.deposit), SUM(debts.balance - debts.deposit) FROM debts";
        DecimalFormat amountFormater = new DecimalFormat("###,###.##");

        Action preparationAction = o -> {};

        Action resultAction = o -> {
            ResultSet result = (ResultSet) o;
            try {
                if(result.next()) {
                    totalCollected.append(result.getInt(1));
                    totalToCollect.append(result.getInt(2));
                } else {
                    throw new RuntimeException("No fue posible determinar las cantidades recaudadas y por recaudar.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        return "<html>"
                + "<strong><span style='color:green'>Total recaudado:</span> $ " + amountFormater.
                        format(Integer.parseInt(totalCollected.toString())) + "</strong><br>"
                + "<strong><span style='color:red'>Total por cobrar:</span> $ " + amountFormater.
                        format(Integer.parseInt(totalToCollect.toString())) + "</strong><br>"
                + "</html>";
    }

    public static String getUserInfo(int userId) throws SQLException, ClassNotFoundException {

        StringBuilder nameSb = new StringBuilder();
        StringBuilder usernameSb = new StringBuilder();
        StringBuilder phoneSb = new StringBuilder();
        StringBuilder userCreationDateSb = new StringBuilder();
        StringBuilder userTypeSb = new StringBuilder();

        String query = "SELECT name, username, phone, date_created, type FROM users WHERE id = ?";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        Action resultAction = o -> {
            ResultSet result = (ResultSet) o;
            try {
                if(result.next()) {
                    nameSb.append(result.getString(1));
                    usernameSb.append(result.getString(2));
                    phoneSb.append(result.getString(3));
                    userCreationDateSb.append(result.getString(4));
                    userTypeSb.append(result.getString(5));
                } else {
                    throw new RuntimeException("No fue posible traer la información del usuario.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        return "<html>"
                + "<strong><i>Información del usuario actual</i></strong><br><br>"
                + "<i><strong><span style='color:gray'>Nombre:</span></strong> " + nameSb + "<i><br>"
                + "<i><strong><span style='color:gray'>Nombre de usuario:</span></strong> " + usernameSb + "<i><br>"
                + "<i><strong><span style='color:gray'>Teléfono:</span></strong> " + phoneSb + "<i><br>"
                + "<i><strong><span style='color:gray'>Fecha de creación de usuario:</span></strong> " + userCreationDateSb + "<i><br>"
                + "<i><strong><span style='color:gray'>Tipo de usuario:</span></strong> " + userTypeSb + "<i><br>"
                + "</html>";
    }

    public static String getUsersCredentials() throws SQLException, ClassNotFoundException {
        StringBuilder userInfoCredentialsSb = new StringBuilder();
        userInfoCredentialsSb.append("<html><strong>Credenciales de usuarios no administradores</strong>:<br><br>");

        String query = "SELECT name, username, password FROM users WHERE type != 'administrator'";

        Action preparationAction = o -> {};

        Action resultAction = o -> {
            ResultSet rawUsersCredentials = (ResultSet) o;
            int c = 0;
            try {
                while (rawUsersCredentials.next()) {
                    c++;
                    userInfoCredentialsSb
                            .append("<i><strong><span style='color:gray'>")
                            .append(c)
                            .append(") </span></strong> ")
                            .append(rawUsersCredentials.getString(1))
                            .append("<i>: |")
                            .append(rawUsersCredentials.getString(2))
                            .append("|")
                            .append(rawUsersCredentials.getString(3))
                            .append("<br>");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.SelectBuilder dbop = new DBIO.SelectBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withResultAction(resultAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        return userInfoCredentialsSb.toString();
    }
}
