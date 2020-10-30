package model.IO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Client;
import model.DataDBConnection;
import model.Debt;

/**
 *
 * @author admin
 */
public class Writer {

    private static String rootDir = "Data";

    private Writer() {

    }

    public static void exportToExcel(Object[][] writableData)
            throws WriteException,
            IOException {
        String path;
        path = rootDir
                + "\\printable data.xls";

        WorkbookSettings configuration;
        configuration = new WorkbookSettings();
        configuration.setEncoding("ISO-8859-1");

        WritableWorkbook workbook;
        workbook = Workbook.createWorkbook(new File(path),
                configuration);

        WritableSheet sheet;
        sheet = workbook.createSheet("Clientes",
                0);

        WritableFont font;
        font = new WritableFont(WritableFont.ARIAL,
                10,
                WritableFont.BOLD);

        WritableCellFormat cellFormater;
        cellFormater = new WritableCellFormat(font);

        sheet.addCell(new Label(0,
                0,
                "Apodo",
                cellFormater));
        sheet.addCell(new Label(1,
                0,
                "Nombre",
                cellFormater));
        sheet.addCell(new Label(2,
                0,
                "Teléfono",
                cellFormater));
        sheet.addCell(new Label(3,
                0,
                "Área",
                cellFormater));
        sheet.addCell(new Label(4,
                0,
                "Saldo por pagar",
                cellFormater));
        sheet.addCell(new Label(5,
                0,
                "Deudor Moroso",
                cellFormater));

        font = new WritableFont(WritableFont.ARIAL,
                10,
                WritableFont.NO_BOLD);
        cellFormater = new WritableCellFormat(font);

        for (int i = 0;
                i
                < writableData.length;
                i++) {

            sheet.addCell(new Label(0,
                    i
                    + 1,
                    (String) writableData[i][0],
                    cellFormater));
            sheet.addCell(new Label(1,
                    i
                    + 1,
                    (String) writableData[i][1],
                    cellFormater));
            sheet.addCell(new Label(2,
                    i
                    + 1,
                    (String) writableData[i][2],
                    cellFormater));
            sheet.addCell(new Label(3,
                    i
                    + 1,
                    (String) writableData[i][3],
                    cellFormater));
            sheet.addCell(new Number(4,
                    i
                    + 1,
                    Double.parseDouble((String) writableData[i][4]),
                    cellFormater));

            if (((String) writableData[i][5]).equals("*")) {
                sheet.addCell(new Label(5,
                        i
                        + 1,
                        "Sí",
                        cellFormater));
            } else {
                sheet.addCell(new Label(5,
                        i
                        + 1,
                        "No",
                        cellFormater));
            }
        }

        //Set Column width
        int nickFilter = 0;
        int nameFilter = 0;
        int areaFilter = 0;

        for (int i = 0;
                i
                < writableData.length;
                i++) {
            if (((String) writableData[i][0]).length()
                    > nickFilter) {
                nickFilter = ((String) writableData[i][0]).length();
            }
            if (((String) writableData[i][1]).length()
                    > nameFilter) {
                nameFilter = ((String) writableData[i][1]).length();
            }
            if (((String) writableData[i][3]).length()
                    > areaFilter) {
                areaFilter = ((String) writableData[i][3]).length();
            }
        }

        nickFilter++;
        nameFilter++;
        areaFilter++;

        sheet.setColumnView(0,
                nickFilter);
        sheet.setColumnView(1,
                nameFilter);
        sheet.setColumnView(2,
                11);
        sheet.setColumnView(3,
                areaFilter);
        sheet.setColumnView(4,
                "Saldo por pagar".length()
                + 1);
        sheet.setColumnView(5,
                "Deudor Moroso".length()
                + 1);

        workbook.write();
        workbook.close();
    }

    public static void addClient(Client client)
            throws ClassNotFoundException,
            SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "INSERT INTO clients "
                + "(id, name, nick, cpnumber, area, created_by) "
                + "VALUES ("
                + client.getId()
                + ", '"
                + client.getName()
                + "', '"
                + client.getNick()
                + "', '"
                + client.getCPNumber()
                + "', '"
                + client.getArea()
                + "', "
                + client.getCreatorId()
                + ")";

        Statement statement;
        statement = connection.createStatement();

        if (client.getDebts().
                size()
                > 0) {
            addDebt(client.getDebts().
                    get(0),
                    client);
        }

        statement.execute(query);
    }

    public static void addDebt(Debt debt,
            Client client)
            throws ClassNotFoundException,
            SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "INSERT INTO debts "
                + "(id, id_client, balance, deposit, date, created_by) "
                + "VALUES ("
                + debt.getId()
                + ", "
                + client.getId()
                + ", "
                + debt.getBalance()
                + ", "
                + debt.getDeposit()
                + ", '"
                + debt.getCreationDate()
                + "', "
                + debt.getCreatorId()
                + ")";

        Statement statement;
        statement = connection.createStatement();

        statement.execute(query);
    }

    public static void modifyDebt(Debt debt)
            throws ClassNotFoundException,
            SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "UPDATE debts "
                + "SET deposit = "
                + debt.getDeposit();
        if (debt.getLastDepositDate()
                != null) {
            query += ", last_deposit_date = '"
                    + debt.getLastDepositDate() + "'";
        }
        if (debt.isPaid()) {
            query += ", paid_date = '"
                    + debt.getPaidDate()
                    + "'";
        }
        query += " WHERE id = "
                + debt.getId();
        
        System.out.println(query);
        Statement statement;
        statement = connection.createStatement();

        statement.execute(query);
    }

    public static void modifyClient(Client client)
            throws ParseException,
            ClassNotFoundException,
            SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "UPDATE clients "
                + "SET name = '"
                + client.getName()
                + "', nick = '"
                + client.getNick()
                + "', cpnumber = '"
                + client.getCPNumber()
                + "', area = '"
                + client.getArea()
                + "'"
                + " WHERE id = "
                + client.getId();

        Statement statement;
        statement = connection.createStatement();

        statement.execute(query);
    }

    public static void setDBPreviousAmount() throws ClassNotFoundException, SQLException {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        Date todayDate = new Date();
        
        Connection connection;
        connection = DataDBConnection.getConnection();

        String query;
        query = "UPDATE debts "
                + "SET previous_deposit_amount = deposit "
                + "WHERE last_deposit_date != '"
                + formater.format(todayDate).trim() + "'";
        
        Statement statement;
        statement = connection.createStatement();

        statement.execute(query);
    }
    
    public static void disableClient(Client client) throws ClassNotFoundException, SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        int id = client.getId();
        String name = client.getName();
        String nick = client.getNick();
        String area = client.getArea();
        String CPNumber = client.getCPNumber();
        int createdBy = client.getCreatorId();

        String query;
        query = "INSERT INTO disabled_clients "
                + "(id, name, nick, area, cpnumber, created_by) "
                + "VALUES ("
                + id + ", '"
                + name + "', '"
                + nick + "', '"
                + area + "', '"
                + CPNumber + "', "
                + createdBy + ")";
        
        Statement statement;
        statement = connection.createStatement();

        statement.execute(query);
        
        query = "DELETE FROM clients WHERE id = " + client.getId();
        
        statement = connection.createStatement();

        statement.execute(query);
    }
}
