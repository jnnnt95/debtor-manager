package control;

import model.Debt;
import model.Client;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.MenuView;
import model.Reader;

/**
 *
 * @author admin
 */
public class MenuController {
    private MenuView view;
    private QueryClientController sonQueryClient;
    private AddClientController sonAddClient;
    private LogInController parentLogIn;
    private String sessionKey;
    
    public MenuController(LogInController parentLogIn, String sessionKey) throws IOException, ParseException, ClassNotFoundException, SQLException {
        this.parentLogIn = parentLogIn;
        this.sessionKey = sessionKey;
        sonQueryClient = new QueryClientController(parentLogIn, sessionKey);
        sonAddClient = new AddClientController();
        initView();
    }
    
    private void initView() throws IOException {
        verifySession();
        view = new MenuView();
        view.exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );
        view.queryClientButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    queryClient();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.queryClientButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                try {
                    queryClient();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.addClientButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                try {
                    addClient();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.addClientButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    addClient();
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.businessButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    verifyAuthenticationForBusinessDataDisplay();
            }
        });
    }
    
    public void showBusiness(boolean authenticated) throws IOException, ParseException, ClassNotFoundException, SQLException {
        if(authenticated) {
            DecimalFormat formater;
            formater = new DecimalFormat("###,###.##");
            String business;
            business = "Total ganado: $";

            int totalProfit;
            totalProfit = 0;

            List<Client> clients;
            clients = Reader.getClients();

            for (Client client : clients) {
                for (Debt debt : client.getDebts()) {
                    totalProfit += debt.getDeposit();
                }
            }

            business += formater.format(totalProfit);

            int totalReceivable;
            totalReceivable = 0;

            for (Client client : clients) {
                totalReceivable += client.getTotalNotPaidBalance();
            }

            business += "\n";
            business += "Total por cobrar: $";
            business += formater.format(totalReceivable);

            int totalDefaultReceivable;
            totalDefaultReceivable = 0;

            for (Client client : clients) {
                if (client.isDefaulter()) {
                    totalDefaultReceivable += client.getDefaultAmount();
                }
            }

            business += "\n";
            business += "Total por cobrar de morosos: $";
            business += formater.format(totalDefaultReceivable);

            JOptionPane.showMessageDialog(null, business);
        }
        else {
            JOptionPane.showMessageDialog(null, "No se ha autenticado credenciales");
        }
    }
    
    private void queryClient() throws IOException, ParseException, ClassNotFoundException, SQLException {
        sonQueryClient.setViewData(this);
        tools.swapWindow(sonQueryClient.getView(), view);
    }
    
    private void addClient() throws IOException, ParseException, ClassNotFoundException, SQLException {
        sonAddClient.setViewData(this);
        tools.swapWindow(sonAddClient.getView(), view);
    }
    
    public JFrame getView() {
        return view;
    }
    
    private void verifySession() {
        parentLogIn.verifySession(sessionKey);
    }
    
    private void verifyAuthenticationForBusinessDataDisplay() {
        view.setVisible(false);
        parentLogIn.verifyAuthentication();
    }
}
