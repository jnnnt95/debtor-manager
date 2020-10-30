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
    private final String creationDate;
    private String paidDate;
    private boolean paid;
    private String createdBy;
    private int creatorId;
    private String lastDepositDate;
    
    public Debt(int id,
            int clientId,
            int balance,
            int deposit,
            String creationDate,
            String paidDate,
            String createdBy,
            int creatorId,
            String lastDepositdate) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
        this.deposit = deposit;
        this.totalDebt = 
                balance - deposit;
        paid = totalDebt <= 0;
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.createdBy = createdBy;
        this.creatorId = creatorId;
        this.lastDepositDate = lastDepositdate;
    }
    
    //Getters

    public String getCreatedBy() {
        return createdBy;
    }

    public int getCreatorId() {
        return creatorId;
    }
        
    public int getClientId() {
        return clientId;
    }

    public int getBalance() {
        return balance;
    }

    public String getCreationDate() {
        return creationDate;
    }
    
    public String getPaidDate() {
        return paidDate;
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
    
    public void updateDebt(int deposit, String updateDate) {
        this.deposit += deposit;
        this.totalDebt =
                this.balance - this.deposit;
        this.paid =
                this.totalDebt <= 0;
        
        lastDepositDate = updateDate.trim();
        
        if(this.paid) {
            this.paidDate = updateDate.trim();
        }
        else {
            this.paidDate = null;
        }
    }

    public String getLastDepositDate() {
        return lastDepositDate;
    }
    
    public int getId() {
        return id;
    }
}
