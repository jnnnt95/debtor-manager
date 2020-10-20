package model;

/**
 *
 * @author admin
 */
public class Debt {
    private final int id;
    private final int clientId;
        private final int balance;
    private int deposit;
    private int totalDebt;
    private final String date;
    private boolean paid;
    
    public Debt(int id,
            int clientId,
            int balance,
            int deposit,
            String date) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
        this.deposit = deposit;
        this.totalDebt = 
                balance - deposit;
        paid = totalDebt <= 0;
        this.date = date;
    }
    
    //Getters
    
    public int getClientId() {
        return clientId;
    }

    public int getBalance() {
        return balance;
    }

    public String getDate() {
        return date;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getTotalDebt() {
        return totalDebt;
    }
    
    //Setters

    public void updateDebt(int deposit) {
        this.deposit += deposit;
        this.totalDebt =
                this.balance - this.deposit;
        this.paid =
                this.totalDebt <= 0;
    }
    
    public int getId() {
        return id;
    }
}
