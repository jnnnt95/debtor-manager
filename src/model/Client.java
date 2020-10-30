package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

/**
 *
 * @author admin
 */
public class Client {
    private final int id;
    private String name;
    private String nick;
    private String cpNumber;
    private String area;
    private List<Debt> debts;
    private double mean;
    private double standardDeviation;
    private List<Integer> monthlyAmount;
    private int defaultAmount;
    private List<String> monthlyDates;
    private boolean defaulter;
    private final String createdBy;
    private final int creatorId;
    
    public Client(int id,
            String name,
            String nick,
            String cpNumber,
            String area,
            List<Debt> debts,
            String createdBy,
            int creatorId
            ) throws ParseException {
        this.id = id;
        this.name = name;
        this.nick = nick;
        if(cpNumber != null) {
           this.cpNumber = cpNumber; 
        }
        else {
            this.cpNumber = "";
        }
        this.area = area;
        this.debts = debts;
        this.createdBy = createdBy;
        this.creatorId = creatorId;
        setDefaultAmount();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public int getCreatorId() {
        return creatorId;
    }

    private int getNonPaidBalance() {
        int totalNotPaidBalance;
        totalNotPaidBalance = 0;
        for(Debt debt: debts) {
            if(!debt.isPaid()) {
                totalNotPaidBalance += (debt.getBalance() - debt.getDeposit());
            }
        }
        return totalNotPaidBalance;
    }

    public String getName() {
        return name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public void setCpNumber(String cpNumber) {
        this.cpNumber = cpNumber;
    }

    public String getNick() {
        return nick;
    }
    
    public String getCPNumber() {
        return cpNumber;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public int getId() {
        return id;
    }
    
    public int getTotalNotPaidBalance() {
        return getNonPaidBalance();
    }
    
    public String getArea() {
        return area;
    }
    
    public void sortDebts() throws ParseException {
        ArrayList<Date> dates;
        dates = new ArrayList<>();
        
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        for(Debt debt: debts) {
            dates.add(dateFormat.parse(debt.getCreationDate()));
        }
        
        Collections.sort(dates);
        
        for(int i = 0; i < dates.size(); i++) {
            for(int j = i; j < debts.size(); j++) {
                if(dates.get(i).equals(dateFormat.parse(debts.get(j).getCreationDate()))) {
                    Collections.swap(debts, i, j);
                }
            }
        }
    }

    private void setMean() {
        double[] values;
        values = new double[0];
        if (monthlyAmount.size() > 0) {
            values = new double[monthlyAmount.subList(0, monthlyAmount.size() - 1).size()];
            for (int i = 0; i < monthlyAmount.subList(0, monthlyAmount.size() - 1).size(); i++) {
                values[i]
                        = monthlyAmount.subList(0, monthlyAmount.size() - 1).get(i);
            }
        }
        mean = new Mean().evaluate(values);
    }

    private void setStandardDeviation() {
        double[] values;
        values = new double[0];
        if(monthlyAmount.size() > 0) {
            values = new double[monthlyAmount.subList(0, monthlyAmount.size() - 1).size()];

            for (int i = 0; i < monthlyAmount.subList(0, monthlyAmount.size() - 1).size(); i++) {
                values[i] = monthlyAmount.subList(0, monthlyAmount.size() - 1).get(i);
            }
        }
        
        standardDeviation = new StandardDeviation().evaluate(values);
    }

    private void setMonthlyDates() throws ParseException {
        monthlyDates = new ArrayList<>();

        if (debts.size() > 0) {
            
            SimpleDateFormat formater;
            formater = new SimpleDateFormat("MM/yyyy");

            Calendar calendar;
            calendar = Calendar.getInstance();

            calendar.setTime(formater.parse(debts.get(0).getCreationDate().substring(3)));

            while (!formater.format(calendar.getTime()).equals(formater.format(new Date()))) {
                monthlyDates.add(formater.format(calendar.getTime()));
                calendar.add(Calendar.MONTH, 1);
            }
            monthlyDates.add(formater.format(calendar.getTime()));
        }
    }
    
    public boolean isDefaulter() {
        return defaulter;
    }
    
    public int getDefaultAmount() {
        return defaultAmount;
    }
    
    private void setDefaultAmount() throws ParseException {
        defaultAmount = 0;
        
        SimpleDateFormat dayFormater;
        dayFormater = new SimpleDateFormat("dd/MM/yyyy");
        
        Date today;
        today = dayFormater.parse(dayFormater.format(new Date()));
        
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -30);
        
        Date aMonthAgo;
        aMonthAgo = calendar.getTime();
        
        for(int i = 0; i < debts.size(); i++) {
            if(dayFormater.parse(debts.get(i).getCreationDate()).
                    before(dayFormater.parse(dayFormater.format(aMonthAgo)))
                    &&
                !debts.get(i).isPaid()) {
                defaultAmount += debts.get(i).getTotalDebt();
            }
        }
        
        defaulter = defaultAmount > 0;
    }

    private void setMonthlyAmount() throws ParseException {
        
        monthlyAmount = new ArrayList<>();
        
        for(int i = 0; i < monthlyDates.size(); i++) {
            monthlyAmount.add(0);
        }
        
        //Date generator for comparison
        SimpleDateFormat monthFormater;
        monthFormater = new SimpleDateFormat("MM/yyyy");
        
        
        //Grouping by date
        for(int i = 0; i < debts.size(); i++) {
            
            for(int j = 0; j < monthlyDates.size(); j++) {
                int index;
                index = monthFormater.parse(
                            debts.get(i).getCreationDate().substring(3)
                        ).compareTo(
                                monthFormater.parse(monthlyDates.get(j))
                        );
                
                if (index == 0) {
                    monthlyAmount.set(
                            j,
                            monthlyAmount.get(j)
                            + debts.get(i).getBalance()
                    );
                }
            }
        }
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public List<Integer> getMonthlyAmount() {
        return monthlyAmount;
    }

    public List<String> getMonthlyDates() {
        return monthlyDates;
    }
    
    public void update() throws ParseException {
        setMonthlyDates();
        setMonthlyAmount();
        setMean();
        setStandardDeviation();
        setDefaultAmount();
    }
}
