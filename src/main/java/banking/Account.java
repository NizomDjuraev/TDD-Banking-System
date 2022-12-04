package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String id;
    private double apr;
    double balance;
    String type;
    int monthsPassed = 0;
    DecimalFormat decimalFormat;
    List<String> allInputsEntered;

    public Account(String type, String id, double apr) {
        this.type = type;
        this.id = id;
        this.apr = apr;
        this.balance = 0;
        decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        allInputsEntered = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public double getApr() {
        return apr;
    }

    public double getBalance() {
        return balance;
    }

    public int getMonthsPassed() {
        return monthsPassed;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            balance = 0;
        } else {
            this.balance -= amount;
        }
    }

    private double accountBalanceUpdateAfterOneMonth(double runningBalance) {
        return this.balance += (((this.apr / 100) / 12) * runningBalance);
    }

    public double accountBalanceAfterPenaltyFee() {
        if (25 > this.balance) {
            return this.balance = 0;
        } else {
            return this.balance -= 25;
        }
    }

    public void passMonthsIntoAccount(int monthsPassedIntoAccount) {
        monthsPassed += monthsPassedIntoAccount;
        if (this.balance == 0) {
        }
        if (this.balance < 100) {
            updatedAccountBelowMinimumBalanceCalculation();
        }
        if (this.balance >= 100) {
            updatedAccountAboveMinimumBalanceCalculation();
        }
    }


    public void updatedAccountBelowMinimumBalanceCalculation() {
        if (this.type.equalsIgnoreCase("cd")) {
            int cdMonthCalculation;
            for (cdMonthCalculation = 4 * monthsPassed; cdMonthCalculation > 0; cdMonthCalculation--) {
                accountBalanceUpdateAfterOneMonth(accountBalanceAfterPenaltyFee());
            }
        }
        if (this.type.equalsIgnoreCase("checking") || this.type.equalsIgnoreCase("savings")) {
            int checkingAndSavingsCalculation;
            for (checkingAndSavingsCalculation = monthsPassed; checkingAndSavingsCalculation > 0; checkingAndSavingsCalculation--) {
                accountBalanceUpdateAfterOneMonth(accountBalanceAfterPenaltyFee());
            }
        }
    }

    public void updatedAccountAboveMinimumBalanceCalculation() {
        if (this.type.equalsIgnoreCase("cd")) {
            int cdMonthCalculation;
            for (cdMonthCalculation = 4 * monthsPassed; cdMonthCalculation > 0; cdMonthCalculation--) {
                accountBalanceUpdateAfterOneMonth(this.balance);
            }
        } else {
            int checkingAndSavingsCalculation;
            for (checkingAndSavingsCalculation = monthsPassed; checkingAndSavingsCalculation > 0; checkingAndSavingsCalculation--) {
                accountBalanceUpdateAfterOneMonth(this.balance);
            }
        }
    }


    public List<String> getOldInputs() {
        String accountParts = getType() + " " + getId() + " " + decimalFormat.format(getBalance()) + " " + decimalFormat.format(getApr());
        allInputsEntered.set(0, accountParts);
        return allInputsEntered;
    }
}
