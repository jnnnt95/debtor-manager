package model;

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

/**
 *
 * @author admin
 */
public class Writer {
    public static void exportToExcel(Object[][] writableData) throws WriteException, IOException {
        String path;
        path = "C:\\Debtor Manager\\printable data.xls";
        
        WorkbookSettings configuration;
        configuration = new WorkbookSettings();
        configuration.setEncoding("ISO-8859-1");
        
        WritableWorkbook workbook;
        workbook = Workbook.createWorkbook(new File(path), configuration);
        
        WritableSheet sheet;
        sheet = workbook.createSheet("Clientes", 0);
        
        WritableFont font;
        font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        
        WritableCellFormat cellFormater;
        cellFormater = new WritableCellFormat(font);
        
        sheet.addCell(new Label(0, 0, "Apodo", cellFormater));
        sheet.addCell(new Label(1, 0, "Nombre", cellFormater));
        sheet.addCell(new Label(2, 0, "Teléfono", cellFormater));
        sheet.addCell(new Label(3, 0, "Área", cellFormater));
        sheet.addCell(new Label(4, 0, "Saldo por pagar", cellFormater));
        sheet.addCell(new Label(5, 0, "Deudor Moroso", cellFormater));
        
        font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        cellFormater = new WritableCellFormat(font);
        
        for(int i = 0; i < writableData.length; i++) {
            
            sheet.addCell(new Label(0, i + 1, (String) writableData[i][0], cellFormater));
            sheet.addCell(new Label(1, i + 1, (String) writableData[i][1], cellFormater));
            sheet.addCell(new Label(2, i + 1, (String) writableData[i][2], cellFormater));
            sheet.addCell(new Label(3, i + 1, (String) writableData[i][3], cellFormater));
            sheet.addCell(new Number(4, i + 1, Double.parseDouble((String) writableData[i][4]), cellFormater));
            
            
            if(((String) writableData[i][5]).equals("*")) {
                sheet.addCell(new Label(5, i + 1, "Sí", cellFormater));
            }
            else {
                sheet.addCell(new Label(5, i + 1, "No", cellFormater));
            }
        }
        
        //Set Column width
        int nickFilter = 0;
        int nameFilter = 0;
        int areaFilter = 0;
        
        for(int i = 0; i < writableData.length; i++) {
            if(((String) writableData[i][0]).length() > nickFilter) {
                nickFilter = ((String) writableData[i][0]).length();
            }
            if(((String) writableData[i][1]).length() > nameFilter) {
                nameFilter = ((String) writableData[i][1]).length();
            }
            if(((String) writableData[i][3]).length() > areaFilter) {
                areaFilter = ((String) writableData[i][3]).length();
            }
        }
        
        nickFilter++;
        nameFilter++;
        areaFilter++;
        
        sheet.setColumnView(0, nickFilter);
        sheet.setColumnView(1, nameFilter);
        sheet.setColumnView(2, 11);
        sheet.setColumnView(3, areaFilter);
        sheet.setColumnView(4, "Saldo por pagar".length() +1);
        sheet.setColumnView(5, "Deudor Moroso".length() +1);
        
        workbook.write();
        workbook.close();
    }
    
    public static void addClient(Client client) throws ClassNotFoundException, SQLException{
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        String query;
        query =   "INSERT INTO clients "
                + "(id, name, nick, cpnumber, area) "
                + "VALUES (" + client.getId() + ", '" + client.getName() + "', '" + client.getNick() + "', '" + client.getCPNumber() + "', '" + client.getArea() + "')";
        
        Statement statement;
        statement = connection.createStatement();
        
        if(client.getDebts().size() > 0) {
            addDebt(client.getDebts().get(0), client);
        }
        
        statement.execute(query);
    }
    
    public static void addDebt(Debt debt, Client client) throws ClassNotFoundException, SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        String query;
        query =   "INSERT INTO debts "
                + "(id, id_client, balance, deposit, date) "
                + "VALUES (" + debt.getId() + ", " + client.getId() + ", " + debt.getBalance() + ", " + debt.getDeposit() + ", '" + debt.getDate() + "')";
        
        Statement statement;
        statement = connection.createStatement();
        
        statement.execute(query);
    }
    
    public static void modifyDebt(Debt debt) throws ClassNotFoundException, SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        String query;
        query =   "UPDATE debts "
                + "SET deposit = " + debt.getDeposit()
                + " WHERE id = " + debt.getId();
        
        Statement statement;
        statement = connection.createStatement();
        
        statement.execute(query);
    }
    
    public static void modifyClient(Client client) throws ParseException, ClassNotFoundException, SQLException {
        Connection connection;
        connection = DataDBConnection.getConnection();
        
        String query;
        query =   "UPDATE clients "
                + "SET name = '" + client.getName() + "', nick = '" + client.getNick() + "', cpnumber = '" + client.getCPNumber() + "', area = '" + client.getArea() + "'"
                + " WHERE id = " + client.getId();
        
        Statement statement;
        statement = connection.createStatement();
        
        statement.execute(query);
    }
}
