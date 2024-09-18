package model.IO;

import control.MainController;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
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
import model.Client;
import model.DataDBConnection;
import model.Debt;
import utils.Action;

/**
 *
 * @author admin
 */
public class Writer {

    private Writer() {}

    public static void recordPayment(int clientId, int amount, String date) {

        String query = "INSERT INTO deposits (id_client, received_by, amount, date) VALUES (?, ?, ?, ?)";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, clientId);
                stmt.setInt(2, MainController.getUser().getId());
                stmt.setInt(3, amount);
                stmt.setString(4, date);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.UpdateBuilder dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();
    }

    public static void exportToExcel(Object[][] writableData)
            throws WriteException,
            IOException {
        String path;
        path = "Data\\printable data.xls";

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

        String query = "INSERT INTO clients (id, name, nick, cpnumber, area, created_by) VALUES (?, ?, ?, ?, ?, ?)";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, client.getId());
                stmt.setString(2, client.getName());
                stmt.setString(3, client.getNick());
                stmt.setString(4, client.getCPNumber());
                stmt.setString(5, client.getArea());
                stmt.setInt(6, client.getCreatorId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.UpdateBuilder dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        if (!client.getDebts().isEmpty()) {
            addDebt(client.getDebts().
                    get(0),
                    client);
        }
    }

    public static void addDebt(Debt debt, Client client) {

        String query = "INSERT INTO debts "
                + "(id, id_client, balance, deposit, date, created_by) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, debt.getId());
                stmt.setInt(2, client.getId());
                stmt.setInt(3, debt.getBalance());
                stmt.setInt(4, debt.getDeposit());
                stmt.setString(5, debt.getCreationDate());
                stmt.setInt(6, debt.getCreatorId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.UpdateBuilder dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();
    }

    public static void modifyDebt(Debt debt) {

        String query = "UPDATE debts SET deposit = ?";
        if (debt.isPaid()) {
            query += ", paid_date = ?";
        }
        query += " WHERE id = ?";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, debt.getDeposit());
                if(debt.isPaid()) {
                    stmt.setString(2, debt.getPaidDate());
                    stmt.setInt(3, debt.getId());
                } else {
                    stmt.setInt(2, debt.getId());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.UpdateBuilder dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();
    }

    public static void modifyClient(Client client) {

        String query = "UPDATE clients SET name = ?, nick = ?, cpnumber = ?, area = ?  WHERE id = ?";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {

                stmt.setString(1, client.getName());
                stmt.setString(2, client.getNick());
                stmt.setString(3, client.getCPNumber());
                stmt.setString(4, client.getArea());
                stmt.setInt(5, client.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.UpdateBuilder dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();
    }

    public static void disableClient(Client client) {

        int id = client.getId();
        String name = client.getName();
        String nick = client.getNick();
        String area = client.getArea();
        String CPNumber = client.getCPNumber();
        int createdBy = client.getCreatorId();

        String query = "INSERT INTO disabled_clients (id, name, nick, area, cpnumber, created_by) VALUES (?, ?, ?, ?, ?, ?)";

        Action preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, nick);
                stmt.setString(4, area);
                stmt.setString(5, CPNumber);
                stmt.setInt(6, createdBy);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        DBIO.UpdateBuilder dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();

        query = "DELETE FROM clients WHERE id = ?";

        preparationAction = o -> {
            PreparedStatement stmt = (PreparedStatement) o;
            try {
                stmt.setInt(1, client.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        dbop = new DBIO.UpdateBuilder();
        dbop
                .withPrepareAction(preparationAction)
                .withQuery(query)
                .setDone();
        dbop.run();
    }
}