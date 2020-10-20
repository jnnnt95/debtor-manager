package model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author admin
 */
public class Reader {
    public Reader() {

    }
    
    public static void openExportedData() throws IOException {
        Desktop.getDesktop().open(new File("C:\\Debtor Manager\\printable data.xls"));
    }

    /*
    public static List<Client> getClients() throws IOException, ParseException {
        List<String> rawClients = getRawClients();
        List<Client> clients;
        clients = new ArrayList<>();

        if(updateNecessary()) {
            for (String rawClient : rawClients) {
                String[] filteredData;
                filteredData = dataFilter(rawClient);
                Client client;

                if(filteredData.length < 4) {
                    client = new Client(
                            Integer.parseInt(filteredData[0]),
                            filteredData[1],
                            filteredData[2],
                            "",
                            getClientDebts(Integer.parseInt(filteredData[0]))
                    );
                }
                else {
                    client = new Client(
                            Integer.parseInt(filteredData[0]),
                            filteredData[1],
                            filteredData[2],
                            filteredData[3],
                            getClientDebts(Integer.parseInt(filteredData[0]))
                    );
                }
                clients.add(client);
            }
            Writer.writeUpdatedClients(clients);
        }
        else {
            for (String rawClient : rawClients) {
                String[] filteredData;
                filteredData = dataFilter(rawClient);
                Client client;

                client = new Client(
                        Integer.parseInt(filteredData[0]),
                        filteredData[1],
                        filteredData[2],
                        filteredData[3],
                        getClientDebts(Integer.parseInt(filteredData[0]))
                );
                clients.add(client);
            }
        }

        return clients;
    }*/
    
    public static List<Client> getClients() throws IOException, ParseException, ClassNotFoundException, SQLException {
        ResultSet rawClients = getRawClients();
        List<Client> clients;
        clients = new ArrayList<>();
        
        while(rawClients.next()) {
            Client client;

            client = new Client(
                    rawClients.getInt(1),
                    rawClients.getString(2),
                    rawClients.getString(3),
                    rawClients.getString(4),
                    rawClients.getString(5),
                    getClientDebts(rawClients.getInt(1))
            );
            clients.add(client);
        }

        return clients;
    }
    
    /*public static boolean updateNecessary() throws IOException {
        List<String> samples;
        samples = getRawClients();
        String counter[];
        for(String sample: samples) {
            counter = sample.split(Pattern.quote("|"));
            if (counter.length < 4) {
                return true;
            }
        }
        return false;
    }*/


    /*private static String[] dataFilter(String data) {
        String[] returnable;
        returnable = data.split(Pattern.quote("|"));
        return returnable;
    }*/

    /*private static List<String> getRawClients() throws IOException {
        return Files.readAllLines(Paths.get("Data\\clients"));
    }*/
    
    private static ResultSet getRawClients() throws IOException, ClassNotFoundException, SQLException {
        List<String> rawClients;
        rawClients = new ArrayList<>();
        
        String query;
        query = "SELECT id, name, nick, cpnumber, area FROM clients";
        
        ResultSet result = DataDBConnection.
                getConnection().
                createStatement().
                executeQuery(query);
        
        return result;
    }

    /*private static List<Debt> getClientDebts(int clientId) throws IOException {
        List<String> rawDebts = getRawDebts(clientId);
        List<Debt> debts;
        debts = new ArrayList<>();

        for (String rawDebt : rawDebts) {
            String[] filteredData;
            filteredData = dataFilter(rawDebt);
            Debt debt;
            debt = new Debt(
                    //Client id
                    clientId,
                    //Balance
                    Integer.parseInt(filteredData[0]),
                    //Deposit
                    Integer.parseInt(filteredData[1]),
                    //Debt date
                    filteredData[2]
            );
            debts.add(debt);
        }

        return debts;
    }*/
    
    private static List<Debt> getClientDebts(int clientId) throws IOException, ClassNotFoundException, SQLException {
        ResultSet rawDebts = getRawDebts(clientId);
        List<Debt> debts;
        debts = new ArrayList<>();
        
        while(rawDebts.next()) {
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
                    rawDebts.getString(4)
            );
            debts.add(debt);
        }
        
        return debts;
    }
    
    public static int getNewClientId() throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        String query;
        query = "SELECT MAX(id) FROM clients";
        
        PreparedStatement statement;
        statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        
        result.next();
        
        return result.getInt(1) + 1;
    }
    
    public static int getNewDebtId() throws ClassNotFoundException, SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        String query;
        query = "SELECT MAX(id) FROM debts";
        
        PreparedStatement statement;
        statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        
        result.next();
        
        return result.getInt(1) + 1;
    }

    /*private static List<String> getRawDebts(int clientId) throws IOException {
        List<String> rawDebts;
        rawDebts = new ArrayList<>();

        try {
            rawDebts = Files.readAllLines(Paths.get("Data\\debts\\" + clientId));
        } catch (java.nio.file.NoSuchFileException ex) {
            Writer.writeClientDebts(new ArrayList<>(), clientId);
        }

        return rawDebts;
    }*/
    
    private static ResultSet getRawDebts(int clientId) throws IOException, ClassNotFoundException, SQLException {
        List<String> rawDebts;
        rawDebts = new ArrayList<>();

        String query;
        query =   "SELECT id, balance, deposit, date "
                + "FROM debts "
                + "WHERE id_client = ?";
        
        PreparedStatement statement;
        statement = DataDBConnection.getConnection().prepareStatement(query);
        
        statement.setInt(1, clientId);
        
        ResultSet result = statement.executeQuery();
        
        return result;
    }
}
